package br.com.personalog.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import br.com.personalog.constant.ResponseHttpType;
import br.com.personalog.constant.ResponseMessage;
import br.com.personalog.dao.UserDao;
import br.com.personalog.dao.VerificationTokenDAO;
import br.com.personalog.dto.ResponseObject;
import br.com.personalog.dto.UserDTO;
import br.com.personalog.model.User;
import br.com.personalog.model.VerificationToken;
import br.com.personalog.service.UserService;
import br.com.personalog.util.exception.UserAlreadyExistException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import static br.com.personalog.constant.ResponseMessage.ERROR_MESSAGE;
import static br.com.personalog.constant.ResponseMessage.SUCCESS_MESSAGE;
import static br.com.personalog.util.misc.ServiceUtils.createResponse;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@NonNull
	private UserDao userDao;

	@NonNull
	private VerificationTokenDAO verificationTokenDAO;

	@NonNull
	private MessageSource messages;

//	@NonNull
//	private BCryptPasswordEncoder encoder;

	@Override
	public User getUser(String verificationToken) {
		return verificationTokenDAO.findByToken(verificationToken).getUser();
	}

	@Override
	public ResponseObject<User> registerNewUserAccount(UserDTO user)  {
		User newUser = createUser(user);
		try {
			validate(newUser);
			return createResponse(userDao.save(newUser), SUCCESS_MESSAGE, null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(newUser, ERROR_MESSAGE, null, e, ResponseHttpType.BAD_REQUEST);
		}
	}

	private User createUser(UserDTO dto) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = new User();
		user.setEmail(dto.getEmail());
		//TODO encryp password
		user.setPassword(encoder.encode(dto.getPassword()));
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setRoles(String.join(",", dto.getRoles()));
		user.setDtcreation(LocalDateTime.now());
		return user;
	}

	@Override
	public VerificationToken createVerificationToken(User user) {
		VerificationToken myToken = new VerificationToken(user, UUID.randomUUID().toString());
		return verificationTokenDAO.save(myToken);
	}

	@Override
	public void saveRegisteredUser(User user) {
		userDao.save(user);
	}

	@Override public VerificationToken getVerificationToken(String token) {
		return verificationTokenDAO.findByToken(token);
	}

	private void validate(User user) throws UserAlreadyExistException {
		if (userDao.isEmailExists(user.getEmail())) {
			throw new UserAlreadyExistException(messages.getMessage("validation.exception.account.email.exists", new String[] { user.getEmail() }, Locale.ENGLISH), null);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.findByEmail(email);
		//TODO implemento i18n
		Optional.ofNullable(user).orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + email));
		return createUserDetails(user, true, true, true);
	}

	private UserDetails createUserDetails(User user,  boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked) {
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(),
				user.getPassword(),
				user.isEnabled(),
				accountNonExpired,
				credentialsNonExpired,
				accountNonLocked,
				getAuthorities(user.getRolesList())
		);
	}

	private List<GrantedAuthority> getAuthorities (List<String> roles) {
		return AuthorityUtils.createAuthorityList(roles.toArray(String[]::new));
	}

	@Override
	public ResponseObject confirmRegistration(Locale locale, String token) {
		//TODO refactor the IFs
		Map<String, String> respMap = new HashMap<>();
		LocalDateTime now = LocalDateTime.now();
		VerificationToken verificationToken = this.getVerificationToken(token);
		User user = verificationToken.getUser();
		ResponseMessage responseMessage = ERROR_MESSAGE;
		if (verificationToken == null) {
			respMap.put("message", messages.getMessage("auth.message.invalid.token", null, locale));
		}else if ((verificationToken.getExpiryDate().compareTo(now)) <= 0) {
			respMap.put("message", messages.getMessage("auth.message.expired.token", null, locale));
			respMap.put("expired", "true");
			respMap.put("token", verificationToken.getToken());
		}else{
			user.setEnabled(true);
			this.saveRegisteredUser(user);
			respMap.put("message", messages.getMessage("auth.message.valid.token", null, locale));
			responseMessage = SUCCESS_MESSAGE;
		}
		return createResponse(respMap, responseMessage, "_token",null);
	}

	@Override
	public VerificationToken generateNewVerificationToken(String existingToken) {
		VerificationToken oldToken = verificationTokenDAO.findByToken(existingToken);
		if(Objects.isNull(oldToken)) return null;//TODO refactor?
		VerificationToken newToken = createVerificationToken(oldToken.getUser());
		verificationTokenDAO.delete(oldToken);
		return newToken;
	}

}

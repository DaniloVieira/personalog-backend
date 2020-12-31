package br.com.personalog.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import br.com.personalog.constant.ResponseHttpType;
import br.com.personalog.dao.UserDao;
import br.com.personalog.dto.ResponseObject;
import br.com.personalog.dto.UserDTO;
import br.com.personalog.model.User;
import br.com.personalog.service.UserService;
import br.com.personalog.util.exception.UserAlreadyExistException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import static br.com.personalog.constant.ResponseMessage.ERROR_MESSAGE;
import static br.com.personalog.constant.ResponseMessage.SUCCESS_MESSAGE;
import static br.com.personalog.service.impl.ServiceUtils.createResponse;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@NonNull
	private UserDao userDao;

//	@NonNull
//	private BCryptPasswordEncoder encoder;

	//@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public ResponseObject registerNewUserAccount(UserDTO user)  {
		try {
			validate(user);
			userDao.save(createUser(user));
			return createResponse(user, SUCCESS_MESSAGE, null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(user, ERROR_MESSAGE, e, ResponseHttpType.BAD_REQUEST);
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
		user.setDtSave(LocalDateTime.now());
		return user;
	}

	private void validate(UserDTO user) throws UserAlreadyExistException {
		if (userDao.isEmailExists(user.getEmail())) {
			throw new UserAlreadyExistException("There is an account with that email address: "+user.getEmail(), null);//TODO implement the internationalization
		}
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.findByEmail(email);
		//TODO implemento i18n
		Optional.ofNullable(user).orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + email));
		return createUserDetails(user, true, true, true, true);
	}

	private UserDetails createUserDetails(User user, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked) {
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(),
				user.getPassword(),
				enabled,
				accountNonExpired,
				credentialsNonExpired,
				accountNonLocked,
				getAuthorities(user.getRolesList())
		);
	}

	private List<GrantedAuthority> getAuthorities (List<String> roles) {
		return AuthorityUtils.createAuthorityList(roles.toArray(String[]::new));
	}
}

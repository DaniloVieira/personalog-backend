package br.com.personalog.service;

import java.util.Locale;

import br.com.personalog.dto.ResponseObject;
import br.com.personalog.dto.UserDTO;
import br.com.personalog.model.User;
import br.com.personalog.model.VerificationToken;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	ResponseObject<User> registerNewUserAccount(UserDTO user);

	User getUser(String verificationToken);

	void createVerificationToken(User user, String token);

	void saveRegisteredUser(User user);

	VerificationToken getVerificationToken(String token);

	String confirmRegistration(Locale locale, String token);
}

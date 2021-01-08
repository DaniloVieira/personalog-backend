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

	VerificationToken createVerificationToken(User user);

	void saveRegisteredUser(User user);

	VerificationToken getVerificationToken(String token);

	ResponseObject confirmRegistration(Locale locale, String token);

	VerificationToken generateNewVerificationToken(String existingToken);
}

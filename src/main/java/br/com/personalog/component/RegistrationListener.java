package br.com.personalog.component;

import java.util.Locale;
import java.util.UUID;

import br.com.personalog.model.User;
import br.com.personalog.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

	@NonNull
	private UserService service;

	@NonNull
	private MessageSource messages;

	@NonNull
	private JavaMailSender mailSender;

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		this.confirmRegistration(event);
	}

	private void confirmRegistration(OnRegistrationCompleteEvent event) {
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		service.createVerificationToken(user, token);

		String recipientAddress = user.getEmail();
		String subject = "Registration Confirmation";
		String confirmationUrl
			= event.getAppUrl() + "/user/reg-confirm?token=" + token;
		//String message = messages.getMessage("validation.success.user.registration.token", null, event.getLocale());
		String message = messages.getMessage("validation.success.user.registration.token", null, Locale.ENGLISH);

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject(subject);
		email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
		mailSender.send(email);
	}
}
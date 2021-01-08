package br.com.personalog.component;

import java.util.Locale;

import javax.validation.constraints.Email;

import br.com.personalog.model.VerificationToken;
import br.com.personalog.service.EmailService;
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
	private EmailService emailService;

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		this.confirmRegistration(event);
	}

	private void confirmRegistration(OnRegistrationCompleteEvent event) {
		VerificationToken token = service.createVerificationToken(event.getUser());
//		emailService.sendVerificationTokenEmail(token, event.getAppUrl(), event.getLocale());
		emailService.sendVerificationTokenEmail(token, event.getAppUrl(), Locale.ENGLISH);//TODO remove when i18n is properlu implemented
	}
}
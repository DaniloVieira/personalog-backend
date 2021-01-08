package br.com.personalog.service.impl;

import java.util.Locale;

import br.com.personalog.dto.EmialDTO;
import br.com.personalog.model.User;
import br.com.personalog.model.VerificationToken;
import br.com.personalog.service.EmailService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

	@NonNull
	private MessageSource messages;

	@NonNull
	private JavaMailSender mailSender;

	@Override
	public void sendEmail(EmialDTO emailDTO){
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(emailDTO.getTo());
		email.setSubject(emailDTO.getSubject());
		email.setText(emailDTO.getText());
		mailSender.send(email);
	}

	@Override
	public void sendVerificationTokenEmail (VerificationToken token, String appUrl, Locale locale){
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Registration Token");
		email.setText(createEmailBodyText(appUrl, token, locale));
		//		email.setFrom(env.getProperty("support.email"));
		email.setTo(token.getUser().getEmail());
		mailSender.send(email);
	}

	private String createEmailBodyText(String appUrl, VerificationToken token, Locale locale) {
		String confirmationUrl = appUrl + "/user/reg-confirm?token=" + token.getToken();
		String message = messages.getMessage("validation.success.user.registration.token", null, locale);
		return message + "\r\n" + "http://localhost:8080" + confirmationUrl;
	}



}

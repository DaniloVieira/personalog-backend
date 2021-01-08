package br.com.personalog.service;

import java.util.Locale;

import br.com.personalog.dto.EmialDTO;
import br.com.personalog.model.VerificationToken;

public interface EmailService  {
	void sendEmail (EmialDTO emailDTO);

	void sendVerificationTokenEmail (VerificationToken token, String appUrl, Locale locale);
}

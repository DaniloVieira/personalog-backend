package br.com.personalog.util.annotation.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.personalog.util.annotation.ValidEmail;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
	private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);
	@Override
	public void initialize(ValidEmail constraintAnnotation) {
	}
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context){
		return (validateEmail(email));
	}
	private boolean validateEmail(String email) {
		Matcher matcher = PATTERN.matcher(email);
		return matcher.matches();
	}
}

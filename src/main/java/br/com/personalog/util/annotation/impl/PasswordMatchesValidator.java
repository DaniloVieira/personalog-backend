package br.com.personalog.util.annotation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.personalog.model.User;
import br.com.personalog.util.annotation.PasswordMatches;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {

	}
	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context){
		User user = (User) obj;
		return user.getPassword().equals(user.getMatchingPassword());//TODO change to do in a generic way: crating another annotation to identify passwords files that must be compared
	}
}

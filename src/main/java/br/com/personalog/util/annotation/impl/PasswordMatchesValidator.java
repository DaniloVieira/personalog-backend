package br.com.personalog.util.annotation.impl;

import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.personalog.dto.UserDTO;
import br.com.personalog.util.annotation.PasswordMatches;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {

	}
	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context){
		UserDTO user = (UserDTO) obj;
		if(Objects.isNull(user.getEmail()) || Objects.isNull(user.getMatchingPassword())){
			return false;
		}
		return user.getPassword().equals(user.getMatchingPassword());//TODO change to do in a generic way: crating another annotation to identify passwords fields that must be compared
	}
}

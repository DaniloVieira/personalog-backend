package br.com.personalog.service;

import br.com.personalog.dto.ResponseObject;
import br.com.personalog.model.User;
import br.com.personalog.util.exception.UserAlreadyExistException;

public interface UserService {
	ResponseObject registerNewUserAccount(User user) ;
}

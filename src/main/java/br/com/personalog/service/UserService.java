package br.com.personalog.service;

import br.com.personalog.dto.ResponseObject;
import br.com.personalog.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	ResponseObject registerNewUserAccount(UserDTO user) ;
}

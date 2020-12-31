package br.com.personalog.rest.endpoint;

import javax.validation.Valid;

import br.com.personalog.dto.ResponseObject;
import br.com.personalog.dto.UserDTO;
import br.com.personalog.model.User;
import br.com.personalog.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static br.com.personalog.rest.endpoint.ResponseUtil.createResponse;

@RestController
@ResponseBody
//@CrossOrigin("${permited-origin}") // opção de CORS com arquivo de configuracao
@RequestMapping(value = "/user/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserEndpoint {

	@NonNull
	private UserService userService;

	@PostMapping("sign-up")
	public ResponseEntity<ResponseObject> registreNewAccount( @RequestBody @Valid UserDTO user ){
		System.out.println(user);
		return createResponse(userService.registerNewUserAccount(user));
	}
}

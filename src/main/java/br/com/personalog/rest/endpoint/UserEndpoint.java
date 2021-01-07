package br.com.personalog.rest.endpoint;

import java.time.LocalDateTime;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import br.com.personalog.component.OnRegistrationCompleteEvent;
import br.com.personalog.dto.ResponseObject;
import br.com.personalog.dto.SingleResponseObject;
import br.com.personalog.dto.UserDTO;
import br.com.personalog.model.User;
import br.com.personalog.model.VerificationToken;
import br.com.personalog.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import static br.com.personalog.util.misc.ResponseUtil.createResponse;

@RestController
@ResponseBody
//@CrossOrigin("${permited-origin}") // opção de CORS com arquivo de configuracao
@RequestMapping(value = "/user/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserEndpoint {

	@NonNull
	private UserService userService;

	@NonNull
	private ApplicationEventPublisher eventPublisher;

	@PostMapping("sign-up")
	public ResponseEntity<ResponseObject> registreNewAccount( @RequestBody @Valid UserDTO user, HttpServletRequest request ){
		System.out.println("[registreNewAccount] - begin");
		ResponseObject registered = userService.registerNewUserAccount(user);
		eventPublisher.publishEvent(createRegistrationEvent(request, (SingleResponseObject<User>) registered));
		System.out.println("[registreNewAccount] - end");
		return createResponse(registered);
	}

	private OnRegistrationCompleteEvent createRegistrationEvent(HttpServletRequest request, SingleResponseObject<User> registered) {
		return new OnRegistrationCompleteEvent(registered.getValue(), request.getLocale(), request.getContextPath());
	}

	@GetMapping("/reg-confirm")
	public String confirmRegistration (WebRequest request,  @RequestParam("token") String token) {
		//TODO adjust
		//Locale locale = request.getLocale();
		Locale locale = Locale.ENGLISH;

		return userService.confirmRegistration(locale, token);
	}


}

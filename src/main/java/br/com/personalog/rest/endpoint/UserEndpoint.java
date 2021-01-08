package br.com.personalog.rest.endpoint;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import br.com.personalog.component.OnRegistrationCompleteEvent;
import br.com.personalog.constant.ResponseMessage;
import br.com.personalog.dto.ResponseObject;
import br.com.personalog.dto.SingleResponseObject;
import br.com.personalog.dto.UserDTO;
import br.com.personalog.model.User;
import br.com.personalog.model.VerificationToken;
import br.com.personalog.service.EmailService;
import br.com.personalog.service.UserService;
import br.com.personalog.util.misc.ServiceUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

	@NonNull
	private JavaMailSender mailSender; //TODO move to a specific util class

	@NonNull
	private MessageSource messages;

	@NonNull
	private EmailService emailService;

	@PostMapping("sign-up")
	public ResponseEntity<ResponseObject> registreNewAccount( @RequestBody @Valid UserDTO user, HttpServletRequest request ){
		ResponseObject registered = userService.registerNewUserAccount(user);
		eventPublisher.publishEvent(createRegistrationEvent(request, (SingleResponseObject<User>) registered));
		return createResponse(registered);
	}

	@GetMapping("resendRegistrationToken")
	public ResponseEntity<ResponseObject> resendRegistrationToken(
		HttpServletRequest request, @RequestParam("token") String existingToken) {
		VerificationToken newToken = userService.generateNewVerificationToken(existingToken);
		//emailService.sendVerificationTokenEmail(newToken, request.getContextPath(), request.getLocale());
		emailService.sendVerificationTokenEmail(newToken, request.getContextPath(), Locale.ENGLISH);//TODO remove when i18n is properly implemented
		return createResponse(ServiceUtils.createResponse("Success", ResponseMessage.SUCCESS_MESSAGE,"", null));
	}

	private String createEmailBodyText(String appUrl, VerificationToken token, Locale locale) {
		String confirmationUrl = appUrl + "/user/reg-confirm?token=" + token.getToken();
		String message = messages.getMessage("validation.success.user.registration.token", null, locale);
		return message + "\r\n" + "http://localhost:8080" + confirmationUrl;
	}

	private OnRegistrationCompleteEvent createRegistrationEvent(HttpServletRequest request, SingleResponseObject<User> registered) {
		return new OnRegistrationCompleteEvent(registered.getValue(), request.getLocale(), request.getContextPath());
	}

	@GetMapping("/reg-confirm")
	public ResponseObject confirmRegistration (WebRequest request,  @RequestParam("token") String token) {
		//TODO adjust
		//Locale locale = request.getLocale();
		Locale locale = Locale.ENGLISH;

		return userService.confirmRegistration(locale, token);
	}


}

package br.com.personalog.util.exception;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
//@RestControllerAdvice
@RequiredArgsConstructor
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@NonNull
	private MessageSource messages;

	@ExceptionHandler({ UserNotFoundException.class })
	public ResponseEntity<Object> handleUserNotFound(RuntimeException ex, WebRequest request) {
		//Locale locale = request.getLocale();
		Locale locale = Locale.ENGLISH; // TODO remove after i18n is correct implemented
		Map<String, String> bodyOfResponse = new HashMap<>();
		logger.error("404 Status Code", ex);
//		GenericResponse bodyOfResponse = new GenericResponse(
//			messages.getMessage("message.userNotFound", null, request.getLocale()), "UserNotFound");

		bodyOfResponse.put("message", messages.getMessage("message.userNotFound", null, locale));
		return handleExceptionInternal(
			ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ MailAuthenticationException.class })
	public ResponseEntity<Object> handleMail(RuntimeException ex, WebRequest request) {
		//Locale locale = request.getLocale();
		Locale locale = Locale.ENGLISH; // TODO remove after i18n is correct implemented
		Map<String, String> bodyOfResponse = new HashMap<>();
		logger.error("500 Status Code", ex);
//		GenericResponse bodyOfResponse = new GenericResponse(
//			messages.getMessage(
//				"message.email.config.error", null, request.getLocale()), "MailError");

		bodyOfResponse.put("message", messages.getMessage("message.email.config.error", null, locale));
		return handleExceptionInternal(
			ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleInternal(RuntimeException ex, WebRequest request) {
		//Locale locale = request.getLocale();
		Locale locale = Locale.ENGLISH; // TODO remove after i18n is correct implemented
		Map<String, String> bodyOfResponse = new HashMap<>();
		logger.error("500 Status Code", ex);
//		GenericResponse bodyOfResponse = new GenericResponse(
//			messages.getMessage(
//				"message.error", null, request.getLocale()), "InternalError");

		bodyOfResponse.put("message", messages.getMessage("message.error", null, locale));
		return handleExceptionInternal(
			ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
}
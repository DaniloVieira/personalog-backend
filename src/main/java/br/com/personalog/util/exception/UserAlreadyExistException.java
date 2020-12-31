package br.com.personalog.util.exception;

public class UserAlreadyExistException extends Exception {
	public UserAlreadyExistException(String message, Throwable err) {
		super(message, err);
	}
}

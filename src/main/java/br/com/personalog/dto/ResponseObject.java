package br.com.personalog.dto;

import br.com.personalog.constant.ResponseHttpType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public abstract class ResponseObject<T> {

	private String message;
	private String cause;
	private Exception e;
	@JsonIgnore
	private ResponseHttpType responseHttpType;

	public ResponseObject<T> message(String message) {
		this.message = message;
		return this;
	}

	public ResponseObject<T> cause(String cause) {
		this.cause = cause;
		return this;
	}

	public ResponseObject<T> exception(Exception e) {
		this.e = e;
		return this;
	}

	public ResponseObject<T> responseHttpType(ResponseHttpType responseHttpType) {
		this.responseHttpType = responseHttpType;
		return this;
	}

	//	public String getType(){
	//		return value != null ? "_" + value.getClass().getSimpleName().toLowerCase() : null;
	//	}

}

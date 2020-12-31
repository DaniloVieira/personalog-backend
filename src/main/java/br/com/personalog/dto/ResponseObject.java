package br.com.personalog.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import br.com.personalog.constant.ResponseHttpType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
public abstract class ResponseObject<T> {

	private String message;
	private String error;
	private Exception e;
	@JsonIgnore
	private ResponseHttpType responseHttpType;

	public ResponseObject<T> message(String message) {
		this.message = message;
		return this;
	}

	public ResponseObject<T> cause(String cause) {
		this.error = cause;
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

	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime getTimestamp (){
		return LocalDateTime.now();
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public Integer getStatus(){
		return this.responseHttpType == null ? null : responseHttpType.getCode();
	}

	//	public String getType(){
	//		return value != null ? "_" + value.getClass().getSimpleName().toLowerCase() : null;
	//	}

}

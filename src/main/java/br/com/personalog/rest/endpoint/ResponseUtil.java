package br.com.personalog.rest.endpoint;

import java.util.Objects;

import br.com.personalog.dto.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

public class ResponseUtil {

	static ResponseEntity<ResponseObject> createResponse(ResponseObject response) {

		HttpStatus httpStatus = getStatus(response);

		return new ResponseEntity<ResponseObject>(response, httpStatus);
	}

	static HttpStatus getStatus(ResponseObject response) {
		if(Objects.nonNull(response.getResponseHttpType())){
			HttpStatus.valueOf(response.getResponseHttpType().getCode());
		}
		return HttpStatus.OK;
	}
}
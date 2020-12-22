package br.com.personalog.service.impl;

import java.util.Collection;
import java.util.Objects;

import br.com.personalog.constant.ResponseHttpType;
import br.com.personalog.constant.ResponseMessage;
import br.com.personalog.dto.PagedResponseObject;
import br.com.personalog.dto.ResponseObject;
import br.com.personalog.dto.SingleResponseObject;

public class ServiceUtils {
	public ServiceUtils() {
	}

	static ResponseObject createResponse(Object response, ResponseMessage responseMessage, Exception e, ResponseHttpType responseHttpType) {
		return new SingleResponseObject<Object>(response)
			.responseHttpType(responseHttpType)
			.message(responseMessage.getMsg())
//			.exception(e)
			.cause(getRootCauseMessage(e));
	}

	static ResponseObject createResponse(Object response, ResponseMessage responseMessage, Exception e) {
		return createResponse(response, responseMessage, e, null);
	}

	static ResponseObject createResponse(Collection response, ResponseMessage responseMessage, Integer pageSize, Integer totalSize, Integer currentPage, Exception e, ResponseHttpType responseHttpType) {
		return new PagedResponseObject<Collection>(response)
			.currentPage(currentPage)
			.pageSize(pageSize)
			.totalSize(totalSize)
			.responseHttpType(responseHttpType)
			.message(responseMessage.getMsg())
			//			.exception(e)
			.cause(getRootCauseMessage(e));
	}

	static ResponseObject createResponse(Collection response, ResponseMessage responseMessage, Integer pageSize, Integer totalSize, Integer currentPage, Exception e) {
		return createResponse(response, responseMessage, pageSize, totalSize, currentPage, e, null);
	}


	public static String getRootCauseMessage(Throwable throwable) {
		if(Objects.nonNull(throwable)){
			Throwable rootCause = throwable;
			while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
				rootCause = rootCause.getCause();
			}
			return rootCause.getMessage();
		}
		return null;
	}
}
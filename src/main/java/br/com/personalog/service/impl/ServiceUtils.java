package br.com.personalog.service.impl;

import java.util.Collection;
import java.util.Objects;

import br.com.personalog.constant.ResponseMessage;
import br.com.personalog.dto.PagedResponseObject;
import br.com.personalog.dto.ResponseObject;
import br.com.personalog.dto.SingleResponseObject;

public class ServiceUtils {
	public ServiceUtils() {
	}

	static ResponseObject createResponse(Object response, ResponseMessage responseMessage, Exception e) {
		return new SingleResponseObject<Object>(response)
			.message(responseMessage.getMsg())
			//			.exception(e)
			.cause(Objects.nonNull(e) ? e.getMessage() : null);
	}

	static ResponseObject createResponse(Collection response, ResponseMessage responseMessage, Integer pageSize, Integer totalSize, Integer currentPage, Exception e) {
		return new PagedResponseObject<Collection>(response)
			.currentPage(currentPage)
			.pageSize(pageSize)
			.totalSize(totalSize)
			.message(responseMessage.getMsg())
			//			.exception(e)
			.cause(Objects.nonNull(e) ? e.getMessage() : null);
	}
}
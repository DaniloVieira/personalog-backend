package br.com.personalog.service.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import br.com.personalog.constant.ResponseMessage;
import br.com.personalog.dao.EntrylogDao;
import br.com.personalog.dto.PagedResponseObject;
import br.com.personalog.dto.ResponseObject;
import br.com.personalog.dto.SingleResponseObject;
import br.com.personalog.model.Entrylog;
import br.com.personalog.service.EntryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntryServiceImpl implements EntryService {

	@NonNull
	private EntrylogDao entrylogDao;

	@Override
	public ResponseObject saveEntrylog(Entrylog entrylog) {
		try {
			return createResponse(entrylogDao.saveEntry(entrylog), ResponseMessage.SUCCESS_MESSAGE, null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(entrylog, ResponseMessage.ERROR_MESSAGE, e);
		}
	}

	@Override
	public ResponseObject listAll() {
		try {
			return createResponse(Collections.singleton(entrylogDao.findAll()), ResponseMessage.SUCCESS_MESSAGE, 10, 999, 2, null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(null, ResponseMessage.ERROR_MESSAGE, e);
		}
	}

	@Override
	public ResponseObject listByFilters(Integer moodId, String description, LocalDateTime initialDateTime, LocalDateTime finalDateTime, Integer page, Integer pageSize) {
		try {
			Integer totalSize = entrylogDao.totalRecords(moodId, description, initialDateTime, finalDateTime);
			return createResponse(Collections.singleton(entrylogDao.obtainsPagedEntrylog(description, initialDateTime, finalDateTime, moodId, page, pageSize)), ResponseMessage.SUCCESS_MESSAGE, pageSize, totalSize, page, null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(null, ResponseMessage.ERROR_MESSAGE, e);
		}
	}

	@Override
	public ResponseObject deleteEntrylog(Integer id) {
		try {
			return createResponse(entrylogDao.deleteEntry(id), ResponseMessage.SUCCESS_MESSAGE, null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(new Entrylog(id, null, null, null, null), ResponseMessage.ERROR_MESSAGE, e);
		}
	}

	private ResponseObject createResponse(Object response, ResponseMessage responseMessage, Exception e) {
		return new SingleResponseObject<>(response)
			.message(responseMessage.getMsg())
			//			.exception(e)
			.cause(Objects.nonNull(e) ? e.getMessage() : null);
	}

	private ResponseObject createResponse(Collection response, ResponseMessage responseMessage, Integer pageSize, Integer totalSize, Integer currentPage, Exception e) {
		return new PagedResponseObject<>(response)
			.currentPage(currentPage)
			.pageSize(pageSize)
			.totalSize(totalSize)
			.message(responseMessage.getMsg())
			//			.exception(e)
			.cause(Objects.nonNull(e) ? e.getMessage() : null);
	}
}

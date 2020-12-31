package br.com.personalog.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import br.com.personalog.constant.ResponseMessage;
import br.com.personalog.dao.EntrylogDao;
import br.com.personalog.dto.ResponseObject;
import br.com.personalog.model.Entrylog;
import br.com.personalog.service.EntryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static br.com.personalog.util.misc.ServiceUtils.createResponse;

@Service
@RequiredArgsConstructor
public class EntryServiceImpl implements EntryService {

	@NonNull
	private EntrylogDao entrylogDao;

	@Override
	public ResponseObject<Entrylog> saveEntrylog(Entrylog entrylog) {
		try {
			return createResponse(entrylogDao.saveEntry(entrylog), ResponseMessage.SUCCESS_MESSAGE, null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(entrylog, ResponseMessage.ERROR_MESSAGE, e);
		}
	}

	@Override
	public ResponseObject<List<Entrylog>> listAll() {
		try {
			return createResponse(Collections.singleton(entrylogDao.findAll()), ResponseMessage.SUCCESS_MESSAGE, 10, 999, 2, null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(null, ResponseMessage.ERROR_MESSAGE, e);
		}
	}

	@Override
	public ResponseObject<List<Entrylog>> listByFilters(Integer moodId, String description, LocalDateTime initialDateTime, LocalDateTime finalDateTime, Integer page, Integer pageSize) {
		try {
			Integer totalSize = entrylogDao.totalRecords(moodId, description, initialDateTime, finalDateTime);
			return createResponse(Collections.singleton(entrylogDao.obtainsPagedEntrylog(description, initialDateTime, finalDateTime, moodId, page, pageSize)), ResponseMessage.SUCCESS_MESSAGE, pageSize, totalSize, page, null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(null, ResponseMessage.ERROR_MESSAGE, e);
		}
	}

	@Override
	public ResponseObject<Entrylog> deleteEntrylog(long id) {
		try {
			return createResponse(entrylogDao.deleteEntry(id), ResponseMessage.SUCCESS_MESSAGE, null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(new Entrylog(id, null, null, null, null), ResponseMessage.ERROR_MESSAGE, e);
		}
	}

//	private ResponseObject createResponse(Object response, ResponseMessage responseMessage, Exception e) {
//		return serviceUtils.createResponse(response, responseMessage, e);
//	}
//
//	private ResponseObject createResponse(Collection response, ResponseMessage responseMessage, Integer pageSize, Integer totalSize, Integer currentPage, Exception e) {
//		return serviceUtils.createResponse(response, responseMessage, pageSize, totalSize, currentPage, e);
//	}
}

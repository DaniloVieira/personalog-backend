package br.com.personalog.service.impl;

import java.util.Collections;
import java.util.List;

import br.com.personalog.constant.ResponseMessage;
import br.com.personalog.dao.MoodDao;
import br.com.personalog.dto.ResponseObject;
import br.com.personalog.model.Entrylog;
import br.com.personalog.model.Mood;
import br.com.personalog.service.MoodService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static br.com.personalog.util.misc.ServiceUtils.createResponse;

@Service
@RequiredArgsConstructor
public class MoodServiceImpl implements MoodService {

	@NonNull
	private MoodDao moodDao;

	@Override public ResponseObject<Mood> save(Mood mood) {
		try {
			return createResponse(moodDao.save(mood), ResponseMessage.SUCCESS_MESSAGE, null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(mood, ResponseMessage.ERROR_MESSAGE, e);
		}
	}

	@Override
	public ResponseObject<List<Mood>> listAll() {
		try {
			return createResponse(Collections.singleton(moodDao.findAll()), ResponseMessage.SUCCESS_MESSAGE, null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(null, ResponseMessage.ERROR_MESSAGE, e);
		}
	}

	@Override public ResponseObject deleteMood(long id) {
		try {
			return createResponse(moodDao.deleteMood(id), ResponseMessage.SUCCESS_MESSAGE, null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(new Entrylog(id, null, null, null, null), ResponseMessage.ERROR_MESSAGE, e);
		}
	}

}

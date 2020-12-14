package br.com.personalog.service;

import java.time.LocalDateTime;

import br.com.personalog.dto.ResponseObject;
import br.com.personalog.model.Entrylog;

public interface EntryService {

	ResponseObject saveEntrylog(Entrylog entrylog);

	ResponseObject listByFilters(Integer moodId, String description, LocalDateTime initialDateTime, LocalDateTime finalDateTime);

	ResponseObject deleteEntrylog(Integer id);

	ResponseObject listAll();

}

package br.com.personalog.service;

import java.time.LocalDateTime;
import java.util.List;

import br.com.personalog.dto.ResponseObject;
import br.com.personalog.model.Entrylog;

public interface EntryService {

	ResponseObject<Entrylog> saveEntrylog(Entrylog entrylog);

	ResponseObject<List<Entrylog>> listByFilters(Integer moodId, String description, LocalDateTime initialDateTime, LocalDateTime finalDateTime, Integer page, Integer pageSize);

	ResponseObject<Entrylog> deleteEntrylog(long id);

	ResponseObject<List<Entrylog>> listAll();

}

package br.com.personalog.dao.query;

import java.time.LocalDateTime;
import java.util.List;

import br.com.personalog.model.Entrylog;

public interface EntrylogDaoQuery {

	Entrylog saveEntry(Entrylog entrylog);

	Entrylog deleteEntry(Integer id);

	Entrylog findEntry(Integer id);

	List<Entrylog> obtainsPagedEntrylog(String description, LocalDateTime initialDateTime, LocalDateTime finalDateTime, Integer moodId, Integer page, Integer pageSize);
}

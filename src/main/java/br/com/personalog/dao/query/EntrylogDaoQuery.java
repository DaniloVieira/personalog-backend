package br.com.personalog.dao.query;

import java.time.LocalDateTime;
import java.util.List;

import br.com.personalog.model.Entrylog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EntrylogDaoQuery {

	Entrylog saveEntry(Entrylog entrylog );

	Entrylog deleteEntry(Integer id);

	Entrylog findEntry(Integer id);


}

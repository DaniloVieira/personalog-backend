package br.com.personalog.dao;

import java.time.LocalDateTime;
import java.util.List;

import br.com.personalog.dao.query.EntrylogDaoQuery;
import br.com.personalog.model.Entrylog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EntrylogDao extends JpaRepository<Entrylog, Integer>, EntrylogDaoQuery {

	@Query(value = "SELECT e FROM Entrylog e "
		+ "WHERE (e.mood.id = :moodId OR :moodId IS NULL)"
		+ "AND (e.description LIKE '%'||:description||'%' OR :description IS NULL)"
		+ "AND (e.dateTime >= :initialDateTime OR :initialDateTime IS NULL)"
		+ "AND (e.dateTime <= :finalDateTime OR :finalDateTime IS NULL)")
	List<Entrylog> findEntrylog(@Param("moodId") Integer moodId,
		@Param("description") String description,
		@Param("initialDateTime") LocalDateTime initialDateTime,
		@Param("finalDateTime") LocalDateTime finalDateTime);


}

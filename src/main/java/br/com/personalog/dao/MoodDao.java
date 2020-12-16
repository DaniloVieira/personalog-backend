package br.com.personalog.dao;

import br.com.personalog.dao.query.MoodDaoQuery;
import br.com.personalog.model.Mood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoodDao extends JpaRepository<Mood, Integer>, MoodDaoQuery  {
}

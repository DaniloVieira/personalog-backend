package br.com.personalog.dao.impl;

import javax.persistence.EntityManager;

import br.com.personalog.dao.query.MoodDaoQuery;
import br.com.personalog.model.Mood;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class MoodDaoImpl implements MoodDaoQuery {

	@NonNull
	private EntityManager entityManager;

	@Override
	public Mood findMood(long id) {
		return entityManager.find(Mood.class, id);
	}

	@Override
	public Mood deleteMood(long id) {
		Mood removed = findMood(id);
		entityManager.remove(removed);
		return removed;
	}
}

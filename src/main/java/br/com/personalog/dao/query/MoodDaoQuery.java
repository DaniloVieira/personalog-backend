package br.com.personalog.dao.query;

import br.com.personalog.model.Mood;

public interface MoodDaoQuery {
	Mood findMood(long id);

	Mood deleteMood(long id);
}

package br.com.personalog.dao.query;

import br.com.personalog.model.Mood;

public interface MoodDaoQuery {
	Mood findMood(Integer id);

	Mood deleteMood(Integer id);
}

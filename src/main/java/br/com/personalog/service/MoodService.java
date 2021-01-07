package br.com.personalog.service;

import java.util.List;

import br.com.personalog.dto.ResponseObject;
import br.com.personalog.model.Mood;

public interface MoodService {
	ResponseObject<Mood> save(Mood mood);

	ResponseObject<List<Mood>> listAll();

	ResponseObject<Mood> deleteMood(long id);
}

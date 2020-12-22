package br.com.personalog.service;

import br.com.personalog.dto.ResponseObject;
import br.com.personalog.model.Mood;

public interface MoodService {
	ResponseObject save(Mood mood);

	ResponseObject listAll();

	ResponseObject deleteMood(Integer id);
}

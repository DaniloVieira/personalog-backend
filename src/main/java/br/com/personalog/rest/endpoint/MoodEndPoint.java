package br.com.personalog.rest.endpoint;

import br.com.personalog.dto.ResponseObject;
import br.com.personalog.model.Entrylog;
import br.com.personalog.model.Mood;
import br.com.personalog.service.MoodService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static br.com.personalog.rest.endpoint.ResponseUtil.createResponse;

@RestController
@ResponseBody
//@CrossOrigin("${permited-origin}") // opção de CORS com arquivo de configuracao
@RequestMapping(value = "/mood/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MoodEndPoint {

	@NonNull
	private MoodService moodService;

	@PostMapping("save")
	public ResponseEntity<ResponseObject> saveMood(@RequestBody Mood mood) {
		return createResponse(moodService.save(mood));
	}

	@PutMapping("save")
	public ResponseEntity<ResponseObject> updateMood(@RequestBody Mood mood) {
		return createResponse(moodService.save(mood));
	}

	@GetMapping("listall")
	public ResponseEntity<ResponseObject> listAll() {
		return createResponse(moodService.listAll());
	}

	@DeleteMapping("{id}")
	public ResponseEntity<ResponseObject> deleteEntry(@PathVariable Integer id) {
		return createResponse(moodService.deleteMood(id));
	}

}

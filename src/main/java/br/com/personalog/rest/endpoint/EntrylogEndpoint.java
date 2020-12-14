package br.com.personalog.rest.endpoint;

import java.time.LocalDateTime;

import br.com.personalog.dto.ResponseObject;
import br.com.personalog.dto.SingleResponseObject;
import br.com.personalog.model.Entrylog;
import br.com.personalog.service.EntryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
//@CrossOrigin("${permited-origin}") // opção de CORS com arquivo de configuracao
@RequestMapping(value =  "/entry-log/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EntrylogEndpoint {

	@NonNull
	private EntryService entryservice;

	@PostMapping("save")
	public ResponseEntity<ResponseObject> saveEntry (@RequestBody Entrylog entryLog){
		return createResponse(entryservice.saveEntrylog(entryLog));
	}

	@PutMapping("save")
	public ResponseEntity<ResponseObject> updateEntry (@RequestBody Entrylog entryLog){
		return createResponse(entryservice.saveEntrylog(entryLog));
	}

	@GetMapping("listall")
	public ResponseEntity<ResponseObject> listAll(){
		return createResponse(entryservice.listAll());
	}

	@GetMapping("list")
	public ResponseEntity<ResponseObject> list(
		@RequestParam(name = "mood",      required = false) Integer moodId,
		@RequestParam(name = "desc",      required = false) String descrition,
		@RequestParam(name = "iniDtTime", required = false)
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
		LocalDateTime initialDateTime,
		@RequestParam(name = "fnlDtTime", required = false)
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
		LocalDateTime finalDateTime){
		return createResponse(entryservice.listByFilters(moodId, descrition, initialDateTime, finalDateTime));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<ResponseObject> deleteEntry(@PathVariable Integer id){
		return createResponse(entryservice.deleteEntrylog(id));
	}

	private ResponseEntity<ResponseObject> createResponse (ResponseObject response){

		HttpStatus httpStatus = getStatus(response);

		return new ResponseEntity<>(response, httpStatus);
	}

	private HttpStatus getStatus(ResponseObject response) {
		return HttpStatus.OK;
	}

}

package br.com.personalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("br.com.personalog.model")
@SpringBootApplication
public class PersonalogBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalogBackendApplication.class, args);
	}

}

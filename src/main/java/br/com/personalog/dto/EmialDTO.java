package br.com.personalog.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmialDTO {

	private String to;
	private String subject;
	private String text;
}

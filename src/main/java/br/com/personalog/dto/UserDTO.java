package br.com.personalog.dto;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.personalog.model.User;
import br.com.personalog.util.annotation.PasswordMatches;
import br.com.personalog.util.annotation.ValidEmail;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@PasswordMatches
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"password", "matchingPassword"}, allowSetters = true)
public class UserDTO {


	@NotNull
	@NotEmpty
	//@Size(min = 1, message = "{Size.userDto.firstName}")
	private String firstName;

	@NotNull
	@NotEmpty
	//@Size(min = 1, message = "{Size.userDto.lastName}")
	private String lastName;

	@NotNull
	@NotEmpty
	private String password;

	@NotNull
	@NotEmpty
	private String matchingPassword;

	@NotNull
	@NotEmpty
	@ValidEmail
	private String email;

	@NotNull
	@NotEmpty
	private List<String> roles;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dtSave;


}

package br.com.personalog.dto;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.personalog.model.User;
import br.com.personalog.util.annotation.PasswordMatches;
import br.com.personalog.util.annotation.ValidEmail;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class UserDTO {


	@NotNull
	@NotEmpty
	//	@Size(min = 1, message = "{Size.userDto.firstName}")
	private String firstName;

	@NotNull
	@NotEmpty
	//	@Size(min = 1, message = "{Size.userDto.lastName}")
	private String lastName;

	@NotNull
	@NotEmpty
	private String password;

	@NotNull
	@NotEmpty
	@JsonIgnore
	private String matchingPassword;

	@NotNull
	@NotEmpty
	@ValidEmail
	private String email;

	@NotNull
	@NotEmpty
	private List<String> roles;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "dt_save")
	private LocalDateTime dtSave;


}

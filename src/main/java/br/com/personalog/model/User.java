package br.com.personalog.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.personalog.util.annotation.PasswordMatches;
import br.com.personalog.util.annotation.ValidEmail;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@PasswordMatches
@Table(name = "USER", schema = "PERSONALOG")
public class User {

	@Id
	@Column(name = "id_user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@NotEmpty
	@Column(name = "nm_user_first")
//	@Size(min = 1, message = "{Size.userDto.firstName}")
	private String firstName;

	@NotNull
	@NotEmpty
	@Column(name = "nm_user_last")
//	@Size(min = 1, message = "{Size.userDto.lastName}")
	private String lastName;

	@NotNull
	@NotEmpty
	private String password;

	@NotNull
	@NotEmpty
	@Transient
	private String matchingPassword;

	@NotNull
	@NotEmpty
	@ValidEmail
	private String email;

	@NotNull
	@NotEmpty
	@Column(name = "ds_roles")
	private String roles;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "dt_save")
	private LocalDateTime dtSave;

//	// TODO provisory
//	public void setRoles(List<String> roles){
//		//this.roles = roles.stream().collect(Collectors.joining(","));
//		this.roles = String.join(",", roles);
//	}
//
//	// TODO provisory
//	public List<String> getRoles (){
//		return Stream.of(roles.split(",", -1)).collect(Collectors.toList());
//	}

}

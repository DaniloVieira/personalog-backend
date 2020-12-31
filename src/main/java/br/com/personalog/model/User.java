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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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
	private String email;

	@NotNull
	@NotEmpty
	@Column(name = "ds_roles")
	private String roles;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "dt_save")
	private LocalDateTime dtSave;

	public List<String> getRolesList (){
		return Stream.of(roles.split(",", -1)).collect(Collectors.toList());
	}

}

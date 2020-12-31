package br.com.personalog.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "VERIFICATION_TOKEN", schema = "PERSONALOG")
public class VerificationToken {
	private static final int EXPIRATION = 60 * 24;

	public VerificationToken(User user, String token) {
		this.user = user;
		this.token = token;
		this.expiryDate = calculateExpiryDate(EXPIRATION);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_verif_token")

	private Long id;

	@Column(name = "ds_token")
	private String token;

	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "fk_user")
	private User user;


	@Column(name = "dt_creation")
	private LocalDateTime creationDate;

	@Column(name = "dt_expiration")
	@NonNull
	private LocalDateTime expiryDate;

	private static LocalDateTime calculateExpiryDate(int expiryTimeInMinutes) {
		return LocalDateTime.now().plusMinutes(expiryTimeInMinutes);
	}
}

package br.com.personalog.dao;

import br.com.personalog.model.User;
import br.com.personalog.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VerificationTokenDAO extends JpaRepository<VerificationToken, Long> {


	//@Query("SELECT t FROM VerificationToken t WHERE t.token = :token")
	VerificationToken findByToken(String token);

	//@Query("SELECT u FROM VerificationToken t JOIN t.user u WHERE t.token = :token")
	VerificationToken findByUser(User user);
}

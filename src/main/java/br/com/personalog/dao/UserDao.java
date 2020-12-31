package br.com.personalog.dao;

import br.com.personalog.dao.query.UserDaoQuery;
import br.com.personalog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Integer>, UserDaoQuery {

	@Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = :email")
	Boolean isEmailExists(@Param("email") String email);

	@Query(value = "SELECT u FROM User u WHERE u.email = :email")
	User findByEmail(@Param("email") String email);


}

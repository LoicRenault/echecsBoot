package echecsBoot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import echecsBoot.entity.Login;

public interface LoginRepository extends JpaRepository<Login, String> {
	@Query("select l from Login l left join fetch l.roles where l.login=:login")
	public Optional<Login> findByIdWithRoles(String login);
	
	public Optional<Login> findByLogin(String login);
}

package echecsBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import echecsBoot.entity.LoginRole;

public interface RoleRepository extends JpaRepository<LoginRole, Integer> {

}

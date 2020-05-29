package echecsBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import echecsBoot.entity.Partie;

public interface PartieRepository extends JpaRepository<Partie, Integer> {

	
}

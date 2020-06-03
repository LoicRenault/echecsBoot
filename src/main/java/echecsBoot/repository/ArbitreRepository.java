package echecsBoot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import echecsBoot.entity.Arbitre;
import echecsBoot.entity.Niveau;
import echecsBoot.entity.Tournoi;


public interface ArbitreRepository extends JpaRepository<Arbitre, Integer>{
	
	Optional<Arbitre> findByNom(String nom);
	
	Optional<Arbitre> findByPrenom(String prenom);
	
	List<Arbitre> findByNiveau(Niveau niveau);

	@Query("select a from Arbitre a where :tournoi= a.tournois")
	Optional<Arbitre> findByTournoi(@Param("tournoi")Tournoi tournoi);

	@Query("select count(t.id) from Tournoi t where :arbitre = t.arbitre")
	Integer countAllTournoiByArbitre(@Param("arbitre")Arbitre arbitre);
	
}

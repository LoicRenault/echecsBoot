package echecsBoot.repository;

//import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//import echecsBoot.entity.Arbitre;
import echecsBoot.entity.Cadence;
//import echecsBoot.entity.Club;
import echecsBoot.entity.Membre;
import echecsBoot.entity.Partie;

public interface PartieRepository extends JpaRepository<Partie, Integer> {

	@Query("select p from Partie p where p.joueurBlancs=:joueurBlancs")
	Optional<Partie> findByJoueurBlancs(@Param("joueurBlancs") Membre joueurBlancs);
	
	@Query("select p from Partie p where p.joueurNoirs=:joueurNoirs")
	Optional<Partie> findByJoueurNoirs(@Param("joueurNoirs") Membre joueurNoirs);
	
	//@Query("select p from Partie p where p.date=:date")
	//List<Partie> findByDate(@Param("datePartie") Date datePartie);

	@Query("select p from Partie p where p.cadence=:cadence")
	List<Partie> findByCadence(@Param("cadence") Cadence cadence);

	//@Query("select p from Partie p where p.organisateur=:club")
	//List<Partie> findByClub(@Param("organisateur") Club organisateur);
	
	//@Query("select p from Partie p where p.arbitre =:arbitre")
	//List<Partie> findByArbitre(@Param("arbitre") Arbitre arbitre);

	@Query("select count(membres) from Partie")
	List<Integer> countAllMembreByPartie();

	
	
}

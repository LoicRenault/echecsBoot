package echecsBoot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import echecsBoot.entity.Club;
import echecsBoot.entity.Membre;
import echecsBoot.entity.Tournoi;

public interface MembreRepository extends JpaRepository<Membre, Integer> {

	Optional<Membre> findByNomAndPrenom(String nom, String prenom);

	List<Membre> findByClub(Club club);

	List<Membre> findByElo(Integer elo);
	
	Optional<Membre> findByNom(String nom);

	@Query("select MAX(e.elo) from HistoriqueElo e where e.id.membre.id = :id")
	Integer findEloMaxById(@Param("id") Integer id);

	@Query("select p from Participation p where :tournoi= p.id.tournoi")
	List<Membre> findByTournoi(@Param("tournoi")Tournoi tournoi);

	@Query("select count(p.id) from Partie p where (p.joueurBlancs.id = :id) or (p.joueurNoirs.id = :id)")
	Integer countPartiesById(@Param("id") Integer id);

	@Query("select count(p.id) from Partie p where (p.joueurBlancs.id = :id and p.resultBlancs = 1) or (p.joueurNoirs.id =:id and p.resultNoirs = 1) ")
	Integer countVictoiresById(@Param("id")Integer id);

	@Query("select count(p.id) from Partie p where (p.joueurBlancs.id = :id and p.resultBlancs = 0.5) or (p.joueurNoirs.id =:id and p.resultNoirs = 0.5) ")
	Integer countNulsById(@Param("id")Integer id);

	@Query("select count(p.id) from Partie p where (p.joueurBlancs.id = :id and p.resultBlancs = 0) or (p.joueurNoirs.id =:id and p.resultNoirs = 0) ")
	Integer countDefaitesById(@Param("id")Integer id);
	
//	M�thode qui est compos�e de countVictoires et countPartiesById	(� int�grer directement dans le service?)
//	Double pourcentageVictoire(Integer id);
	
	@Query("select count(p.id.tournoi) from Participation p where :id = p.id.participant.id")
	Integer countTournoi(@Param("id")Integer id);
	
}

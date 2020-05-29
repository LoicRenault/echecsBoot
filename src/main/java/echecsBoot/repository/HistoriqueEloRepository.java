package echecsBoot.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import echecsBoot.entity.HistoriqueElo;
import echecsBoot.entity.HistoriqueEloKey;

public interface HistoriqueEloRepository extends JpaRepository<HistoriqueElo, HistoriqueEloKey> {

	@Query("select h from HistoriqueElo h where h.id.date = :date")
	List<HistoriqueElo> findByDate(@Param("date") Date date, Pageable pageable);

	@Query("select h from HistoriqueElo h where h.id.membre.id = :id")
	List<HistoriqueElo> findByMembre(@Param("id") Integer id, Pageable peageable);

	
}

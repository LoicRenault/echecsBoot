package echecsBoot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import echecsBoot.entity.ContenuPedagogique;
import echecsBoot.entity.Livre;
import echecsBoot.entity.Video;

public interface ContenuPedagogiqueRepository extends JpaRepository<ContenuPedagogique, Integer> {
	Optional<ContenuPedagogique> findByTitre(String titre);

	List<ContenuPedagogique> findByAuteur(String auteur, Pageable pageable);

	@Query("select l from Livre l")
	List<Livre> findAllLivre(Pageable pageable);

	@Query("select v from Video v")
	List<Video> findAllVideo(Pageable pageable);

	Integer countById(Integer id);

	@Query("select count(l.id) from Livre l")
	Integer countByLivre();

	@Query("select count(v.id) from Video v")
	Integer countByVideo();

	
}

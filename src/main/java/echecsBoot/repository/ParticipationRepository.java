package echecsBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import echecsBoot.entity.Participation;
import echecsBoot.entity.ParticipationKey;

public interface ParticipationRepository extends JpaRepository<Participation, ParticipationKey>{
	

}

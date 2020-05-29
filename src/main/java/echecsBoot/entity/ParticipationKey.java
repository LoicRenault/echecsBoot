package echecsBoot.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ParticipationKey implements Serializable {
	@ManyToOne
	@JoinColumn(name = "participant", foreignKey = @ForeignKey(name = "participation_participant_fk"))
	private Membre participant;
	@ManyToOne
	@JoinColumn(name = "tournoi", foreignKey = @ForeignKey(name = "participation_tournoi_fk"))
	private Tournoi tournoi;

	public ParticipationKey() {
	}

	public ParticipationKey(Membre participant, Tournoi tournoi) {
		this.participant = participant;
		this.tournoi = tournoi;
	}

	public Membre getParticipant() {
		return participant;
	}

	public void setParticipant(Membre participant) {
		this.participant = participant;
	}

	public Tournoi getTournoi() {
		return tournoi;
	}

	public void setTournoi(Tournoi tournoi) {
		this.tournoi = tournoi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((participant == null) ? 0 : participant.hashCode());
		result = prime * result + ((tournoi == null) ? 0 : tournoi.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParticipationKey other = (ParticipationKey) obj;
		if (participant == null) {
			if (other.participant != null)
				return false;
		} else if (!participant.equals(other.participant))
			return false;
		if (tournoi == null) {
			if (other.tournoi != null)
				return false;
		} else if (!tournoi.equals(other.tournoi))
			return false;
		return true;
	}

}

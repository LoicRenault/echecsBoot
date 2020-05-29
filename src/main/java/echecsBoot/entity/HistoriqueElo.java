package echecsBoot.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import echecsBoot.entity.view.Views;

@Entity
@Table(name = "historique_elo")
public class HistoriqueElo {
	@JsonView(Views.Common.class)
	@EmbeddedId
	private HistoriqueEloKey id;
	@JsonView(Views.Common.class)
	@Column(name = "elo", length = 5)
	private Integer elo;
	
	public HistoriqueElo() {
		super();
	}

	public HistoriqueEloKey getId() {
		return id;
	}

	public void setId(HistoriqueEloKey id) {
		this.id = id;
	}

	public Integer getElo() {
		return elo;
	}

	public void setElo(Integer elo) {
		this.elo = elo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		HistoriqueElo other = (HistoriqueElo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
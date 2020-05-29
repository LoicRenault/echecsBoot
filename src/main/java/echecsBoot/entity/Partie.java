package echecsBoot.entity;

import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
//import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonView;

import echecsBoot.entity.view.Views;

@Entity
@Table(name = "partie")
@SequenceGenerator(name = "seqPartie", sequenceName = "seq_partie", allocationSize = 1, initialValue = 100)

public class Partie {
	@JsonView(Views.Common.class)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPartie")
	@Column(name = "id_partie")
	private Integer id;
	@JsonView(Views.Common.class)
	@ManyToOne
	@JoinColumn(name = "joueur_blancs", foreignKey = @ForeignKey(name = "partie_joueur_blancs_fk"))
	private Membre joueurBlancs;
	@JsonView(Views.Common.class)
	@ManyToOne
	@JoinColumn(name = "joueur_noirs", foreignKey = @ForeignKey(name = "partie_joueur_noirs_fk"))
	private Membre joueurNoirs;
	@JsonView(Views.Common.class)
	@Column(name = "cadence_partie", length = 50)
	@Enumerated(EnumType.STRING)
	private Cadence cadence;
	@JsonView(Views.Common.class)
	@Column(name = "result_blancs_partie", length = 5, nullable = false)
	private Double resultBlancs;
	@JsonView(Views.Common.class)
	@Column(name = "result_noirs_partie", length = 5, nullable = false)
	private Double resultNoirs;
	@JsonView(Views.Common.class)
	@Column(name = "date_partie")
	@Temporal(TemporalType.DATE)
	private Date datePartie;
	@JsonView(Views.Common.class)
	@ManyToOne
	@JoinColumn(name = "partie_tournoi", foreignKey = @ForeignKey(name = "partie_partie_tournoi_fk"))
	private Tournoi partieTournoi;

	public Partie() {

	}

	// getters & setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Membre getJoueurBlancs() {
		return joueurBlancs;
	}

	public void setJoueurBlancs(Membre joueurBlancs) {
		this.joueurBlancs = joueurBlancs;
	}

	public Membre getJoueurNoirs() {
		return joueurNoirs;
	}

	public void setJoueurNoirs(Membre joueurNoirs) {
		this.joueurNoirs = joueurNoirs;
	}

	public Cadence getCadence() {
		return cadence;
	}

	public void setCadence(Cadence cadence) {
		this.cadence = cadence;
	}

	public Double getResultBlancs() {
		return resultBlancs;
	}

	public void setResultBlancs(Double resultBlancs) {
		this.resultBlancs = resultBlancs;
	}

	public Double getResultNoirs() {
		return resultNoirs;
	}

	public void setResultNoirs(Double resultNoirs) {
		this.resultNoirs = resultNoirs;
	}

	public Date getDatePartie() {
		return datePartie;
	}

	public void setDatePartie(Date datePartie) {
		this.datePartie = datePartie;
	}

	public Tournoi getPartieTournoi() {
		return partieTournoi;
	}

	public void setPartieTournoi(Tournoi partieTournoi) {
		this.partieTournoi = partieTournoi;
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
		Partie other = (Partie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

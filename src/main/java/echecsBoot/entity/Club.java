package echecsBoot.entity;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

import echecsBoot.entity.view.Views;

@Entity
@Table(name = "club")
@SequenceGenerator(name = "seqClub", sequenceName = "seq_club", initialValue = 100, allocationSize = 1)
public class Club {
	@JsonView(Views.Common.class)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqClub")
	@Column(name = "id_club")
	private Integer id;
	@JsonView(Views.Common.class)
	@Column(name = "nom_club", length = 50, nullable = false)
	private String nom;
	@JsonView(Views.Common.class)
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "numero", column = @Column(name = "numero_club")),
			@AttributeOverride(name = "rue", column = @Column(name = "rue_club", length = 200)),
			@AttributeOverride(name = "codePostal", column = @Column(name = "code_postal_club", length = 11)),
			@AttributeOverride(name = "ville", column = @Column(name = "ville_club", length = 100)),
			@AttributeOverride(name = "pays", column = @Column(name = "pays_club", length = 100)) })
	private Adresse adresse;
	@JsonView(Views.Common.class)
	@OneToMany(mappedBy = "club")
	private List<Membre> membres;
	@OneToMany(mappedBy = "organisateur")
	private List<Tournoi> tournois;
	@Version
	private int version;

	public Club() {
	}

	public Club(String nom) {
		this.nom = nom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Membre> getMembres() {
		return membres;
	}

	public void setMembres(List<Membre> membres) {
		this.membres = membres;
	}

	public List<Tournoi> getTournois() {
		return tournois;
	}

	public void setTournois(List<Tournoi> tournois) {
		this.tournois = tournois;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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
		Club other = (Club) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

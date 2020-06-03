package echecsBoot.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonView;

import echecsBoot.entity.view.Views;

@Entity
@Table(name = "membre")
@SequenceGenerator(name = "seqMembre", sequenceName = "seq_membre", initialValue = 100, allocationSize = 1)
public class Membre {
	@JsonView(Views.Common.class)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMembre")
	@Column(name = "id_membre", length = 10)
	private Integer id;
	@JsonView(Views.Common.class)
	@Column(name = "prenom", length = 40, nullable = false)
	private String prenom;
	@JsonView(Views.Common.class)
	@Column(name = "nom", length = 40, nullable = false)
	private String nom;
	@JsonView(Views.Common.class)
	@Column(name = "email", length = 40, nullable = false)
	private String email;
	@JsonView(Views.Common.class)
	@Column(name = "elo", length = 5)
	private Integer elo;
	@JsonView(Views.Common.class)
	@Column(name = "civilite", length = 4)
	@Enumerated(EnumType.STRING)
	private Civilite civilite;
	@Version
	private int version;
	@JsonView(Views.Common.class)
	@Column(name = "nationalite", length = 2)
	@Enumerated(EnumType.STRING)
	private Nationalite nationalite;
	@JsonView(Views.Common.class)
	@Embedded
	private Adresse adresse;
	@JsonView(Views.Common.class)
	@OneToMany(mappedBy = "joueurBlancs")
	private List<Partie> partieBlancs;
	@JsonView(Views.Common.class)
	@OneToMany(mappedBy = "joueurNoirs")
	private List<Partie> partieNoirs;
	@JsonView(Views.Common.class)
	@ManyToOne
	@JoinColumn(name = "club", foreignKey = @ForeignKey(name = "membre_club_fk"))
	private Club club;
	@JsonView(Views.Common.class)
	@OneToMany(mappedBy = "acheteur")
	private List<ContenuPedagogique> contenuPedagogique;
	@JsonView(Views.Common.class)
	@OneToMany(mappedBy = "id.membre")
	private List<HistoriqueElo> historiqueElo;
	@JsonView(Views.Common.class)
	@OneToMany(mappedBy = "id.participant")
	private List<Participation> participations;
	@JsonView(Views.Common.class)
	@OneToOne(mappedBy = "membre" )
	private Login login;

	public Membre() {
	}

	public Membre(String prenom, String nom, String email, Integer elo) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.elo = elo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getElo() {
		return elo;
	}

	public void setElo(Integer elo) {
		this.elo = elo;
	}

	public Civilite getCivilite() {
		return civilite;
	}

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Nationalite getNationalite() {
		return nationalite;
	}

	public void setNationalite(Nationalite nationalite) {
		this.nationalite = nationalite;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public List<Partie> getPartieBlancs() {
		return partieBlancs;
	}

	public void setPartieBlancs(List<Partie> partieBlancs) {
		this.partieBlancs = partieBlancs;
	}

	public List<Partie> getPartieNoirs() {
		return partieNoirs;
	}

	public void setPartieNoirs(List<Partie> partieNoirs) {
		this.partieNoirs = partieNoirs;
	}

	public void setContenuPedagogique(List<ContenuPedagogique> contenuPedagogique) {
		this.contenuPedagogique = contenuPedagogique;
	}

	public void setHistoriqueElo(List<HistoriqueElo> historiqueElo) {
		this.historiqueElo = historiqueElo;
	}

	public List<ContenuPedagogique> getContenuPedagogique() {
		return contenuPedagogique;
	}

	public List<HistoriqueElo> getHistoriqueElo() {
		return historiqueElo;
	}

	public List<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
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
		Membre other = (Membre) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
package echecsBoot.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import echecsBoot.entity.view.Views;

@Entity
@Table(name = "login")
public class Login {
	@JsonView(Views.Common.class)
	@Id
	@Column(name = "username", length = 100)
	private String login;
	@JsonView(Views.Common.class)
	@Column(name = "pass", length = 100, nullable = false)
	private String password;
	private boolean enable;
	@JsonView(Views.Common.class)
	@OneToMany(mappedBy = "login")
	private Set<LoginRole> roles;
	@OneToOne
	@JoinColumn(name = "loginmembre", foreignKey = @ForeignKey(name = "login_loginmembre_fk"))
	private Membre membre;

	public Login() {

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Set<LoginRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<LoginRole> roles) {
		this.roles = roles;
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		Login other = (Login) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

}

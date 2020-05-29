package echecsBoot.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonView;

import echecsBoot.entity.view.Views;

@Entity
@DiscriminatorValue("L")
public class Livre extends ContenuPedagogique {
	@JsonView(Views.Common.class)
	@Column(name = "isbn", length = 15)
	private String isbn;

	public Livre() {
		super();
	}

	public Livre(String titre, Double prix) {
		super(titre, prix);
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}

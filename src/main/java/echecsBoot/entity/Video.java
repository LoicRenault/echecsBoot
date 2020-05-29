package echecsBoot.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonView;

import echecsBoot.entity.view.Views;

@Entity
@DiscriminatorValue("V")
public class Video extends ContenuPedagogique {
	@JsonView(Views.Common.class)
	@Column(name = "url", length = 150)
	private String url;

	public Video() {
		super();
	}

	public Video(String titre, Double prix, String url) {
		super(titre, prix);
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

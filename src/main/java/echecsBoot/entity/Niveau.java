package echecsBoot.entity;

public enum Niveau {
	AF4("Arbitre f�d�ral 4"), AF3("Arbitre f�d�ral 3"), AF2("Arbitre f�d�ral 2"), AF1("Arbitre f�d�ral 1") , AI("Arbitre international");

	private String label;

	private Niveau(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}

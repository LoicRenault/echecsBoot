package echecsBoot.entity;

public enum Niveau {
	AF4("Arbitre fédéral 4"), AF3("Arbitre fédéral 3"), AF2("Arbitre fédéral 2"), AF1("Arbitre fédéral 1") , AI("Arbitre international");

	private String label;

	private Niveau(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}

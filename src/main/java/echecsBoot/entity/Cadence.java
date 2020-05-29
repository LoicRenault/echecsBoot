package echecsBoot.entity;

public enum Cadence {
	Fischer("1h30+30s/c 40c + 30min+30s/c"), Rapide("15min+10s/c"), Blitz("3min+2s/c");

	private String label;

	private Cadence(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}

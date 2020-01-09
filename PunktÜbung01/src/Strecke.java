
public class Strecke {

	// ATTRIBUTE
	private Punkt p1;
	private Punkt p2;

	// KONSTRUKTOR
	public Strecke(Punkt p1, Punkt p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	// METHODEN
	public double getLaenge() {
		double laenge;

		final int distX = Math.abs(p1.getX() - p2.getX());
		final int distY = Math.abs(p1.getY() - p2.getY());

		laenge = Math.sqrt(distX * distX + distY * distY);
		return laenge;
	}
}

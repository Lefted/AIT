
public class Kreis extends GeometrischesObjekt {

	// ATTRIBUTE
	private Punkt punkt;
	private int radius;

	// KONSTRUKTOR
	public Kreis(Punkt punkt, int radius) {
		super();
		this.punkt = punkt;
		this.radius = radius;
	}

	// METHODEN
	@Override
	public void berechneFlaeche() {
		this.flaeche = 2 * Math.PI * this.radius * this.radius;
	}

	public Punkt getPunkt() {
		return punkt;
	}

	public void setPunkt(Punkt punkt) {
		this.punkt = punkt;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
		this.berechneFlaeche();
	}
}

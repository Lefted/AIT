
public class Rechteck {

	// ATTRIBUTE
	private Punkt a;
	private Punkt b;
	private double seiteA;
	private double seiteB;

	// KONSTRUKTOR
	public Rechteck(Punkt a, Punkt b) {
		this.a = a;
		this.b = b;

		this.seitenBerechnen();
	}

	// METHODEN
	private void seitenBerechnen() {
		this.seiteA = Math.abs(this.a.getX() - this.b.getX());
		this.seiteB = Math.abs(this.a.getY() - this.b.getY());
	}

	public double getFlaeche() {
		return this.seiteA * this.seiteB;
	}

	public Punkt getA() {
		return a;
	}

	public void setA(Punkt a) {
		this.a = a;
		this.seitenBerechnen();
	}

	public Punkt getB() {
		return b;
	}

	public void setB(Punkt b) {
		this.b = b;
		this.seitenBerechnen();
	}

}

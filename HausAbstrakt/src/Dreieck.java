
public class Dreieck extends GeometrischesObjekt {

	// ATTRIBUTE
	private Punkt a;
	private Punkt b;
	private Punkt c;
	private double seiteA;
	private double seiteB;
	private double seiteC;

	// KONSTRUKTOR
	public Dreieck(Punkt a, Punkt b, Punkt c) {
		this.a = a;
		this.b = b;
		this.c = c;

		this.seitenBerechnen();
	}

	// METHODEN
	private void seitenBerechnen() {
		this.seiteA = this.getAbstand(this.b, this.c);
		this.seiteB = this.getAbstand(this.a, this.c);
		this.seiteC = this.getAbstand(this.a, this.b);
		
		this.berechneFlaeche();
	}

	private double getAbstand(Punkt p1, Punkt p2) {
		final double distX = Math.abs(p1.getX() - p2.getX());
		final double distY = Math.abs(p1.getY() - p2.getY());

		// SATZ DES PYTHAGORAS
		return Math.sqrt(distX * distX + distY * distY);
	}

	@Override
	public void berechneFlaeche() {
		final double s = (this.seiteA + this.seiteB + this.seiteC) / 2;

		// SATZ DES HERON
		this.flaeche = Math.sqrt(s * (s - this.seiteA) * (s - this.seiteB) * (s - this.seiteC));
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

	public Punkt getC() {
		return c;
	}

	public void setC(Punkt c) {
		this.c = c;
		this.seitenBerechnen();
	}
}

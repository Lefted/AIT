
public class Haus {

	// ATTRIBUTE
	private Dreieck d1;
	private Dreieck d2;
	private Rechteck r1;

	// KONSTRUKTOR
	public Haus(Dreieck d1, Dreieck d2, Rechteck r1) {
		this.d1 = d1;
		this.d2 = d2;
		this.r1 = r1;
	}

	// METHODEN
	/* gibt die gesamte Fläche zurück */
	public double getFlaeche() {
		return this.d1.getFlaeche() + this.d2.getFlaeche() + this.r1.getFlaeche();
	}

	public Dreieck getD1() {
		return d1;
	}

	public void setD1(Dreieck d1) {
		this.d1 = d1;
	}

	public Dreieck getD2() {
		return d2;
	}

	public void setD2(Dreieck d2) {
		this.d2 = d2;
	}

	public Rechteck getR1() {
		return r1;
	}

	public void setR1(Rechteck r1) {
		this.r1 = r1;
	}
}

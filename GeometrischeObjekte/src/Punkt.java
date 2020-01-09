
public class Punkt {

	// ATTRIBUTE
	private double x; // x-Koordinate des Punktes
	private double y; // y-Koordinate des Punktes

	// KONSTRUKTOR
	public Punkt(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// METHODEN
	public double getAbstand() {
		// Abstand zum Ursprung mit Satz des Pythagoras berechnen
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}

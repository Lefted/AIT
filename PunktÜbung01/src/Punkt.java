
public class Punkt {

	// ATTRIBUTE
	private int x; // x-Koordinate des Punktes
	private int y; // y-Koordinate des Punktes

	// DEFAULT KONSTRUKTOR
	public Punkt() {
		System.out.println("Default Konstruktor wurde benutzt");
	}

	// ÜBERLADENER KONSTRUKTOR
	public Punkt(int x, int y) {
		this.x = x;
		this.y = y;
		System.out.println("Überladener Konstruktor wurde benutzt");
	}

	// METHODEN
	public double getAbstand() {
		// Abstand zum Ursprung mit Satz des Pythagoras berechnen
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}

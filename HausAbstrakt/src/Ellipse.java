
public class Ellipse extends GeometrischesObjekt {

	// ATTRIBUTE
	private Punkt mitte;
	private int laengeX;
	private int laengeY;

	// KONSTRUKTOR
	public Ellipse(Punkt m, int laengeX, int laengeY) {
		this.mitte = m;
		this.laengeX = laengeX;
		this.laengeY = laengeY;
		this.berechneFlaeche();
	}

	// METHODEN
	@Override
	public void berechneFlaeche() {
		this.flaeche = Math.PI * this.laengeX * this.laengeY;
	}

	public Punkt getM() {
		return mitte;
	}

	public void setM(Punkt m) {
		this.mitte = m;
	}

	public int getLaengeX() {
		return laengeX;
	}

	public void setLaengeX(int laengeX) {
		this.laengeX = laengeX;
		this.berechneFlaeche();
	}

	public int getLaengeY() {
		return laengeY;
	}

	public void setLaengeY(int laengeY) {
		this.laengeY = laengeY;
		this.berechneFlaeche();
	}

}

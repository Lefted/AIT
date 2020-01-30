
public abstract class Fahrzeug {

	// ATTRIBUTE
	private int anzahlRaeder;
	private int geschwindigkeit;
	private int vMax;
	private String hersteller;
	private String modellname;

	// KONSTRUKTOR
	public Fahrzeug() {
//		System.out.println("Ich bin ein Fahrzeug");
	}

	// METHODEN
	public void bremsen(int wert) {
		this.geschwindigkeit -= wert;
		this.geschwindigkeit = (this.geschwindigkeit < 0) ? 0 : this.geschwindigkeit;
	}

	public void beschleunigen(int wert) {
		this.geschwindigkeit += wert;
		this.geschwindigkeit = (this.geschwindigkeit > this.vMax) ? this.vMax : this.geschwindigkeit;
	}

	public int getAnzahlRaeder() {
		return anzahlRaeder;
	}

	public void setAnzahlRaeder(int anzahlRaeder) {
		this.anzahlRaeder = anzahlRaeder;
	}

	public int getvMax() {
		return vMax;
	}

	public void setvMax(int vMax) {
		this.vMax = vMax;
	}

	public String getHersteller() {
		return hersteller;
	}

	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}

	public String getModellname() {
		return modellname;
	}

	public void setModellname(String modellname) {
		this.modellname = modellname;
	}

	public int getGeschwindigkeit() {
		return geschwindigkeit;
	}

	@Override
	public String toString() {
		return "Fahrzeug [anzahlRaeder=" + anzahlRaeder + ", geschwindigkeit=" + geschwindigkeit + ", vMax=" + vMax
				+ ", hersteller=" + hersteller + ", modellname=" + modellname + "]";
	}
}

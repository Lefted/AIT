
public abstract class GeometrischesObjekt {

	// ATTRIBUTE
	protected double flaeche;
	private String farbe;

	// METHODEN
	public abstract void berechneFlaeche();
	
	public double getFlaeche() {
		return flaeche;
	}

	public String getFarbe() {
		return farbe;
	}

	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}
}

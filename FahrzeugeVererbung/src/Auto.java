
public class Auto extends Fahrzeug {

	// ATTRIBUTE
	private boolean hatKlimaanlage = true;

	// KONSTRUKTOR
	public Auto() {
//		System.out.println("Ich bin ein Auto");
		this.setAnzahlRaeder(4);
	}

	// METHODEN
	public boolean isHatKlimaanlage() {
		return hatKlimaanlage;
	}

	public void setHatKlimaanlage(boolean hatKlimaanlage) {
		this.hatKlimaanlage = hatKlimaanlage;
	}

}

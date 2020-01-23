
public class Cabrio extends Auto {

	// ATTRIBUTE
	private int vMaxDach;
	@SuppressWarnings("unused")
	private boolean dachOffen;

	// KONSTRUKTOR
	public Cabrio() {
//		System.out.println("Ich bin ein Cabrio");
	}

	// METHODEN
	public void dachOeffnen() {
		if (this.getGeschwindigkeit() <= this.vMaxDach) {
			this.dachOffen = true;
		}
		final String ausgabe = (this.dachOffen) ? "Dach wurde erfolgreich geöffnet!"
				: "Das Dach konnte bei der aktuellen Geschwindigkeit nicht geöffnet werden!";
		System.out.println(ausgabe);
	}

	public void dachSchliessen() {
		if (this.getGeschwindigkeit() <= this.vMaxDach) {
			this.dachOffen = false;
		}
		final String ausgabe = (this.dachOffen)
				? "Dach konnte bei der aktuellen Geschwindigkeit nicht geschlossen werden!"
				: "Das Dach wurde erfolgreich geschlossen!";
		System.out.println(ausgabe);
	}
}

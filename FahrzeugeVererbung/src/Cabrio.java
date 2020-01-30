
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
		final String ausgabe = (this.dachOffen) ? "Dach wurde erfolgreich ge�ffnet!"
				: "Das Dach konnte bei der aktuellen Geschwindigkeit nicht ge�ffnet werden!";
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

	@Override
	public String toString() {
		return super.toString() + "Cabrio [vMaxDach=" + vMaxDach + ", dachOffen=" + dachOffen + "]";
	}

	public int getvMaxDach() {
		return vMaxDach;
	}

	public void setvMaxDach(int vMaxDach) {
		this.vMaxDach = vMaxDach;
	}

	public boolean isDachOffen() {
		return dachOffen;
	}
}

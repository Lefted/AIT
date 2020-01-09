
public class Magier {

	// INSTANZVARIABLEN
	private String name;
	private int energie = 30;
	private int angriffswert;
	private int verteidigungswert;

	public void angreifen(Magier m2) {
		System.out.println("Angriff " + this.name + " mit Angriffswert " + this.angriffswert + ", Verteidigung " + m2.getName()
				+ " mit Verteidigungswert " + m2.getVerteidigungswert());

		final int SCHADEN = ((this.angriffswert - m2.getVerteidigungswert()) > 0) ? this.angriffswert - m2.getVerteidigungswert() : 0;
		
		System.out.println(m2.getName() + " verliert Energiepunkte: " + SCHADEN);
		m2.setEnergie(m2.getEnergie() - SCHADEN);
	}

	public void angriffswertErneuern() {
		this.angriffswert = (int) (Math.random() * 5) + 3;
	}

	public void verteidigungswertErneuern() {
		this.verteidigungswert = (int) (Math.random() * 3) + 4;
	}

	public void versucheReinkarnation() {
		final int CHANCE = (int) (Math.random() * 100) + 1;
		
		if (CHANCE == 1) {
			this.energie = 5;
			System.out.println("+ + " + this.name + " hat Reinkarnation angewandt! + +");
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEnergie() {
		return energie;
	}

	public void setEnergie(int energie) {
		this.energie = energie;
	}

	public int getAngriffswert() {
		return angriffswert;
	}

	public void setAngriffswert(int angriffswert) {
		this.angriffswert = angriffswert;
	}

	public int getVerteidigungswert() {
		return verteidigungswert;
	}

	public void setVerteidigungswert(int verteidigungswert) {
		this.verteidigungswert = verteidigungswert;
	}

}

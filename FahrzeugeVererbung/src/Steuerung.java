
public class Steuerung {

	public static void main(String[] args) {

		Auto auto = new Auto();
		auto.setvMax(3);
		auto.beschleunigen(4);
		auto.setHatKlimaanlage(true);
		auto.setHersteller("Audi");
		auto.setModellname("R8");

		System.out.println(auto.toString());
		auto.bremsen(50);
		System.out.println(auto.toString());

		Cabrio cabrio = new Cabrio();
		cabrio.setvMax(100);
		cabrio.beschleunigen(51);
		cabrio.setHatKlimaanlage(true);
		cabrio.setHersteller("Audi");
		cabrio.setModellname("R8");
		cabrio.setvMaxDach(50);

		System.out.println(cabrio.toString());
		cabrio.dachOeffnen();
		cabrio.bremsen(50);
		cabrio.dachOeffnen();
		System.out.println(cabrio.toString());

		Motorrad motorrad = new Motorrad();
		motorrad.beschleunigen(3);
		System.out.println(motorrad.toString());
	}

}

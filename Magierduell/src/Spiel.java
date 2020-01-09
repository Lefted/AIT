import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Spiel {

	public static void main(String[] args) throws IOException {

		boolean spielLaufend = true;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		Magier m1 = new Magier();
		Magier m2 = new Magier();

		System.out.println("Wie soll Magier 1 heiﬂen?");
		m1.setName(reader.readLine());
		
		System.out.println("Wie soll Magier 2 heiﬂen?");
		m2.setName(reader.readLine());

		
		System.out.println("Das Duell kann beginnen!");
		System.out.println();
		
		while (spielLaufend) {
			
//			ausgabe des aktuellen energiewerts
			System.out.println("Aktueller Energiestand: " + m1.getName() + ": " + m1.getEnergie() + " " + m2.getName()
					+ ": " + m2.getEnergie());
			
//			stats aktualisieren
			m1.angriffswertErneuern();
			m1.verteidigungswertErneuern();
			m2.angriffswertErneuern();
			m2.verteidigungswertErneuern();
			
//			angreifen
			m1.angreifen(m2);
			m2.angreifen(m1);
			
//			tod
			if (m1.getEnergie() <= 0 || m2.getEnergie() <= 0) {
				
//				reinkarnation
				m1.versucheReinkarnation();
				m2.versucheReinkarnation();

//				endg¸ltiger tod
				if (m1.getEnergie() <= 0 || m2.getEnergie() <= 0) {
					// gleichstand
					if (m2.getEnergie() == m1.getEnergie()) {
						System.out.println("Endstand: "  + m1.getName() + " 0 " + m2.getName() + " 0");
						System.out.println("Beide Magier haben sich gegenseitig ausgeknockt");
					} else {
						final String VERLIERER = (m1.getEnergie() < m2.getEnergie()) ? m1.getName() : m2.getName();
						
						System.out.println("Endstand: "  + m1.getName() + " " + m1.getEnergie() + " " + m2.getName() + " " + m2.getEnergie());
						System.out.println(VERLIERER + " verliert!");
					}
					spielLaufend = false;
				}
			} else {
				System.out.println();
			}
		}
	}

}

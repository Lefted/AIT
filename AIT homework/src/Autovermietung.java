import java.io.*;

public class Autovermietung {

	public static void main(String[] args) throws IOException {

		// Variablen und Objekte deklarieren
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String klasse;
		int tage;
		int strecke;
		int tagespreis = 0;
		double kilometerpreis = 0;
		double endpreis;
		boolean fehler = false;

		// Einlesen
		
		System.out.println("Autovermietung");
		System.out.println();
		System.out.print("Bitte geben Sie ihre Autoklasse (A-D) ein: ");
		klasse = reader.readLine();
		System.out.print("Bitte geben Sie die Anzahl der Miettage ein: ");
		tage = Integer.parseInt(reader.readLine());
		System.out.print("Bitte geben Sie die gefahrene Strecke (in km) ein: ");
		strecke = Integer.parseInt(reader.readLine());

		/*	Verarbeiten: 
		 * 	Tagespreis und Kilometerpreis der jeweiligen Klasse zuordnen
		 */

		switch (klasse.toUpperCase()) {
		case "A":
			tagespreis = 35;
			kilometerpreis = 0.1;
			break;
		case "B":
			tagespreis = 52;
			kilometerpreis = 0.11;
			break;
		case "C":
			tagespreis = 77;
			kilometerpreis = 0.11;
			break;
		case "D":
			tagespreis = 99;
			kilometerpreis = 0.15;
			break;
		default:
			fehler = true;
			break;
		}
		/*
		 * Falls eine ungültige Klasse eingegebn wurde, 
		 * wird dies dem Kunden mitgeteilt
		 */
		if (fehler) {
			System.out.println("Bitte geben Sie eine gültige Klasse (A-D) ein.");
		}

		// Berechnung des Endpreises
		else {

			// Ist der Kunde mehr als die Freistrecke gefahren

			if (strecke * tage > 100) {
				endpreis = tagespreis * tage + (strecke - 100) * kilometerpreis;
			}

			// Ist der Kunde nur innerhalb der Freistrecke gefahren

			else {
				endpreis = tagespreis * tage;
			}
			
			System.out.println("Sie müssen " + endpreis + " Euro bezahlen.");
		}
		System.out.println();
		System.out.println("Programmende Autovermietung");
	}
}

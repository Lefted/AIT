import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SchiffeVersenken {

	public static void main(String[] args) throws IOException {
		//VARIABLEN
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		char[][] spielfeld = initSpielfeld();

		ausgabeSpielfeld(spielfeld);

		//aufgabe 3
		//FORMAT
		System.out.println();

		//RICHTUNG
		System.out.print("In welcher Richtung soll das 1.Schiff (Länge = 2) platziert werden? (H)orizontal/(V)ertikal: ");
		String eingabe = reader.readLine();
		//in großbuchstaben umwandeln
		char richtung = Character.toUpperCase(eingabe.charAt(0));

		//validen wert für richtung erzwingen
		while (!richtigeRichtung(richtung)) {
			System.out.println("Ungültige Eingabe für Richtung!");
			System.out.print("Bitte erneut eingeben (H/V): ");
			eingabe = reader.readLine();
			richtung = Character.toUpperCase(eingabe.charAt(0));
		}

		//REIHE
		System.out.print("In welcher Reihe soll der Anfang des 1.Schiffs (Länge = 2) platziert werden? (0-9): ");
		eingabe = reader.readLine();
		//in großbuchstaben umwandeln
		char reihe = Character.toUpperCase(eingabe.charAt(0));

		//validen wert für reihe erzwingen
		while (!richtigeReihe(reihe)) {
			System.out.println("Ungültige Eingabe für Reihe!");
			System.out.print("Bitte erneut eingeben (0-9): ");
			eingabe = reader.readLine();
			reihe = Character.toUpperCase(eingabe.charAt(0));
		}

		//SPALTE
		System.out.print("In welcher Spalte soll der Anfang des 1. Schiffs (Länge = 2) platziert werden? (A-J): ");
		eingabe = reader.readLine();
		//in großbuchstaben umwandeln
		char spalte = Character.toUpperCase(eingabe.charAt(0));

		//validen wert für spalte erzwingen
		while (!richtigeSpalte(spalte)) {
			System.out.println("Ungültige Eingabe für Spalte!");
			System.out.print("Bitte erneut eingeben (A-J): ");
			eingabe = reader.readLine();
			spalte = Character.toUpperCase(eingabe.charAt(0));
		}

		//FORMAT
		System.out.println();
	}

	//auf richtige spalte testen
	private static boolean richtigeSpalte(char spalte) {
		if (!((spalte == 'A') || (spalte == 'B') || (spalte == 'C') || (spalte == 'D') || (spalte == 'E') || (spalte == 'F') || (spalte == 'G')
				|| (spalte == 'H') || (spalte == 'I') || (spalte == 'J'))) {
			return false;
		}
		return true;
	}

	//auf richtige richtung testen
	private static boolean richtigeRichtung(char richtung) {
		if ((richtung != 'H') && (richtung != 'V')) {
			return false;
		}
		return true;
	}
	
	//auf richtige reihe testen

	//auf richtige reihe testen
	private static boolean richtigeReihe(char reihe) {
		if (!((0 <= Character.getNumericValue(reihe)) && (Character.getNumericValue(reihe) < 10))) {
			return false;
		}
		return true;
	}

	//spielfeld mit '.' initialisieren
	
	//spielfeld mit '.' initialisieren
	private static char[][] initSpielfeld() {
		char[][] spielfeld = new char[10][10];

		for (int i = 0; i < spielfeld.length; i++) {
			for (int j = 0; j < spielfeld[0].length; j++) {
				spielfeld[i][j] = '.';
			}
		}
		return spielfeld;
	}

	//spielfeld in der konsole ausgaben
	
	//spielfeld in der konsole ausgeben
	private static void ausgabeSpielfeld(char[][] spielfeld) {
		System.out.println("  A B C D E F G H I J");

		for (int i = 0; i < spielfeld.length; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < spielfeld[0].length; j++) {

				System.out.print(spielfeld[i][j] + " ");
			}
			System.out.println();
		}
	}

	// TODO
	// schleife mit switch auf i und länge setzen

}

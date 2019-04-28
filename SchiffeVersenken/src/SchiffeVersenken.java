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
		eingabeSchiffe(spielfeld, reader);
	}

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

	//aufgabe 3
	//spieler schiffer platzieren lassen
	private static void eingabeSchiffe(char[][] spielfeld, BufferedReader reader) throws IOException {
		for (int i = 0; i < 5; i++) {
			//länge deklarieren
			int laenge;

			//länge der schiffe anhand des durchgangs setzen
			switch (i + 1) {
			case 1:
				laenge = 2;
				break;
			case 2:
				laenge = 3;
				break;
			case 3:
				laenge = 3;
				break;
			case 4:
				laenge = 4;
				break;
			case 5:
				laenge = 5;
				break;
			default:
				laenge = 2;
				break;
			}

			//FORMAT
			System.out.println();

			//RICHTUNG
			System.err.print(
					"In welcher Richtung soll das " + (i + 1) + ".Schiff (Länge = " + laenge + ") platziert werden? (H)orizontal/(V)ertikal: ");
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
			System.err.print("In welcher Reihe soll der Anfang des " + (i + 1) + ".Schiffs (Länge = " + laenge + ") platziert werden? (0-9): ");
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
			System.err.print("In welcher Spalte soll der Anfang des " + (i + 1) + ". Schiffs (Länge = " + laenge + ") platziert werden? (A-J): ");
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
			
			//FORAMT
			System.out.println();
			
			//nach jeder eingabe spielfeld aktualisieren
			ausgabeSpielfeld(spielfeld);
		}

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
	private static boolean richtigeReihe(char reihe) {
		if (!((0 <= Character.getNumericValue(reihe)) && (Character.getNumericValue(reihe) < 10))) {
			return false;
		}
		return true;
	}
}

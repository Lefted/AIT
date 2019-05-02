import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SchiffeVersenken {

	public static void main(String[] args) throws IOException {
		// VARIABLEN
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// spielfeld[x][y]
		char[][] spielfeld = spielfeldInitalisieren();

		spielfeldAusgeben(spielfeld);

		// aufgabe 3
		// 5x : einlesen, �berpr�fen, schiff hinzuf�gen, feld ausgeben
		schiffeEinlesen(spielfeld, reader);
	}

	// spielfeld mit '.' initialisieren
	private static char[][] spielfeldInitalisieren() {
		char[][] spielfeld = new char[10][10];

		for (int i = 0; i < spielfeld.length; i++) {
			for (int j = 0; j < spielfeld[0].length; j++) {
				spielfeld[i][j] = '.';
			}
		}
		return spielfeld;
	}

	// spielfeld in der konsole ausgeben
	private static void spielfeldAusgeben(char[][] spielfeld) {
		System.out.println("  A B C D E F G H I J");

		for (int i = 0; i < spielfeld.length; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < spielfeld[0].length; j++) {

				System.out.print(spielfeld[j][i] + " ");
			}
			System.out.println();
		}
	}

	// aufgabe 3
	// spieler schiffer platzieren lassen
	private static void schiffeEinlesen(char[][] spielfeld, BufferedReader reader) throws IOException {
		for (int i = 0; i < 5; i++) {
			// l�nge des schiffe setzen
			int[] laengen = { 2, 3, 3, 4, 5 };
			int laenge = laengen[i];

			// FORMAT
			System.out.println();

			// RICHTUNG
			System.out.print("In welcher Richtung soll das " + (i + 1) + ".Schiff (L�nge = " + laenge
					+ ") platziert werden? (H)orizontal/(V)ertikal: ");
			String eingabe = reader.readLine();
			// in gro�buchstaben umwandeln
			char richtung = Character.toUpperCase(eingabe.charAt(0));

			// validen wert f�r richtung erzwingen
			while (!richtigeRichtung(richtung)) {
				System.out.println("\"" + eingabe + "\"" + " ist keine g�ltige Eingabe f�r Richtung!");
				System.out.print("Bitte erneut eingeben (H/V): ");
				eingabe = reader.readLine();
				richtung = Character.toUpperCase(eingabe.charAt(0));
			}

			// REIHE
			System.out.print("In welcher Reihe soll der Anfang des " + (i + 1) + ".Schiffs (L�nge = " + laenge
					+ ") platziert werden? (0-9): ");
			eingabe = reader.readLine();
			// in gro�buchstaben umwandeln
			char reihe = Character.toUpperCase(eingabe.charAt(0));

			// validen wert f�r reihe erzwingen
			while (!richtigeReihe(reihe)) {
				System.out.println("\"" + eingabe + "\"" + " ist keine g�ltige Eingabe f�r Reihe!");
				System.out.print("Bitte erneut eingeben (0-9): ");
				eingabe = reader.readLine();
				reihe = Character.toUpperCase(eingabe.charAt(0));
			}

			// SPALTE
			System.out.print("In welcher Spalte soll der Anfang des " + (i + 1) + ". Schiffs (L�nge = " + laenge
					+ ") platziert werden? (A-J): ");
			eingabe = reader.readLine();
			// in gro�buchstaben umwandeln
			char spalte = Character.toUpperCase(eingabe.charAt(0));

			// validen wert f�r spalte erzwingen
			while (!richtigeSpalte(spalte)) {
				System.out.println("\"" + eingabe + "\"" + " ist keine g�ltige Eingabe f�r Spalte!");
				System.out.print("Bitte erneut eingeben (A-J): ");
				eingabe = reader.readLine();
				spalte = Character.toUpperCase(eingabe.charAt(0));
			}

			// TODO
			// sichergehen, dass Schiff nicht au�erhalb des spielfelds geht
			// sichergehen, dass Schiff nicht mit anderen Schiffen �berlappt

			if (!schiffAu�erhalb(richtung, reihe, spalte, spielfeld, laenge)) {
				// spielfeld[][] aktuelles schiff hinzuf�gen
				System.out.println("g");
				schiffHinzufuegen(spielfeld, richtung, reihe, spalte, laenge);
			} else {
				System.out.println();
				System.err.println("Schiff liegt au�erhalb des Spielfelds");
				System.err.println("Shutting down system");
				System.exit(-1);
			}

			// FORAMT
			System.out.println();

			// nach jeder eingabe spielfeld aktualisieren
			spielfeldAusgeben(spielfeld);
		}

	}

	// spielfeld neue schiffe hinzuf�gen
	private static void schiffHinzufuegen(char[][] spielfeld, char richtung, char reihe, char spalte, int laenge) {
		// spalte/reihe in koordinaten umwandeln
		int posX = spalte - 'A';
		int posY = reihe - '0';

		if (richtung == 'H') {
			for (int i = 0; i < laenge; i++) {
				spielfeld[posX][posY] = '*';
				posX++;
			}
		} else if (richtung == 'V') {
			for (int i = 0; i < laenge; i++) {
				spielfeld[posX][posY] = '*';
				posY++;
			}
		} else {
			// fehler verarbeiten
			System.out.println();
			System.err.println("Variable 'richtung' has an invalid value: " + richtung);
			System.out.println("Exiting program");
			System.exit(-1);
		}
	}

	// auf richtige spalte testen
	private static boolean richtigeSpalte(char spalte) {
		final int decWert = spalte;

		if (!(('A' <= decWert) && (decWert <= 'J'))) {
			return false;
		}
		return true;
	}

	// auf richtige richtung testen
	private static boolean richtigeRichtung(char richtung) {
		if ((richtung != 'H') && (richtung != 'V')) {
			return false;
		}
		return true;
	}

	// auf richtige reihe testen
	private static boolean richtigeReihe(char reihe) {
		if (!((0 <= Character.getNumericValue(reihe)) && (Character.getNumericValue(reihe) < 10))) {
			return false;
		}
		return true;
	}

	// testen ob schiff au�erhalb des spielfeld ist
	private static boolean schiffAu�erhalb(char richtung, char reihe, char spalte, char[][] spielfeld, int laenge) {
		int posX = spalte - 'A';
		int posY = reihe - '0';
		
		if (richtung == 'H') {
			for (int i = 0; i < laenge; i++) {
				posX++;
				if (posX >= spielfeld.length) {
					return true;
				}
			}
		} else if (richtung == 'V') {
			for (int i = 0; i < laenge; i++) {
				posY++;
				if (posY >= spielfeld[0].length) {
					return true;
				}
			}
		}
		return false;
	}
}
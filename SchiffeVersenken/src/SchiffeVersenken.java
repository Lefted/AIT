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
		// 5x : einlesen, überprüfen, schiff hinzufügen, feld ausgeben
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
		// falls die eingabe ungültig war soll sie wiederholt werden
		boolean wiederholen = false;

		// länge des schiffe setzen
		final int[] laengen = { 2, 3, 3, 4, 5 };

		// für alle 5 schiffe
		for (int i = 0; i < 5; i++) {
			// laenge des aktuellen schiffs festlegen
			final int laenge = laengen[i];

			do {
				// FORMAT
				System.out.println();

				// RICHTUNG
				System.out.print(
						"In welcher Richtung soll das " + (i + 1) + ".Schiff (Länge = " + laenge + ") platziert werden? (H)orizontal/(V)ertikal: ");
				String eingabe = reader.readLine();
				// in großbuchstaben umwandeln
				char richtung = Character.toUpperCase(eingabe.charAt(0));

				// validen wert für richtung erzwingen
				while (!richtigeRichtung(richtung)) {
					System.out.println("\"" + eingabe + "\"" + " ist keine gültige Eingabe für Richtung!");
					System.out.print("Bitte erneut eingeben (H/V): ");
					eingabe = reader.readLine();
					richtung = Character.toUpperCase(eingabe.charAt(0));
				}

				// REIHE
				System.out.print("In welcher Reihe soll der Anfang des " + (i + 1) + ".Schiffs (Länge = " + laenge + ") platziert werden? (0-9): ");
				eingabe = reader.readLine();
				// in großbuchstaben umwandeln
				char reihe = Character.toUpperCase(eingabe.charAt(0));

				// validen wert für reihe erzwingen
				while (!richtigeReihe(reihe)) {
					System.out.println("\"" + eingabe + "\"" + " ist keine gültige Eingabe für Reihe!");
					System.out.print("Bitte erneut eingeben (0-9): ");
					eingabe = reader.readLine();
					reihe = Character.toUpperCase(eingabe.charAt(0));
				}

				// SPALTE
				System.out.print("In welcher Spalte soll der Anfang des " + (i + 1) + ". Schiffs (Länge = " + laenge + ") platziert werden? (A-J): ");
				eingabe = reader.readLine();
				// in großbuchstaben umwandeln
				char spalte = Character.toUpperCase(eingabe.charAt(0));

				// validen wert für spalte erzwingen
				while (!richtigeSpalte(spalte)) {
					System.out.println("\"" + eingabe + "\"" + " ist keine gültige Eingabe für Spalte!");
					System.out.print("Bitte erneut eingeben (A-J): ");
					eingabe = reader.readLine();
					spalte = Character.toUpperCase(eingabe.charAt(0));
				}

				// sichergehen, dass schiff nicht außerhalb des spielfelds liegt
				if (schiffAußerhalb(richtung, reihe, spalte, spielfeld, laenge)) {
					System.out.println();
					System.out.println("Schiff liegt außerhalb des Spielfelds!");
					System.out.println("Bitte erneut eingeben.");
					wiederholen = true;
				}
				// sichergehen, dass schiff nicht mit anderem überlappt
				else if (schiffUeberlappt(richtung, reihe, spalte, spielfeld, laenge)) {
					System.out.println();
					System.out.println("Schiff kann nicht an Position gesetzt werden, wo bereits ein Schiff liegt!");
					System.out.println("Bitte erneut eingeben.");
					wiederholen = true;
				} else {
					// schiff muss nicht erneut eingegeben werden
					wiederholen = false;
					// spielfeld[][] aktuelles schiff hinzufügen
					schiffHinzufuegen(spielfeld, richtung, reihe, spalte, laenge);

					// nach jeder eingabe spielfeld aktualisieren
					spielfeldAusgeben(spielfeld);
				}
			} while (wiederholen == true);
		}

	}

	// spielfeld neue schiffe hinzufügen
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

	// testen, ob feld frei
	private static boolean schiffUeberlappt(char richtung, char reihe, char spalte, char[][] spielfeld, int laenge) {
		int posX = spalte - 'A';
		int posY = reihe - '0';
		
		boolean ergebnis = false;
		
		if (richtung == 'H') {
			// potentielle positionen durchgehen
			for (int i = 0; i < laenge; i++) {
				// testen, ob bereits dort ein schiff gesetzt wurde
				ergebnis = (spielfeld[posX][posY] != '.') ? true : ergebnis;
				posX++;
			}
		} else if (richtung == 'V') {
			// potentielle positionen durchgehen
			for (int i = 0; i < laenge; i++) {
				// testen ob bereits dort ein schiff gesetzt wurde
				ergebnis = (spielfeld[posX][posY] != '.') ? true : ergebnis;
				posY++;
			}
		}
		return ergebnis;
	}
	
	// testen ob schiff außerhalb des spielfeld ist
	private static boolean schiffAußerhalb(char richtung, char reihe, char spalte, char[][] spielfeld, int laenge) {
		int posX = spalte - 'A';
		int posY = reihe - '0';

		boolean ergebnis = false;
		
		if (richtung == 'H') {
			// potentielle positionen durchgehen bis auf schiffanfang
			for (int i = 1; i < laenge; i++) {
				posX++;
				// testen ob außerhalb des bereichs
				ergebnis = (posX >= spielfeld.length) ? true : ergebnis;
				if (ergebnis) {
				}
			}
		} else if (richtung == 'V') {
			// potentielle positionen durchgehen bis auf schiffanfang
			for (int i = 1; i < laenge; i++) {
				posY++;
				// testen ob außerhalb des bereichs
				ergebnis = (posY >= spielfeld[0].length) ? true : ergebnis;
			}
		}
		return ergebnis;
	}
}
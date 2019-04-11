import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SchiffeVersenken {
	
	
	
	public static void main(String[] args) throws IOException {
		//VARIABLEN
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		char[][] spielfeld = initSpielfeld();
		
		ausgabeSpielfeld(spielfeld);
		
		// Aufgabe 3
		System.out.println();
		
		System.out.print("In welcher Richtung soll das 1.Schiff (Länge = 2) platziert werden? (H)orizontal/(V)ertikal: ");
		String eingabe = reader.readLine();
		final char richtung = eingabe.charAt(0);
		
		System.out.print("In welcher Reihe soll der Anfang des 1.Schiffs (Länge = 2) platziert werden? (0-9): ");
		eingabe = reader.readLine();
		final char reihe = eingabe.charAt(0);
		
		System.out.print("In welcher Spalte soll der Anfang des 1. Schiffs (Länge = 2) platziert werden? (A-J): ");
		eingabe = reader.readLine();
		final char spalte = eingabe.charAt(0);
		
		
		System.out.println();
		
		//überprüfen
		// richtung überprüfen
		if ((richtung != 'H') && (richtung != 'V')) {
			System.out.println("Fehler bei der Richtung!");
		}
		if (!((0 <= Character.getNumericValue(reihe)) && (Character.getNumericValue(reihe) < 10))) {
			System.out.println("Fehler bei der Reihe!");
		}
		
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
	
	//spielfeld in der konsole ausgaben
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

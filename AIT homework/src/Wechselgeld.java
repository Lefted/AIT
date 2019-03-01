import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Wechselgeld {

	// VARIABLEN
	public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	// arrays
	public static int[] wert = { 5, 10, 20, 50, 100, 200 };
	public static int[] wechselgeld = { 5, 5, 5, 5, 5, 5 };

	// zahlen
	public static int preis = 5;
	public static int eingabe;
	public static int rückgeld;

	public static void main(String[] args) throws NumberFormatException, IOException {
		initData();
		ausgebenRückgeld();
	}

	// einlesen/berechnen/initialisieren der daten
	public static void initData() throws NumberFormatException, IOException {
		System.out.print("Bitte geben Sie des Preis des gewählten Artikels (in Euro) ein: ");
		preis = (int) (Double.parseDouble(reader.readLine()) * 100);
		System.out.print("Bitte geben Sie den Wert des eingeworfenen Geldes (in Euro) ein: ");
		eingabe = (int) (Double.parseDouble(reader.readLine()) * 100);
		System.out.println();

		if ((eingabe - preis) > 0) {
			rückgeld = eingabe - preis;
		} else {
			System.out.println("Sie haben zu wenig Geld eingeworfen ._.");
		}
	}

	// ausgabe des rückgelds als münzen
	public static void ausgebenRückgeld() {

		for (int i = wert.length - 1; i >= 0; i--) {
			final int anzahlMünzen = rückgeld / wert[i];
			if (wechselgeld[i] > 0) {
				for (int j = 0; j < anzahlMünzen; j++) {
					System.out.println((wert[i] / 100D) + " Euro");
					rückgeld -= wert[i];
				}
			}
		}
	}

}

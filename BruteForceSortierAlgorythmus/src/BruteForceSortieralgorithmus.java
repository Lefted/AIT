import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class BruteForceSortieralgorithmus {

	/*
	 * zahlen werden von kleinster zu größter sortiert
	 */

	private static long millisekundenStart = 0;
	private static long millisekundenEnde = 0;
	
	public static void main(String[] args) {
		// VARIABLEN
		int[] zahlen = arrayErstellen();
		printIstRichtigeReihenfolge(zahlen);

		millisekundenStart = System.currentTimeMillis();
		while (!istRichtigeReihenfolge(zahlen)) {
			mischen(zahlen);
			printIstRichtigeReihenfolge(zahlen);
			
		}
	}

	private static int[] arrayErstellen() {
		int[] array = null;
		String eingabe = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Bitte geben Sie die Anzahl der Elemente ein: ");
			eingabe = reader.readLine();
			final int anzahlElemente = Integer.parseInt(eingabe);
			array = new int[anzahlElemente];
			
			for (int i = 0; i < array.length; i++) {
				System.out.print("Bitte geben Sie den " + (i+1) + ". Wert ein: ");
				eingabe = reader.readLine();
				final int wert = Integer.parseInt(eingabe);
				array[i] = wert;
			}
			System.out.println();
		} catch (NumberFormatException | IOException e) {
			if (eingabe.equalsIgnoreCase("default") || eingabe.equalsIgnoreCase("d")) {
				final int defaultInt[] = {1, 3, 12, 9, 420, 69, 31, 666};
				return defaultInt;
			}
			if (eingabe.equalsIgnoreCase("d10")) {
				final int defaultInt[] = {1, 3, 12, 9, 420, 69, 31, 666, 187, 42};
				return defaultInt;
			}
			if (eingabe.equalsIgnoreCase("d9")) {
				final int defaultInt[] = {1, 3, 12, 9, 420, 69, 31, 666, 31};
				return defaultInt;
			}
			System.out.println("Bitte gehen Sie gültige Werte ein!");
		}
		return array;
	}
	
	// in der konsole ausgeben ob zahlen in richtiger reihenfolge sind
	private static void printIstRichtigeReihenfolge(int[] array) {
		if (istRichtigeReihenfolge(array)) {
			System.out.println("Die Zahlen sind in der richtigen Reihenfolge:");
			printArray(array);
			printDeltaTime();
		} else {
			System.out.println("Die Zahlen sind in der falschen Reiehenfolge.");
			printArray(array);
		}
	}
	
	private static void printDeltaTime() {
		millisekundenEnde = System.currentTimeMillis();
		final long deltaMillisekunden = millisekundenEnde - millisekundenStart;
		final double sekunden = deltaMillisekunden / 1000D;
		final int minuten = (int) (deltaMillisekunden / (1000 * 60));
		final int stunden = (int) (deltaMillisekunden / (1000 * 60 * 60));
		System.err.println("Vergangene Zeit: " + stunden + ":" + minuten + ":" + sekunden);
	}

	private static void printArray(int[] array) {
		for (int element : array) {
			System.out.print(element + " ");
		}
		System.out.println();
		System.out.println();
	}

	// auf richtige reihenfolge überprüfen
	private static boolean istRichtigeReihenfolge(int[] array) {
		// boolean ob zahlen getauscht werden mussten
		boolean getauscht = false;

		for (int i = 0; i < array.length; i++) {
			int erstesElement = i;
			int zweitesElement = i + 1;

			if (erstesElement != (array.length - 1)) {
				if (array[erstesElement] > array[zweitesElement]) {
					// tauschen

//					final int temp = array[erstesElement];
//					array[erstesElement] = zweitesElement;
//					array[zweitesElement] = temp;

					getauscht = true;
				}
			}
		}
		return !getauscht;
	}
	
	// Implementing Fisher–Yates shuffle
	static void mischen(int[] array) {
		// If running on Java 6 or older, use `new Random()` on RHS here
		Random rnd = ThreadLocalRandom.current();
		for (int i = array.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			int a = array[index];
			array[index] = array[i];
			array[i] = a;
		}
	}
}


public class Wetterstation {

	public static void main(String[] args) {

		int[] temp = { 12, 14, 9, 12, 15, 16, 15, 15, 11, 8, 12, 13, 15, 12 };
		double summe = 0;
		double durchschnitt;

		for (int i : temp) {
			System.out.println(i + " C°");
			summe += i;
		}
		System.out.println();

		durchschnitt = summe / temp.length;
		double gerunded = Math.round(durchschnitt * 100.0) / 100.0;
		System.out.println("Der Durchschnitt beträgt ca. " + gerunded + " C°.");

		int max = temp[0];
		int min = temp[0];

		for (int element : temp) {
			if (element > max) {
				max = element;
			}
			if (element < min) {
				min = element;
			}
		}

		System.out.println("Die höchste gemessene Temperatur ist: " + max);
		System.out.println("Die niedrigste gemessene Temperatur ist: " + min);
		System.out.println();

		@SuppressWarnings("unused")
		int indexTag = 0;
		int[] diff = new int[temp.length - 1];

		// Differenzen berechnen
		for (int i = 0; i < diff.length; i++) {
			if (temp[i] > temp[i + 1]) {
				diff[i] = temp[i] - temp[i + 1];
			} else {
				diff[i] = temp[i + 1] - temp[i];
			}
		}
		
		int maxDifferenz = diff[0];
		int maxDifferenzIndex = 0;
		for (int i = 0; i < diff.length; i++) {
			if (diff[i] > maxDifferenz) {
				maxDifferenz = diff[i];
				maxDifferenzIndex = i;
			}
		}

		System.out.println("Die größte Temperaturdifferenz beträgt: " + maxDifferenz);
		System.out.println("Sie findet bei den Tagen " + maxDifferenzIndex + "-" + (maxDifferenzIndex + 1) + " statt.");

	}
}

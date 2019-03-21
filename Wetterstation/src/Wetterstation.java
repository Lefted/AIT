
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
		System.out.println("Der Durchschnitt beträgt " + gerunded + " C°.");

		int max = temp[0];
		int min = temp[0];

		for (int i = 0; i < temp.length; i++) {
			if (temp[i] > max) {
				max = temp[i];
			}
			if (temp[i] < min) {
				min = temp[i];
			}
		}

		System.out.println(max);
		System.out.println(min);

		System.out.println();

		int indexTag = 0;
		int differenz = 0;
		for (int i = 0; i < temp.length; i++) {

			if (i > 0) {

				int heute = temp[i];
				int gestern = temp[i - 1];
				int tempDifferenz = 0;

				if (heute > gestern) {
					tempDifferenz = heute - gestern;
				}
				if (gestern > heute) {
					tempDifferenz = gestern - heute;
				}

				if (tempDifferenz > differenz) {
					differenz = tempDifferenz;
					indexTag = i;
				}
			}
		}

		System.out.println("Differenz: " + differenz + "C°");
		System.out.println("Tag " + indexTag + "-" + (indexTag + 1));

	}
}


public class KleinesEinMalEins {

	public static void main(String[] args) {
		kleinesEinMalEins(8, 13);
	}

	public static void kleinesEinMalEins(int maxFaktor1, int maxFaktor2) {

		for (int multiplikator = 1; multiplikator <= maxFaktor1; multiplikator++) {

			for (int multiplikant = 1; multiplikant <= maxFaktor2; multiplikant++) {
				int produkt = multiplikant * multiplikator;
//				System.out.print(produkt + " ");
				System.out.printf("%5d", produkt);

			}
			System.out.println();
		}
	}

}

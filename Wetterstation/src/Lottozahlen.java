
public class Lottozahlen {

	static int[] zahlen = new int[5];
	static int[] wahrscheinlichkeit = new int[50];

	public static void main(String[] args) {

		for (int i = 0; i < 10000; i++) {
			generate();

			for (int j = 0; j < zahlen.length; j++) {
				wahrscheinlichkeit[zahlen[j]] += 1;
			}

		}
		
		for (int i = 0; i < wahrscheinlichkeit.length; i++) {
			System.out.print(wahrscheinlichkeit[i]);
			System.out.print("    " + i);
			System.out.println();
		}

	}

	private static void generate() {
		for (int i = 0; i < zahlen.length; i++) {
			int random = (int) (Math.random() * 49 + 1);

			for (int j = 0; j < zahlen.length; j++) {
				if (zahlen[j] == random) {
					generate();
					return;
				}
			}
			zahlen[i] = random;
		}
	}

	@SuppressWarnings("unused")
	private static void output() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < zahlen.length; i++) {
			buffer.append(" " + zahlen[i]);
			if (i != (zahlen.length - 1)) {
				buffer.append(",");
			}
		}
		System.out.println("Ziehung:" + buffer.toString());
	}
}


public class Lottozahlen {

	static int[] zahlen = new int[5];
	static int[] statistik = new int[50];

	public static void main(String[] args) {

		for (int i = 0; i < 10000; i++) {
//			generate();

			for (int k = 0; k < zahlen.length; i++) {
				do {
					int random = (int) (Math.random() * 49 + 1);
					zahlen[k] = random;
					System.out.println("Loop");
				} while (zahlDoppelt(zahlen[k]));

			}

			output();

			for (int j = 0; j < zahlen.length; j++) {
				statistik[zahlen[j]] += 1;
			}

		}
		System.out.println();

		for (int i = 0; i < statistik.length; i++) {
			System.out.printf("%2d", i);
			System.out.printf("%8d", statistik[i]);
			System.out.println();
		}

	}

	private static boolean zahlDoppelt(int testZahl) {
		for (int i = 0; i < testZahl; i++) {
			if (zahlen[i] == testZahl) {
				return true;
			}
		}
		return false;

	}

//	private static void generate() {
//		for (int i = 0; i < zahlen.length; i++) {
//			int random = (int) (Math.random() * 49 + 1);
//
//			for (int j = 0; j < zahlen.length; j++) {
//				if (zahlen[j] == random) {
//					generate();
//					return;
//				}
//			}
//			zahlen[i] = random;
//		}
//	}

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

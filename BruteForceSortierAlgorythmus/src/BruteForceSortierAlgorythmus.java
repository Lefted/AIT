public class BruteForceSortierAlgorythmus {

	public static void main(String[] args) {
		// VARIABLEN
		int[] zahlen = { 1, 3, 9, 12 };

		System.out.println(richtigeReihenfolge(zahlen));
	}

	// auf richtige reihenfolge überprüfen
	private static boolean richtigeReihenfolge(int[] array) {
		boolean getauscht = false;

		for (int i = 0; i < array.length; i++) {
			int erstesElement = i;
			int zweitesElement = i + 1;

			if (erstesElement != array.length - 1) {
				if (array[erstesElement] > array[zweitesElement]) {
					// tauschen
					
					final int temp = array[erstesElement];
					array[erstesElement] = zweitesElement;
					array[zweitesElement] = temp;
					
					getauscht = true;				
				}
			}
			
		}
		return !getauscht;
	}
}

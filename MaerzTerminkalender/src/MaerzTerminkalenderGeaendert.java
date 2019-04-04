
public class MaerzTerminkalenderGeaendert {

	public static void main(String[] args) {
		//VARIABLES
		String[][] mearzKalender = new String[31][24];

		//alle termine von anfang an auf 'nichts' festlegen
		for (int i = 0; i < mearzKalender.length; i++) {
			for (int j = 0; j < mearzKalender[i].length; j++) {
				mearzKalender[i][j] = "nichts";
			}
		}

		//einige termine festlegen
		mearzKalender[0][5] = "Aufstehen";
		mearzKalender[0][12] = "Mittagessen";
		mearzKalender[30][23] = "Gute Nacht";

		//alle termine im märz für jede stunde ausgeben
		for (int i = 0; i < mearzKalender.length; i++) {
			for (int j = 0; j < mearzKalender[i].length; j++) {
				System.out.println((i + 1) + ". März, " + j + " Uhr: " + mearzKalender[i][j]);
			}
		}

		//termine wieder löschen gegen geheimdienst
		for (int i = 0; i < mearzKalender.length; i++) {
			for (int j = 0; j < mearzKalender[i].length; j++) {
				if (!mearzKalender[i][j].equalsIgnoreCase("nichts")) {
					mearzKalender[i][j] = "nichts";
				}
			}
		}
	}
}

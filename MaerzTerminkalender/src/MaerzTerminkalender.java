
public class MaerzTerminkalender {

	public static void main(String[] args) {

		String[][] mearzKalender = new String[31][24];

		for (int i = 0; i < mearzKalender.length; i++) {
			for (int j = 0; j < mearzKalender[i].length; j++) {
				mearzKalender[i][j] = "nichts";
			}
		}
		
		mearzKalender[0][5] = "Aufstehen";
		mearzKalender[0][12] = "Mittagessen";
		mearzKalender[30][23] = "Gute Nacht";
		
		for (int i = 0; i < mearzKalender[0].length; i++) {
			System.out.println("1. März, " + i + " Uhr: " + mearzKalender[0][i]);
		}
	}
}

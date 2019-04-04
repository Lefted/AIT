
public class Kleines1x1 {

	public static void main(String[] args) {
		
		int[][] einMaleins = new int[10][10];
		
		//initialisieren
		for (int i = 0; i < einMaleins.length; i++) {
			for (int j = 0; j < einMaleins[i].length; j++) {
				einMaleins[i][j] = (i + 1) * (j + 1);
			}
		}
		
		//ausgabe
		for (int i = 0; i < einMaleins.length; i++) {
			for (int j = 0; j < einMaleins[i].length; j++) {
				 System.out.printf("%4d", einMaleins[i][j]);
			}
			System.out.println();
		} 
	}
}

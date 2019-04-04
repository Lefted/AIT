import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuaderVolumenberechnung {

	public static void main(String[] args) throws NumberFormatException, IOException {
		//VARIABLEN
		int[][][] volumina = new int[10][15][20];
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < volumina.length; i++) {
			for (int j = 0; j < volumina[i].length; j++) {
				for (int k = 0; k < volumina[i][j].length; k++) {
					volumina[i][j][k] = (i+1)*(j+1)*(k+1);
				}
			}
			
		}
		
		System.out.println("Quader-Volumenberechnung:");
		System.out.print("Bitte geben Sie die ganzzahlige Seitenlänge a (1 bis 10) (in Metern) ein: ");
		final int a = Integer.parseInt(reader.readLine());
		System.out.print("Bitte geben Sie die ganzzahlige Seitenlänge b (1 bis 15) (in Metern) ein: ");
		final int b = Integer.parseInt(reader.readLine());
		System.out.print("Bitte geben Sie die ganzzahlige Seitenlänge c (1 bis 20) (in Metern) ein: ");
		final int c = Integer.parseInt(reader.readLine());
		//ausgabe des volumen
		System.out.println("Das Volumen des Quaders beträgt " + volumina[a - 1][b - 1][c - 1] + " Kubikmeter.");
	}
}

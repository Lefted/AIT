package me.moritz.gameoflife;
import java.awt.Color;
import java.awt.EventQueue;

import my.dzeik.life.GameOfLifeLib;

@SuppressWarnings("serial")
public class GameOfLife extends GameOfLifeLib {

	/*
	 * ein Unsichtbares Array daten existiert bereits boolean[][] daten = new
	 * boolean[ZELLEN_X][ZELLEN_Y]
	 * 
	 * in den Variablen ZELLEN_X bzw. ZELLEN_Y stehen jeweils die Anzahl der x und y
	 * Zellen
	 */

	/**
	 * Launch the application.
	 */

	boolean[][] naechsteDaten = new boolean[ZELLEN_X][ZELLEN_Y];
	public static GameOfLife frame;
	public static int generation = 0;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					frame = new GameOfLife();
					frame.setLocationRelativeTo(null);
					frame.getContentPane().setBackground(Color.LIGHT_GRAY);
					frame.setVisible(true);
					frame.setResizable(false);
					frame.autoDelay = 1;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void step() {
		erneuereFeld();
		aktualisiereFeld();
		generation = (generation < Integer.MAX_VALUE) ? generation += 1 : generation;
		frame.getGenerationsLabel().setText("Generation: " + generation);
		frame.getContentPane().revalidate();
	}

	public void erneuereFeld() {
		for (int y = 0; y < ZELLEN_Y; y++) {
			for (int x = 0; x < ZELLEN_X; x++) {
				
				final int NACHBARN = zaehleLebendeNachbarn(x, y);
				
				if (daten[x][y] == true) {
					if ((NACHBARN == 2) || (NACHBARN == 3)) {
						naechsteDaten[x][y] = true;
					} else {
						naechsteDaten[x][y] = false;
					}
				} else {
					if (NACHBARN == 3) {
						naechsteDaten[x][y] = true;
					} else {
						naechsteDaten[x][y] = false;
					}
				}
			}
		}
	}

	public int zaehleLebendeNachbarn(int posX, int posY) {
		int nachbarn = 0;
		
		for (int y = posY - 1; y <= (posY + 1); y++) {
			for (int x = posX - 1; x <= (posX + 1); x++) {

				int zugriffX = x;
				int zugriffY = y;
				
				if (zugriffX < 0) {
					zugriffX = ZELLEN_X -1;
				}
				if (zugriffY < 0) {
					zugriffY = ZELLEN_Y -1;
				}
				if (zugriffX > (ZELLEN_X -1)) {
					zugriffX = 0;
				}
				if (zugriffY > (ZELLEN_Y -1)) {
					zugriffY = 0;
				}
				
				if (daten[zugriffX][zugriffY] == true) {
					nachbarn += 1;
				}
			}
		}

		if (daten[posX][posY] == true) {
			nachbarn -= 1;
		}
		return nachbarn;
	}

	public void aktualisiereFeld() {
		for (int i = 0; i < ZELLEN_Y; i++) {
			for (int j = 0; j < ZELLEN_X; j++) {
				daten[j][i] = naechsteDaten[j][i];
			}
		}
	}
}

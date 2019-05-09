import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SchiffeVersenken {

	// main method
	public static void main(String[] args) throws IOException {
		// VARIABLEN
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// spielfeld[x][y]
		char[][] spielfeld = spielfeldInitalisieren();

		// spielmodus auswahlscreen ausgeben
		spielmodiWahlAusgeben();
		// spielmodus wählen
		final boolean multiplayer = spielmodusEinlesen(reader);

		if (multiplayer == false) {
			manageSingleplayer(reader, spielfeld);
		} else {
			manageMultiplayer(reader, spielfeld);
		}

		// aufgabe 3
		// 5x : einlesen, überprüfen, schiff hinzufügen, feld ausgeben
		//		schiffeEinlesen(spielfeld, reader);
	}

	// code für den singleplayer
	private static void manageSingleplayer(BufferedReader reader, char[][] spielfeld) throws IOException {
		clear();
		schiffeEinlesen(spielfeld, reader);

	}

	// code für den multipalyer
	private static void manageMultiplayer(BufferedReader reader, char[][] spielfeld) throws IOException {

		final String ip = "";
		final int port = 1201;
		final boolean istServer = netzwerktypEinlesen(reader);

		ServerSocket serverSocket = null;
		Socket socket;
		
		if (istServer) {
			System.out.println("Versuche Server zu starten");
			serverSocket = new ServerSocket(port);
			socket = serverSocket.accept();
			System.out.println("Server gestartet!");
		} else {
			socket = verbindeZuServer(reader, ip, port);
		}
		

		DataInputStream dataIn = new DataInputStream(socket.getInputStream());
		DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());

		String msgIn = "";
		String msgOut = "";

		// TESTS
		if (istServer) {
			while (msgIn != "end") {
				msgIn = dataIn.readUTF();
				System.out.println(msgIn);
				
				msgOut = reader.readLine();
				dataOut.writeUTF(msgOut);
				dataOut.flush();
			}
			socket.close();
		}
		
		if (!istServer) {
			while (msgIn != "end") {
				msgOut = reader.readLine();
				dataOut.writeUTF(msgOut);
//				dataOut.flush();
				
				msgIn = dataIn.readUTF();
				System.out.println(msgIn);  //print server msg
			}
		}
		
	}

	// verbinde den client zum server
	private static Socket verbindeZuServer(BufferedReader reader, String ip, int port) {
		boolean fehler = false;
		Socket socket = new Socket();
		do {
			try {
				// verbindungsfehler
				if (fehler) {
					System.out.println("Konnte nicht zum Server verbinden, bitte überprüfen Sie die IP!");
				}
				
				System.out.print("Zu welcher IP soll verbunden werden? (Bsp. 127.0.0.1): ");
				final String eingabe = reader.readLine();
				
				socket = new Socket(eingabe, port);
			} catch (Exception e) {
				fehler = true;
			}
		} while (fehler);
		return socket;
	}
	
	// einstellungen für den server/client festlegen
	private static boolean netzwerktypEinlesen(BufferedReader reader) throws IOException {
		int fehlerCode = 0;
		boolean server = false;
		do {
			// falls fehler auftreten diese ausgeben
			if (fehlerCode == 1) {
				System.out.println("Bitte tätigen Sie eine gültige Eingabe (S/C)!");
			}

			System.out.print("Möchtest du Server oder Client sein? (S/C): ");
			String eingabe = reader.readLine();
			if (Character.toUpperCase(eingabe.charAt(0)) == 'S') {
				server = true;
				fehlerCode = 0;
			} else if (Character.toUpperCase(eingabe.charAt(0)) == 'C') {
				server = false;
				fehlerCode = 0;
			} else {
				fehlerCode = 1;
			}

		} while (fehlerCode != 0);
		return server;
	}

	// konsole clearen (mit \n vollspamen)
	private static void clear() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 50; i++) {
			buffer.append("\n");
		}
		System.out.println(buffer);
	}

	// spielfeld mit '.' initialisieren
	private static char[][] spielfeldInitalisieren() {
		char[][] spielfeld = new char[10][10];

		for (int i = 0; i < spielfeld.length; i++) {
			for (int j = 0; j < spielfeld[0].length; j++) {
				spielfeld[i][j] = '.';
			}
		}
		return spielfeld;
	}

	// nutzer gibt an welcher spielmodus gespielt wird
	private static boolean spielmodusEinlesen(BufferedReader reader) {
		boolean multiplayer = false;
		boolean fehler = false;
		do {
			if (fehler) {
				System.out.println("Bitte tätigen Sie eine gültige Eingabe (S/M)!");
			}
			System.out.print("Bitte wählen Sie einen Spielmodus: ");
			try {
				String eingabe = reader.readLine();
				if (Character.toUpperCase(eingabe.charAt(0)) == 'M') {
					multiplayer = true;
					fehler = false;
				} else if (Character.toUpperCase(eingabe.charAt(0)) == 'S') {
					multiplayer = false;
					fehler = false;
				} else {
					fehler = true;
				}
			} catch (IOException e) {
				fehler = true;
			}

		} while (fehler);
		return multiplayer;
	}

	// spielmodi in der konsole ausgeben
	private static void spielmodiWahlAusgeben() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 49; j++) {

				// erste linie
				if (i == 0) {
					switch (j) {
					case 0:
						buffer.append("╔");
						break;
					case 48:
						buffer.append("╗");
						break;
					default:
						buffer.append("═");
						break;
					}
				}
				// links und rechts
				if ((i > 0) && (i < 18)) {
					if ((j == 15) && (i == 3)) {
						buffer.append("sᴄʜɪғғᴇ ᴠᴇʀsᴇɴᴋᴇɴ");
					} else if ((j == 15) && (i == 7)) {
						buffer.append("sɪɴɢʟᴇᴘʟᴀʏᴇʀ  (s)");
					} else if ((j == 15) && (i == 10)) {
						buffer.append("ᴍᴜʟᴛɪᴘʟᴀʏᴇʀ   (ᴍ)");
					} else if ((j == 39) && (i == 17)) {
						buffer.append("von Moritz");
					}

					else if (j == 0) {
						buffer.append("║");
					} else if (j == 48) {
						buffer.append("║");
					} else if (i == 3) {
						if (((j > 0) && (j < 13)) || (j < 32)) {
							buffer.append(" ");
						}
					} else if (i == 7) {
						if (((j > 0) && (j < 15)) || (j < 32)) {
							buffer.append(" ");
						}
					} else if (i == 10) {
						if (((j > 0) && (j < 15)) || (j < 32)) {
							buffer.append(" ");
						}
					} else if (i == 17) {
						if (j < 38) {
							buffer.append(" ");
						}
					} else {
						buffer.append(" ");
					}
				}
				// unten
				if (i == 18) {
					switch (j) {
					case 0:
						buffer.append("╚");
						break;
					case 48:
						buffer.append("╝");
						break;
					default:
						buffer.append("═");
						break;
					}
				}

			}
			buffer.append("\n");
		}

		System.out.println(buffer);
	}

	// spielfeld in der konsole ausgeben
	private static void spielfeldAusgeben(char[][] spielfeld) {
		System.out.println("  A B C D E F G H I J");

		for (int i = 0; i < spielfeld.length; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < spielfeld[0].length; j++) {

				System.out.print(spielfeld[j][i] + " ");
			}
			System.out.println();
		}
	}

	// aufgabe 3
	// spieler schiffer platzieren lassen
	private static void schiffeEinlesen(char[][] spielfeld, BufferedReader reader) throws IOException {
		// falls die eingabe ungültig war soll sie wiederholt werden
		boolean wiederholen = false;

		// länge des schiffe setzen
		final int[] laengen = { 2, 3, 3, 4, 5 };

		spielfeldAusgeben(spielfeld);

		// für alle 5 schiffe
		for (int i = 0; i < 5; i++) {
			// laenge des aktuellen schiffs festlegen
			final int laenge = laengen[i];

			do {
				// FORMAT
				System.out.println();

				// RICHTUNG
				System.out.print(
						"In welcher Richtung soll das " + (i + 1) + ".Schiff (Länge = " + laenge + ") platziert werden? (H)orizontal/(V)ertikal: ");
				String eingabe = reader.readLine();
				// in großbuchstaben umwandeln
				char richtung = Character.toUpperCase(eingabe.charAt(0));

				// validen wert für richtung erzwingen
				while (!richtigeRichtung(richtung)) {
					System.out.println("\"" + eingabe + "\"" + " ist keine gültige Eingabe für Richtung!");
					System.out.print("Bitte erneut eingeben (H/V): ");
					eingabe = reader.readLine();
					richtung = Character.toUpperCase(eingabe.charAt(0));
				}

				// REIHE
				System.out.print("In welcher Reihe soll der Anfang des " + (i + 1) + ".Schiffs (Länge = " + laenge + ") platziert werden? (0-9): ");
				eingabe = reader.readLine();
				// in großbuchstaben umwandeln
				char reihe = Character.toUpperCase(eingabe.charAt(0));

				// validen wert für reihe erzwingen
				while (!richtigeReihe(reihe)) {
					System.out.println("\"" + eingabe + "\"" + " ist keine gültige Eingabe für Reihe!");
					System.out.print("Bitte erneut eingeben (0-9): ");
					eingabe = reader.readLine();
					reihe = Character.toUpperCase(eingabe.charAt(0));
				}

				// SPALTE
				System.out.print("In welcher Spalte soll der Anfang des " + (i + 1) + ". Schiffs (Länge = " + laenge + ") platziert werden? (A-J): ");
				eingabe = reader.readLine();
				// in großbuchstaben umwandeln
				char spalte = Character.toUpperCase(eingabe.charAt(0));

				// validen wert für spalte erzwingen
				while (!richtigeSpalte(spalte)) {
					System.out.println("\"" + eingabe + "\"" + " ist keine gültige Eingabe für Spalte!");
					System.out.print("Bitte erneut eingeben (A-J): ");
					eingabe = reader.readLine();
					spalte = Character.toUpperCase(eingabe.charAt(0));
				}

				final int schiffBerührtIndex = schiffBeruehrt(richtung, reihe, spalte, spielfeld, laenge);

				// sichergehen, dass schiff nicht außerhalb des spielfelds liegt
				if (schiffAußerhalb(richtung, reihe, spalte, spielfeld, laenge)) {
					System.out.println();
					System.out.println("Schiff liegt außerhalb des Spielfelds!");
					System.out.println("Bitte erneut eingeben.");
					wiederholen = true;
				}
				// sichergehen, dass schiff nicht mit anderem überlappt
				else if (schiffUeberlappt(richtung, reihe, spalte, spielfeld, laenge)) {
					System.out.println();
					System.out.println("Schiff kann nicht an Position gesetzt werden, wo bereits ein Schiff liegt!");
					System.out.println("Bitte erneut eingeben.");
					wiederholen = true;
				} else if (schiffBerührtIndex != 0) {
					System.out.println();
					switch (schiffBerührtIndex) {
					case 1:
						System.out.println("Schiff kann nicht mit anderem Schiff über Ecke gesetzt!");
						break;
					case 2:
						System.out.println("Schiff berührt anderes Schiff!");
						break;
					}
					wiederholen = true;
				} else {
					// schiff muss nicht erneut eingegeben werden
					wiederholen = false;
					// spielfeld[][] aktuelles schiff hinzufügen
					schiffHinzufuegen(spielfeld, richtung, reihe, spalte, laenge);

					// nach jeder eingabe spielfeld aktualisieren
					spielfeldAusgeben(spielfeld);
				}
			} while (wiederholen == true);
			// FORMAT
			System.out.println();
		}
	}

	// spielfeld neue schiffe hinzufügen
	private static void schiffHinzufuegen(char[][] spielfeld, char richtung, char reihe, char spalte, int laenge) {
		// spalte/reihe in koordinaten umwandeln
		int posX = spalte - 'A';
		int posY = reihe - '0';

		if (richtung == 'H') {
			for (int i = 0; i < laenge; i++) {
				spielfeld[posX][posY] = '*';
				posX++;
			}
		} else if (richtung == 'V') {
			for (int i = 0; i < laenge; i++) {
				spielfeld[posX][posY] = '*';
				posY++;
			}
		} else {
			// fehler verarbeiten
			System.out.println();
			System.err.println("Variable 'richtung' has an invalid value: " + richtung);
			System.out.println("Exiting program");
			System.exit(-1);
		}
	}

	// auf richtige spalte testen
	private static boolean richtigeSpalte(char spalte) {
		final int decWert = spalte;

		if (!(('A' <= decWert) && (decWert <= 'J'))) {
			return false;
		}
		return true;
	}

	// auf richtige richtung testen
	private static boolean richtigeRichtung(char richtung) {
		if ((richtung != 'H') && (richtung != 'V')) {
			return false;
		}
		return true;
	}

	// auf richtige reihe testen
	private static boolean richtigeReihe(char reihe) {
		if (!((0 <= Character.getNumericValue(reihe)) && (Character.getNumericValue(reihe) < 10))) {
			return false;
		}
		return true;
	}

	// testen ob schiff außerhalb des spielfeld ist
	private static boolean schiffAußerhalb(char richtung, char reihe, char spalte, char[][] spielfeld, int laenge) {
		int posX = spalte - 'A';
		int posY = reihe - '0';

		boolean ergebnis = false;

		if (richtung == 'H') {
			// potentielle positionen durchgehen bis auf schiffanfang
			for (int i = 1; i < laenge; i++) {
				posX++;
				// testen ob außerhalb des bereichs
				ergebnis = (posX >= spielfeld.length) ? true : ergebnis;
				if (ergebnis) {
				}
			}
		} else if (richtung == 'V') {
			// potentielle positionen durchgehen bis auf schiffanfang
			for (int i = 1; i < laenge; i++) {
				posY++;
				// testen ob außerhalb des bereichs
				ergebnis = (posY >= spielfeld[0].length) ? true : ergebnis;
			}
		}
		return ergebnis;
	}

	// testen, ob feld frei
	private static boolean schiffUeberlappt(char richtung, char reihe, char spalte, char[][] spielfeld, int laenge) {
		int posX = spalte - 'A';
		int posY = reihe - '0';

		boolean ergebnis = false;

		if (richtung == 'H') {
			// potentielle positionen durchgehen
			for (int i = 0; i < laenge; i++) {
				// testen, ob bereits dort ein schiff gesetzt wurde
				ergebnis = (spielfeld[posX][posY] != '.') ? true : ergebnis;
				posX++;
			}
		} else if (richtung == 'V') {
			// potentielle positionen durchgehen
			for (int i = 0; i < laenge; i++) {
				// testen ob bereits dort ein schiff gesetzt wurde
				ergebnis = (spielfeld[posX][posY] != '.') ? true : ergebnis;
				posY++;
			}
		}
		return ergebnis;
	}

	// testen, ob schiff über eck oder an anderes schiff gesetzt wurde
	private static int schiffBeruehrt(char richtung, char reihe, char spalte, char[][] spielfeld, int laenge) {
		System.out.println("Aufgerufen!");
		// keine berühungen
		int ergebnis = 0;

		int posX = spalte - 'A';
		int posY = reihe - '0';

		if (richtung == 'H') {
			// startpositionen verschieben
			posX--;
			posY--;
			int startPosX = posX;

			// potentielle positionen durchgehen außer es wurde bereits ein fehler gefunden
			for (int i = 0; (i < 3) && (ergebnis == 0); i++) {
				for (int j = 0; (j < (laenge + 2)) && (ergebnis == 0); j++) {
					// sichergehen, dass positionen im spielfeld sind
					if ((posX > 0) && (posX < spielfeld.length) && (posY > 0) && (posY < spielfeld.length)) {
						// auf überlappungen testen
						if (spielfeld[posX][posY] != '.') {
							// testen, ob überlappung bei ecke war
							if (((i == 0) && (j == 0)) || ((i == 0) && (j == (laenge + 1))) || ((i == 2) && (j == 0))
									|| ((i == 2) && (j == (laenge + 1)))) {
								// schiff ist über ecke platziert
								ergebnis = 1;
							} else {
								// schiff berührt anderes an kante
								ergebnis = 2;
							}
						}
						posX++;
					}
				}
				posY++;
				posX = startPosX;
			}
		} else if (richtung == 'V') {
			// startpositionen verschieben
			posX--;
			posY--;
			int startPosX = posX;

			// potentielle positionen durchgehen außer es wurde bereits ein fehler gefunden
			for (int i = 0; (i < (laenge + 2)) && (ergebnis == 0); i++) {
				for (int j = 0; (j < 3) && (ergebnis == 0); j++) {
					// sichergehen, dass positionen im spielfeld sind
					if ((posX > 0) && (posX < spielfeld.length) && (posY > 0) && (posY < spielfeld.length)) {
						// auf überlappung testen
						if (spielfeld[posX][posY] != '.') {
							// testen, ob überlappung bei ecke war
							if (((i == 0) && (j == 0)) || ((i == 0) && (j == 2)) || ((i == (laenge + 1)) && (j == 0))
									|| ((i == (laenge + 1)) && (j == 2))) {
								// schiff ist über ecke platziert
								ergebnis = 1;
							} else {
								// schiff berührt anderes an kante
								ergebnis = 2;
							}
						}
						posX++;
					}
				}
				posY++;
				posX = startPosX;
			}
		}
		return ergebnis;
	}
}
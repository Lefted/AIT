import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class SchiffeVersenken {

	// main
	public static void main(String[] args) throws IOException {
		// VARIABLEN
		// spielfeld[x][y]
		char[][] spielfeld = spielfeldInitalisieren();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		final boolean multiplayer;

		spielmodiWahlAusgeben();
		multiplayer = spielmodusEinlesen(reader);

		if (multiplayer == false) {
			singleplayer(reader, spielfeld);
		} else {
			multiplayer(reader, spielfeld);
		}
	}

	// code für den singleplayer-verlauf
	private static void singleplayer(BufferedReader reader, char[][] spielfeld) throws IOException {
		konsoleLeeren();
		schiffeEinlesen(spielfeld, reader);

	}

	// code für den multipalyer-verlauf
	private static void multiplayer(BufferedReader reader, char[][] spielfeld) throws IOException {
		// VARIABLEN
		final String ip = "";
		final int port = 1201;
		final boolean istServer = netzwerktypEinlesen(reader);

		ServerSocket serverSocket = null;
		Socket socket;
		DataInputStream dataIn;
		DataOutputStream dataOut;
		String msgIn = "";

		// variablen initialisieren
		{
			if (istServer == true) {
				serverSocket = new ServerSocket(port);
				System.out.println("Server auf IP-Addresse:" + InetAddress.getLocalHost().getHostAddress() + " gestartet!");
				System.out.println("Warte auf Client...");
				socket = serverSocket.accept();
				System.out.println("Client erfolgreich gefunden!");
			} else {
				socket = clientVerbinden(reader, ip, port);
				System.out.println("Verbindung erfolgreich hergestellt!");
			}
			dataIn = new DataInputStream(socket.getInputStream());
			dataOut = new DataOutputStream(socket.getOutputStream());
		}

		// spielverlauf
		try {
			//VARIABLEN
			boolean spielLaeuft = true;
			boolean instanzHatErstenZug = false;
			boolean weiter = false;
			final String infoPre = "<info> ";
			
			// bildschirm leeren
			System.out.println();
			System.out.println("Drücke {Enter}, um mit dem Platzieren deiner Schiff zu starten...");
			while (reader.ready()) {
				reader.skip(1);
			}
			reader.readLine();
			konsoleLeeren();
			
			// schiffe platzieren
			schiffeEinlesen(spielfeld, reader, dataOut);
			datenSenden("schiffIndex:alle", dataOut);

			// status ausgeben das wie vielte schiff der mitspieler setzt
			while (!msgIn.equalsIgnoreCase("schiffIndex:alle")) {
				if (dataIn.available() != 0) {
					msgIn = dataIn.readUTF();
					if (msgIn.contains("schiffIndex:")) {
						if (msgIn.equalsIgnoreCase("schiffIndex:alle")) {
							System.out.println(infoPre + "Dein Mitspieler hat alle Schiffe gesetzt.");
							if (!istServer) {
								System.out.println(infoPre + "Dein Mitspieler wählt aus, wer den ersten Zug machen darf.");
							}
						} else {
							int anzahlSchiffeMitspieler = Integer.parseInt(msgIn.replace("schiffIndex:", ""));
							System.out.println(infoPre + "Dein Mitspieler setzt gerade das " + anzahlSchiffeMitspieler + ". Schiff.");
						}
					}
				}
			}

			// festlegen wer den ersten zug hat
			if (istServer == true) {
				instanzHatErstenZug = ersterZug(istServer, reader, dataOut);
				if (instanzHatErstenZug == true) {
					datenSenden("ersterZug:false", dataOut);
				} else {
					datenSenden("ersterZug:true", dataOut);
				}
			} else {
				while (!msgIn.contains("ersterZug")) {
					if (dataIn.available() != 0) {
						msgIn = dataIn.readUTF();
						if (msgIn.equalsIgnoreCase("ersterZug:true")) {
							instanzHatErstenZug = true;
						} else if (msgIn.equalsIgnoreCase("ersterZug:false")) {
							instanzHatErstenZug = false;
						}
					}
				}
			}

			// schuss-loop
			while (spielLaeuft == true) {
				// TODO schießen
				if (instanzHatErstenZug) {

					//TODO kanone einlesen
					schießen(reader, dataOut);
//					System.out.println("TODO schießen");
//					System.out.println("Press {enter} to continue...");
//
					//eingaben aus buffer leeren
					while (reader.ready()) {
						reader.skip(1);
					}
					System.out.println();
					System.out.println("Zum Fortfahren {Enter} drücken.");
					reader.readLine();
					
					//TODO
					// vom anderen spieler schauen ob treffer war und schiff removen

					
					datenSenden("istFertig", dataOut);

					//warten bis anderer gezogen hat
					weiter = false;
					msgIn = "";

					System.out.println("Warten bis Mitspieler gezogen hat...");
					while (weiter == false) {
						if (dataIn.available() != 0) {
							msgIn = dataIn.readUTF();
							if (msgIn.equalsIgnoreCase("istFertig")) {
								weiter = true;
							}
						}
					}

				} else {
					// warten bis anderer spieler gezogen hat
					// weiter resetten damit erneut gewartet wird
					weiter = false;
					msgIn = "";

					System.out.println("Warten bis Mitspieler gezogen hat...");
					while (weiter == false) {
						if (dataIn.available() != 0) {
							msgIn = dataIn.readUTF();
							if (msgIn.equalsIgnoreCase("istFertig")) {
								weiter = true;
							}
							//TODO spieler sagen ob getroffen wurde
						}
					}
					//TODO selbst schießen
					schießen(reader, dataOut);
					
					while (reader.ready()) {
						reader.skip(1);
					}
					System.out.println();
					System.out.println("Zum Fortfahren {Enter} drücken.");
					reader.readLine();
					datenSenden("istFertig", dataOut);
				}
			}

		} catch (SocketException e) {
			System.out.flush();
			if (istServer == true) {
				System.err.println("Verbindung zum Client verloren!");
			} else {
				System.err.println("Verbindung zum Server verloren!");
			}
		}

	}

	// (multiplayer) schuss einlesen, überprüfen, und überprüfen ob getroffen wurde
	private static void schießen(BufferedReader reader, DataOutputStream dataOutput) throws IOException {
		konsoleLeeren();
		System.out.println("Hier wäre dann das Schießen!");
	}
	
	// (multiplayer) bestimmt wer den ersten zug macht, gibt zurück ob instanz den erster zug hat
	private static boolean ersterZug(boolean istServer, BufferedReader reader, DataOutputStream dataOutput) {
		boolean fehler = false;
		boolean instanzHatErstenZug = true;
		//FORMAT
		System.out.println();
		do {
			try {
				if (fehler) {
					System.out.flush();
					System.err.println("Bitte geben Sie einen gültigen Wert an!");
					System.err.flush();
				}
				System.out.print("Wer soll den ersten Zug machen? Client (C), Server (S), Zufall (Z):");
				
				String eingabe = reader.readLine();
				char c = Character.toUpperCase(eingabe.charAt(0));

				if (c == 'C') {
					instanzHatErstenZug = (istServer) ? false : true;
					fehler = false;
				} else if (c == 'S') {
					instanzHatErstenZug = (istServer) ? true : false;
					fehler = false;
				} else if (c == 'Z') {
					final int random = (int) (Math.random() * 2);
					instanzHatErstenZug = (random == 1) ? true : false;
					fehler = false;
				} else {
					fehler = true;
				}
			} catch (Exception e) {
				fehler = true;
			}
		} while (fehler);
		return instanzHatErstenZug;
	}

	// verbinde den client zum server
	private static Socket clientVerbinden(BufferedReader reader, String ip, int port) {
		boolean fehler = false;
		Socket socket = new Socket();
		String eingabe;
		// schleife
		do {
			// fehler abfangen
			try {
				if (fehler == true) {
					// verbindungsfehler dem nutzer mitteilen
					System.out.println("Konnte nicht zum Server verbinden, bitte überprüfen Sie die IP!");
				}

				System.out.print("Zu welcher IP soll verbunden werden? (Bsp. 127.0.0.1): ");
				eingabe = reader.readLine();
				// eingegebene ip ausprobieren
				socket = new Socket(eingabe, port);
				fehler = false;
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
	private static void konsoleLeeren() {
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

	// spielmodi auswahlbildschirm in der konsole ausgeben
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
						//						buffer.append("sᴄʜɪғғᴇ ᴠᴇʀsᴇɴᴋᴇɴ");
						buffer.append("SCHIFFE VERSENKEN");
					} else if ((j == 15) && (i == 7)) {
						//						buffer.append("sɪɴɢʟᴇᴘʟᴀʏᴇʀ  (s)");
						buffer.append("SINGLEPLAYER (S)");
					} else if ((j == 15) && (i == 10)) {
						//						buffer.append("ᴍᴜʟᴛɪᴘʟᴀʏᴇʀ   (ᴍ)");
						buffer.append("MULTIPLAYER (M)");
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
						if (((j > 0) && (j < 15)) || (j < 33)) {
							buffer.append(" ");
						}
					} else if (i == 10) {
						if (((j > 0) && (j < 15)) || (j < 34)) {
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

	// (multiplayer) spieler schiffe platzieren lassen; sendet aktuelles schiff als info
	private static void schiffeEinlesen(char[][] spielfeld, BufferedReader reader, DataOutputStream dataOutput) throws IOException {
		// falls die eingabe ungültig war soll sie wiederholt werden
		boolean wiederholen = false;
		int indexSchiff = 0;

		// länge des schiffe setzen
		final int[] laengen = { 2, 3, 3, 4, 5 };

		//FORMAT
		System.out.println();
		spielfeldAusgeben(spielfeld);

		// für alle 5 schiffe
		for (int i = 0; i < 5; i++) {
			indexSchiff = i + 1;
			datenSenden("schiffIndex:" + indexSchiff, dataOutput);

			// laenge des aktuellen schiffs festlegen
			final int laenge = laengen[i];

			do {
				// FORMAT
				System.out.println();

				// RICHTUNG
				System.out.print(
						"In welcher Richtung soll das " + (i + 1) + ".Schiff (Länge = " + laenge + ") platziert werden? (H)orizontal/(V)ertikal: ");
				String eingabe = reader.readLine();

				//DEVELOPER
				if (eingabe.equalsIgnoreCase("skip")) {
					spielfeld[0][0] = '*';
					spielfeld[1][0] = '*';

					spielfeld[0][2] = '*';
					spielfeld[1][2] = '*';
					spielfeld[2][2] = '*';

					spielfeld[0][4] = '*';
					spielfeld[1][4] = '*';
					spielfeld[2][4] = '*';

					spielfeld[0][6] = '*';
					spielfeld[1][6] = '*';
					spielfeld[2][6] = '*';
					spielfeld[3][6] = '*';

					spielfeld[0][8] = '*';
					spielfeld[1][8] = '*';
					spielfeld[2][8] = '*';
					spielfeld[3][8] = '*';
					spielfeld[4][8] = '*';
					konsoleLeeren();
					spielfeldAusgeben(spielfeld);
					return;
				}

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
					konsoleLeeren();
					spielfeldAusgeben(spielfeld);
				}
			} while (wiederholen == true);
			// FORMAT
			System.out.println();
		}
	}

	// (singleplayer) spieler schiffe platzieren lassen
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
					konsoleLeeren();
					spielfeldAusgeben(spielfeld);
				}
			} while (wiederholen == true);
			// FORMAT
			System.out.println();
		}
	}

	// multiplayer daten tauschen
	private static void datenSenden(String msg, DataOutputStream dataOut) throws IOException {
		dataOut.writeUTF(msg);
		dataOut.flush();
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
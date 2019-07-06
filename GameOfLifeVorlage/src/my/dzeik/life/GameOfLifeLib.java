package my.dzeik.life;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import me.moritz.gameoflife.GameOfLife;

@SuppressWarnings("serial")
public abstract class GameOfLifeLib extends JFrame {

	public int autoDelay = 500;
	private JPanel contentPane;
	private GameOfLiveSpielfläche dieSpielFläche;
	protected boolean[][] daten;
	@SuppressWarnings("rawtypes")
	private JComboBox cbInitArt;
	private JToggleButton tglAuto;
	private JButton btnStep;
	private Timer timer;
	protected final int ZELLEN_X = 40;
	protected final int ZELLEN_Y = 40;
	private JSpinner spTimerDelay;
	private JLabel generationsLabel;

	public JLabel getGenerationsLabel() {
		return generationsLabel;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GameOfLifeLib() {
		this.setTitle("Game of Life (AIT) decompiled");
		this.setSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(3);
		this.setBounds(100, 100, 574, 536);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		this.contentPane.setLayout((LayoutManager) null);
		this.dieSpielFläche = new GameOfLiveSpielfläche(40, 40);
		this.dieSpielFläche.setBounds(28, 40, 410, 409);
		this.contentPane.add(this.dieSpielFläche);

		this.generationsLabel = new JLabel("Generation: " + GameOfLife.generation);
		this.generationsLabel.setBounds(448, 385, 130, 20);
		this.contentPane.add(this.generationsLabel);

		JButton btnInit = new JButton("Initialisieren");
		btnInit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameOfLifeLib.this.initialize();
			}
		});
		btnInit.setBounds(448, 109, 104, 23);
		this.contentPane.add(btnInit);
		this.cbInitArt = new JComboBox();
		this.cbInitArt.setModel(new DefaultComboBoxModel(new String[] { "Zufall", "Kreuz", "Glider", "Form", "Blume" }));
		this.cbInitArt.setBounds(448, 40, 108, 22);
		this.contentPane.add(this.cbInitArt);
		this.btnStep = new JButton("Schritt");
		this.btnStep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameOfLifeLib.this.step();
				GameOfLifeLib.this.updateData();
			}
		});
		this.btnStep.setBounds(448, 164, 104, 23);
		this.contentPane.add(this.btnStep);
		this.tglAuto = new JToggleButton("Auto");
		this.tglAuto.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (GameOfLifeLib.this.tglAuto.isSelected()) {
					GameOfLifeLib.this.btnStep.setEnabled(false);
					if (GameOfLifeLib.this.timer == null) {
						GameOfLifeLib.this.timer = new Timer();
						GameOfLifeLib.this.timer.schedule(new TimerTask() {
							@Override
							public void run() {
								GameOfLifeLib.this.step();
								GameOfLifeLib.this.updateData();
							}
						}, GameOfLifeLib.this.autoDelay, GameOfLifeLib.this.autoDelay);
					}
				} else {
					GameOfLifeLib.this.timer.cancel();
					GameOfLifeLib.this.timer = null;
					GameOfLifeLib.this.btnStep.setEnabled(true);
				}

			}
		});
		this.tglAuto.setBounds(448, 219, 104, 23);
		this.contentPane.add(this.tglAuto);
		this.spTimerDelay = new JSpinner();
		this.spTimerDelay.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				GameOfLifeLib.this.autoDelay = Integer.parseInt(GameOfLifeLib.this.spTimerDelay.getModel().getValue().toString());

				if (GameOfLifeLib.this.tglAuto.isEnabled() && (GameOfLifeLib.this.timer != null)) {
					GameOfLifeLib.this.timer.cancel();
					GameOfLifeLib.this.timer = null;
					GameOfLifeLib.this.timer = new Timer();
					GameOfLifeLib.this.timer.schedule(new TimerTask() {
						@Override
						public void run() {
							GameOfLifeLib.this.step();
							GameOfLifeLib.this.updateData();
						}
					}, GameOfLifeLib.this.autoDelay, GameOfLifeLib.this.autoDelay);
				}
			}
		});
		this.spTimerDelay.setModel(new SpinnerNumberModel(500, 1, 1000, 10));
		this.spTimerDelay.setBounds(448, 308, 85, 20);
		this.contentPane.add(this.spTimerDelay);
		JFormattedTextField tf = ((DefaultEditor) this.spTimerDelay.getEditor()).getTextField();
		tf.setEditable(false);
		JLabel lblTimerDelay = new JLabel("Timer Delay:");
		lblTimerDelay.setBounds(448, 281, 91, 16);
		this.contentPane.add(lblTimerDelay);
		this.daten = new boolean[40][40];
		this.clearData();
	}

	public abstract void step();

	public void updateData() {
		this.dieSpielFläche.setData(this.daten);
		this.dieSpielFläche.repaint();
	}

	public void initialize() {
		GameOfLife.generation = 0;
		String art = this.cbInitArt.getSelectedItem().toString();
		System.out.println("HashCode: " + art.hashCode());
		switch (art.hashCode()) {
		case -1607156394:
			if (art.equals("Zufall")) {
				this.clearData();
				Random var6 = new Random();

				for (int x = 0; x < this.daten.length; ++x) {
					for (int y = 0; y < this.daten[x].length; ++y) {
						this.daten[x][y] = var6.nextBoolean();
					}
				}
			}
			break;
		case 2195684:
			if (art.equals("Form")) {
				this.clearData();
				this.daten[10][13] = true;
				this.daten[10][16] = true;
				this.daten[11][13] = true;
				this.daten[11][16] = true;
				this.daten[12][11] = true;
				this.daten[12][12] = true;
				this.daten[12][14] = true;
				this.daten[12][15] = true;
				this.daten[12][17] = true;
				this.daten[12][18] = true;
				this.daten[13][13] = true;
				this.daten[13][16] = true;
				this.daten[14][13] = true;
				this.daten[14][16] = true;
				this.daten[15][11] = true;
				this.daten[15][12] = true;
				this.daten[15][14] = true;
				this.daten[15][15] = true;
				this.daten[15][17] = true;
				this.daten[15][18] = true;
				this.daten[16][13] = true;
				this.daten[16][16] = true;
				this.daten[17][13] = true;
				this.daten[17][16] = true;
			}
			break;
		case 72761059:
			if (art.equals("Kreuz")) {
				this.clearData();

				for (int g = 0; g < this.daten.length; ++g) {
					this.daten[g][g] = true;
					this.daten[g][this.daten.length - g - 1] = true;
				}
			}
			break;
		case 2135637389:
			if (art.equals("Glider")) {
				this.clearData();
				this.daten[2][0] = true;
				this.daten[2][1] = true;
				this.daten[2][2] = true;
				this.daten[1][2] = true;
				this.daten[0][1] = true;
			}
			break;
		case 64285731:
			if (art.equals("Blume")) {
				this.clearData();

			}
		}

		this.dieSpielFläche.setData(this.daten);
		this.dieSpielFläche.repaint();
	}

	private void clearData() {
		for (int i = 0; i < this.daten.length; ++i) {
			for (int j = 0; j < this.daten[i].length; ++j) {
				this.daten[i][j] = false;
			}
		}

	}

	public JSpinner getSpTimerDelay() {
		return this.spTimerDelay;
	}
}

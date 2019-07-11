import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import my.dzeik.life.EditorFlaeche;

public class Editor {

	private static Editor instance;
	private static JFrame frame;
	private EditorFlaeche flaeche;
	
	private static String title;
	private static int witdh;
	private static int height;
	
	private JTextField textField;
	private JLabel label;
	
	public Editor(String title, int width, int height) {
		this.witdh = width;
		this.height = height;
		this.title = title;
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setVisible(true);
		
		init();
	}
	
	private void init() {
		flaeche = new EditorFlaeche(40, 40);
		frame.add(flaeche);
		
		textField = new JTextField();
		textField.setBounds(400, 50, 100, 30);
		frame.add(textField);
		
		label = new JLabel("Mustername");
		label.setBounds(400, 25, 100, 30);
		frame.add(label);
	}
	
	public static void btnEditorClicked() {
		instance = new Editor("Editor", 40*9+1 + 250, 40*9+1);
	}
}

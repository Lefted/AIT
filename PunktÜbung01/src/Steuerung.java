
public class Steuerung {

	public static void main(String[] args) {

		// Objekte mit underschiedlichen Konstruktoren erzeugen
		Punkt p1 = new Punkt();
		Punkt p2 = new Punkt(30, 40);

		// Attribute von p1 festlegen
		p1.setX(10);
		p1.setY(20);

		System.out.println("Abstand p1 zum Ursprung:" + p1.getAbstand());
		System.out.println("Abstand p2 zum Ursprung:" + p2.getAbstand());
	}
}


public class Steuerung {

	public static void main(String[] args) {

		@SuppressWarnings("unused")
		final Punkt p1 = new Punkt(1, 1);
		final Punkt p2 = new Punkt(3, 1);
		final Punkt p3 = new Punkt(3, 2);
		final Punkt p4 = new Punkt(2, 3);
		final Punkt p5 = new Punkt(1, 2);
		final Punkt p6 = new Punkt(2, 2);

		final Dreieck d1 = new Dreieck(p5, p4, p6);
		final Dreieck d2 = new Dreieck(p6, p3, p4);
		final Rechteck r1 = new Rechteck(p5, p2);

		final Haus haus = new Haus(d1, d2, r1);
		System.out.println("Fläche:" + haus.getFlaeche());

	}
}


public class Steuerung {

	public static void main(String[] args) {
		
		Punkt a = new Punkt(1,1);
		Punkt b = new Punkt(3,1);
		Punkt c = new Punkt(3,2);
		
		Dreieck dasDreieck = new Dreieck(a, b, c);
		Rechteck dasRechteck = new Rechteck(a, c);
		
		System.out.println("Flächeninhalt Dreieck:" + dasDreieck.getFlaeche());
		System.out.println("Flächeninhalt Rechteck:" + dasRechteck.getFlaeche());
	}
	
}

package animation;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class textClass {
	//classe pour le texte, permet d'en afficher simplement ou d'appliquer certains effets (rebond, apparition, tourner...)

	Color myColour;
	Font font;
	Chrono time;
	private boolean start = true;
	private int alpha =255; //transparence, de 0 pour transparent à 255 opaque
	private int R, G,B;
	
	public textClass() {
		// TODO Auto-generated constructor stub
	System.out.println("Nouvelle classe texte crée !!!");
	R = 205; G =0; B=50;
	myColour = new Color(R, G, B, alpha);
	time = new Chrono(); //pour compter le temps
	}
	
	public void appear(Graphics g, String text, int abscisse, int ordonne, int size) {
		
		font = new Font("Verdana", Font.BOLD, size);
		g.setFont(font);
		g.setColor(myColour);
	    g.drawString(text, abscisse, ordonne);
		
	}
	
		
public void appear(Graphics g, String text, int abscisse, int ordonne, int size, int secondes) {
		
		font = new Font("Verdana", Font.BOLD, size);
		g.setFont(font);
		g.setColor(myColour);
	    g.drawString(text, abscisse, ordonne);
		
		System.out.println(time.getTemps() ); // affichage en secondes
		if (time.getTemps() >secondes) { //si les secondes sont écoulées
			alpha = 0;
			myColour = new Color(R, G, B, alpha);
			time.stop();
		}
	}

	public void countTemps() {
		if (start ==true) {
//System.out.println("Wesh alors\n\n\n\n\n\n\n\n\n");
		time.start();//démarrage du chrono
		start =false;
		}
	}

	public void pauseTemps() {
		time.pause();
	}
	
	public void resumeTemps() {
		time.resume();
	}
	public void resetTemps() {
		time.stop();
		start = true;
	}
	
	public void setColour(int R1, int G1, int B1) {
		R = R1;
		G = G1;
		B = B1;
	}
	
}

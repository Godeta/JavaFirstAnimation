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

	public void come(Graphics g,String text, int abscisse, int ordonne, int size,int abscFin, int ordoFin, int sizeFin, int vitesse) {
		//if (vitesse <=0) {vitesse =1;}
		g.setColor(myColour);
		//while(abscisse != abscFin && ordonne != ordoFin) { //tant que le déplacement n'est pas fini

			if (abscisse - abscFin <0) { //l'abscisse se rapproche de sa destination
				abscisse +=vitesse;
			}
			else {
				abscisse-=vitesse;
			}
			if (ordonne - ordoFin <0) {
				ordonne +=vitesse;
			}
			else {
				ordonne-=vitesse;
			}
			if (size - sizeFin <0) {
				size +=vitesse;
			}
			else {
				size-=vitesse;
			}
			font = new Font("Verdana", Font.BOLD, size);
			g.setFont(font);
			g.drawString(text, abscisse, ordonne);
		//}
		
	}
	
	public void mouvTest(Graphics g,String text, int abscisse, int ordonne, int size,int abscFin, int ordoFin, int sizeFin) {
		//if (vitesse <=0) {vitesse =1;}
		g.setColor(myColour);
		//while(abscisse != abscFin && ordonne != ordoFin) { //tant que le déplacement n'est pas fini
		font = new Font("Verdana", Font.BOLD, size);
		g.setFont(font);
			if (abscisse - abscFin <0) { //l'abscisse se rapproche de sa destination
				abscisse +=1;
			}
			else {
				abscisse-=1;
			}
			if (ordonne - ordoFin <0) {
				ordonne +=1;
			}
			else {
				ordonne-=1;
			}
			if (size - sizeFin <0) {
				size +=1;
			}
			else {
				size-=1;
			}
			g.drawString(abscisse + " et aussi l'ordonné : " + ordonne, 300, 300);
			g.drawString(text, abscisse, ordonne);
		//}
		
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

package animation;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D; //nottament pour utiliser les dégradés
import java.awt.GradientPaint;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JPanel;
 
@SuppressWarnings("serial") //enlève le warning de serialisation
public class Panneau extends JPanel {

	  private int posX = -50;
	  private int posY = -50;
	 //Source -> generate getters and setters
	  public int getPosX() {
			return posX;
		}

		public void setPosX(int posX) {
			this.posX = posX;
		}

		public int getPosY() {
			return posY;
		}

		public void setPosY(int posY) {
			this.posY = posY;
		}

		//affichage
  public void paintComponent(Graphics g){ 
    //Vous verrez cette phrase chaque fois que la méthode sera invoquée
    System.out.println("Le panneau est executé !"); 

    //g.setBackground(Color.ORANGE);

    background(g);
    //figures(g);
    text(g);
    
    degrade(g);
  }               

  //rectangle, cercle et autre...
  private void figures (Graphics g) {
	  
	  //ellipse
	    int x1 = this.getWidth()/4;
	    int y1 = this.getHeight()/4;                      
	    g.fillOval(x1, y1, this.getWidth()/2, this.getHeight()/2);

	    //20 en coordonnées et 75 de largeur et longueur, drawOval pareil que fill sauf qu'il est vide à l'intérieur
	    g.drawOval(20, 20, 75+posY, 75);

	    //rectangle
	        //x1, y1, width, height
	        g.drawRect(10+posX, 10, 50, 60);
	        g.fillRect(65, 65, 30, 40);
	        //x1, y1, width, height, arcWidth, arcHeight
	        g.drawRoundRect(10, 10, 30, 50, 10+posX, 10);

	    //lignes
	        //x1, y1, x2, y2
	        g.drawLine(10, 0, 10, 10);
	        g.drawLine(10, this.getHeight(), 10, this.getHeight()-10);
	        
	        g.setColor(Color.red);
	        //polygones, drawPolygon(int[] x, int[] y, int nbrePoints);
	        int x[] = {20, 30, 50, 60, 60, 50, 30, 20};
	        int y[] = {30, 20, 20, 30, 50, 60, 60, 50};
	        g.drawPolygon(x, y, 8); //si on met polyline ça trace juste les lignes sans relier les deux derniers points
	    g.setColor(Color.gray);
	        int x2[] = {50, 60, 80, 90, 90, 80, 60, 50, 200};
	        int y2[] = {60, 50, 50, 60, 80, 90, 90, 80, 100};
	        g.fillPolygon(x2, y2, 9);

	  
  }
  
  //text en haut
  private void text (Graphics g) {
	  g.setColor(Color.black);
	    //texte
	    Font font = new Font("Verdana", Font.BOLD, 20);
	    g.drawString("Un texte sans font et tout petit !", 40, this.getHeight()-20);
	    g.setFont(font);
	    g.drawString("Bienvenu dans cette", this.getWidth()/10, this.getHeight()/8);
	    g.drawString("première animation !!!",this.getWidth()/9, this.getHeight()/8+20);
  }
  
  //images pour le décor
  private void background(Graphics g) {
	  
	    //image : drawImage(Image img, int x, int y, int width, int height, Observer obs)
	    try {
	      Image img = ImageIO.read(new File("Images/three_cool_fight_background.gif"));
	           
	      //Pour une image de fond, prend toute la taille de la fenêtre
	      g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	     
	    	
	      /*Image icon = new ImageIcon(getClass().getResource("/Images/three_cool_fight_background.gif")).getImage();
	      g.drawImage(icon, 0, 0, this.getWidth(), this.getHeight(), this);
	      */
	      
	      //image fixe, taille de l'image initiale
	      //g.drawImage(img, 0, 0, this);

	    } catch (IOException e) {
	      e.printStackTrace();
	    }  
	  
  }
  
  //dégradé pour fair un arc en ciel
  private void degrade (Graphics g) {
    Graphics2D g2d = (Graphics2D)g;
    GradientPaint gp, gp2, gp3, gp4, gp5, gp6; 
    gp = new GradientPaint(0, 0, Color.RED, 20, 0, Color.magenta, true);
    gp2 = new GradientPaint(20, 0, Color.magenta, 40, 0, Color.blue, true);
    gp3 = new GradientPaint(40, 0, Color.blue, 60, 0, Color.green, true);
    gp4 = new GradientPaint(60, 0, Color.green, 80, 0, Color.yellow, true);
    gp5 = new GradientPaint(80, 0, Color.yellow, 100, 0, Color.orange, true);
    gp6 = new GradientPaint(100, 0, Color.orange, 120, 0, Color.red, true);

    g2d.setPaint(gp);
    g2d.fillRect(0, 0+posX*2, 20, this.getHeight());               
    g2d.setPaint(gp2);
    g2d.fillRect(20, 0+posX*2, 20, this.getHeight());
    g2d.setPaint(gp3);
    g2d.fillRect(40, 0+posX*2, 20, this.getHeight());
    g2d.setPaint(gp4);
    g2d.fillRect(60, 0+posX*2, 20, this.getHeight());
    g2d.setPaint(gp5);
    g2d.fillRect(80, 0+posX*2, 20, this.getHeight());
    g2d.setPaint(gp6);
    g2d.fillRect(100, 0+posX*2, 40, this.getHeight());

    
    //rectangle avec dégradé
    //Graphics2D g2d = (Graphics2D)g;         
    GradientPaint gp11 = new GradientPaint(0, 0, Color.RED, 30, 30, Color.cyan, true);                
    g2d.setPaint(gp11);
    g2d.fillRect(100, this.getHeight()/2+100, 50, 70-posX);  
  }
  
}
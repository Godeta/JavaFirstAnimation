package animation;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D; //nottament pour utiliser les dégradés
import java.awt.GradientPaint;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

 
@SuppressWarnings("serial") //enlève le warning de serialisation
public class Panneau extends JPanel {

	  private int posX = -50;
	  private int posY = -50;
	  private int shiX = 0;
	  private int bug =0; //pour faire des réactions marrantes avec le fond
	  private boolean once = true; //pour n'effectuer qu'une fois l'initialisation
	  private boolean side = true; //pour choisir dans quel sens il va, true = à droite et false = gauche
	  private int i =0; //pour parcourir le tableau
	  private int vit =0;
	  private int animation;
	  private String action = "Walk", mode = "Normal";
	  
	  //déclaration des images
	  Image[] tabImg = new Image[7]; //créer un tableau de 7 images
	  Image[] tabImgA = new Image[20]; //tableau pour les actions du personnage

	//Source -> generate getters and setters
	  public int getPosX() { //truc
			return posX;
		}

		public int getVit() {
		return vit;
	}

	public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}
		public String getMode() {
			return mode;
		}

		public void setMode(String mode) {
			this.mode = mode;
		}

	public void setVit(int vit) {
		this.vit = vit;
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
		
		 public int getShiX() {
				return shiX;
		}

			public void setShiX(int shiX) {
				this.shiX = shiX;
		}
			
			public boolean isSide() {
				return side;
			}

			public void setSide(boolean side) {
				this.side = side;
			}
			
			//initialisation images dans IniImage
			

		//affichage
  public void paintComponent(Graphics g){ 
    //Vous verrez cette phrase chaque fois que la méthode sera invoquée
    System.out.println(mode); 

    animation++; //variable qui augmente à chaque nouvel affichage
    
    //if (animation >10) {animation =0;}
    
    //g.setBackground(Color.ORANGE);
    if (once == true) {
    IniImages();
    IniSound();
    once = false;
    }
    
    background(g);
    //figures(g);
    text(g);
    
    degrade(g);
    
    shizuo(g);
   
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
	  g.setColor(Color.white);
	    //texte
	    Font font = new Font("Verdana", Font.BOLD, 20);
	    g.drawString("Un texte sans font et tout petit !", 40, this.getHeight()-20);
	    g.setFont(font);
	    g.drawString("Bienvenu dans cette", this.getWidth()/10, this.getHeight()/8);
	    g.drawString("première animation !!!",this.getWidth()/9, this.getHeight()/8+20);
	    font = new Font("Impact", Font.BOLD,30);
	    g.setFont(font);
	    g.setColor(Color.blue);
	    g.drawString("Appuyez sur C pour afficher la liste des contrôles !",this.getWidth()/4, this.getHeight()-60);
  }
  
  //images pour le décor
  private void background(Graphics g) {
	  
	    //image : drawImage(Image img, int x, int y, int width, int height, Observer obs)
	    try {
	    	Image img = ImageIO.read(new File("Images/three_cool_fight_background.gif"));
	    	//initialisation des images dans la classe
	           
	      //Pour une image de fond, prend toute la taille de la fenêtre
	      g.drawImage(img, bug*posX, bug*posY, this.getWidth(), this.getHeight(), this);
	     
	    	
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
  
  
  //personnage Shizuo
  private void shizuo(Graphics g) {

	  //afficher les touches
	  if (mode == "Control") {
		   //x1, y1, width, height
	        g.drawRect(10+posX, 10, 50, 60);
	        g.setColor(Color.black);
	        g.fillRect(0, 0, this.getWidth(), this.getHeight() );
	        
	        Font police = new Font("Comic-sans-ms", Font.BOLD,30);
		    g.setFont(police);
		    g.setColor(Color.white);
		    g.drawString("Flèche de droite et de gauche pour déplacement latéraux.",this.getWidth()/4, this.getHeight()/8);
		    g.drawString("Presser K pour un kick et L pour un coup de lampadaire !",this.getWidth()/4, this.getHeight()/8+50);
		    g.drawString("Presser P pour provoquer et relacher M pour revenir au début de la map + passer en mode bizarre",this.getWidth()/10, this.getHeight()/8+100);
		    g.drawString("En mode bizarre : t pour augmenter l'acceleration et r pour la ralentir.",this.getWidth()/8, this.getHeight()/8+150);
		    g.drawString("Presser C pour voir les contrôles et U pour quitter ce mode.",this.getWidth()/4, this.getHeight()/8+200);
		    g.drawString("U permet de revenir en mode normal (donc plus de mode bizarre ou autre)",this.getWidth()/6, this.getHeight()/8+250);
	  }
	  
	  
	  else {
		  
	  
	  //boucle d'animation du personnage
	  
	  if (action=="Walk" && mode == "Normal") { //en normal, la vitesse doit être constante (-vit) et l'animation aussi
	  if (animation%15 ==1) {
	  i++;
	  }
       if (i>6) {i=1; }
		  if(vit ==0) {
			  i=0;
		  }
		       if (i>tabImg.length) {i=0;}
		      //image : drawImage(Image img, int x, int y, int width, int height, Observer obs)
		       if (side == true) {
		    	   g.drawImage(tabImg[i], shiX-vit, this.getHeight()/3+40,600,500, this);
		       }
		       else {
		    	   g.drawImage(tabImg[i], shiX+600-vit, this.getHeight()/3+40,-600,500, this);
		       }
		  }
	  
	      //raté if (shiX %20 >0) { i++; }//modulo le nombre d'images
	  else if (action == "Walk" && mode == "Bizarre") {
	  if (vit%35  >5) { //modulo le nombre d'images, de 0 à 35, séparé tous les 5 donc 7 images
		  i++;
		  }
	  if(vit ==0) {
		  i=0;
	  }
	  else if (vit >0 && vit <4) {i=1;}
	  
	       if (i>tabImg.length) {i=0;}
	      //image : drawImage(Image img, int x, int y, int width, int height, Observer obs)
	       if (side == true) {
	    	   g.drawImage(tabImg[i], shiX, this.getHeight()/3+40,600,500, this);
	       }
	       else {
	    	   g.drawImage(tabImg[i], shiX+600, this.getHeight()/3+40,-600,500, this);
	       }
	  }
	  
	  else if (action == "kick") {
		  if (animation%10 ==0) {
		  i++;
		  }
	       if (i>5) {i=0; }
	      //image : drawImage(Image img, int x, int y, int width, int height, Observer obs)
	       if (side == true) {
	    	   g.drawImage(tabImgA[i], shiX, this.getHeight()/3+40,600,500, this);
	       }
	       else {
	    	   g.drawImage(tabImgA[i], shiX+600, this.getHeight()/3+40,-600,500, this);
	       }
		  
		  
	  }
	  
	  else if (action == "lamppost") {
		  if (animation%10 ==0) {
		  i++;
		  }
	       if (i>5) {i=0; }
	      //image : drawImage(Image img, int x, int y, int width, int height, Observer obs)
	       if (side == true) {
	    	   g.drawImage(tabImgA[i+5], shiX, this.getHeight()/3+40,600,500, this);
	       }
	       else {
	    	   g.drawImage(tabImgA[i+5], shiX+600, this.getHeight()/3+40,-600,500, this);
	       }
	  }
	  
	  else if (action == "provoc") {
		  if (animation%15 ==0) {
		  i++;
		  }
	       if (i>4) {i=0; }
	      //image : drawImage(Image img, int x, int y, int width, int height, Observer obs)
	       if (side == true) {
	    	   g.drawImage(tabImgA[i+12], shiX, this.getHeight()/3+40,600,500, this);
	       }
	       else {
	    	   g.drawImage(tabImgA[i+12], shiX+600, this.getHeight()/3+40,-600,500, this);
		       }
		  }

	  }
  }
  
  //initialisation des images
  private void IniImages() {
	  
	  try {
		  //stand et déplacements
		  tabImg[0] = ImageIO.read(new File("Images/Shizuo/HiwStance.png"));
		  tabImg[1] = ImageIO.read(new File("Images/Shizuo/Walk/Hiw400_00.png"));
		  tabImg[2] = ImageIO.read(new File("Images/Shizuo/Walk/Hiw400_01.png"));
		  tabImg[3] = ImageIO.read(new File("Images/Shizuo/Walk/Hiw400_02.png"));
		  tabImg[4] = ImageIO.read(new File("Images/Shizuo/Walk/Hiw400_03.png"));
		  tabImg[5] = ImageIO.read(new File("Images/Shizuo/Walk/Hiw400_04.png"));
		  tabImg[6] = ImageIO.read(new File("Images/Shizuo/Walk/Hiw400_05.png"));
		  
		  //ataques, de 0 à 5 : kick, de 6 à 11 : coup de lampadaire, 12 à 16 : provocation 
		  //kick
		  tabImgA[0] = ImageIO.read(new File("Images/Shizuo/kick/Hiw001_00.png"));
		  tabImgA[1] = ImageIO.read(new File("Images/Shizuo/kick/Hiw001_01.png"));
		  tabImgA[2] = ImageIO.read(new File("Images/Shizuo/kick/Hiw001_02.png"));
		  tabImgA[3] = ImageIO.read(new File("Images/Shizuo/kick/Hiw001_03.png"));
		  tabImgA[4] = ImageIO.read(new File("Images/Shizuo/kick/Hiw001_04.png"));
		  tabImgA[5] = ImageIO.read(new File("Images/Shizuo/kick/Hiw001_05.png"));
		  //lampadaire
		  tabImgA[6] = ImageIO.read(new File("Images/Shizuo/red_light_hit/Hiw002_00.png"));
		  tabImgA[7] = ImageIO.read(new File("Images/Shizuo/red_light_hit/Hiw002_01.png"));
		  tabImgA[8] = ImageIO.read(new File("Images/Shizuo/red_light_hit/Hiw002_02.png"));
		  tabImgA[9] = ImageIO.read(new File("Images/Shizuo/red_light_hit/Hiw002_03.png"));
		  tabImgA[10] = ImageIO.read(new File("Images/Shizuo/red_light_hit/Hiw002_04.png"));
		  tabImgA[11] = ImageIO.read(new File("Images/Shizuo/red_light_hit/Hiw002_05.png"));
		  
		  //provoc
		  tabImgA[12] = ImageIO.read(new File("Images/Shizuo/provoc/Hiw320_00.png"));
		  tabImgA[13] = ImageIO.read(new File("Images/Shizuo/provoc/Hiw320_01.png"));
		  tabImgA[14] = ImageIO.read(new File("Images/Shizuo/provoc/Hiw320_02.png"));
		  tabImgA[15] = ImageIO.read(new File("Images/Shizuo/provoc/Hiw320_03.png"));
		  tabImgA[16] = ImageIO.read(new File("Images/Shizuo/provoc/Hiw320_04.png"));
		  
    } 
	  
	  catch (IOException e) {
      e.printStackTrace();
    }  
	  
  }
  
  private void IniSound() {
	  //déclaration audio
	  //filePath = "Sounds/party_started.wav"; 
		try {SimpleAudioPlayer audioPlayer = 
						new SimpleAudioPlayer("Sounds/naruto_op1.wav"); 
		audioPlayer.play();
		}
		catch (Exception ex) 
		{ 
			System.out.println("Error with playing sound."); 
			ex.printStackTrace(); 
		
		} 
		
  }

  

}
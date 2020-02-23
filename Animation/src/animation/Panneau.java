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
import javax.swing.ImageIcon;
import javax.swing.JPanel;

 
@SuppressWarnings("serial") //enlève le warning de serialisation
public class Panneau extends JPanel {

	  private int posX = -50;
	  private int posY = -50;
	  private int shiX = 0;
	  private int bug =0; //pour faire des réactions marrantes avec le fond
	  private boolean once = true; //pour n'effectuer qu'une fois l'initialisation
	  private boolean side = true; //pour choisir dans quel sens il va, true = à droite et false = gauche
	  private boolean eventHold; //pour un évènement à maintenir
	  private int i =0; //pour parcourir le tableau
	  private int j =1; //parcourir le tableau de Jul
	  private int vit =0;
	  private int animation;
	  private int soundChange =0;
	  private String action = "Walk", mode = "Normal";
	  private String tabMod[];
	  private int alpha = 127; // 50% transparece
	  Color myColour; //initialisé dans l'éveil de Jul
	  textClass lyric = new textClass(); //texte créé avec ma classe texte pour pouvoir faire des fonctions qui le manipule facilement
	  
	  //déclaration des images
	  Image[] tabImg = new Image[7]; //créer un tableau de 7 images
	  Image[] tabImgA = new Image[20]; //tableau pour les actions du personnage
	  Image[] tabImgJul = new Image[10];
	  Image Simon, Simon2, Antoine, Rominou, JulBack;
	  
	  //création sons
	  SimpleAudioPlayer JulPlayerJ;
	  SimpleAudioPlayer JulPlayerB;
	  SimpleAudioPlayer audioPlayer;
	  //initialisation dans Ini Sound

	//Source -> generate getters and setters
	  public int getPosX() { //truc
			return posX;
		}

		public int getVit() {
		return vit;
	}

		public void setTabMode (String[] tabMod2) {
			this.tabMod = tabMod2;
		}
		
	public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}
		
		public void setEventHold(boolean eventHold) {
			this.eventHold = eventHold;
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
			
			public int getBug() {
				return bug;
			}

			public void setBug(int bug) {
				this.bug = bug;
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
    if(bug>0) {
    degrade(g);}
    
    if (mode=="Jul") { //en mode Jul, lorsque le mode éveil s'active
    	alpha = animation%150+20;
		   myColour = new Color(255, 50, 0, alpha);
		   textJul(g);
		   if (eventHold ==true) {
			   g.drawImage(JulBack, 0, 0, this.getWidth(), this.getHeight(), this);
		   }
    }
    
    infoDev(g);
    
    shizuo(g);
    ChangeSound(soundChange);
   
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
	    g.drawString("Projet de Arnaud GODET en Fevrier 2020", 40, this.getHeight()-20);
	    g.setFont(font);
	    g.drawString("Bienvenu dans cette", this.getWidth()/10, this.getHeight()/8);
	    g.drawString("première animation !!!",this.getWidth()/9, this.getHeight()/8+20);
	    font = new Font("Impact", Font.BOLD,30);
	    g.setFont(font);
	    Graphics2D g2d = (Graphics2D)g;
	    GradientPaint grad;
	    grad = new GradientPaint(0, 0, Color.orange, 30, 30, Color.white, true);  
	    g2d.setPaint(grad);
	    g.drawString("Appuyez sur C pour afficher la liste des contrôles !",this.getWidth()/4, this.getHeight()-60);
  }
  
  //images pour le décor
  private void background(Graphics g) {
	  
	    //image : drawImage(Image img, int x, int y, int width, int height, Observer obs)
	    try {
	    	Image img = ImageIO.read(new File("Images/three_cool_fight_background.gif"));
	    	//initialisation des images dans la classe
	           
	      //Pour une image de fond, prend toute la taille de la fenêtre
	      //g.drawImage(img, bug*posX, bug*posY, this.getWidth(), this.getHeight(), this);
	    	//pour afficher un gif il faut utiliser ImageIcon !
	    	g.drawImage(new ImageIcon("Images/three_cool_fight_background.gif").getImage(), bug*posX, bug*posY,this.getWidth(), this.getHeight(), this);
	     
	    	
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
	        //g.drawRect(10+posX, 10, 50, 60);
	        g.setColor(Color.black);
	        g.fillRect(0, 0, this.getWidth(), this.getHeight() );
	        
	        Font police = new Font("Comic-sans-ms", Font.BOLD,30);
		    g.setFont(police);
		    g.setColor(Color.white);
		    g.drawString("Flèche de droite et de gauche pour déplacement latéraux.",this.getWidth()/4, this.getHeight()/8);
		    g.drawString("Presser K pour un kick et L pour un coup de lampadaire !",this.getWidth()/4, this.getHeight()/8+50);
		    g.drawString("Presser C pour voir les contrôles et U pour quitter ce mode.",this.getWidth()/4, this.getHeight()/8+200);
		    g.drawString("Presser P pour provoquer et relacher M pour revenir au début de la map + passer au mode suivant.",this.getWidth()/10, this.getHeight()/8+100);
		    g.drawString("En mode bizarre : T pour augmenter l'acceleration et R pour la ralentir.",this.getWidth()/8, this.getHeight()/8+150);
		    g.drawString("U permet de revenir en mode normal (donc plus de mode bizarre ou autre)",this.getWidth()/6, this.getHeight()/8+250);
		    g.drawString("Presser B lance le mode bug (juste pour s'amuser, U pour l'arrêter).",this.getWidth()/6, this.getHeight()/8+300);
		    g.drawString("En mode Jul, faire une provocation pour activer son éveil !!!",this.getWidth()/4, this.getHeight()/8+350);
		    g.drawString("Presser V pour afficher la liste des modes.",this.getWidth()/4, this.getHeight()/8+400);
		    g.drawString("Presser I pour afficher l'évolution des variables.",this.getWidth()/4, this.getHeight()/8+450);
		    g.drawString("Presser H pour activer les évènements à maintenir (il n'y en a que rarement).",this.getWidth()/6, this.getHeight()/8+500);
		    
		    
	  }
	  
	  else if (mode =="ListeMod") {
		   //x1, y1, width, height
	        g.setColor(Color.blue);
	        g.fillRect(0, 0, this.getWidth(), this.getHeight() );
	        
	        Font titre = new Font("Comic-sans-ms", Font.BOLD,70);
	        Font blabla = new Font("Verdana", Font.BOLD,30);
	        
		    g.setFont(titre);
		    g.setColor(Color.white);
		    g.drawString("Liste des modes :",this.getWidth()/4, this.getHeight()/8+100);
		    
		    g.setFont(blabla);
		    g.setColor(Color.orange);
		//affiche la liste des modes
		    for (int i=0; i<tabMod.length; i++ ) {
		    	g.drawString("Mode : "+ tabMod[i],this.getWidth()/2, this.getHeight()/8+150+i*50);
		    }
		    g.drawString("(Appuyez sur U pour revenir au jeu)",50, this.getHeight()-100);
	  }
	  
	  
	  else {
		  
	  
	  //boucle d'animation du personnage
	  
	  if (action=="Walk" && mode != "Bizarre") { //en normal, la vitesse doit être constante (-vit) et l'animation aussi
	  if (animation%20 ==1) {
	  i++;
	  }
       if (i>6) {i=1; }
		  if(vit ==0) {
			  i=0;
		  }
		       if (i>tabImg.length) {i=0;}
		      //image : drawImage(Image img, int x, int y, int width, int height, Observer obs)
		       if (side == true) {
		    	   g.drawImage(tabImg[i], shiX, this.getHeight()/3+40,600,500, this);
		    	   if (mode=="Simon") {
		    		   g.drawImage(Simon, shiX, this.getHeight()/3+40,600,500, this);
		    	   }
		    	   else if (mode == "Antoine") {
		    		   g.drawImage(Antoine, shiX, this.getHeight()/3+40,600,500, this);
		    	   }
		    	   else if (mode == "Rominou") {
		    		   g.drawImage(Rominou, shiX, this.getHeight()/3+40,600,500, this);
		    	   }
		    	   else if (JulPlayerJ.isPlaying() == true) { //Jul animation avec la musique jdvc
		    		   if (JulPlayerJ.time() > 11000000 ) { //moment où il commence à parler
		    			   if (animation%20 ==1) {
		    				  j++;
		    				  }
		    		   }
		    			       if (j>4) {j=1; }
		    		   g.drawImage(tabImgJul[j], shiX, this.getHeight()/3+40,600,500, this);
		    	   }
		    	   else if (JulPlayerB.isPlaying() == true) {// Jul statique et musique beuh
		    		   g.drawImage(tabImgJul[0], shiX, this.getHeight()/3+40,600,500, this);
		    		   g.setColor(myColour);
		    		   g.fillRect(0, 0, this.getWidth(), this.getHeight() );
		    		   
		    	   }
		       }
		       else {
		    	   g.drawImage(tabImg[i], shiX+600, this.getHeight()/3+40,-600,500, this);
		    	   if (mode=="Simon") {
		    		   g.drawImage(Simon, shiX+600, this.getHeight()/3+40,-600,500, this);
		    	   }
		    	   else if (mode =="Antoine") {
		    		   g.drawImage(Antoine, shiX+600, this.getHeight()/3+40,-600,500, this);
		    	   }
		    	   else if (mode =="Rominou") {
		    		   g.drawImage(Rominou, shiX+600, this.getHeight()/3+40,-600,500, this);
		    	   }
		    	   else if (JulPlayerJ.isPlaying() == true) { //Jul animation avec la musique jdvc
		    		   if (JulPlayerJ.time() > 11000000 ) { //moment où il commence à parler
		    		   if (animation%20 ==1) {
		    				  j++;
		    				  }
		    		   }
		    			       if (j>4) {j=1; }
		    		   g.drawImage(tabImgJul[j], shiX+600, this.getHeight()/3+40,-600,500, this);
		    	   }
		    	   else if (JulPlayerB.isPlaying() == true) {// Jul statique et musique beuh
		    		   g.drawImage(tabImgJul[0], shiX+600, this.getHeight()/3+40,-600,500, this);
		    		   g.setColor(myColour);
		    		   g.fillRect(0, 0, this.getWidth(), this.getHeight() );
		    	   }
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
	    	   if (mode=="Simon") {
	    		   g.drawImage(Simon2, shiX, this.getHeight()/3+40,600,500, this);
	    	   }
	       }
	       else {
	    	   g.drawImage(tabImgA[i], shiX+600, this.getHeight()/3+40,-600,500, this);
	    	   if (mode=="Simon") {
	    		   g.drawImage(Simon2, shiX+600, this.getHeight()/3+40,-600,500, this);
	    	   }
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
		  
		  Simon = ImageIO.read(new File("Images/Shizuo/Simon_mod.png"));
		  Simon2 = ImageIO.read(new File("Images/Shizuo/Simon2_mod.png"));
		  Antoine = ImageIO.read(new File("Images/Shizuo/Antoine_mod.png"));
		  Rominou = ImageIO.read(new File("Images/Shizuo/Rominou.png"));
		  JulBack = ImageIO.read(new File("Images/Jul/jul_background.png"));
		  
		  //Jul
		  tabImgJul[0] = ImageIO.read(new File("Images/Jul/jul2-2.png"));
		  tabImgJul[1] = ImageIO.read(new File("Images/Jul/julS.png"));
		  tabImgJul[2] = ImageIO.read(new File("Images/Jul/julS2.png"));
		  tabImgJul[3] = ImageIO.read(new File("Images/Jul/julS3.png"));
		  tabImgJul[4] = ImageIO.read(new File("Images/Jul/julS4.png"));
    } 
	  
	  catch (IOException e) {
      e.printStackTrace();
    }  
	  
  }

  private void IniSound() {
	  //déclaration audio
	  //filePath = "Sounds/party_started.wav"; 
		try { audioPlayer = new SimpleAudioPlayer("Sounds/naruto_op1.wav"); 
		 JulPlayerB = new SimpleAudioPlayer("Sounds/jul_beu.wav"); 
		 JulPlayerJ = new SimpleAudioPlayer("Sounds/jul_jcvd.wav"); 
		//audioPlayer.play();
		}
		catch (Exception ex) 
		{ 
			System.out.println("Error with playing sound."); 
			ex.printStackTrace(); 
		
		} 
		
  }
  
  private void infoDev (Graphics g) { //affichage graphique de l'évolution des variables
	  if (mode =="infos") {
		  g.setColor(Color.black);
	  g.drawString("ANIMATION :"+animation%15+" vit "+vit+" shiX"+shiX,this.getWidth()/6, this.getHeight()/8+250);
	  g.drawString("SOUNDCHANGE "+ soundChange +" MODE :" + mode + " TEMPS musique "+ audioPlayer.time() + " JUL :" + JulPlayerJ.time(),this.getWidth()/6, this.getHeight()/8+100);
	  
	  }
  }
  
  private void textJul(Graphics g) {
	  //lyric.countTemps();
	  //lyric.appear(g, "ça veut l'port d'armes et les bras d'Jean CLaude Vandahme !", 100, 200, 50, 2);
	  if (JulPlayerJ.time() > 11000000 && JulPlayerJ.time()<13000000) { //moment où il commence à parler
		  //g.drawString("TEST",this.getWidth()/6, this.getHeight()/8+250);
		  /*lyric.countTemps(); //lance le chrono
		  lyric.setColour(50, 100, 0); //choisi la couleur du texte
		 lyric.appear(g, "Ça veut l'port d'armes ", 100, 200, 50, 2); //fait apparaître le texte à l'emplacement donné pendant un certain intervalle de temps
		 lyric.appear(g,  "Et les gros bras à Jean Claude Van Damme !", 100, 300, 50, 2); //texte, x, y, taille 50 et 2s*/
		  
		  lyric.setColour(100, 50, 0); //choisi la couleur du texte
		  lyric.appear(g, "Ça veut l'port d'armes ", 100, 200, 50); //fait apparaître le texte à l'emplacement donné 
			 lyric.appear(g,  "Et les gros bras à Jean Claude Van Damme !", 100, 300, 50); //texte, x, y et taille 50 */
	  }
	  else if(JulPlayerJ.time() > 13000000 && JulPlayerJ.time()<17000000) {
		  lyric.setColour(0, 200, 0); //choisi la couleur du texte
		  lyric.appear(g, "J'fume la beuh d'Amsterdam", 100, 200, 50); //fait apparaître le texte à l'emplacement donné 
			 lyric.appear(g,  "Celle qui est bonne, qui t'monte au crâne!", 100, 300, 50); //texte, x, y et taille 50 */
	  }
	  else if(JulPlayerJ.time() > 17000000 && JulPlayerJ.time()<22000000) {
		  lyric.setColour(0, 0, 100); //choisi la couleur du texte
		  lyric.appear(g, "Tu veux de la bonne, le petit t'écrase deux Doliprane", 100, 200, 50); //fait apparaître le texte à l'emplacement donné 
			 lyric.appear(g,  "Si dans le bis tu déconnes, le petit reviendra armé en bécane", 100, 300, 50); //texte, x, y et taille 50 */
	  }
  }
  
  private void ChangeSound(int x) { //prend une variable x et joue un son selon la valeur de x
	  if (mode == "Jul" ) {
		  if (action =="provoc") {
			  soundChange =2;
		  }
		  if (soundChange != 666 && soundChange !=2) {
		  soundChange =1;}
		  
	  }
	  if (x==0) { //joue Naruto
		  try {
		  JulPlayerJ.stop();
		  JulPlayerB.stop(); }
		  catch (Exception ex) {
			  System.out.println("Error with stopping sound."); 
				ex.printStackTrace();
		  }
		  audioPlayer.play();
	  }
	  else if (x==1) { //joue JCVD
		  try {
		  audioPlayer.stop();
		  JulPlayerB.stop(); }
		  catch (Exception ex) {
			  System.out.println("Error with stopping sound."); 
				ex.printStackTrace();
		  }
		  JulPlayerJ.play();
		  soundChange =666;
	  }
	  else if (x==2) { //joue beuh magique
		  try {
			  audioPlayer.stop();
			  JulPlayerJ.stop(); 
			  }
			  catch (Exception ex) {
				  System.out.println("Error with stopping sound."); 
					ex.printStackTrace();
			  }
			  JulPlayerB.play();
			  soundChange =666;
	  }
	  else if (x==666) { //dans la boucle, si on ne change pas de son
		  System.out.println("Pas de son a changer, tout va bien.");
		  if (mode !="Jul") {soundChange = 0;}
	  }
	  else {x=0;} //si on a une valeur incohérente par exemple dépasser 2 alors retour à la musique Naruto
  }

  

}
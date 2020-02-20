package animation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;

import java.io.File;
//pour le son
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip; class fenetre extends JFrame { //fille de JFrame qui récupère de quoi faire une fenêtre

    private Panneau pan = new Panneau();
	private boolean run = true;
    private JLabel label = new JLabel();
    private ObjKeyListener truc = new ObjKeyListener(label);
	
	
	  public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}
    
    //constructeur
  public fenetre(){ 
      //titre
    this.setTitle("Projet incroyable made by Arnaud de ouf qui tue avec des rayons lasers et tout et tout");
    //taille
    this.setSize(400, 500);
    //emplacement, (x,y) null = au centre
    this.setLocationRelativeTo(null);
    //autoriser ou non le redimensionnement
    setResizable(true);
    //mettre la fenêtre toujours au premier plan ou non
    setAlwaysOnTop(true);
    //retirer les contours ou non
    setUndecorated(false);
    //pour que ça se ferme en appuyant sur la croix
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    //la fenêtre s'affiche          
    this.setVisible(true);

    //inutile car il y a déjà une image
    //pan.setBackground(Color.ORANGE);
    /*
    //Instanciation d'un objet Jpanel donc content panel
    JPanel pan = new JPanel();
    //Définition de sa couleur de fond
    pan.setBackground(Color.ORANGE);        */
    
    //On prévient notre JFrame que notre Panneau créé avec JPanel sera son content pane
    this.setContentPane(pan);
    listen();
    mouvement();
  }

  //constructeur si on donne la taille
  public fenetre(int width, int height){ 
    this.setTitle("Projet incroyable made by Arnaud de ouf qui tue avec des rayons lasers et tout et tout");
    this.setSize(width, height);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
    this.setVisible(true);
    this.setContentPane(pan);
    
    listen();
    mouvement();
  }
  
  private void listen() {
	  //label et truc initialisés dans la classe !
	    // ajout d'un seul composant dans cette fenêtre : un JLabel
	    
	    add(label, BorderLayout.CENTER);
	     
	    // ajoute un écouteur d'événements personnalisé à la fenêtre
	    
	    addKeyListener(truc);
  }
  
  //rappelle la fonction d'affichage dans Panneau, sleep plus ou moins grand permet de gérer un peu les fps 
  private void mouvement(){
	    while (run == true){
	      int x = pan.getPosX(), y = pan.getPosY(), shiX = pan.getShiX(), vitS = pan.getVit();
	      x++;
	      y++;
	      vitS =truc.getVitS();
		  shiX = truc.getShiX();
	      
	      pan.setPosX(x);
	      pan.setPosY(y);
	      pan.setShiX(shiX);
	      pan.setVit(vitS);
	      
	      pan.setSide(truc.isSide() ); //pour le boolean coté
	      pan.setAction(truc.getAction() ); //pour le String action
	      pan.setMode(truc.getMode() ); //pour le String mode
	      pan.setBug(truc.getBug() );
	      pan.setTabMode(truc.getTabMod() );
	      pan.setEventHold(truc.isEventHold() );
	      pan.repaint();  
	      try {
	        Thread.sleep(10);
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
	      
	      //Si nos coordonnées arrivent au bord de notre composant
	      //On réinitialise
	      if(x == pan.getWidth() || y == pan.getHeight()){
	        pan.setPosX(-50);
	        pan.setPosY(-50);
	      }
	      
	    }
	  } 


  
}
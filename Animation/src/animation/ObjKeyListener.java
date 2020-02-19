package animation;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;

public class ObjKeyListener implements KeyListener {
	  private final JLabel label;
	  private int shiX =0;
	  private int vitS =0;
	  private int speed =1;
	  private int bug =0;
	  private boolean Side;
	  private String action = "Walk";
	  private String mode = "Normal";
	  
	  public int getBug() {return bug;}
		public String getMode() {
			return mode;
		}

		public void setMode(String mode) {
			this.mode = mode;
		}
	  
		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}
	  
	  public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isSide() {return Side;}
	     
	    public int getVitS() {
		return vitS;
	}

	public void setVitS(int vitS) {
		this.vitS = vitS;
	}

		public int getShiX() {
		return shiX;
	}

	public void setShiX(int shiX) {
		this.shiX = shiX;
	}

		public ObjKeyListener(JLabel label_) {
	        label = label_;
	    }
	 
	    public void keyPressed(KeyEvent e) {
	        label.setText("Touche pressée : " + e.getKeyCode() + 
	                " (" + e.getKeyChar() + ")" +"shix vaut :" +shiX );
	                if (e.getKeyCode()==65) {
	                    System.out.println("Vous avez appuyé sur a !!!");
	                }
	                else if (e.getKeyCode()==39) { //flèche de droite
	                	vitS+=speed;
	                	if (getMode() == "Bizarre" ) { //en mdoe Bizarre il accélère
	                	shiX+=10+vitS;
	                	} 
	                	else {
	                		shiX+=15;
	                	}
	                	Side = true;
	                	action = "Walk";
	                }
	                else if (e.getKeyCode()==37) { //flèche de gauche
	                	vitS-=speed;
	                	if (getMode() == "Bizarre" ) { //en mdoe Bizarre il accélère
		                	shiX+=10+vitS;
		                	} 
		                	else {
		                		shiX-=15;
		                	}
	                	Side = false;
	                	action = "Walk";	
	                }
	                else if (e.getKeyCode()==84) { //si on appuit sur t comme TITOUAN
	                	speed +=1;
	                }
	                else if (e.getKeyCode()==82) { //si on appuit sur r comme ROMINOU
	                	speed-=1;
	                }
	                
	                else if (e.getKeyCode()==77) { //si on appuit sur m
	                	shiX = 100;
	                	mode = "Bizarre";
	                }
	                else if (e.getKeyCode()==KeyEvent.VK_U) {
	                	mode = "Normal";
	                	bug =0;
	                }
	                else if (e.getKeyCode()==KeyEvent.VK_K) {
	                	action = "kick";
	                }
	                else if (e.getKeyCode()==KeyEvent.VK_L) {
	                	action = "lamppost";
	                }
	                else if (e.getKeyCode()==KeyEvent.VK_P) {
	                	action = "provoc";
	                }
	                else if (e.getKeyCode() == KeyEvent.VK_C) {
	                	mode = "Control";
	                }
	                else { vitS=0;
	                action = "Walk";}
	    }
	 
	    public void keyReleased(KeyEvent e) {
	        label.setText("Touche relâchée : " + e.getKeyCode() +
	                " (" + e.getKeyChar() + ")" +"VitS vaut : "+ vitS);
	        
	        //lorsque l'on relache la touche la vitesse redevient 0
	        if (e.getKeyCode()==39) { //flèche de droite
            	vitS =0;
            }
            else if (e.getKeyCode()==37) { //flèche de droite
            	vitS =0;
            }
            else if (e.getKeyCode()==KeyEvent.VK_K) { //après avoir fini une action on retrouve notre position de base
            	if (getMode()!="Crazy") { //en mode crazy animation en boucle
            	action = "Walk";}
            }
            else if (e.getKeyCode()==KeyEvent.VK_L) {
            	if (getMode()!="Crazy") { //en mode crazy animation en boucle
            	action = "Walk"; }
            }
            else if (e.getKeyCode()==KeyEvent.VK_P) {
            	if (getMode()!="Crazy") { //en mode crazy animation en boucle
            	action = "Walk"; }
            }
            else if (e.getKeyCode()==KeyEvent.VK_B) {
            	bug+=1;
            }
	    }
	 
	    public void keyTyped(KeyEvent e) {
	        // on ne fait rien
	    }
}

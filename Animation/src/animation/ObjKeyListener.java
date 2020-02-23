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
	  private boolean eventHold = false;
	  private String action = "Walk";
	  private String mode = "Normal";
	  //tableaux de Modes et d'Animations
	  private int iterator =0; //pour parcourir les tableaux

	private String tabMod[] = {"Normal", "Bizarre", "Simon", "Antoine", "Rominou", "Jul", "QTE"};
	 private String tabAnim[] = {"Basic", "Crazy", "Push"};
	  
	  //augmente un tableau et revient à 0 si la valeur dépasse la taille du tableau
	  public void tabUp(String tab[]) {
		  iterator++;
		  if (iterator>tab.length-1) {iterator =0;}
	  }
	  
	  public int getBug() {return bug;}
	  
		public boolean isEventHold() {
			return eventHold;
		}

	  
	  public String getMode() {
			return mode;
		}
		
	  public String[] getTabMod() {
				return tabMod;
			}
	  public void setTabMod(String[] tabMod) {
				this.tabMod = tabMod;
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
	                	vitS+=speed;
	                	if (getMode() == "Bizarre" ) { //en mdoe Bizarre il accélère
		                	shiX+=10-vitS;
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
	                else if(e.getKeyCode()==KeyEvent.VK_H) {
	                	eventHold = true;
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
            else if (e.getKeyCode()==KeyEvent.VK_B) { //mode bug
            	bug+=1;
            }
            else if (e.getKeyCode()==77) { //si on appuit sur m, changement de mode
            	shiX = 100;
            	tabUp(tabMod); //se déplace de 1 dans tabMod
            	mode = tabMod[iterator]; //le mode devient le texte présent dans tabMod
            }
            else if (e.getKeyCode()==KeyEvent.VK_V) { //mode bug
            	mode = "ListeMod";
            }
            else if(e.getKeyCode()==KeyEvent.VK_I) {
            	mode ="infos";
            }
            else if(e.getKeyCode()==KeyEvent.VK_H) {
            	eventHold = false;
            }
	        
	    }
	 
	    public void keyTyped(KeyEvent e) {
	        // on ne fait rien
	    }
}

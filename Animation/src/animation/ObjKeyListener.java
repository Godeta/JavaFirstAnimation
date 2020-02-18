package animation;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;

public class ObjKeyListener implements KeyListener {
	  private final JLabel label;
	  private int shiX =0;
	  private int vitS =0;
	     
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
	        label.setText("Touche press�e : " + e.getKeyCode() + 
	                " (" + e.getKeyChar() + ")" +"shix vaut :" +shiX );
	                if (e.getKeyCode()==65) {
	                    System.out.println("Vous avez appuy� sur a !!!");
	                }
	                else if (e.getKeyCode()==39) { //fl�che de droite
	                	vitS+=1;
	                	shiX+=10+vitS;
	                }
	                else if (e.getKeyCode()==37) { //fl�che de droite
	                	vitS+=1;
	                	shiX-=10+vitS;
	                	
	                }
	                else { vitS=0;}
	    }
	 
	    public void keyReleased(KeyEvent e) {
	        label.setText("Touche rel�ch�e : " + e.getKeyCode() +
	                " (" + e.getKeyChar() + ")" +"VitS vaut : "+ vitS);
	        
	        //lorsque l'on relache la touche la vitesse redevient 0
	        if (e.getKeyCode()==39) { //fl�che de droite
            	vitS =0;
            }
            else if (e.getKeyCode()==37) { //fl�che de droite
            	vitS =0;
            }
	    }
	 
	    public void keyTyped(KeyEvent e) {
	        // on ne fait rien
	    }
}

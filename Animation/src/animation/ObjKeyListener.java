package animation;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;

public class ObjKeyListener implements KeyListener {
	  private final JLabel label;
	  private int shiX =0;
	     
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
	                " (" + e.getKeyChar() + ")" +"shix vaut :" +shiX);
	                if (e.getKeyCode()==65) {
	                    System.out.println("Vous avez appuyé sur a !!!");
	                }
	                if (e.getKeyCode()==39) { //flèche de droite
	                	shiX+=10;
	                }
	                if (e.getKeyCode()==37) { //flèche de droite
	                	shiX-=10;
	                }
	    }
	 
	    public void keyReleased(KeyEvent e) {
	        label.setText("Touche relâchée : " + e.getKeyCode() +
	                " (" + e.getKeyChar() + ")");
	    }
	 
	    public void keyTyped(KeyEvent e) {
	        // on ne fait rien
	    }
}

package animation;
import javax.swing.JFrame;
import java.awt.Dimension;

public class MainClass {

	public static void main(String[] args){
		//récupère les dimensions de l'écran, environ
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
	      //créer une fenêtre
	    JFrame fen = new fenetre(width,height);
	    
	}
	
}

package animation;
import javax.swing.JFrame;
import java.awt.Dimension;

public class MainClass {

	public static void main(String[] args){
		//r�cup�re les dimensions de l'�cran, environ
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
	      //cr�er une fen�tre
	    JFrame fen = new fenetre(width,height);
	    
	}
	
}

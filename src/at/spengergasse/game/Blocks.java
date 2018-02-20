package at.spengergasse.game;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class Blocks {
	
	 public static void Rectangle newBlock(int x, int y) {
	 Rectangle rectangle = new Rectangle();
     rectangle.setX(600);
     rectangle.setY(0);
     rectangle.setWidth(x);
     rectangle.setHeight(y);
     rectangle.setFill(Color.BLACK);
     return rectangle;
	 }

	 private static boolean checkBounds(Rectangle block) {
		 if (block.getBoundsInParent().intersects(block.getBoundsInParent())) {
	    	   return true;      //collision
		 } else {
			 return false;    //no collision
		 }
	    }	

   }

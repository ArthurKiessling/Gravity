package at.spengergasse.game;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class Blocks {
	
	 public static Rectangle newBlock(int x, int y, int w,int h) {
	 Rectangle rectangle = new Rectangle();
     rectangle.setX(w);
     rectangle.setY(h);
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
	 public static void generate() {
			for(int idx=0, h=-120; idx<=6;idx++,h+=160){
				for(int x=0, w=0;x<3;x++,w+=310) {
					Rectangle r= newBlock(180,20,w,h);
					Game.root.getChildren().addAll(r);
					Game.rectangle.add(r);
				}
			}
		}
	 public static void down() {
		 for(int idx=0; idx<Game.rectangle.size();idx++){
			 double h= Game.rectangle.get(idx).getY()+1;
			 Game.rectangle.get(idx).setY(h);
		 }
	 }
	 public static void check() {
		 for(int idx=0; idx<Game.rectangle.size();idx++){
			 if(Game.rectangle.get(idx).getY()>=980) {
				 Game.rectangle.get(idx).setY(-120);
			 }
		 }
	 }
	 public static void handle(long now) {
		 down();
		 check();
	 }
   }

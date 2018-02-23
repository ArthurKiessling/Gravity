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

	 public static void checkBounds() {
		 for(int idx =0; idx <Game.rectangle.size();idx++) {
		 if (Player.player.getBoundsInParent().intersects(Game.rectangle.get(idx).getBoundsInParent())) {
	    	  //return true;      //collision
	    	  System.out.println("Collide ============= Collide");
		 } else {
			//return false;    //no collision
			 System.out.println("00");
		 }
		 }
		 //return false;
		 System.out.println("00");
	    }	
	    
	 public static void generate() {
		 Rectangle r;
			for(int idx=0, h=-80; idx<=5;idx++,h+=200){
				for(int x=0, w=0;x<3;x++,w+=310) {
					if(x==1)r= newBlock(180,20,w,h+80);
					else r= newBlock(180,20,w,h);
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
				 Game.rectangle.get(idx).setY(-200);
			 }
		 }
	 }
	 public static void handle(long now) {
		 down();
		 check();
		// checkBounds();
	 }
   }
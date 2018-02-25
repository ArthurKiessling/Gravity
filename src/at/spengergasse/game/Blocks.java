package at.spengergasse.game;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class Blocks {
	
	private static final String BLOCK_IMAGE_LOC ="block.png";
	
	 public static Node newBlock(int x, int y) {
	 Image blockImg= new Image(BLOCK_IMAGE_LOC);
	 Node block=new ImageView(blockImg);
	 block.relocate(x, y);
     return block;
	 }

	    
	 public static void generate() {
		 Node r;
			for(int idx=0, h=-80; idx<=5;idx++,h+=200){
				for(int x=0, w=0;x<3;x++,w+=310) {
					if(x==1)r= newBlock(w,h+80);
					else r= newBlock(w,h);
					Game.root.getChildren().addAll(r);
					Game.block.add(r);
				}
			}
		}
	 public static void down() {
		 for(int idx=0; idx<Game.block.size();idx++){
			 double h= Game.block.get(idx).getBoundsInParent().getMinY()+1;
			 Game.block.get(idx).relocate(Game.block.get(idx).getBoundsInParent().getMinX(),h);
		 }
	 }
	 public static void check() {
		 for(int idx=0; idx<Game.block.size();idx++){
			 if(Game.block.get(idx).getBoundsInParent().getMinY()>=980) {
				 Game.block.get(idx).relocate(Game.block.get(idx).getBoundsInParent().getMinX(),-200);
			 }
		 }
	 }
	 public static void handle(long now) {
		 down();
		 check();
		// checkBounds();
	 }
   }
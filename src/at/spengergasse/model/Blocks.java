package at.spengergasse.model;


import at.spengergasse.gui.Controls;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Blocks {


	private static String BLOCK_IMAGE_LOC;
	public static int[] lifes;
	public static Image icon ;
	
	 public static Node newBlock(int x, int y,String BlockImg) {
	 Image blockImg= new Image(BLOCK_IMAGE_LOC);
	 Node block=new ImageView(blockImg);
	 block.relocate(x, y);
     return block;
	 }

	    
	 public static void generate(String BlockImg,Group root) {
		 BLOCK_IMAGE_LOC =BlockImg;
		 Node r;
			for(int idx=0, h=-80; idx<=5;idx++,h+=200){
				for(int x=0, w=0;x<3;x++,w+=310) {
					if(x==1)r= newBlock(w,h+80,BlockImg);
					else r= newBlock(w,h,BlockImg);
					root.getChildren().addAll(r);
					Controls.block.add(r);
					
				}
			}
		}

	 public static void check() {
		 for(int idx=0; idx<Controls.block.size();idx++){
			 if(blockBounds(idx).getMinY()>=980) {
				 Controls.block.get(idx).relocate(blockBounds(idx).getMinX(),-200);
			 }
		 }
	 }
	 public static void down() {
		 for(int idx=0; idx<Controls.block.size();idx++){
			 double h= blockBounds(idx).getMinY()+0.6f;
			 Controls.block.get(idx).relocate(blockBounds(idx).getMinX(),h);
		 }
	
	 }


	private static Bounds blockBounds(int idx) {
		return Controls.block.get(idx).getBoundsInParent();
	}
	 public static void handle(long now) {
		 down();
		 check();
	 }

	 public static void heart(Group root) {
		 lifes = new int[10];
		 for(int i = 0; i< 5;i++) {
			 Image heartimage = new Image("img/blocks/heart.png",32,32,false,false);
			 Node heart = new ImageView(heartimage);
			 lifes[i]=root.getChildren().size();
			 root.getChildren().add(heart);
			 heart.relocate(i*30, 5);
			 
		 }
		 for(int i = 0; i< 5;i++) {
			 Image heartimage = new Image("img/blocks/heart.png",32,32,false,false);
			 Node heart = new ImageView(heartimage);
			 lifes[5+i]=root.getChildren().size();
			 root.getChildren().add(heart);
			 heart.relocate(650+i*30, 5);
		 }
	}


	
}
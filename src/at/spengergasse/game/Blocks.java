package at.spengergasse.game;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class Blocks {
	private static String BACKGROUND_IMAGE_LOC ="img/Background.png";
    private static Image backgroundImage;
    public static Node background;
    
	private static final String BLOCK_IMAGE_LOC ="img/block.png";
	public static int[] lifes;
	
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

	 public static void check() {
		 for(int idx=0; idx<Game.block.size();idx++){
			 if(Game.block.get(idx).getBoundsInParent().getMinY()>=980) {
				 Game.block.get(idx).relocate(Game.block.get(idx).getBoundsInParent().getMinX(),-200);
			 }
		 }
	 }
	 public static void down() {
		 for(int idx=0; idx<Game.block.size();idx++){
			 double h= Game.block.get(idx).getBoundsInParent().getMinY()+0.6;
			 Game.block.get(idx).relocate(Game.block.get(idx).getBoundsInParent().getMinX(),h);
		 }
	 }
	 public static void handle(long now) {
		 down();
		 check();
		// checkBounds();
	 }
	 public static void Background(Group root) {
			backgroundImage = new Image(BACKGROUND_IMAGE_LOC);
	        background = new ImageView(backgroundImage);
			root.getChildren().addAll(background);
			background.relocate(0, 0);
		}
	 public static void heart(Group root) {
		 lifes = new int[10];
		 Node balken = new Rectangle(800,40,Color.BLACK);
		 root.getChildren().add(balken);
		 balken.relocate(0, 0);
		 for(int i = 0; i< 5;i++) {
			 Image heartimage = new Image("img/heart.png",32,32,false,false);
			 Node heart = new ImageView(heartimage);
			 lifes[i]=root.getChildren().size();
			 root.getChildren().add(heart);
			 heart.relocate(i*30, 5);
			 
		 }
		 for(int i = 0; i< 5;i++) {
			 Image heartimage = new Image("img/heart.png",32,32,false,false);
			 Node heart = new ImageView(heartimage);
			 lifes[5+i]=root.getChildren().size();
			 root.getChildren().add(heart);
			 heart.relocate(650+i*30, 5);
		 }
	}
	 
	
}
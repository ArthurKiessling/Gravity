package at.spengergasse.game;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class Blocks {
	public static float speed=0.6f;
	private static String BACKGROUND_IMAGE_LOC ="img/blocks/Background.png";
    private static Image backgroundImage;
    public static Node background;
    
	private static final String BLOCK_IMAGE_LOC ="img/blocks/block.png";
	public static int[] lifes;
	
	static Node[] weapons;
	
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
			 double h= Game.block.get(idx).getBoundsInParent().getMinY()+speed;
			 Game.block.get(idx).relocate(Game.block.get(idx).getBoundsInParent().getMinX(),h);
		 }
	
	 }
	 public static void handle(long now) {
		 down();
		 check();
		weaponsDown();
	 }
	 public static void Background(Group root) {
			backgroundImage = new Image(BACKGROUND_IMAGE_LOC);
	        background = new ImageView(backgroundImage);
			root.getChildren().addAll(background);
			background.relocate(0, 0);
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

	 public static void weapons(Group root) {
		 weapons =new Node[2];
		 for(int i = 0; i<2;i++) {
			 Image weaponsimage = new Image("img/weapons/weapon2.png");
			 Node weapons = new ImageView(weaponsimage);
			 root.getChildren().add(weapons);
			 Blocks.weapons[i]=weapons;
			 weapons.relocate(630*i, 60);
		 }
	}
	 public static void weaponsDown() {
		 if(checkWeapons(weapons[0])==1) {
			 weapons[0].relocate(Game.player.player.getBoundsInParent().getMaxX()-20,Game.player.player.getBoundsInParent().getMinY()+10);
		 }
		 if(checkWeapons(weapons[0])==2) {
			 weapons[0].relocate(Game.player2.player.getBoundsInParent().getMinX()-20,Game.player2.player.getBoundsInParent().getMinY()+10);
		 }
		 if(checkWeapons(weapons[1])==1) {
			 weapons[1].relocate(Game.player.player.getBoundsInParent().getMaxX()-20,Game.player.player.getBoundsInParent().getMinY()+10);
		 }
		 if(checkWeapons(weapons[1])==2) {
			 weapons[1].relocate(Game.player2.player.getBoundsInParent().getMinX()-20,Game.player2.player.getBoundsInParent().getMinY()+10);
		 }
	 }
	
	
	  public static int checkWeapons(Node weapon) {
				 if(weapon.getBoundsInParent().intersects(Game.player.player.getBoundsInParent().getWidth(), Game.player.player.getBoundsInParent().getHeight(),Game.player.player.getBoundsInParent().getMinX(),Game.player.player.getBoundsInParent().getMinY())) {
					 return 1;
				 }else
				 if(weapon.getBoundsInParent().intersects(Game.player2.player.getBoundsInParent().getWidth(), Game.player2.player.getBoundsInParent().getHeight(),Game.player2.player.getBoundsInParent().getMinX(),Game.player2.player.getBoundsInParent().getMinY())) {
					 return 2;
				 }
			return 3; 
		}
}
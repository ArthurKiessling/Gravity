/**
 * 
 */
package at.spengergasse.game;

import javafx.scene.Node;

/**
 * @author Arthur Kiessling
 *
 */
public class Physiks {
	
	
	public static boolean checkBoundsDown(Node player) {
		for(int idx=0; idx <Game.block.size();idx++) {
			if (player.getBoundsInParent().intersects(Game.block.get(idx).getBoundsInParent().getMinX() , Game.block.get(idx).getBoundsInParent().getMinY(), 180, 20)&&
				player.getBoundsInParent().getMaxY() < Game.block.get(idx).getBoundsInParent().getMaxY()&&
				player.getBoundsInParent().getMaxX() <= Game.block.get(idx).getBoundsInParent().getMaxX()+40&&
			    player.getBoundsInParent().getMinX() >= Game.block.get(idx).getBoundsInParent().getMinX()-40) {
				 return true;      //collision
			 } 
		} 
		return false;
	}
	
	 public static boolean checkBoundsUp(Node player) {
		 for(int idx =0; idx <Game.block.size();idx++) {
	        if(player.getBoundsInParent().getMinY()>= Game.block.get(idx).getBoundsInParent().getMaxY()&&
	        	player.getBoundsInParent().getMinY()<= Game.block.get(idx).getBoundsInParent().getMaxY()+6&&
	            player.getBoundsInParent().getMaxX() <= Game.block.get(idx).getBoundsInParent().getMaxX()+40&&
	            player.getBoundsInParent().getMinX() >= Game.block.get(idx).getBoundsInParent().getMinX()-40)
	          return true;
		 }
		return false;
	}
	 
	 public static boolean checkBoundsLeft(Node player) {
		 for(int idx =0; idx <Game.block.size();idx++) {
	        if(player.getBoundsInParent().getMaxY()<= Game.block.get(idx).getBoundsInParent().getMaxY()+player.getBoundsInParent().getHeight()&&
	        	player.getBoundsInParent().getMinY()>= Game.block.get(idx).getBoundsInParent().getMinY()-player.getBoundsInParent().getHeight()&&
	        	player.getBoundsInParent().intersects(Game.block.get(idx).getBoundsInParent().getMinX() , Game.block.get(idx).getBoundsInParent().getMinY(), 180, 20)&&
	            player.getBoundsInParent().getMinX() >= Game.block.get(idx).getBoundsInParent().getMaxX()&&
	            player.getBoundsInParent().getMaxX()<= Game.block.get(idx).getBoundsInParent().getMaxX()+player.getBoundsInParent().getWidth())
	          return true;
		 }
		return false;
	}
	 
	 public static boolean checkBoundsRight(Node player) {
		 for(int idx =0; idx <Game.block.size();idx++) {
	        if(player.getBoundsInParent().getMaxY()<= Game.block.get(idx).getBoundsInParent().getMaxY()+player.getBoundsInParent().getHeight()&&
	        	player.getBoundsInParent().getMinY()>= Game.block.get(idx).getBoundsInParent().getMinY()-player.getBoundsInParent().getHeight()&&
	        	player.getBoundsInParent().intersects(Game.block.get(idx).getBoundsInParent().getMinX() ,Game.block.get(idx).getBoundsInParent().getMinY(), 180, 20)&&
	            player.getBoundsInParent().getMaxX() <= Game.block.get(idx).getBoundsInParent().getMinX()&&
	            player.getBoundsInParent().getMinX() >= Game.block.get(idx).getBoundsInParent().getMinX()-player.getBoundsInParent().getWidth())
	          return true;
		 }
		return false;
	}
	 
	 public static void moveHeroBy(double dx,double dy,Node player) {
	        if (dx == 0&& dy==0) return;
	        final double cx = player.getBoundsInLocal().getWidth()  / 2;
	        double x = cx + player.getLayoutX() + dx;
	        final double cy = player.getBoundsInLocal().getHeight()  / 2;
	        double y = cy + player.getLayoutY() + dy;
	        moveHeroTo(x,y,player);
	    }

	    public static void moveHeroTo(double x,double y, Node player) {
	        final double cx = player.getBoundsInLocal().getWidth()  / 2;
	        final double cy = player.getBoundsInLocal().getHeight()  / 2;
	        if (x - cx >= 0 && x + cx <= Game.W&&y- cy >= 0 && y + cy <= Game.H) {
	            player.relocate(x - cx,y-cy);}
	    }
	    
}

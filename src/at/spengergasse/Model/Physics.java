
/**
 * 
 */
package at.spengergasse.Model;

import at.spengergasse.Gui.Game;
import at.spengergasse.Gui.Start;
import javafx.geometry.Bounds;
import javafx.scene.Node;

/**
 * @author Arthur Kiessling
 *
 */
public class Physics {
	
	
	public static boolean checkBoundsDown(Node player) {
		for(int idx=0; idx <Game.block.size();idx++) {
			if (checkIntersects(player,idx)&&
				playerBounds(player).getMaxY()<blockBounds(idx).getMaxY()&& xCheck(player,idx)) {
				   return true;
			 } 
		} 
		return false;
	}

	
	
	 public static boolean checkBoundsUp(Node player) {
		 for(int idx =0; idx <Game.block.size();idx++) {
	        if(playerBounds(player).getMinY()>= blockBounds(idx).getMaxY()&&
	        	playerBounds(player).getMinY()<= blockBounds(idx).getMaxY()+6&&
	        	xCheck(player,idx))
	        	return true;
	        }
		return false;
		 }
	 
	 public static boolean checkBoundsLeft(Node player) {
		 for(int idx =0; idx <Game.block.size();idx++) {
	        if(yCheck(player,idx)&&checkIntersects(player,idx)&&
	            playerBounds(player).getMinX() >= blockBounds(idx).getMaxX()&&
	            playerBounds(player).getMaxX()<= blockBounds(idx).getMaxX()+playerBounds(player).getWidth())
	          return true;
		 }
		return false;
	}
	 
	 public static boolean checkBoundsRight(Node player) {
		 for(int idx =0; idx <Game.block.size();idx++) {
	        if(yCheck(player,idx)&&checkIntersects(player,idx)&&
	            playerBounds(player).getMaxX() <= blockBounds(idx).getMinX()&&
	            playerBounds(player).getMinX() >= blockBounds(idx).getMinX()-playerBounds(player).getWidth())
	          return true;
		 }
		return false;
	}
	 public static boolean checkTwo(Node player,Node other) {
	        if(playerBounds(player).intersects(playerBounds(other))){
	        	return true;
	        }
	        return false;
	}
	 private static Bounds playerBounds(Node player) {
		return player.getBoundsInParent();
	}

	private static Bounds blockBounds(int idx) {
		return Game.block.get(idx).getBoundsInParent();
	}
	
	private static boolean xCheck(Node player,int idx) {
		if(playerBounds(player).getMaxX() <=  blockBounds(idx).getMaxX()+40&&
		 playerBounds(player).getMinX() >=  blockBounds(idx).getMinX()-40) {
			return true;
		}
		return false;
	}
	private static boolean yCheck(Node player,int idx) {
		if(playerBounds(player).getMaxY()<= blockBounds(idx).getMaxY()+playerBounds(player).getHeight()&&
        	playerBounds(player).getMinY()>= blockBounds(idx).getMinY()-playerBounds(player).getHeight()) {
    			return true;
    		}
    		return false;
    	}
	
	private static boolean checkIntersects(Node player,int idx) {
		if(playerBounds(player).intersects( blockBounds(idx).getMinX() , blockBounds(idx).getMinY(), 180, 20)) {
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
	        if (x - cx >= 0 && x + cx <= Start.W&&y- cy >= 0&& y + cy <= Start.H) {
	            player.relocate(x - cx,y-cy);}
	    }
	    
}

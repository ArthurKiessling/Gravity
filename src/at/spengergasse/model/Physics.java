
/**
 * 
 */
package at.spengergasse.model;

import at.spengergasse.gui.Controls;
import at.spengergasse.gui.Start;
import javafx.geometry.Bounds;
import javafx.scene.Node;

/**
 * @author Arthur Kiessling
 *
 */
public class Physics {
	
	/**
	 * Gecheckt ob ein Spieler runterfallen kann
	 * @param player
	 * @return
	 */
	public static boolean checkBoundsDown(Node player) {
		for(int idx=0; idx <Controls.block.size();idx++) {
			if (checkIntersects(player,idx)&&
				playerBounds(player).getMaxY()<blockBounds(idx).getMaxY()&& xCheck(player,idx)) {
				   return true;
			 } 
		} 
		return false;
	}

	
	/**
	 * Gecheckt ob ein Spieler springen darf
	 * @param player
	 * @return 
	 */
	 public static boolean checkBoundsUp(Node player) {
		 for(int idx =0; idx <Controls.block.size();idx++) {
	        if(playerBounds(player).getMinY()>= blockBounds(idx).getMaxY()&&
	        	playerBounds(player).getMinY()<= blockBounds(idx).getMaxY()+6&&
	        	xCheck(player,idx))
	        	return true;
	        }
		return false;
		 }
	 
	 /**
	  * Gecheckt ob ein Spieler nach Links gehen darf
	  * mit Intersects 
	  * @param player
	  * @return
	  */
	 public static boolean checkBoundsLeft(Node player) {
		 for(int idx =0; idx <Controls.block.size();idx++) {
	        if(yCheck(player,idx)&&checkIntersects(player,idx)&&
	            playerBounds(player).getMinX() >= blockBounds(idx).getMaxX()&&
	            playerBounds(player).getMaxX()<= blockBounds(idx).getMaxX()+playerBounds(player).getWidth())
	          return true;
		 }
		return false;
	}
	 
	 /**
	  * Gecheckt ob ein Spieler nach Rechts gehen darf
	  * mit Intersects 
	  * @param player
	  * @return 
	  */
	 public static boolean checkBoundsRight(Node player) {
		 for(int idx =0; idx <Controls.block.size();idx++) {
	        if(yCheck(player,idx)&&checkIntersects(player,idx)&&
	            playerBounds(player).getMaxX() <= blockBounds(idx).getMinX()&&
	            playerBounds(player).getMinX() >= blockBounds(idx).getMinX()-playerBounds(player).getWidth())
	          return true;
		 }
		return false;
	}
	 
	 /**
	  * Intersect zwischen zwei Spielern wird gecheckt
	  * @param player
	  * @param other
	  * @return
	  */
	 public static boolean checkTwo(Node player,Node other) {
	        if(playerBounds(player).intersects(playerBounds(other))){
	        	return true;
	        }
	        return false;
	}
	 
	 /**
	  * Bounds eines Spielers werden gegetet
	  * @param player
	  * @return
	  */
	 private static Bounds playerBounds(Node player) {
		return player.getBoundsInParent();
	}
	 
	 /**
	  * Bounds eines Blocks werden gegetet
	  */
	private static Bounds blockBounds(int idx) {
		return Controls.block.get(idx).getBoundsInParent();
	}
	
	/**
	 * Dazu da um eine X-Bereich eines Blockes mit einem Player zu vergleichen
	 * @param player
	 * @param idx
	 * @return
	 */
	private static boolean xCheck(Node player,int idx) {
		if(playerBounds(player).getMaxX() <=  blockBounds(idx).getMaxX()+40&&
		 playerBounds(player).getMinX() >=  blockBounds(idx).getMinX()-40) {
			return true;
		}
		return false;
	}
	
	/**
	 * Dazu da um eine Y-Bereich eines Blockes mit einem Player zu vergleichen
	 * @param player
	 * @param idx
	 * @return
	 */
	private static boolean yCheck(Node player,int idx) {
		if(playerBounds(player).getMaxY()<= blockBounds(idx).getMaxY()+playerBounds(player).getHeight()&&
        	playerBounds(player).getMinY()>= blockBounds(idx).getMinY()-playerBounds(player).getHeight()) {
    			return true;
    		}
    		return false;
    	}
	
	/**
	 * Intesects zwischen einem Spieler und einem Block
	 * @param player
	 * @param idx
	 * @return
	 */
	private static boolean checkIntersects(Node player,int idx) {
		if(playerBounds(player).intersects( blockBounds(idx).getMinX() , blockBounds(idx).getMinY(), 180, 20)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Für einen Spieler
	 * die richtigen x,y Koordianten werden ausgerechnet
	 * @param dx
	 * @param dy
	 * @param player
	 */
	 public static void moveHeroBy(double dx,double dy,Node player) {
	        if (dx == 0&& dy==0) return;
	        final double cx = player.getBoundsInLocal().getWidth()  / 2;
	        double x = cx + player.getLayoutX() + dx;
	        final double cy = player.getBoundsInLocal().getHeight()  / 2;
	        double y = cy + player.getLayoutY() + dy;
	        moveHeroTo(x,y,player);
	    }
	 /**
	  * der Spieler wird mit den neuen Koordinaten relocatet
	  * @param x
	  * @param y
	  * @param player
	  */
	    public static void moveHeroTo(double x,double y, Node player) {
	        final double cx = player.getBoundsInLocal().getWidth()  / 2;
	        final double cy = player.getBoundsInLocal().getHeight()  / 2;
	        if (x - cx >= 0 && x + cx <= Start.W&&y- cy >= 0&& y + cy <= Start.H) {
	            player.relocate(x - cx,y-cy);}
	    }
	    
}

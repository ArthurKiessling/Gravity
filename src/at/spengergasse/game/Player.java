/**
 * 
 */
package at.spengergasse.game;
import  javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * @author Arthur Kiessling
 *
 */
public class Player{
	private static final String PLAYER_IMAGE_LOC ="Hero-icon.png";

    private static Image playerImage;
    static Node player;

    static boolean up;
    static boolean down;
    static boolean goEast;
	static boolean goWest;
	static boolean running;
	
    public static void move(Group root) throws Exception {
        playerImage = new Image(PLAYER_IMAGE_LOC);
        player = new ImageView(playerImage);
        root.getChildren().add(player);
        moveHeroTo(0,0);
       
    }		
    
    public static void rightStart() {
		goEast  = true;
	}

    public static boolean leftStart() {
		return goWest  = true;
	}
    public static void rightStop() {
		goEast  = false;
	}

    public static void leftStop() {
		goWest  = false;
	}
    public static void runningStart() {
  		running = true;
  	}

      public static void runningStop() {
  		running  = false;
  	}
     public static void jumpStart() {
    	 up=true;
     }
     public static void downStart() {
    	 down=true;
     }
     public static void jumpStop() {
    	 up=false;
     }
     public static void downStop() {
    	 down=false;
     }
    public static void handle(long now) {
        double dx = 0, dy=0, x=2;
         if (goEast)  dx += 3;
         if (goWest)  dx -= 3;
         if(up&&!checkBoundsUp()) dy-=5;
         if(checkBoundsDown())x=1;
         if (running) { dx *= 2;dy*=2;}
           moveHeroBy(dx,dy+x);
          }
    
    public static void Scene() {
    Game.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case A:  Player.leftStart(); break;
                case D: Player.rightStart(); break;
                case W:  Player.jumpStart(); break;
                case SHIFT: Player.runningStart( ); break;
            }
        }
    });
    Game.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case A:  Player.leftStop(); break;
                case D: Player.rightStop(); break;
                case W:  Player.jumpStop(); break;
                case SHIFT: Player.runningStop(); break;
            }
        }
    });
    }


    private static void moveHeroBy(double dx,double dy) {
        if (dx == 0&& dy==0) return;
        final double cx = player.getBoundsInLocal().getWidth()  / 2;
        double x = cx + player.getLayoutX() + dx;
        final double cy = player.getBoundsInLocal().getHeight()  / 2;
        double y = cy + player.getLayoutY() + dy;
        moveHeroTo(x,y);
    }

    private static void moveHeroTo(double x,double y) {
        final double cx = player.getBoundsInLocal().getWidth()  / 2;
        final double cy = player.getBoundsInLocal().getHeight()  / 2;
        if (x - cx >= 0 && x + cx <= Game.W&&y- cy >= 0 && y + cy <= Game.H) {
            player.relocate(x - cx,y-cy);}
    }
    
	private static boolean checkBoundsDown() {
		for(int idx=0; idx <Game.rectangle.size();idx++) {
			if (player.getBoundsInParent().intersects(Game.rectangle.get(idx).getBoundsInParent().getMinX() , Game.rectangle.get(idx).getBoundsInParent().getMinY(), 180, 20)&&
				player.getBoundsInParent().getMaxX() <= Game.rectangle.get(idx).getBoundsInParent().getMaxX()) {
				 return true;      //collision
			 } 
		} 
		return false;
	}
	
	 public static boolean checkBoundsUp() {
		 for(int idx =0; idx <Game.rectangle.size();idx++) {
	        if(player.getBoundsInParent().intersects(Game.rectangle.get(idx).getBoundsInParent().getMinX() , Game.rectangle.get(idx).getBoundsInParent().getMinY()+1, 180, 20)&&
	            player.getBoundsInParent().getMaxX() <= Game.rectangle.get(idx).getBoundsInParent().getMaxX())
	          return true;
		 }
		return false;}
	 
}
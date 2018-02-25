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
    public static Node player;

   private static boolean up;
   private static boolean down;
   private static boolean goEast;
   private	static boolean goWest;
   private static boolean running;
   private static int jump=0;
	
    public static void move(Group root) throws Exception {
        playerImage = new Image(PLAYER_IMAGE_LOC);
        player = new ImageView(playerImage);
        Physiks.moveHeroTo(0,0,player);
		root.getChildren().addAll(player);
    }		
    
    public static void rightStart() {
		goEast  = true;
	}

    public static boolean leftStart() {
		return goWest  = true;
	}
    public static void leftStop() {
		goWest  = false;
	}
    public static void rightStop() {
		goEast  = false;
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
        double dx = 0, dy=0, x=4;
        if(Physiks.checkBoundsUp(player))jump=0;
        if(jump>0&&jump<45&&!Physiks.checkBoundsUp(player)) {dy-=8;jump++;	if(jump==44) {jump=0;}  }
         if (goEast&&!Physiks.checkBoundsRight(player))  dx += 3;
         if (goWest&&!Physiks.checkBoundsLeft(player))  dx -= 3;
         if(up&&jump==0&&Physiks.checkBoundsDown(player)) {dy-=8;jump++;}
         if(Physiks.checkBoundsDown(player)) {x=0.6;}
         if (running) { dx *= 2;}
           Physiks.moveHeroBy(dx,dy+x,player);
          }
    
    public static void Scene() {
    Game.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case LEFT:  Player.leftStart(); break;
                case RIGHT: Player.rightStart(); break;
                case SPACE:  Player.jumpStart(); break;
                case SHIFT: Player.runningStart( ); break;
            }
        }
    });
    Game.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case LEFT:  Player.leftStop(); break;
                case RIGHT: Player.rightStop(); break;
                case SPACE:  Player.jumpStop(); break;
                case SHIFT: Player.runningStop(); break;
            }
        }
    });
    }


    

}
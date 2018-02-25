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
public class Player2{
	private static final String PLAYER2_IMAGE_LOC ="Hero-icon.png";

    private static Image playerImage2;
    static Node player2;

    private static boolean up2;
    private static boolean down2;
    private static boolean goEast2;
	private static boolean goWest2;
	private static boolean running2;
	
    public static void move() throws Exception {
        playerImage2 = new Image(PLAYER2_IMAGE_LOC);
        player2 = new ImageView(playerImage2);
        Physiks.moveHeroTo(0,0,player2);
    }		
    
    public static void rightStart2() {
		goEast2  = true;
	}

    public static boolean leftStart2() {
		return goWest2  = true;
	}
    public static void rightStop2() {
		goEast2  = false;
	}

    public static void leftStop2() {
		goWest2  = false;
	}
    public static void runningStart2() {
  		running2 = true;
  	}

      public static void runningStop2() {
  		running2  = false;
  	}
     public static void jumpStart2() {
    	 up2=true;
     }
     public static void downStart2() {
    	 down2=true;
     }
     public static void jumpStop2() {
    	 up2=false;
     }
     public static void downStop2() {
    	 down2=false;
     }
    public static void handle(long now) {
        double dx = 0, dy=0, x=4;
         if (goEast2&&!Physiks.checkBoundsRight(player2))  dx += 3;
         if (goWest2&&!Physiks.checkBoundsLeft(player2))  dx -= 3;
         if(up2&&!Physiks.checkBoundsUp(player2)) dy-=10;
         if(Physiks.checkBoundsDown(player2))x=1;
         if (running2) { dx *= 2;}
           Physiks.moveHeroBy(dx,dy+x,player2);
          }
    
    public static void Scene() {
    Game.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case LEFT:  Player2.leftStart2(); break;
                case RIGHT: Player2.rightStart2(); break;
                case UP:  Player2.jumpStart2(); break;
                case ENTER: Player2.runningStart2( ); break;
            }
        }
    });
    Game.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case LEFT:  Player2.leftStop2(); break;
                case RIGHT: Player2.rightStop2(); break;
                case UP:  Player2.jumpStop2(); break;
                case ENTER: Player2.runningStop2(); break;
            }
        }
    });
    }


    

}
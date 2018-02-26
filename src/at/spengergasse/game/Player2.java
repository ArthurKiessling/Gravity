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
	private static final String PLAYER_IMAGE_LOC ="img/Hero-icon.png";
    private static Image playerImage;
    public static Node player2;

   private static boolean up;
   private static boolean goEast;
   private	static boolean goWest;
   private static boolean running;
   private static int jump;
   private static float multi=1;
   public static int life=5;
	
    public static void move(Group root) throws Exception {
        playerImage = new Image(PLAYER_IMAGE_LOC);
        player2 = new ImageView(playerImage);
        Physiks.moveHeroTo(0,0,player2);
        Scene();
		root.getChildren().addAll(player2);
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
     public static void jumpStop() {
    	 up=false;
     }
    public static void handle(long now) {
        float dx = 0, dy=0, x=4;
        if(Physiks.checkBoundsUp(player2))jump=0;
        if(jump>0&&jump<80&&!Physiks.checkBoundsUp(player2)) {dy-=9*multi;jump++;multi-=0.012;if(jump==72) {jump=0;multi=1;}  }
         if (goEast&&!Physiks.checkBoundsRight(player2))  dx += 3;
         if (goWest&&!Physiks.checkBoundsLeft(player2))  dx -= 3;
         if(up&&jump==0&&Physiks.checkBoundsDown(player2)) {dy-=9;jump++;}
         if(Physiks.checkBoundsDown(player2)) {x=(float) 0.6;if(multi<0.9) {multi=1;}}
         if (running) { dx *= 2;}
         if(player2.getBoundsInParent().getMinY()>Game.H-player2.getBoundsInParent().getHeight()-4) {player2.relocate(200, 0); life--;}
           Physiks.moveHeroBy(dx,dy+x,player2);
          }
    
    public static void Scene() {
    Game.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case A:  Player2.leftStart(); break;
                case D: Player2.rightStart(); break;
                case W:  Player2.jumpStart(); break;
                case Q: Player2.runningStart( ); break;
            }
        }
    });
    Game.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case A:  Player2.leftStop(); break;
                case D: Player2.rightStop(); break;
                case W:  Player2.jumpStop(); break;
                case Q: Player2.runningStop(); break;
            }
        }
    });
    }


    

}
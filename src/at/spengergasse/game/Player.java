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


    private static final String HERO_IMAGE_LOC =  "http://icons.iconarchive.com/icons/raindropmemory/legendora/64/Hero-icon.png";

    private static Image heroImage;
    private static Node hero;

    static boolean x;
    static boolean up;
    static boolean down;
    static boolean goEast;
	static boolean goWest;
	static boolean running;

    public static void move(Group root) throws Exception {
        heroImage = new Image(HERO_IMAGE_LOC);
        hero = new ImageView(heroImage);
        root.getChildren().add(hero);
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
        int dx = 0, dy=0; 
         if (goEast)  dx += 3;
         if (goWest)  dx -= 3;
         if(up) 	dy-=3;
         if(down)	dy+=3;
         if (running) { dx *= 2;dy*=2;}
           moveHeroBy(dx,dy+1);
    	
          }
        


    private static void moveHeroBy(int dx,int dy) {
        if (dx == 0&& dy==0) return;
        final double cx = hero.getBoundsInLocal().getWidth()  / 2;
        double x = cx + hero.getLayoutX() + dx;
        final double cy = hero.getBoundsInLocal().getHeight()  / 2;
        double y = cy + hero.getLayoutY() + dy;
        moveHeroTo(x,y);
        
    }

    private static void moveHeroTo(double x,double y) {
        final double cx = hero.getBoundsInLocal().getWidth()  / 2;
        final double cy = hero.getBoundsInLocal().getHeight()  / 2;
        if (x - cx >= 0 && x + cx <= Game.W&&y- cy >= 0 && y + cy <= Game.H) {
            hero.relocate(x - cx,y-cy);
        }
    }



}
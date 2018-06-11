/**
 * 
 */
package at.spengergasse.model;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import at.spengergasse.gui.Controls;
import at.spengergasse.gui.Start;
import javafx.geometry.Bounds;
import javafx.scene.*;
import javafx.scene.image.*;

/**
 * @author Arthur Kiessling
 *
 */
public class Player{
   private Image playerImage;
   public Node player;
   public int ID;

   private boolean up;
   public boolean goEast;
   public boolean goWest;
   private int jump;
   private float multi=1;
   public int life=5;
   public Node weapon ;
   public boolean lastLeft;
   public boolean shoot;
   public boolean lastShootLeft;
   private Controls cont;
   
   private Sound sound;
   /**
    * Nodes werden erstellt und richtig relocatet
    * Group hinzugefügt
    * leben werden gesetzt
    * @param ID
    * @param img
    * @param lifes
    * @param cont
    * @throws Exception
    */
	public void move(int ID,String img,int lifes,Controls cont) throws Exception {
		sound= new Sound();
		this.cont=cont;
		playerImage = new Image(img);
        player = new ImageView(playerImage);
        if(ID==2) {player.relocate(730, 50);lastLeft=true;}
        else { player.relocate(0, 50);}
        cont.getRoot().getChildren().addAll(player);
        setID(ID);
        this.life=lifes;
    }		
    
  /**
   * Geschaut ob man springen darf
   * die Springphysik
   * ob man nach links oder nach rechts gehen darf
   * Sound wird beim springen abgespielt
   * wenn jemand runterfällt leben abgezogen 
   * relocatet
   * @param now
   */
    public void handle(long now) {
        float dx = 0, dy=0, x=4;
        if(Physics.checkBoundsUp(player))jump=0;
        if(jump>0&&jump<80&&!Physics.checkBoundsUp(player)) {dy-=9*multi;jump++;multi-=0.012;if(jump==72) {jump=0;multi=1;}  }
         if (goEast&&!Physics.checkBoundsRight(player))  dx += 3;
         if (goWest&&!Physics.checkBoundsLeft(player))   dx -= 3;
         if(up&&jump==0&&Physics.checkBoundsDown(player)) {dy-=9;jump++; try {
			try {
				sound.playSound("src/sound/jump.wav");
			} catch (IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}} catch (LineUnavailableException e) {e.printStackTrace();}}
         if(Physics.checkBoundsDown(player)) {x=0.6f; if(multi<0.9) {multi=1;}}
        if(playerBounds().getMinY()>Start.H-playerBounds().getHeight()-10) {life--;if(ID==2) {player.relocate(500, 0); } else {player.relocate(200,0);}}
           Physics.moveHeroBy(dx,dy+x,player);
           displayUpdate();
          }

/**
 * PlayerBounds werden gegetet
 * @return
 */
	private Bounds playerBounds() {
		return player.getBoundsInParent();
	}
    /** 
     * Herzen werden in die Group eingefügt
     * immer aktualliesirt
     */
    public void displayUpdate() {
		 for(int idx=0;idx<5;idx++) {
			 if(idx>=life) {
				 Image heartimage = new Image("/img/blocks/heartX.png",32,32,false,false);
				 Node newHeart=new ImageView(heartimage);
				 if(ID==2) {newHeart.relocate(770-idx*30, 5);cont.getRoot().getChildren().set(Blocks.lifes[5+idx], newHeart);}
				 else { newHeart.relocate(idx*30, 5);cont.getRoot().getChildren().set(Blocks.lifes[idx], newHeart);}
			 }
		 } 
	}
  

    

	/**
	 * @param life the life to set
	 */
	public void loseLife() {
		life--;
	}

	/**
	 * @param weapon the weapon to set
	 */
	public void setWeapon(Node weapon) {
		this.weapon = weapon;
	}

	/**
	 * @return the weapon
	 */
	public Node getWeapon() {
		return weapon;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}
	
	/**
	 * wird nach Rechts gegangen
	 * Waffe wird richtig gedreht
	 */
	  public void rightStart() {
			goEast  = true;
			if(lastLeft==true) {lastLeft=false;
			cont.getWea().rotateItem(ID,true,weapon);}
		}

	  /**
		 * wird nach Links gegangen
		 * Waffe wird richtig gedreht
		 */
	    public void leftStart() {
			 goWest  = true;
			 if(lastLeft==false) {lastLeft=true;
			 cont.getWea().rotateItem(ID,false,weapon);}
		}
	    
	    /**
	     * Gehen wird nach Links gestoppt
	     */
	    public  void leftStop() {
			goWest  = false;
		}
	    
	    /**
	     * Gehen wird nach Rechts gestoppt
	     */
	    public void rightStop() {
			goEast  = false;
		}
	    
	    /**
	     * Es wird gesprungen
	     */
	     public  void jumpStart() {
	    	 up=true;
	     }
	     
	     /**
	      * der Sprung wird gestoppt
	      */
	     public void jumpStop() {
	    	 up=false;
	     }
	     
	     /**
	      * das Schiessen wird gestartet
	      */
	     public void shootStart() {
			 shoot = true;
		}
	     
	     /** 
	      * das Schiessen wird gestoppt
	      */
	    public  void shootStop() {
			shoot  = false;
		}
}  

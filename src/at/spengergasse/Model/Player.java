/**
 * 
 */
package at.spengergasse.Model;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import at.spengergasse.Gui.Game;
import at.spengergasse.Gui.Start;
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

	public  void move(Group root,int ID,String img,int lifes) throws Exception {
		playerImage = new Image(img);
        player = new ImageView(playerImage);
        if(ID==2)player.relocate(730, 50);
        else player.relocate(0, 50);
        root.getChildren().addAll(player);
        setID(ID);
        this.life=lifes;
    }		
    
  
    public void handle(long no) {
        float dx = 0, dy=0, x=4;
        if(Physics.checkBoundsUp(player))jump=0;
        if(jump>0&&jump<80&&!Physics.checkBoundsUp(player)) {dy-=9*multi;jump++;multi-=0.012;if(jump==72) {jump=0;multi=1;}  }
         if (goEast&&!Physics.checkBoundsRight(player))  dx += 3;
         if (goWest&&!Physics.checkBoundsLeft(player))   dx -= 3;
         if(up&&jump==0&&Physics.checkBoundsDown(player)) {dy-=9;jump++; try {
			try {
				Sound.playSound("src/sound/jump.wav");
			} catch (IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}} catch (LineUnavailableException e) {e.printStackTrace();}}
         if(Physics.checkBoundsDown(player)) {x=0.6f; if(multi<0.9) {multi=1;}}
        if(playerBounds().getMinY()>Start.H-playerBounds().getHeight()-10) {life--;if(ID==2) {player.relocate(500, 0); } else {player.relocate(200,0);}}
           Physics.moveHeroBy(dx,dy+x,player);
           displayUpdate();
          }


	private Bounds playerBounds() {
		return player.getBoundsInParent();
	}
    
    public void displayUpdate() {
		 for(int idx=0;idx<5;idx++) {
			 if(idx>=life) {
				 Image heartimage = new Image("/img/blocks/heartX.png",32,32,false,false);
				 Node newHeart=new ImageView(heartimage);
				 if(ID==2) {newHeart.relocate(idx*30+650, 5);Game.root.getChildren().set(Blocks.lifes[5+idx], newHeart);}
				 else { newHeart.relocate(idx*30, 5);Game.root.getChildren().set(Blocks.lifes[idx], newHeart);}
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
	  public void rightStart() {
			goEast  = true;
			if(lastLeft==true) {lastLeft=false;
			Weapon.rotateItem(ID,true,weapon);}
		}

	    public void leftStart() {
			 goWest  = true;
			 if(lastLeft==false) {lastLeft=true;
			 Weapon.rotateItem(ID,false,weapon);}
		}
	    public  void leftStop() {
			goWest  = false;
		}
	    public void rightStop() {
			goEast  = false;
		}

	     public  void jumpStart() {
	    	 up=true;
	     }
	     public void jumpStop() {
	    	 up=false;
	     }
	     
	     public void shootStart() {
			 shoot = true;
		}
	    public  void shootStop() {
			shoot  = false;
		}
}  

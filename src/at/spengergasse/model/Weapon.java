/**
 * 
 */
package at.spengergasse.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import at.spengergasse.gui.Game;
import at.spengergasse.gui.Start;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

/**
 * @author Arthur Kiessling
 *
 */
public class Weapon {
	private static int weaponsNb[];
	private static Image weaponsImage2 = new Image("/img/weapons/weapon2.png");
	private static Image bulletImg=new Image("/img/weapons/bullet.png",40,20,false,false);
	private static ArrayList<Node>bullets;
	private static ArrayList<Boolean>bulletsLeft;
	private static int[] count;

	 public static void handle(long now) {
		 weaponsDown();	 
		 if(count[0]>30||count[1]>30) shoot();
		 bulletControl();
		 bulletHit();
		 count[0]++;count[1]++;
	 }
	 
	 public static void genWeapons(Group root) {
		 weaponsNb=new int[2];
		 count=new int[2];
		 bullets= new ArrayList<Node>();
		 bulletsLeft=new ArrayList<Boolean>();
		 for(int i = 0; i<2;i++) {
			 Image weaponsimage = new Image("/img/weapons/weapon2.png");
			 Node weapon = new ImageView(weaponsimage);
			 weapon.setRotationAxis(Rotate.Y_AXIS);
			 if(i==1) {weapon.setRotate(180);Game.player[1].setWeapon(weapon);}
			 else Game.player[0].setWeapon(weapon);
			 weaponsNb[i]=root.getChildren().size();
			 root.getChildren().add(weapon);
			 weapon.relocate(630*i, 200);
		 }
	}
	 public static void weaponsDown() {	
		 for(int idx=0; idx<weaponsNb.length;idx++) {
		 if(Game.player[idx].lastLeft) {getPlayer(idx).relocate(playerBounds(idx).getMinX()-30,playerBounds(idx).getMinY()+10);}
		 else {getPlayer(idx).relocate(playerBounds(idx).getMinX()+30,playerBounds(idx).getMinY()+10);}	 
		 }
	 }

	private static Node getPlayer(int idx) {
		return Game.player[idx].weapon;
	}

	private static Bounds playerBounds(int idx) {
		return Game.player[idx].player.getBoundsInParent();
	}
	 public static void rotateItem(int nb, boolean lastLeft, Node weapon) {
		weapon= new ImageView(weaponsImage2);
		 weapon.setRotationAxis(Rotate.Y_AXIS);
		 if(!lastLeft) weapon.setRotate(180); 
		 Game.player[nb-1].setWeapon(weapon);
		 Game.root.getChildren().set(weaponsNb[nb-1], weapon);
	 }
	 
	 public static void shoot()  {
		 for(int idx=0;idx<Game.player.length;idx++) {
		 if(Game.player[idx].shoot) {
				 if(Game.player[idx].lastLeft) {
					 newBullet(playerBounds(idx).getMinX()-50,playerBounds(idx).getMinY()+10,true);	bulletsLeft.add(false);}
				 else {
					 newBullet(playerBounds(idx).getMaxX()+70,playerBounds(idx).getMinY()+10,false);bulletsLeft.add(true);	} 
			try {
				Sound.playSound("src/sound/laser.wav");
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	 }
		 count[0]=0;count[1]=0;
}
	 
	 public static Node newBullet(double x, double y,boolean left) {
		 Node bullet=new ImageView(bulletImg);
		 bullet.setRotationAxis(Rotate.Y_AXIS);
		 if(left)bullet.setRotate(180);
		 bullets.add(bullet);
		 Game.root.getChildren().addAll(bullet);
		 bullet.relocate(x, y);
		 return bullet;
	 }
	 
	 public static void bulletControl() {
		 for(int idx=0;idx<bullets.size();idx++) {
			 if(bulletsBounds(idx).getMinX()>Start.W||bulletsBounds(idx).getMinY()>Start.H) {
				 Game.root.getChildren().removeAll(bullets.get(idx));
			 }
			 if(bulletsLeft.get(idx)) {
				 bullets.get(idx).relocate(bulletsBounds(idx).getMinX()+5, bulletsBounds(idx).getMinY()+0.6f);
			 }
			 else bullets.get(idx).relocate(bulletsBounds(idx).getMinX()-5, bulletsBounds(idx).getMinY()+0.6f);
		 }
	 }

	private static Bounds bulletsBounds(int idx) {
		return bullets.get(idx).getBoundsInParent();
	}
	 
	 public static void bulletHit() {
		 for(int id=0; id<Game.player.length;id++) {
			 for(int idx=0;idx<bullets.size();idx++) {
				 if(Physics.checkTwo(Game.player[id].player,bullets.get(idx))){
					 try {
						Sound.playSound("src/sound/hit.wav");
					} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 Game.player[id].loseLife();
					 Game.root.getChildren().remove(Game.root.getChildren().indexOf(bullets.get(idx)));
					 bullets.remove(idx);
					 bulletsLeft.remove(idx);
				 }  
			 }
		 }
 }
}

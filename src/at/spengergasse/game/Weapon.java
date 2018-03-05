/**
 * 
 */
package at.spengergasse.game;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
	private static Image weaponsImage2 = new Image("img/weapons/weapon2.png");
	private static Image bulletImg=new Image("img/weapons/bullet.png",40,20,false,false);
	private static ArrayList<Node>bullets;
	private static ArrayList<Boolean>bulletsLeft;
	private static int[] count;

	 public static void handle(long now) {
		 weaponsDown();	 
		 if(count[0]>40||count[1]>40) shoot();
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
			 Image weaponsimage = new Image("img/weapons/weapon2.png");
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
		 if(Game.player[idx].lastLeft) {Game.player[idx].weapon.relocate(Game.player[idx].player.getBoundsInParent().getMinX()-30,Game.player[idx].player.getBoundsInParent().getMinY()+10);}
		 else {Game.player[idx].weapon.relocate(Game.player[idx].player.getBoundsInParent().getMinX()+30,Game.player[idx].player.getBoundsInParent().getMinY()+10);}	 
		 }
	 }
	 public static void rotateItem(int nb, boolean lastLeft, Node weapon) {
		weapon= new ImageView(weaponsImage2);
		 weapon.setRotationAxis(Rotate.Y_AXIS);
		 if(!lastLeft) weapon.setRotate(180); 
		 Game.player[nb-1].setWeapon(weapon);
		 Game.root.getChildren().set(weaponsNb[nb-1], weapon);
	 }
	 
	 public static void shoot() {
		 for(int idx=0;idx<Game.player.length;idx++) {
		 if(Game.player[idx].shoot) {
				 if(Game.player[idx].lastLeft) {
					 newBullet(Game.player[idx].player.getBoundsInParent().getMinX()-50,Game.player[idx].player.getBoundsInParent().getMinY()+10,true);	bulletsLeft.add(false);}
				 else {
					 newBullet(Game.player[idx].player.getBoundsInParent().getMaxX()+70,Game.player[idx].player.getBoundsInParent().getMinY()+10,false);bulletsLeft.add(true);	} 
				 try {
					Sound.playSound("src/sound/laser.wav");
				} catch (LineUnavailableException | InterruptedException | IOException
						| UnsupportedAudioFileException e) {
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
			 if(bullets.get(idx).getBoundsInParent().getMinX()>Game.W||bullets.get(idx).getBoundsInParent().getMinY()>Game.H) {
				 Game.root.getChildren().removeAll(bullets.get(idx));
			 }
			 if(bulletsLeft.get(idx)) {
				 bullets.get(idx).relocate(bullets.get(idx).getBoundsInParent().getMinX()+5, bullets.get(idx).getBoundsInParent().getMinY()+Blocks.speed);
			 }
			 else bullets.get(idx).relocate(bullets.get(idx).getBoundsInParent().getMinX()-5, bullets.get(idx).getBoundsInParent().getMinY()+Blocks.speed);
		 }
	 }
	 
	 public static void bulletHit() {
		 for(int id=0; id<Game.player.length;id++) {
			 for(int idx=0;idx<bullets.size();idx++) {
				 if(Physics.checkTwo(Game.player[id].player,bullets.get(idx))){
					 Game.player[id].loseLife();
					 Game.root.getChildren().remove(Game.root.getChildren().indexOf(bullets.get(idx)));
					 bullets.remove(idx);
					 bulletsLeft.remove(idx);
				 }  
			 }
		 }
	 }
}

/**
 * 
 */
package at.spengergasse.game;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
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
	private static boolean go=true;
	 public static void handle(long now) {
		 weaponsDown();
		 shoot();
	 }
	 
	 public static void genWeapons(Group root) {
		 weaponsNb=new int[2];
		 bullets= new ArrayList<Node>();
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
		 for(int idx=0;idx<1;idx++) {
			 if(Game.player[idx].shoot==true) {
				 if(bullets.size()<5) {
				 if(Game.player[idx].lastLeft) {
					 	for(int idx2=0;idx<5;idx++) {
					 		bullets.add(newBullet(Game.player[idx].player.getBoundsInParent().getMinX()-10*idx2,Game.player[idx].player.getBoundsInParent().getMinY()+10));	
					 	}
				 	}
				 else {
					 for(int idx2=0;idx<5;idx++) {
					 bullets.add(newBullet(Game.player[idx].player.getBoundsInParent().getMinX()+1*idx2,Game.player[idx].player.getBoundsInParent().getMinY()+10));
					 }
				 } 
			 }
			 }

		 }
	 }
	 
	 public static Node newBullet(double x, double y) {
		 Node bullet=new ImageView(bulletImg);
		 Game.root.getChildren().addAll(bullet);
		 bullet.relocate(x, y);
		 return bullet;
	 }
	 
	 public static void bulletControl() {
		 for(int idx=0;idx<1;idx++) {
			 if(Game.player[idx].lastShootLeft) {
				 for(int idx2=0; idx<5; idx++) {
					bullets.get(idx2).relocate(bullets.get(idx2).getBoundsInParent().getMinX()+2,bullets.get(idx2).getBoundsInParent().getMinY()) ;
				 }
			 }
			 else {
				 for(int idx2=0; idx<5; idx++) {
					 bullets.get(idx2).relocate(bullets.get(idx2).getBoundsInParent().getMinX()+2,bullets.get(idx2).getBoundsInParent().getMinY());
				 }
			 }
		 }
	 }
	 
	 public static void bulletHit() {
		 
	 }
}

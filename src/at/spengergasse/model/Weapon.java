/**
 * 
 */
package at.spengergasse.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import at.spengergasse.controls.Controls;
import at.spengergasse.gui.Start;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

/**
 * @author Arthur Kiessling
 *
 */
public class Weapon {
	private int weaponsNb[];
	private Image weaponsImage;
	private Image bulletImg;
	private static ArrayList<Node>bullets;
	private ArrayList<Boolean>bulletsLeft;
	private int[] count;
	private Controls cont;
	
	public void handle(long now) {
		 weaponsDown();	 
		 if(count[0]>30||count[1]>30) shoot();
		 bulletControl();
		 bulletHit();
		 count[0]++;count[1]++;
	 }
	 
	 public void genWeapons(Controls cont) {
		 weaponsNb=new int[2];
		 bulletImg=new Image(getClass().getResourceAsStream("/img/weapons/bullet.png"));
		 count=new int[2];
		 bullets= new ArrayList<Node>();
		 bulletsLeft=new ArrayList<Boolean>();
		 for(int i = 0; i<2;i++) {
			 weaponsImage = new Image("/img/weapons/weapon2.png");
			 Node weapon = new ImageView(weaponsImage);
			 weapon.setRotationAxis(Rotate.Y_AXIS);
			 if(i==1) {weapon.setRotate(180);cont.getPlayer(1).setWeapon(weapon);}
			 else cont.getPlayer(0).setWeapon(weapon);
			 weaponsNb[i]=cont.getRoot().getChildren().size();
			 cont.getRoot().getChildren().add(weapon);
			 weapon.relocate(630*i, 200);
		 }
	}
	 private void weaponsDown() {	
		 System.out.println("s"); //WARNING!!!!!!!!!!!!!!!
		 for(int idx=0; idx<weaponsNb.length;idx++) {
		 if(cont.getPlayer(idx).lastLeft) {getPlayer(idx).relocate(playerBounds(idx).getMinX()-30,playerBounds(idx).getMinY()+10);}
		 else {getPlayer(idx).relocate(playerBounds(idx).getMinX()+30,playerBounds(idx).getMinY()+10);}	 
		 }
	 }

	private Node getPlayer(int idx) {
		return cont.getPlayer(idx).weapon;
	}

	private Bounds playerBounds(int idx) {
		return cont.getPlayer(idx).player.getBoundsInParent();
	}
	 public void rotateItem(int nb, boolean lastLeft, Node weapon) {
		weapon= new ImageView(weaponsImage);
		 weapon.setRotationAxis(Rotate.Y_AXIS);
		 if(!lastLeft) weapon.setRotate(180); 
		 cont.getPlayer(nb-1).setWeapon(weapon);
		 cont.getRoot().getChildren().set(weaponsNb[nb-1], weapon);
	 }
	 
	 public void shoot()  {
		 for(int idx=0;idx<cont.getPlayerArr().length;idx++) {
		 if(cont.getPlayer(idx).shoot) {
				 if(cont.getPlayer(idx).lastLeft) {
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
	 
	 public Node newBullet(double x, double y,boolean left) {
		 Node bullet=new ImageView(bulletImg);
		 bullet.setRotationAxis(Rotate.Y_AXIS);
		 if(left)bullet.setRotate(180);
		 bullets.add(bullet);
		 cont.getRoot().getChildren().addAll(bullet);
		 bullet.relocate(x, y);
		 return bullet;
	 }
	 
	 public void bulletControl() {
		 for(int idx=0;idx<bullets.size();idx++) {
			 if(bulletsBounds(idx).getMinX()>Start.W||bulletsBounds(idx).getMinY()>Start.H) {
				 cont.getRoot().getChildren().removeAll(bullets.get(idx));
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
	 
	 public void bulletHit() {
		 for(int id=0; id<cont.getPlayerArr().length;id++) {
			 for(int idx=0;idx<bullets.size();idx++) {
				 if(Physics.checkTwo(cont.getPlayer(id).player,bullets.get(idx))){
					 try {
						Sound.playSound("src/sound/hit.wav");
					} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 cont.getPlayer(id).loseLife();
					 cont.getRoot().getChildren().remove(cont.getRoot().getChildren().indexOf(bullets.get(idx)));
					 bullets.remove(idx);
					 bulletsLeft.remove(idx);
				 }  
			 }
		 }
 }
}

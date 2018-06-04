/**
 * 
 */
package at.spengergasse.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import at.spengergasse.gui.Controls;
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
	private Image bulletImage;
	private static ArrayList<Node>bullets;
	private ArrayList<Boolean>bulletsLeft;
	private int[] count;
	private Controls cont;
	
	/**
	 * weaponsDown()
	 * shoot()
	 * bulletControl()
	 * bulletHit()
	 * werden gehandelt
	 * und ein counter geht
	 * @param now
	 */
	public void handle(long now) {
		 weaponsDown();	 
		 if(count[0]>30||count[1]>30) shoot();
		 bulletControl();
		 bulletHit();
		 count[0]++;count[1]++;
	 }
	 /**
	  * zwei Waffen werden generiert
	  * Control Objekt wird zugewiesen
	  * Waffen zu den Spieler hinzugefügt
	  * @param cont
	  * @param weaponImg
	  * @param bulletImg
	  */
	 public void genWeapons(Controls cont,String weaponImg,String bulletImg) {
		 this.cont=cont;
		 weaponsImage=new Image(weaponImg);
		 bulletImage=new Image(bulletImg);
		 weaponsNb=new int[2];
		 count=new int[2];
		 bullets= new ArrayList<Node>();
		 bulletsLeft=new ArrayList<Boolean>();
		 for(int i = 0; i<2;i++) {
			 Node weapon = new ImageView(weaponsImage);
			 weapon.setRotationAxis(Rotate.Y_AXIS);
			 if(i==1) {weapon.setRotate(180);cont.getPlayer(1).setWeapon(weapon);}
			 else cont.getPlayer(0).setWeapon(weapon);
			 weaponsNb[i]=cont.getRoot().getChildren().size();
			 cont.getRoot().getChildren().add(weapon);
		 }
	}
	 
	 /**
	  * Weapons werden immer zum Spieler relocatet
	  */
	 private void weaponsDown() {	
		 for(int idx=0; idx<weaponsNb.length;idx++) {
		if(cont.getPlayer(idx).lastLeft) {getWeapon(idx).relocate(playerBounds(idx).getMinX()-30,playerBounds(idx).getMinY()+10);}
		else {getWeapon(idx).relocate(playerBounds(idx).getMinX()+30,playerBounds(idx).getMinY()+10);}	 
		 }
	 }
/**
 * get Player Weapon
 * @param idx
 * @return
 */
	private Node getWeapon(int idx) {
		return cont.player[idx].weapon;
	}
/**
 * playerBounds werden gegetet
 * @param idx
 * @return
 */
	private Bounds playerBounds(int idx) {
		return cont.player[idx].player.getBoundsInParent();
	}
	
	/**
	 * Waffen in die richtige Richtung des Spieler gedreht
	 * @param nb
	 * @param lastLeft
	 * @param weapon
	 */
	 public void rotateItem(int nb, boolean lastLeft, Node weapon) {
		weapon= new ImageView(weaponsImage);
		 weapon.setRotationAxis(Rotate.Y_AXIS);
		 if(!lastLeft) weapon.setRotate(180); 
		 cont.getPlayer(nb-1).setWeapon(weapon);
		 cont.root.getChildren().set(weaponsNb[nb-1], weapon);
	 }
	/**
	 * Schüsse werden abgefeuert
	 * in die richtige Richtung
	 * ein Sound wird abgespielt 
	 * count wird zurückgesetzt
	 */
	 public void shoot()  {
		 for(int idx=0;idx<cont.player.length;idx++) {
		 if(cont.getPlayer(idx).shoot) {
				 if(cont.getPlayer(idx).lastLeft) {
					 newBullet(playerBounds(idx).getMinX()-70,playerBounds(idx).getMinY()+10,true);	bulletsLeft.add(false);}
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
	 /**
	  * Bullet wird erstellt
	  * Node mit Image erstellt
	  * relocatet
	  * ArrayList hinzugefügt
	  * @param x
	  * @param y
	  * @param left
	  * @return bullet
	  */
	 public Node newBullet(double x, double y,boolean left) {
		 Node bullet=new ImageView(bulletImage);
		 bullet.setRotationAxis(Rotate.Y_AXIS);
		 if(left) {bullet.setRotate(180);}
		 bullets.add(bullet);
		 cont.root.getChildren().addAll(bullet);
		 bullet.relocate(x, y);
		 return bullet;
	 }
	 
	 /**
	  * Blullets werden geprüft, dass sie nicht aus dem Bild kommen, sonst werden sie gelöscht
	  */
	 public void bulletControl() {
		 if(bullets.size()!=0) {
		 for(int idx=0;idx<bullets.size();idx++) {
			 if(bulletsBounds(idx).getMinX()>Start.W||bulletsBounds(idx).getMinY()>Start.H) {
				 cont.root.getChildren().removeAll(bullets.get(idx));
			 }
			 if(bulletsLeft.get(idx)) {
				 bullets.get(idx).relocate(bulletsBounds(idx).getMinX()+5, bulletsBounds(idx).getMinY()+0.6f);
			 }
			 else bullets.get(idx).relocate(bulletsBounds(idx).getMinX()-5, bulletsBounds(idx).getMinY()+0.6f);
		 }}
	 }

	 /**
	  * bulletBounds werden gegetet
	  * @param idx
	  * @return
	  */
	private Bounds bulletsBounds(int idx) {
		return bullets.get(idx).getBoundsInParent();
	}
	 /**
	  * wird geschaut ob ein Spieler getroffen wurde
	  * wenn Sound wird abgespielt und Leben wird abgezogen
	  * bullet wird aus ArrayList gelöscht
	  */
	 public void bulletHit() {
		 for(int id=0; id<cont.player.length;id++) {
			 for(int idx=0;idx<bullets.size();idx++) {
				 if(Physics.checkTwo(cont.getPlayer(id).player,bullets.get(idx))){
					 try {
						Sound.playSound("src/sound/hit.wav");
					} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 cont.getPlayer(id).loseLife();
					 cont.root.getChildren().remove(cont.root.getChildren().indexOf(bullets.get(idx)));
					 bullets.remove(idx);
					 bulletsLeft.remove(idx);
				 }  
			 }
		 }
 }
}

/**
 * 
 */
package at.spengergasse.game;

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
	public static Node[] weapons;
	
	 public static void handle(long now) {
		 weaponsDown();
	 }
	 
	 public static void genWeapons(Group root) {
		 weapons =new Node[2];
		 for(int i = 0; i<2;i++) {
			 Image weaponsimage = new Image("img/weapons/weapon2.png");
			 Node weapon = new ImageView(weaponsimage);
			 weapon.setRotationAxis(Rotate.Y_AXIS);
			 if(i==1)weapon.setRotate(180);Game.player2.setWeapon(weapon);
			 Game.player.setWeapon(weapon);
			 root.getChildren().add(weapon);
			 weapons[i]=weapon;
			 weapon.relocate(630*i, 200);
		 }
	}
	 public static void weaponsDown() {
		 weapons[0].relocate(Game.player.player.getBoundsInParent().getMaxX()-30,Game.player.player.getBoundsInParent().getMinY()+10);
			 weapons[1].relocate(Game.player2.player.getBoundsInParent().getMinX()-20,Game.player2.player.getBoundsInParent().getMinY()+10);
	 }
	 public static void rotateItem(Node item,int x) {
		 item.setScaleX(x);
	 }
	public static int getWeapon(Node weapon) {
		for(int idx=0;idx<weapons.length;idx++) {
		if(weapons [idx].equals(weapon)) {
			return idx;
			}
		}
		return -1;
	}
}

/**
 * 
 */
package at.spengergasse.model;


import at.spengergasse.gui.Game;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Arthur Kiessling
 *
 */
public class Items{
	
	public Items(String path,int x,int y){
		Image Img= new Image(path);
		 Node node=new ImageView(Img);
		 Game.root.getChildren().add(node);
		 node.relocate(x, y);
		 }
}

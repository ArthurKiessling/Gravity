/**
 * 
 */
package at.spengergasse.model;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Arthur Kiessling
 *
 */
public class Items{
	
	public Items(String path,Group root){
		Image Img= new Image(path);
		 Node node=new ImageView(Img);
		 root.getChildren().add(node);

		 }
}


package at.spengergasse.Scenes;

import at.spengergasse.Controls.Controls;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javafx.stage.Stage;

public class Decide extends Application {
	public double W=Start.W;
	public  double H=Start.H;
	private Group root ;
	private Scene scene;
	
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("SpaceJump");
		root = new Group();
		scene = new Scene(root, W, H, Color.WHITE);
		Image picture= new Image(getClass().getResourceAsStream("/img/background/MenuBackground.png"));
		Image icon=new Image(getClass().getResourceAsStream("/img/playerSkins/icon.png"));
		primaryStage.getIcons().addAll(icon);
		Node Background = new ImageView(picture);
		root.getChildren().add(Background);
		Controls.decide(primaryStage,root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}


}


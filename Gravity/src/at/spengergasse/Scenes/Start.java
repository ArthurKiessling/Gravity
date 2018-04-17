
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

public class Start extends Application {
	public static double W=800;
	public static double H=800;
	public Group root ;
	public Scene scene;
	
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("SpaceJump");
		root = new Group();
		scene = new Scene(root, W, H, Color.WHITE);
		Image icon=new Image(getClass().getResourceAsStream("/img/playerSkins/icon.png"));
		primaryStage.getIcons().addAll(icon);
		Image picture= new Image(getClass().getResourceAsStream("/img/background/MenuBackground.png"));
		Node Background = new ImageView(picture);
		root.getChildren().add(Background);
		Controls.startMenu(primaryStage,root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	

}


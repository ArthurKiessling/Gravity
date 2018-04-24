package at.spengergasse.Scenes;


import at.spengergasse.Controls.*;
import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DeathScreen extends Application {
	public double W=Start.W;
	public  double H=Start.H;
	private Group root;
	private Scene scene;
	
	public void start(Stage primaryStage) {
		primaryStage.setTitle("SpaceJump");
		root = new Group();
		scene = new Scene(root, W, H, Color.WHITE);
		Image icon=new Image(getClass().getResourceAsStream("/img/playerSkins/icon.png"));
		primaryStage.getIcons().addAll(icon);
		Image picture= new Image(getClass().getResourceAsStream("/img/background/DeathScreen.png"));
		Node Background = new ImageView(picture);
		root.getChildren().add(Background);
		primaryStage.setScene(scene);
		primaryStage.show();
		Controls.stopStage(KeyCode.ENTER,primaryStage,scene);
	}
	
}

package at.spengergasse.gui;


import at.spengergasse.controls.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DeathScreen extends Application {
	public double W=Start.W;
	public  double H=Start.H;
	private Group root;
	private Scene scene;
	
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Gravity");
		root = new Group();
		scene = new Scene(root, W, H, Color.WHITE);
		Start.genStartOptions(primaryStage,root,getClass().getResourceAsStream("/img/background/DeathScreen.png"),getClass().getResourceAsStream("/img/playerSkins/icon.png"));
		primaryStage.setScene(scene);
		primaryStage.show();
		Controls.stopStage(KeyCode.ENTER,primaryStage,scene);
	}
	
}

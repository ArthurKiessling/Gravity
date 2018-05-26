
package at.spengergasse.Gui;

import at.spengergasse.Controls.Controls;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import javafx.stage.Stage;

public class Decide extends Application {
	public double W=Start.W;
	public  double H=Start.H;
	private Group root ;
	private Scene scene;
	
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Gravity");
		root = new Group();
		scene = new Scene(root, W, H, Color.WHITE);
		Start.genStartOptions(primaryStage,root,getClass().getResourceAsStream("/img/background/MenuBackground.png"),getClass().getResourceAsStream("/img/playerSkins/icon.png"));
		Controls.decide(primaryStage,root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}


}



package at.spengergasse.Gui;

import at.spengergasse.Controls.Controls;
import at.spengergasse.Model.Player;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public  class saveScreen extends Application {
	public double W=Start.W;
	public  double H=Start.H;
	private Group root ;
	private Scene scene;
	
	public void start(Stage primaryStage,Player[] player) throws Exception {
		primaryStage.setTitle("Gravity");
		root = new Group();
		scene = new Scene(root, W, H, Color.WHITE);
		Start.genStartOptions(primaryStage,root,getClass().getResourceAsStream("/img/background/MenuBackground.png"),getClass().getResourceAsStream("/img/playerSkins/icon.png"));
		Controls.saveScreen(primaryStage,root,player);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	

}


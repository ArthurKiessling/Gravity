
package at.spengergasse.Gui;

import java.io.InputStream;
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
		primaryStage.setTitle("Gravity");
		root = new Group();
		scene = new Scene(root, W, H, Color.WHITE);
		primaryStage.setResizable(false);
		genStartOptions(primaryStage,root,getClass().getResourceAsStream("/img/background/MenuBackground.png"),getClass().getResourceAsStream("/img/playerSkins/icon.png"));
		Controls.startMenu(primaryStage,root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}


	public static void genStartOptions(Stage primaryStage,Group root,InputStream backgroundLink,InputStream iconLink) {
		Image icon=new Image(iconLink);
		primaryStage.getIcons().addAll(icon);
		Image picture= new Image(backgroundLink);
		Node Background = new ImageView(picture);
		root.getChildren().add(Background);
	}

	public static void main(String[] args) {
		launch(args);
			
	}

}


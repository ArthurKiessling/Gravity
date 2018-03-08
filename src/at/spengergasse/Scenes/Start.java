
package at.spengergasse.Scenes;

import at.spengergasse.Controls.Controls;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javafx.stage.Stage;

public class Start extends Application {
	static double W=800;
	static double H=800;
	static Group root ;
	static Scene scene;
	public Image picture ;
	
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("SpaceJump");
		root = new Group();
		scene = new Scene(root, W, H, Color.WHITE);
		Image picture= new Image("/img/blocks/startmenu.png");
		Node Background = new ImageView(picture);
		root.getChildren().add(Background);
		Button button = new Button();
		Controls.startGame(primaryStage,root);
		Controls.ExitGame(primaryStage,root);
		new AnimationTimer() {
			@Override
			public void handle(long now){
			}
		}.start();
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	

}


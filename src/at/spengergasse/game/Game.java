package at.spengergasse.game;

import java.util.ArrayList;

import at.spengergasse.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Game extends Application {
	static ArrayList<Rectangle> rectangle;
	static double W=800;
	static double H=800;
	static Group root ;
	static Scene scene;
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Jump and Run");
		
		root = new Group();
		scene = new Scene(root, W, H, Color.WHITE);
		rectangle= new ArrayList<Rectangle>();
		
		Blocks.generate();
		Player.move(root);
		Player.Scene();
     
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				Player.handle(now);
				Blocks.handle(now);
			}
		}.start();
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}
}
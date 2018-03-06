package at.spengergasse.game;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DeathScreen extends Application {
	static ArrayList<Node> block;
	static double W=800;
	static double H=800;
	static Group root ;
	static Scene scene;
	
	
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("SpaceJump");
		root = new Group();
		scene = new Scene(root, W, H, Color.WHITE);
		
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

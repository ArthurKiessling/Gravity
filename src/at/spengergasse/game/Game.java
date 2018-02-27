package at.spengergasse.game;

import java.util.ArrayList;

import com.sun.prism.Image;

import at.spengergasse.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Game extends Application {
	static ArrayList<Node> block;
	static double W=800;
	static double H=800;
	static Group root ;
	static Scene scene;


	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Jump and Run");
		root = new Group();
		scene = new Scene(root, W, H, Color.WHITE);
		block= new ArrayList<Node>();
		
		Blocks.Background(root);
		Blocks.generate();
		
		Player2.move(root);
		Player.move(root);
		new AnimationTimer() {
			@Override
			public void handle(long now){
				Player.handle(now);
				Player2.handle(now);
				Blocks.handle(now);
				if(Player.life==0||Player2.life==0)primaryStage.close();
			}
		}.start();
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);


	}
	
}
package at.spengergasse.game;

import java.util.ArrayList;

import at.spengergasse.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Game extends Application {
	static ArrayList<Rectangle> block;
	static double W=800;
	static double H=800;
	static Group root ;
	static Scene scene;
	public static int p1Life;
	public static int p2Life;
	public static String Winner;
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Jump and Run");
		
		root = new Group();
		scene = new Scene(root, W, H, Color.WHITE);
		block= new ArrayList<Rectangle>();
		
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

	/**
	 * @return the p1Life
	 */
	public static int getP1Life() {
		return p1Life;
	}

	/**
	 * @param p1Life the p1Life to set
	 */
	public static void setP1Life(int p1Life) {
		Game.p1Life = p1Life;
	}

	/**
	 * @return the p2Life
	 */
	public static int getP2Life() {
		return p2Life;
	}

	/**
	 * @param p2Life the p2Life to set
	 */
	public static void setP2Life(int p2Life) {
		Game.p2Life = p2Life;
	}

	/**
	 * @return the winner
	 */
	public static String getWinner() {
		return Winner;
	}

	/**
	 * @param winner the winner to set
	 */
	public static void setWinner(String winner) {
		Winner = winner;
	}
	
}
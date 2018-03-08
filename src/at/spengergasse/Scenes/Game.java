 package at.spengergasse.Scenes;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import at.spengergasse.Controls.Controls;
import at.spengergasse.Model.Blocks;
import at.spengergasse.Model.Player;
import at.spengergasse.Model.Sound;
import at.spengergasse.Model.Weapon;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {
	public static ArrayList<Node> block;
	public static double W=800;
	public static double H=800;
	public static Group root ;
	static Scene scene;
	public static Player[] player;
	
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("SpaceJump");
		root = new Group();
		scene = new Scene(root, W, H, Color.WHITE);
		block= new ArrayList<Node>();
		player= new Player[2];
		Controls cont=new Controls();
		Player player1= new Player();   
		Player player2= new Player();
		player[0]=(player1);
		player[1]=(player2);
		Blocks.Background(root);
		Blocks.generate();
		primaryStage.getIcons().addAll(Blocks.icon);
		cont.playerControls(KeyCode.A, KeyCode.D, KeyCode.W, KeyCode.SPACE,scene,player[0]);
		cont.playerControls(KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP,KeyCode.ENTER,scene,player[1]);
		player[0].move(root,1);
		player[1].move(root,2);
		Blocks.heart(root); 
		Weapon.genWeapons(root);
		new AnimationTimer() {
			@Override
			public void handle(long now){
				player[0].handle(now);
				player[1].handle(now);
				Blocks.handle(now);
				Weapon.handle(now);
				if(player[0].life==0||player[1].life==0) {
					try {try {Sound.playSound("src/sound/gameover.wav");} catch (IOException | UnsupportedAudioFileException e) {e.printStackTrace();	}} catch (LineUnavailableException e) {e.printStackTrace();}
					DeathScreen d = new DeathScreen();
					d.start(primaryStage);
					}
			}
		}.start();
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
		
	}
	
}

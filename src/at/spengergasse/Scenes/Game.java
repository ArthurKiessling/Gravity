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
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {
	public static ArrayList<Node> block;
	public static double W=800;
	public static double H=800;
	public static Group root ;
	public Scene scene;
	public static Player[] player;
	
	public void start(Stage primaryStage,String Background,String Block,String Skin,String Skin2) throws Exception {
		primaryStage.setTitle("SpaceJump");
		root = new Group();
		scene = new Scene(root, W, H, Color.WHITE);
		block= new ArrayList<Node>();
		player= new Player[2];
		Player player1= new Player();   
		Player player2= new Player();
		player[0]=(player1);
		player[1]=(player2);
		Image icon=new Image(getClass().getResourceAsStream("/img/playerSkins/icon.png"));
		primaryStage.getIcons().addAll(icon);
		Blocks.generate(Background,Block,root);
		primaryStage.getIcons().add(Blocks.icon);
		Controls.playerControls(KeyCode.A, KeyCode.D, KeyCode.W, KeyCode.SPACE,KeyCode.ESCAPE,scene,player[0],primaryStage);
		Controls.playerControls(KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP,KeyCode.ENTER,KeyCode.ESCAPE,scene,player[1],primaryStage);
		player[0].move(root,1,Skin);
		player[1].move(root,2,Skin2);
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

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}

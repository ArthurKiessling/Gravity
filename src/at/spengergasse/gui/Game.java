 package at.spengergasse.gui;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import at.spengergasse.controls.Controls;
import at.spengergasse.model.Blocks;
import at.spengergasse.model.Player;
import at.spengergasse.model.Sound;
import at.spengergasse.model.Weapon;
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
	public double W=Start.W;
	public double H=Start.H;
	public static Group root ;
	public Scene scene;
	public static Player[] player;
	public static AnimationTimer timer;
	public static int WorldID;
	
	public void start(Stage primaryStage,String Background,String Block,String[] Skin,int lifesP1,int lifesP2) throws Exception {
		primaryStage.setTitle("Gravity");
		root = new Group();
		scene = new Scene(root, W, H, Color.WHITE);
		Start.genStartOptions(primaryStage,root,getClass().getResourceAsStream(Background),getClass().getResourceAsStream("/img/playerSkins/icon.png"));
		block= new ArrayList<Node>();
		Blocks.generate(Block, root);
		Sound.playBackgroundSound("src/sound/backgroundmusic.wav");
		player= new Player[2];
		Player player1= new Player();   
		Player player2= new Player();
		player[0]=(player1);
		player[1]=(player2);
		Controls.playerControls(KeyCode.A, KeyCode.D, KeyCode.W, KeyCode.SPACE,KeyCode.ESCAPE,scene,player[0],primaryStage);
		Controls.playerControls(KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP,KeyCode.DOWN,KeyCode.ESCAPE,scene,player[1],primaryStage);
		player[0].move(root,1,Skin[0],lifesP1);
		player[1].move(root,2,Skin[1],lifesP2);
		Blocks.heart(root); 
		Weapon.genWeapons(root);
		timer = new AnimationTimer() {
			@Override
			public void handle(long now){
				player[0].handle(now);
				player[1].handle(now);
				Blocks.handle(now);
				Weapon.handle(now);
				if(player[0].life==0||player[1].life==0) {
					Sound.close();
					try {
						Sound.playSound("src/sound/gameover.wav");
					} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Controls.death(primaryStage, root);
					timer.stop();
					}
			}
		};
		timer.start();
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
	
	}

	
}
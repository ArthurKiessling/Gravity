package at.spengergasse.game;

import java.awt.Event;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
//nfspgnsi
public class StartMenu extends Application {
	static double W=800;
	static double H=800;
	static Group root ;
	static Scene scene;
	public  Image picture ;
	
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("SpaceJump");
		root = new Group();
		scene = new Scene(root, W, H, Color.WHITE);
		Image picture= new Image("/img/blocks/startmenu.png");
		Node Background = new ImageView(picture);
		root.getChildren().add(Background);
		startgame(KeyCode.ENTER,primaryStage);
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
	
	public void startgame(KeyCode enter, Stage primaryStage) {
		scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent event) {
	        	KeyCode code = event.getCode();
				if(enter == code) {
					try {
						Game game = new Game();
						game.start(primaryStage);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	        }
	    });
	}
}


package at.spengergasse.game;

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

	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Jump and Run");
		Group root = new Group();
		Scene scene = new Scene(root, 800, 800, Color.WHITE);
		
		Player.move(root);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT:  Player.leftStart(); break;
                    case RIGHT: Player.rightStart(); break;
                    case SHIFT: Player.runningStart( ); break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT:  Player.leftStop(); break;
                    case RIGHT: Player.rightStop(); break;
                    case SHIFT: Player.runningStop(); break;
                }
            }
        });
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				Player.handle(now);
			}
		}.start();

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}
}

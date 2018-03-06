package at.spengergasse.game;



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

public class DeathScreen extends Application {
	static double W=800;
	static double H=800;
	static Group root ;
	static Scene scene;
	public  Image picture ;
	static Stage stage;
	
	public void start(Stage primaryStage) {
		primaryStage.setTitle("SpaceJump");
		root = new Group();
		scene = new Scene(root, W, H, Color.WHITE);
		stage=primaryStage;
		Image picture= new Image("/img/blocks/DeathScreen.png");
		Node Background = new ImageView(picture);
		root.getChildren().add(Background);
		Stop(KeyCode.ENTER);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
	
    public void Stop(KeyCode StopKey) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
        	KeyCode code = event.getCode();
			if (code == StopKey) {
			}
        }
      });
        scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
    			if (code == StopKey) {
    				Stop();
    			}
            }
        });
    }
    
    private  void Stop() {
    	stage.close();
    }
}

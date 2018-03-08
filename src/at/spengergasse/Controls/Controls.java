/**
 * 
 */
package at.spengergasse.Controls;

import at.spengergasse.Model.Player;
import at.spengergasse.Scenes.Game;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * @author Arthur Kiessling
 *
 */
public class Controls {
	
    public static void stopStage(KeyCode StopKey,Stage stage, Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
        	KeyCode code = event.getCode();
			if (code == StopKey) {
				stage.close();
			}
        }
      });

    }
    
	public static void ExitGame( Stage primaryStage,Group root) {
		Button button = new Button();
		Image img = new Image("/buttonImg/ExitButton.png",300,150,true,true);
		button.setGraphic(new ImageView(img));
		button.relocate(240, 400);
		root.getChildren().add(button);
		 button.setOnAction(value ->  {
			 primaryStage.close();
	        });
	}
    
	public static void startGame( Stage primaryStage,Group root) {
		Button button = new Button();
		Image img = new Image("/buttonImg/StartButton.png",300,150,true,true);
		button.setGraphic(new ImageView(img));
		button.relocate(240, 200);
		root.getChildren().add(button);
		 button.setOnAction(value ->  {
	           Game g= new Game();
	           try {
				g.start(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        });
	}
	
    public void playerControls(KeyCode leftKey, KeyCode rightKey, KeyCode jumpKey, KeyCode shootKey, Scene scene,Player player) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
        	KeyCode code = event.getCode();
			if (code == leftKey)
				player.leftStart();
			else if (code == rightKey)
				player.rightStart();
			else if (code == jumpKey)
				player.jumpStart();
			else if (code == shootKey) {
				player.shootStart();
			}
        }
    });
    scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            KeyCode code = event.getCode();
			if (code == leftKey) {
				player.leftStop();
			} else if (code == rightKey) {
				player.rightStop();
			} else if (code == jumpKey) {
				player.jumpStop();
			} else if (code == shootKey) {
				player.shootStop();
			}
        }
    });
    
    }

}

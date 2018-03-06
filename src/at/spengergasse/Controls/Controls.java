/**
 * 
 */
package at.spengergasse.Controls;

import at.spengergasse.Scenes.Game;
import at.spengergasse.game.Player;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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
    

    
	public static void startGame(KeyCode enter, Stage primaryStage,Scene scene) {
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

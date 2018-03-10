/**
 * 
 */
package at.spengergasse.Controls;

import at.spengergasse.Model.Player;
import at.spengergasse.Scenes.Decide;
import at.spengergasse.Scenes.Game;
import at.spengergasse.Scenes.Start;
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
				Start s= new Start();
				try {
					s.start(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        }
      });

    }
    
	public static void decide(Stage primaryStage,Group root) {
		
		Button button = new Button();
		Image img = new Image("img/buttonImg/ButtonSpace.png",300,150,true,true);
		button.setGraphic(new ImageView(img));
		button.relocate(240, 400);
		root.getChildren().add(button);
		 button.setOnAction(value ->  {
	           Game g= new Game();
	           try { 
	           g.start(primaryStage,"/img/background/Background.png","/img/blocks/Block.png","/img/playerSkins/Space-icon.png","/img/playerSkins/Space2-icon.png");
			} catch (Exception e) {				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        });
		 
			Button button2 = new Button();
			Image img2 = new Image("img//buttonImg/ButtonLand.png",300,150,true,true);
			button2.setGraphic(new ImageView(img2));
			button2.relocate(240, 200);
			root.getChildren().add(button2);
			 button2.setOnAction(value ->  {
			     Game g= new Game();
		           try {
					g.start(primaryStage,"/img/background/Background2.png","/img/blocks/Block2.png","/img/playerSkins/Player.png","/img/playerSkins/Player2.png");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        });
	}
    
	public static void startMenu(Stage primaryStage,Group root) {
		Button button = new Button();
		Image img = new Image("img/buttonImg/StartButton.png",300,150,true,true);
		button.setGraphic(new ImageView(img));
		button.relocate(240, 200);
		root.getChildren().add(button);
		 button.setOnAction(value ->  {
	        	   Decide d= new Decide();
				try {
					d.start(primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	        });
		 
			Button button2 = new Button();
			Image img2 = new Image("img/buttonImg/ExitButton.png",300,150,true,true);
			button2.setGraphic(new ImageView(img2));
			button2.relocate(240, 400);
			root.getChildren().add(button2);
			 button2.setOnAction(value ->  {
				 primaryStage.close();
		        });
	}
	
    public static void playerControls(KeyCode leftKey, KeyCode rightKey, KeyCode jumpKey, KeyCode shootKey,KeyCode ExitKey, Scene scene,Player player,Stage stage) {
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
        	} else if (code == ExitKey) {
        		  Start d= new Start();
    	           try {
    				d.start(stage);
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	}
        }
    });
    
    }

}

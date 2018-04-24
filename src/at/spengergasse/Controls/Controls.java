/**
 * 
 */
package at.spengergasse.Controls;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import at.spengergasse.Scenes.saveScreen;
import at.spengergasse.Model.Player;
import at.spengergasse.Scenes.DeathScreen;
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
		Image img = new Image("img/buttonImg/SpaceButton.png");
		button.setGraphic(new ImageView(img));
		button.relocate(240, 325);
		root.getChildren().add(button);
		 button.setOnAction(value ->  {
	           Game g= new Game();
	           try { 
	           g.start(primaryStage,"/img/background/Background.png","/img/blocks/Block.png","/img/playerSkins/Space-icon.png","/img/playerSkins/Space2-icon.png",5,5);
			} catch (Exception e) {				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        });
		 
			Button button2 = new Button();
			Image img2 = new Image("img//buttonImg/EarthButton.png");
			button2.setGraphic(new ImageView(img2));
			button2.relocate(240, 100);
			root.getChildren().add(button2);
			 button2.setOnAction(value ->  {
			     Game g= new Game();
		           try {
					g.start(primaryStage,"/img/background/Background2.png","/img/blocks/Block2.png","/img/playerSkins/Player.png","/img/playerSkins/Player2.png",5,5);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       });
			Button button3 = new Button();
			Image img3 = new Image("img//buttonImg/OldGameButton.png");
			button3.setGraphic(new ImageView(img3));
			button3.relocate(240, 550);
			root.getChildren().add(button3);
			 button3.setOnAction(value ->  {
				 int[] lifes =null;
				try {
					lifes = read();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    Game g= new Game();
		          try {
				g.start(primaryStage,"/img/background/Background2.png","/img/blocks/Block2.png","/img/playerSkins/Player.png","/img/playerSkins/Player2.png",lifes[0],lifes[1]);
		         } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		         }
		          });
	}
    
	public static void startMenu(Stage primaryStage,Group root) {
		Button button = new Button();
		Image img = new Image("img/buttonImg/StartButton.png");
		button.setGraphic(new ImageView(img));
		button.relocate(240, 200);
		root.getChildren().add(button);
		 button.setOnAction(value ->  {
	        	   Decide d= new Decide();
				try {d.start(primaryStage);
				} catch (Exception e) {e.printStackTrace();}
	        });
		 
			Button button2 = new Button();
			Image img2 = new Image("img/buttonImg/ExitButton.png");
			button2.setGraphic(new ImageView(img2));
			button2.relocate(240, 400);
			root.getChildren().add(button2);
			 button2.setOnAction(value ->  {
				 primaryStage.close();
		        });
	}
	
	public static void saveScreen(Stage primaryStage,Group root,Player[] player) {
		Button button = new Button();
		Image img = new Image("img/buttonImg/StartButton.png");
		button.setGraphic(new ImageView(img));
		button.relocate(240, 200);
		root.getChildren().add(button);
		 button.setOnAction(value ->  {
	           Start s= new Start();
	           try { 
	           s.start(primaryStage);
			} catch (Exception e) {				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        });
		 
			Button button2 = new Button();
			Image img2 = new Image("img//buttonImg/SaveButton.png");
			button2.setGraphic(new ImageView(img2));
			button2.relocate(240, 400);
			root.getChildren().add(button2);
			button2.setOnAction(value ->  {
			try {
			save(player[0].life,player[1].life);
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
			});
	}
	public static void death(Stage primaryStage,Group root) {
		DeathScreen d = new DeathScreen();
		d.start(primaryStage);
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
       		  saveScreen d= new saveScreen();
        		 Player[] player =Game.player;
       		  Game.timer.stop();
   	           try {
    				d.start(stage,player);
   			} catch (Exception e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
    			}
        	}
        }
    });
    
    }
    public static int[] read() throws IOException {
    	InputStream os= Files.newInputStream(Paths.get("Spielstand.dat"));
    	DataInputStream dos = new DataInputStream(os);
    	int[] personenLeben=new int[2];
    	personenLeben[0]=dos.readInt();
    	personenLeben[1]=dos.readInt();
    	dos.close();
    	return personenLeben;
    }
    public static void save(int statsP1,int statsP2) throws IOException {
    	OutputStream os= Files.newOutputStream(Paths.get("Spielstand.dat"));
    	DataOutputStream dos = new DataOutputStream(os);
    	dos.writeInt(statsP1);
    	dos.writeInt(statsP2);
    	dos.close();
    }
}

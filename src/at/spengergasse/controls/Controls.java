/**
 * 
 */
package at.spengergasse.controls;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import at.spengergasse.gui.DeathScreen;
import at.spengergasse.gui.Game;
import at.spengergasse.gui.ActionListener;
import at.spengergasse.gui.Start;
import at.spengergasse.gui.saveScreen;
import at.spengergasse.model.Blocks;
import at.spengergasse.model.Player;
import at.spengergasse.model.Sound;
import at.spengergasse.model.Weapon;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * @author Arthur Kiessling
 *
 */
public class Controls extends Stage{
	private Button startButton;
	private Button closeButton;
	private Button saveButton;
	private Button returnButton;
	private Button backButton;
	private Button spaceButton;
	private Button earthButton;
	private Button oldGameButton;
	
	public static ArrayList<Node> block;
	private Player[] player;
	private AnimationTimer timer;
	private Weapon wea;
	
	private Scene scene;
	private Group root;
	private Stage stage;
	private ActionListener listener;	
	
	// arguments to the frame
	//private List<String> args;
	
	public Controls(List<String> args) {
		//this.args=args;
		listener=new ActionListener(this);
		stage=new Stage();
		stage.setTitle("Gravity");
		stage.setResizable(false);
		root=new Group(); 
		startMenu();
	}
	
	   public void startMenu() {
		root = new Group();
		Start.genStartOptions(stage,root,getClass().getResourceAsStream("/img/background/MenuBackground.png"),getClass().getResourceAsStream("/img/playerSkins/icon.png"));
		
		startButton =genButton(240,200,"img/buttonImg/StartButton.png");

		closeButton=genButton(240,400,"img/buttonImg/ExitButton.png");


		scene = new Scene(root, Start.W, Start.H, Color.WHITE);
		stage.setScene(scene);
		stage.show();
		}
	   /*	
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
*/	    
	   public void game(String Background,String Block,String[] Skin,int lifesP1,int lifesP2){
			root = new Group();

			Start.genStartOptions(stage,root,getClass().getResourceAsStream(Background),getClass().getResourceAsStream("/img/playerSkins/icon.png"));
			block= new ArrayList<Node>();
			Blocks.generate(Block, root);
			/*try {
				Sound.playBackgroundSound("src/sound/backgroundmusic.wav");
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}*/
			player= new Player[2];
			Player player1= new Player();   
			Player player2= new Player();
			player[0]=(player1);
			player[1]=(player2);
			playerControls(KeyCode.A, KeyCode.D, KeyCode.W, KeyCode.SPACE,KeyCode.ESCAPE,scene,player[0],stage);
			playerControls(KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP,KeyCode.DOWN,KeyCode.ESCAPE,scene,player[1],stage);
			try {
				player[0].move(1,Skin[0],lifesP1,this);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				player[1].move(2,Skin[1],lifesP2,this);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Blocks.heart(root); 
			wea=new Weapon();
			wea.genWeapons(this);
			timer = new AnimationTimer() {
				@Override
				public void handle(long now){
					player[0].handle(now);
					player[1].handle(now);
					Blocks.handle(now);
					wea.handle(now);
					if(player[0].life==0||player[1].life==0) {
						Sound.close();
						try {
							Sound.playSound("src/sound/gameover.wav");
						} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						death(stage, root);
						timer.stop();
						}
				}
			};
			timer.start();
			scene = new Scene(root, Start.W, Start.H, Color.WHITE);
			stage.setScene(scene);
			stage.show();
		}
	public void decide() {
		root=new Group(); 
		Start.genStartOptions(stage,root,getClass().getResourceAsStream("/img/background/MenuBackground.png"),getClass().getResourceAsStream("/img/playerSkins/icon.png"));
		
		
		spaceButton=genButton(240,325,"img//buttonImg/SpaceButton.png");
		earthButton=genButton(240,100,"img//buttonImg/EarthButton.png");
		oldGameButton=genButton(240, 550,"img//buttonImg/OldGameButton.png");
		
		scene = new Scene(root, Start.W, Start.H, Color.WHITE);
		stage.setScene(scene);
		stage.show();
	}


	/*	
	
	
	public static void saveScreen(Stage primaryStage,Group root,Player[] player) { 
		int ID=Game.WorldID;
		Game.timer.stop();
		Button button = genButton(root,240, 25,"img/buttonImg/ReturnButton.png");
		 button.setOnAction(value ->  {
			 Game g= new Game();
			 switch(ID) {
			 case 1:    try { 
		           g.start(primaryStage,getBackground(ID),getBlock(ID),getSkins(ID),Game.player[0].life,Game.player[1].life);
				} catch (Exception e) {				// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 2:   
	          try {
			g.start(primaryStage,getBackground(ID),getBlock(ID),getSkins(ID),Game.player[0].life,Game.player[1].life);
	         } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	         }
				 break;
			 }
	        });
		 
		 Button button1 = genButton(root,240,225,"img/buttonImg/StartButton.png");
		 button1.setOnAction(value ->  {
	           Start s= new Start();
	           try { 
	           s.start(primaryStage);
			} catch (Exception e) {				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        });
		 
			Button button2 = genButton(root,240,425,"img//buttonImg/SaveButton.png");
			button2.setOnAction(value ->  {
					Start s= new Start();
					try {
						save(player[0].life,player[1].life,Game.WorldID);
						s.start(primaryStage);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace(); }
				});
			
			Button button3 = genButton(root,240,625,"img/buttonImg/ExitButton.png");
			 button3.setOnAction(value ->  {
				 primaryStage.close();
		        });
	}
*/
	/**
	 * @param root
	 * @return
	 */
	public Button genButton(int x,int y, String path) {
		Button button = new Button();
		Image img4= new Image(path);
		button.setGraphic(new ImageView(img4));
		button.relocate(x, y);
		root.getChildren().add(button);
		button.addEventHandler(ActionEvent.ACTION, listener);
		return button;
	}
	public void death(Stage primaryStage,Group root) {
		DeathScreen d = new DeathScreen();
		d.start(primaryStage);
	}
    public void playerControls(KeyCode leftKey, KeyCode rightKey, KeyCode jumpKey, KeyCode shootKey,KeyCode ExitKey, Scene scene,Player player,Stage stage) {
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
        		Sound.close();
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
    public int[] read() throws  Fehler, IOException {
    	if(!new File("Spielstand.dat").isFile())throw new Fehler("Geht nicht"); 
    	InputStream os= Files.newInputStream(Paths.get("Spielstand.dat"));
    	DataInputStream dos = new DataInputStream(os);
    	int[] info=new int[3];
    	info[0]=dos.readInt();
    	info[1]=dos.readInt();
    	info[2]=dos.readInt();
    	dos.close();
    	return info;
    }
    public void save(int statsP1,int statsP2,int WorldID) throws IOException {
    	OutputStream os= Files.newOutputStream(Paths.get("Spielstand.dat"));
    	DataOutputStream dos = new DataOutputStream(os);
    	dos.writeInt(statsP1);
    	dos.writeInt(statsP2);
    	dos.writeInt(WorldID);
    	dos.close();
    }
    

	/**
	 * @return
	 */
	public String[] getSkins(int WorldID) {
		String[] skins= new String[2];
		switch(WorldID) {
		case 1: {skins[0]="/img/playerSkins/Space-icon.png";
		skins[1]="/img/playerSkins/Space2-icon.png";}
		break;
		case 2: {skins[0]="/img/playerSkins/Player.png";
		skins[1]="/img/playerSkins/Player2.png";}
		break;
		}
		return skins ;
	}
	/**
	 * @return
	 */
	public String getBlock(int WorldID) {
		if(WorldID==1) {
		return "/img/blocks/Block.png";
		}else return "/img/blocks/Block2.png";
	}


	/**
	 * @return
	 */
	public String getBackground(int WorldID) {
		switch(WorldID) {
		case 1: return "/img/background/Background.png";

		case 2: return "/img/background/Background2.png";
		
		default: return "1";
		}
	}

	/**
	 * @return the startButton
	 */
	public Button getStartButton() {
		return startButton;
	}

	/**
	 * @return the closeButton
	 */
	public Button getCloseButton() {
		return closeButton;
	}

	/**
	 * @return the saveButton
	 */
	public Button getSaveButton() {
		return saveButton;
	}

	/**
	 * @return the returnButton
	 */
	public Button getReturnButton() {
		return returnButton;
	}

	/**
	 * @return the backButton
	 */
	public Button getBackButton() {
		return backButton;
	}

	/**
	 * @return the stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * @return the spaceButton
	 */
	public Button getSpaceButton() {
		return spaceButton;
	}

	/**
	 * @return the earthButton
	 */
	public Button getEarthButton() {
		return earthButton;
	}

	/**
	 * @return the oldGameButton
	 */
	public Button getOldGameButton() {
		return oldGameButton;
	}

	/**
	 * @return the root
	 */
	public Group getRoot() {
		return root;
	}



	/**
	 * @return the player
	 */
	public Player getPlayer(int playerNb) {
		return player[playerNb];
	}

	/**
	 * @return the player
	 */
	public Player[] getPlayerArr() {
		return player;
	}

	/**
	 * @return the wea
	 */
	public Weapon getWea() {
		return wea;
	}
	
	
   }

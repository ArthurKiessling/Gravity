/**
 * 
 */
package at.spengergasse.Controls;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import at.spengergasse.Gui.DeathScreen;
import at.spengergasse.Gui.Decide;
import at.spengergasse.Gui.Game;
import at.spengergasse.Gui.Start;
import at.spengergasse.Gui.saveScreen;
import at.spengergasse.Model.Player;
import at.spengergasse.Model.Sound;
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
public class Controls extends Stage{
	
	/*
	public Controls(List<String> args) {
		this.args=args;
	}*/
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
    
    public static void startMenu(Stage primaryStage,Group root) {
		Button button =genButton(root,240,200,"img/buttonImg/StartButton.png");
		 button.setOnAction(value ->  {
	        	   Decide d= new Decide();
			try {
				d.start(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  });
		 
			Button button2 =genButton(root,240,400,"img/buttonImg/ExitButton.png");
			 button2.setOnAction(value ->  {
				 primaryStage.close();
		        });
	}
    
	public static void decide(Stage primaryStage,Group root) {
		Button button=genButton(root,240,325,getSpaceButton());
		 button.setOnAction(value ->  {
	           Game g= new Game();
	           try { Game.WorldID=1;
	           g.start(primaryStage,getBackground(Game.WorldID),getBlock(Game.WorldID),getSkins(Game.WorldID),5,5);
			} catch (Exception e) {				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        });
		 
			Button button2 =genButton(root,240,100,"img//buttonImg/EarthButton.png");
			 button2.setOnAction(value ->  {
			     Game g= new Game();
		           try {
		        	   Game.WorldID=2;
					g.start(primaryStage,getBackground(Game.WorldID),getBlock(Game.WorldID),getSkins(Game.WorldID),5,5);
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
				 int[]info =null;
					try {
						info = read();
					} catch (Fehler | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
			    Game g= new Game();
		          try {
				g.start(primaryStage,getBackground(info[2]),getBlock(info[2]),getSkins(info[2]),info[0],info[1]);
		         } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		         }
		          });
	}



	
	
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

	/**
	 * @param root
	 * @return
	 */
	public static Button genButton(Group root,int x,int y, String path) {
		Button button = new Button();
		Image img4= new Image(path);
		button.setGraphic(new ImageView(img4));
		button.relocate(x,y);
		root.getChildren().add(button);
		return button;
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
    public static int[] read() throws  Fehler, IOException {
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
    public static void save(int statsP1,int statsP2,int WorldID) throws IOException {
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
	public static String[] getSkins(int WorldID) {
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
	public static String getBlock(int WorldID) {
		if(WorldID==1) {
		return "/img/blocks/Block.png";
		}else return "/img/blocks/Block2.png";
	}

	/**
	 * @return
	 */
	public static String getSpaceButton() {
		return "img/buttonImg/SpaceButton.png";
	}
	/**
	 * @return
	 */
	public static String getBackground(int WorldID) {
		switch(WorldID) {
		case 1: return "/img/background/Background.png";

		case 2: return "/img/background/Background2.png";
		
		default: return "s";
		}
	}
   }

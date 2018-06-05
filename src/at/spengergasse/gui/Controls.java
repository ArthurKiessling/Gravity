/**
 * 
 */
package at.spengergasse.gui;

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

import at.spengergasse.model.Blocks;
import at.spengergasse.model.Player;
import at.spengergasse.model.Sound;
import at.spengergasse.model.Weapon;
import at.spengergasse.test.Fehler;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
	
	private Label winner;
	
	public static ArrayList<Node> block;
	public Player[] player;
	public AnimationTimer timer;
	private Weapon weapon;
	private int WorldID;
	
	private Scene scene;
	public Group root;
	public Stage stage;
	private ActionListener actionListener;	
	private KeyListener keyListener;
	

	/**
	 * Erstellt einen neuen ActionListener
	 * eine neue Stage setzt den Titel Gravity
	 * nicht Resizable
	 * neue Group
	 * startMenu() wird gestartet
	 * @param args
	 */
	public Controls(List<String> args) {
		actionListener=new ActionListener(this);
		stage=new Stage();
		stage.setTitle("Gravity");
		stage.setResizable(false);
		root=new Group(); 
		startMenu();
	}
	
	/**
	 * neue Group erstellt
	 * genStartOptions wird ausgeführt
	 * Buttons mit genButton(x,y,Path) erstellt
	 * neuer startButton
	 * neuer close Button
	 * 
	 * neue scene
	 * stage wird gezeigt
	 */
	   public void startMenu() {
		root = new Group();
		Start.genStartOptions(stage,root,"/img/background/MenuBackground.png","/img/playerSkins/icon.png");
		
		startButton =genButton(240,200,"/img/buttonImg/StartButton.png");
		closeButton=genButton(240,400,"/img/buttonImg/ExitButton.png");

		scene = new Scene(root, Start.W, Start.H, Color.WHITE);
		stage.setScene(scene);
		stage.show();
		}
	   
    /**
     * neue Group
     * genStartOptions wird ausgeführt
	 * Buttons mit genButton(x,y,Path) erstellt
	 * neuer spaceButton
	 * neuer earthButton
	 * neuer oldGameButton
	 * 
	 * neue scene
	 * stage wird gezeigt
     */
	   public void decide() {
			root=new Group(); 
			Start.genStartOptions(stage,root,"/img/background/MenuBackground.png","/img/playerSkins/icon.png");
			
			
			spaceButton=genButton(240,325,"/img/buttonImg/SpaceButton.png");
			earthButton=genButton(240,100,"/img/buttonImg/EarthButton.png");
			oldGameButton=genButton(240, 550,"/img/buttonImg/OldGameButton.png");
			
			scene = new Scene(root, Start.W, Start.H, Color.WHITE);
			stage.setScene(scene);
			stage.show();
		}
	   
	   /**
	     * neue Group
	     * neue scene
	     * genStartOptions wird ausgeführt
		 * neue Block ArrayList
		 * Blocks werden generiert 
		 * Die Hintergrundmusik wird abgespielt
		 * neues Player Array
		 * Player werden erstellt und zum Array hinzugefügt
		 * 
		 * stage wird gezeigt
		 * neuer KeyListener wird erstellt und alles gesetet
		 * 
		 * Player werden zur richtigen Position gemoved
		 * Herzen werden angezeigt von den Spielern
		 * neues Weapon-Object und die Waffen werden erstellt
		 * keyListener handle() gestartet
		 * neuer AnimationTimer wird erstellt und gestartet
		 * 
		 * player handle() 
		 * Blocks handle()
		 * weapon handle()
		 * 
		 * geprüft ob jemand stirbt, wenn deathScreen gestartet
		 * 
		 * timer wird gestartet und stage wird geshowed
		 * @param Background,block,Skin,lifesP1,lifesP2
	     */
	   public void game(String Background,String Block,String[] Skin,int lifesP1,int lifesP2){
			root = new Group();
			scene = new Scene(root, Start.W, Start.H, Color.WHITE);
			Start.genStartOptions(stage,root,Background,"/img/playerSkins/icon.png");
			block= new ArrayList<Node>();
			Blocks.generate(Block, root);
				try {
					Sound.playBackgroundSound("src/sound/backgroundmusic.wav");
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}	
			player= new Player[2];
			Player player1= new Player();   
			Player player2= new Player();
			player[0]=(player1);
			player[1]=(player2);
			keyListener=new KeyListener();
			keyListener.setKeys(this,KeyCode.A, KeyCode.D, KeyCode.W, KeyCode.SPACE,KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP,KeyCode.DOWN,KeyCode.ESCAPE,scene);
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
			weapon=new Weapon();
			weapon.genWeapons(this,"/img/weapons/weapon2.png","/img/weapons/bullet.png");
			keyListener.handle();
			timer = new AnimationTimer() {
				@Override
				public void handle(long now){
					player[0].handle(now);
					player[1].handle(now);
					Blocks.handle(now);
					weapon.handle(now);
					if(player[0].life==0||player[1].life==0) {
						Sound.close();
						try {
							Sound.playSound("src/sound/gameover.wav");
						} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						timer.stop();
						DeathScreen();
						}
				}
			};
			timer.start();
			stage.setScene(scene);
			stage.show();
		}



	/**
	 * timer wird gestoppt
	 * Sound wird abgeschaltet
	 *  Buttons mit genButton(x,y,Path) erstellt
	 * neuer returnButton
	 * neuer backButton
	 * neuer saveButton
	 * neuer closeButton
	 */
	public void saveScreen() { 
		timer.stop();
		Sound.close();
		returnButton = genButton(240, 25,"/img/buttonImg/ReturnButton.png");
		backButton= genButton(240,225,"/img/buttonImg/StartButton.png");
		saveButton = genButton(240,425,"/img/buttonImg/SaveButton.png");
		closeButton = genButton(240,625,"/img/buttonImg/ExitButton.png");
	}
	
	
	/**
	 * neue Group
	 * neue Scene 
	 * genStartOptions wird ausgeführt
	 * stage wird geshowed
	 * neuer backButton
	 */
	public void DeathScreen() {
		root=new Group(); 
		scene = new Scene(root, Start.W, Start.H, Color.WHITE);
		
		Start.genStartOptions(stage,root,"/img/background/DeathScreen.png","/img/playerSkins/icon.png");
		backButton= genButton(240,640,"/img/buttonImg/ReturnButton.png");
		winner=new Label();
		winner.setFont(new Font("04b_30",25));
		winner.setTextFill(Color.WHITE);
		winner.relocate(30, 200);
		String text="";
		if(player[1].life==0)text="Spieler Nr.1 hat das Spiel gewonnen!!!";
		else text="Spieler Nr.2 hat das Spiel gewonnen!!!";
		winner.setText(text);
		root.getChildren().add(winner);
		stage.setScene(scene);
		stage.show();
		
	}
	/**
	 * Buttons werden erstellt
	 * neuer button 
	 * neues Image mit Path
	 * wird relocatet mit x und y
	 * wird zu root hinzugefügt
	 * actionListener hinzugefügt
	 * @param x,y,path
	 * @return
	 */
	public Button genButton(int x,int y, String path) {
		Button button = new Button();
		Image img4= new Image(path);
		button.setGraphic(new ImageView(img4));
		button.relocate(x, y);
		root.getChildren().add(button);
		button.addEventHandler(ActionEvent.ACTION, actionListener);
		return button;
	}
/**
 * Zum lesen von dem Spielstand
 * @return
 * @throws Fehler
 * @throws IOException
 */
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
    
    /**
     *speichern von dem Spielstand
     * @param statsP1
     * @param statsP2
     * @param WorldID
     * @throws IOException
     */
    public void save(int statsP1,int statsP2,int WorldID) throws IOException {
    	OutputStream os= Files.newOutputStream(Paths.get("Spielstand.dat"));
    	DataOutputStream dos = new DataOutputStream(os);
    	dos.writeInt(statsP1);
    	dos.writeInt(statsP2);
    	dos.writeInt(WorldID);
    	dos.close();
    }
    

/**
 * um bestimmte Skins zu bekommen
 * @param WorldID
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
	 * BlocksImg zu bekommen
	 * @param WorldID
	 * @return
	 */
	public String getBlock(int WorldID) {
		if(WorldID==1) {
		return "/img/blocks/Block.png";
		}else return "/img/blocks/Block2.png" ;
	}


	/**
	 * BackgroundImg zu bekommen
	 * @param WorldID
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
	 * @return the weapon
	 */
	public Weapon getWea() {
		return weapon ;
	}

	/**
	 * @param worldID the worldID to set
	 */
	public void setWorldID(int worldID) {
		WorldID = worldID;
	}

	/**
	 * @return the worldID
	 */
	public int getWorldID() {
		return WorldID;
	}
	
	
   }

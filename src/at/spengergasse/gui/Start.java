
package at.spengergasse.gui;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;

public class Start extends Application {
	public static double W=800;
	public static double H=800;

	/**
	 *Application Start
	 *Neues Control-Objekt wird erstellt
	 *Alle Parameter werden an die Control-Klasse übergeben
	 **/
	@Override
	public void start(Stage primaryStage){
		new Controls(getParameters().getRaw());
	}
 
	/**
	 * Standart Konfiguration für den Background
	 * @param primaryStage,root,backgroundLink,iconLink
	 **/
	public static void genStartOptions(Stage primaryStage,Group root,String backgroundLink,String iconLink) {
		Image icon=new Image(iconLink);
		primaryStage.getIcons().addAll(icon);
		Image picture= new Image(backgroundLink);
		Node Background = new ImageView(picture);
		root.getChildren().add(Background);
	}

	/**
	 * Zum starten
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);

	}


}


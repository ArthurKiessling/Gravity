package at.spengergasse.gui;
/**
 * 
 */



import java.io.IOException;

import at.spengergasse.controls.Controls;
import at.spengergasse.controls.Fehler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Arthur Kiessling
 *
 */
public class ActionListener implements EventHandler<ActionEvent>{
	
	// reference to panel
	final private Controls cont;
	/**
	 * 
	 * @param simpleFrame
	 */
	public ActionListener(Controls cont){
		this.cont=cont;
	}

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(ActionEvent arg0) {
		// get component which is source of the event
		Object source=arg0.getSource();
		//**********************************************************************
		
		if (source==cont.getStartButton()){
			cont.decide();		
		}
		if (source==cont.getCloseButton()){
			cont.getStage().close();
		}
		
		if (source==cont.getSpaceButton()){
				cont.game(cont.getBackground(1),cont.getBlock(1),cont.getSkins(1),5,5);
				cont.setWorldID(1);
		}else
		if (source==cont.getEarthButton()){
				cont.game(cont.getBackground(2),cont.getBlock(2),cont.getSkins(2),5,5);
				cont.setWorldID(2);
		}else
		if (source==cont.getOldGameButton()){
			 int[]info =null;
			try {
				info = cont.read();
			} catch (Fehler | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
				cont.game(cont.getBackground(info[2]),cont.getBlock(info[2]),cont.getSkins(info[2]),info[0],info[1]);
				cont.setWorldID(info[2]);
		}
		
		if (source==cont.getReturnButton()){
			cont.root.getChildren().remove(cont.getReturnButton());
			cont.root.getChildren().remove(cont.getBackButton());
			cont.root.getChildren().remove(cont.getSaveButton());
			cont.root.getChildren().remove(cont.getCloseButton());
			cont.timer.start();
		}
		if (source==cont.getBackButton()){
			cont.startMenu();
		}
		if (source==cont.getSaveButton()){
			try {
				cont.save(cont.player[0].life,cont.player[1].life,cont.getWorldID());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}// handle

	

}

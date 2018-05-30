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
 * @author Leo Fanzott
 *
 */
public class ActionListener implements EventHandler<ActionEvent> {
	
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
			System.out.println("close");
			cont.getStage().close();
		}
		
		if (source==cont.getSpaceButton()){
				cont.game(cont.getBackground(1),cont.getBlock(1),cont.getSkins(1),5,5);
		}else
		if (source==cont.getEarthButton()){
				cont.game(cont.getBackground(2),cont.getBlock(2),cont.getSkins(2),5,5);
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
	          
		}
		
		
		
	}// handle

	

}

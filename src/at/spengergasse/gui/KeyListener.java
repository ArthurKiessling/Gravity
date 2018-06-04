package at.spengergasse.gui;
/**
 * 
 */





import at.spengergasse.controls.Controls;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
/**
 * @author Arthur Kiessling
 *
 */
public class KeyListener{
	
	// reference to panel
	
	private KeyCode leftKey1;
	private KeyCode rightKey1;
	private KeyCode jumpKey1;
	private KeyCode shootKey1;
	
	private KeyCode leftKey2;
	private KeyCode rightKey2;
	private KeyCode jumpKey2;
	private KeyCode shootKey2;

	private KeyCode ExitKey;
	private Scene scene;
	private Controls cont;
	/**
	 * 
	 * @param simpleFrame
	 */
	public void setKeys(Controls cont,KeyCode leftKey1, KeyCode rightKey1, KeyCode jumpKey1, KeyCode shootKey1,KeyCode leftKey2, KeyCode rightKey2, KeyCode jumpKey2, KeyCode shootKey2,KeyCode ExitKey,Scene scene) {
		this.cont=cont;
		this.leftKey1=leftKey1;
		this.rightKey1=rightKey1;
		this.jumpKey1=jumpKey1;
		this.shootKey1=shootKey1;

		this.leftKey2=leftKey2;
		this.rightKey2=rightKey2;
		this.jumpKey2=jumpKey2;
		this.shootKey2=shootKey2;
		
		this.ExitKey=ExitKey;
		this.scene=scene;
		handle();
	}

	public void handle() {
	scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
		        	KeyCode code = event.getCode();
					if (code == leftKey1)
						cont.player[0].leftStart();
					else if (code == rightKey1)
						cont.player[0].rightStart();
					else if (code == jumpKey1)
						cont.player[0].jumpStart();
					else if (code == shootKey1)
						cont.player[0].shootStart();
					else if (code == leftKey2)
						cont.player[1].leftStart();
					else if (code == rightKey2)
						cont.player[1].rightStart();
					else if (code == jumpKey2)
						cont.player[1].jumpStart();
					else if (code == shootKey2)
						cont.player[1].shootStart();
		        	
				}

	 	});
	 scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
		        	KeyCode code = event.getCode();
					if (code == leftKey1)
						cont.player[0].leftStop();
					else if (code == rightKey1)
						cont.player[0].rightStop();
					else if (code == jumpKey1)
						cont.player[0].jumpStop();
					else if (code == shootKey1)
						cont.player[0].shootStop();
					else if (code == leftKey2)
						cont.player[1].leftStop();
					else if (code == rightKey2)
						cont.player[1].rightStop();
					else if (code == jumpKey2)
						cont.player[1].jumpStop();
					else if (code == shootKey2)
						cont.player[1].shootStop();
					else if (code == ExitKey)
						cont.saveScreen();
					
				}

	 	});
	 }


}



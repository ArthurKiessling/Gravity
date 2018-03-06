/**
 * 
 */
package at.spengergasse.game;
import at.spengergasse.Scenes.*;
import java.io.File;
import java.io.IOException;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 * @author Arthur Kiessling
 *
 */
public class Sound {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


	}
	public static void playSound(String File) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		File Sound = new File(File);
		   Clip clip =AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
		   clip.start();
	}

}
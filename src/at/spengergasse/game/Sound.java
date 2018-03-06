/**
 * 
 */
package at.spengergasse.game;

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
private static Clip clip;

	public static void main(String[] args) {
		// TODO Auto-generated method stub


	}
	static void playSound(String File) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		File Sound = new File(File);
		   clip =AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
		   clip.start();
	}
	static void stopSound() {
		
	}
}
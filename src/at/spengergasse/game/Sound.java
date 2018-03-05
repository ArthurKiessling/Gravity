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


	public static void main(String[] args) throws LineUnavailableException, InterruptedException, IOException, UnsupportedAudioFileException {
		// TODO Auto-generated method stub
		 
		playSound("src/laser.wav");


	}
	static void playSound(String File) throws LineUnavailableException, InterruptedException, IOException, UnsupportedAudioFileException {
		File Sound = new File(File);
		   Clip clip =AudioSystem.getClip();
		   clip.open(AudioSystem.getAudioInputStream(Sound));
		   clip.start();
		

	}
}
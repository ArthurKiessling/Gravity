/**
 * 
 */
package at.spengergasse.test;

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
public class sound {

	public static void playLaser() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		File Laser = new File("laser.wav");
		Clip clip = AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(Laser));
		clip.start();
		}
	public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		playLaser();
	}
	
}

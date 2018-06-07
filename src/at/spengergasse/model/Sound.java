/**
 * 
 */
package at.spengergasse.model;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 * @author Arthur Kiessling
 *
 */
public class Sound {

	public Clip background;

	/**
	 * Clip wird abgespielt
	 * @param File
	 * @throws LineUnavailableException
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	public void playSound(String File) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(File).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
		   clip.start();
	}
	
	/**
	 * Backgroundmusik wird abgespielt und öfters nach dem Ende wiederholt
	 * @param File
	 * @throws LineUnavailableException
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	public void playBackgroundSound(String File) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(File).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
	        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
	        gainControl.setValue(20f * (float) Math.log10(0.1));
		   clip.loop(1000000);
		        clip.start();

		   background=clip;
	}
	/** 
	 * Backgroundmusik wird geschlossen
	 */
	public void close() {
		background.close();
	}

	
}
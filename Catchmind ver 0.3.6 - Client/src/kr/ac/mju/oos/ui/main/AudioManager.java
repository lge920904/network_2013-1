package kr.ac.mju.oos.ui.main;

import java.io.File;
import java.util.ArrayList;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import kr.ac.mju.oos.constants.Constants.AUDIO_PLAYLIST;
import kr.ac.mju.oos.ui.dialogs.LoginDialog;
import kr.ac.mju.oos.ui.frames.MainFrame;

public class AudioManager {
	private Sequencer sequencer;
	private File file;
	private AUDIO_PLAYLIST playlist;
	private AudioInputStream audioInputStream;
	private Clip clip;
	private ArrayList<File> file_ArrayList;

	public AudioManager() {
		file_ArrayList = new ArrayList<File>();
		for(AUDIO_PLAYLIST playlist : AUDIO_PLAYLIST.values()){
			file = new File(playlist.getAudio_Url());
			file_ArrayList.add(file);
		}
	}

	public void selectMusic(Object object) {
		try {
			if (object instanceof LoginDialog) {
				play(file_ArrayList.get(0));
			} 
			// else if(object instanceof Wait){
			// play(fileList.get(1));
			// }
			else if (object instanceof MainFrame) {
				play(file_ArrayList.get(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stopMusic() {
		if (sequencer.isRunning())
			sequencer.stop();
	}

	public void play(File file) {
		System.out.println(file.exists());
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.setSequence(MidiSystem.getSequence(file));
			sequencer.open(); 
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
			
		/*	 Sequencer sequencer2 = MidiSystem.getSequencer();
			    sequencer2.open();
			    if (sequencer2 instanceof Synthesizer) {
			      Synthesizer synthesizer = (Synthesizer) sequencer2;
			      MidiChannel[] channels = synthesizer.getChannels();

			      // gain is a value between 0 and 1 (loudest)
			      double gain = 0.9D;
			      for (int i = 0; i < channels.length; i++) {
			        channels[i].controlChange(7, (int) (gain * 127.0));
			      }
			    }
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buttonSound(){
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File("./background_music/clicksound.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

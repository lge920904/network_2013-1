package kr.ac.mju.oos.uility;

import java.io.File;
import java.util.ArrayList;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants.AUDIO_PLAYLIST;
import kr.ac.mju.oos.ui.dialogs.LoginDialog;
import kr.ac.mju.oos.ui.frames.MainFrame;
import kr.ac.mju.oos.ui.panels.wait.MainPanel;

public class AudioManager {
	private Sequencer sequencer;
	private File file;
	private AUDIO_PLAYLIST playlist;
	private AudioInputStream audioInputStream;
	private Clip clip;
	private ArrayList<File> file_ArrayList;

	public AudioManager() {
		file_ArrayList = new ArrayList<File>();
		for (AUDIO_PLAYLIST playlist : AUDIO_PLAYLIST.values()) {
			file = new File(playlist.getAudio_Url());
			file_ArrayList.add(file);
		}
	}

	public void selectMusic(Object object) {
		try {
			if (object instanceof LoginDialog) {
				play(file_ArrayList.get(0));
			} /*
			 * else if (object instanceof Main) { play(fileList.get(1)); }
			 */else if (object instanceof MainFrame) {
				JPanel component = ((MainFrame) object).getPanel();
				if (component instanceof MainPanel) {
					play(file_ArrayList.get(1));
				} else if (component instanceof kr.ac.mju.oos.ui.panels.game.MainPanel) {
					play(file_ArrayList.get(2));
				}
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buttonSound() {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(file_ArrayList
					.get(1).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

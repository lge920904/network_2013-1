package kr.ac.mju.oos.ui.main;

import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

import kr.ac.mju.oos.constants.Constants.AUDIO_PLAYLIST;
import kr.ac.mju.oos.ui.dialogs.LoginDialog;
import kr.ac.mju.oos.ui.frames.MainFrame;

public class AudioManager {
	private Sequencer sequencer;
	private File file;
	private AUDIO_PLAYLIST playlist;

	public AudioManager() {
	}

	public void selectMusic(Object object) {
		try {
			if (object instanceof LoginDialog) {
				file = new File(playlist.login.getAudio_Url());
				play(file);
			} else if (object instanceof MainFrame) {
				file = new File(playlist.game.getAudio_Url());
				play(file);
			}
			// else if(object instanceof Wait){
			// if (sequencer!=null)sequencer.stop();
			// file = new File(playlist.wait.getAudio_Url());
			// play(file);
			// }
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
}

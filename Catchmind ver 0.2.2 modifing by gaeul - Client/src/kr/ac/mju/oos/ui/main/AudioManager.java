package kr.ac.mju.oos.ui.main;

import java.io.File;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class AudioManager implements Runnable {
	public AudioManager() {
		System.out.println("create");
		Thread t = new Thread(this);
		t.start();
	}
	public void run() {
		try {
			File file = new File("D:\\bgmusic.mid");
			System.out.println(file.exists());
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.setSequence(MidiSystem.getSequence(file));
			sequencer.open();
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

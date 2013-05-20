package kr.ac.mju.oos.ui.frames;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.ui.main.AudioManager;
import kr.ac.mju.oos.ui.panels.game.MainPanel;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private AudioManager audioManager;
	private MainPanel mainPanel;
	public MainFrame() {
		super();
	}

	public void init(AudioManager audioManager) {
		this.audioManager = audioManager;
		audioManager.selectMusic(this);

		Dimension frame;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		this.setSize(Constants.FRAMES_MAIN_WIDTH, Constants.FRAMES_MAIN_HEIGHT);
		frame = this.getSize();

		this.setLocation((int) (screen.getWidth() / 2 - frame.getWidth() / 2),
				(int) (screen.getHeight() / 2 - frame.getHeight() / 2));

		mainPanel = new MainPanel();
		this.add(mainPanel);
		mainPanel.init(audioManager);

		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

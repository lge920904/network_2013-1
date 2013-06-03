package kr.ac.mju.oos.ui.frames;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.ui.panels.game.MainPanel;
import kr.ac.mju.oos.uility.AudioManager;
import kr.ac.mju.oos.uility.GameModeChanger;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private AudioManager audioManager;
	private kr.ac.mju.oos.ui.panels.wait.MainPanel mainPanel;

	public MainFrame() {
		// TODO Auto-generated constructor stub
		super();
		System.out.println("MainFrameConstruction");
	}

	public void init(AudioManager audioManager, String userID,
			GameModeChanger gameModeChanger) {

		Dimension frame;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		this.setSize(Constants.FRAMES_MAIN_WIDTH, Constants.FRAMES_MAIN_HEIGHT);
		frame = this.getSize();

		this.setLocation((int) (screen.getWidth() / 2 - frame.getWidth() / 2),
				(int) (screen.getHeight() / 2 - frame.getHeight() / 2));
		System.out.println("여기까진와?");
		// mainPanel = new MainPanel();
		// this.add(mainPanel);
		mainPanel = new kr.ac.mju.oos.ui.panels.wait.MainPanel();
		this.add(mainPanel);
		System.out.println("MainPanel add");

		mainPanel.init(gameModeChanger);
		System.out.println("mainPanel init");

		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("왜두개안뜨니?");
		this.setVisible(true);

		this.audioManager = audioManager;
		audioManager.selectMusic(getPanel());
	}

	public JPanel getPanel() {
		return mainPanel;
	}

}

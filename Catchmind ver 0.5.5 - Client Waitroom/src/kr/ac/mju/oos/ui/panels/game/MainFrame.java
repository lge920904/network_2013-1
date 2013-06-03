package kr.ac.mju.oos.ui.panels.game;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.ui.panels.game.MainPanel;
import kr.ac.mju.oos.uility.AudioManager;
import kr.ac.mju.oos.uility.GameModeChanger;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private AudioManager audioManager;
	private kr.ac.mju.oos.ui.panels.game.MainPanel mainPanel;

	public MainFrame() {
		// TODO Auto-generated constructor stub
		super();
		System.out.println("GameFrame Construction");
	}

	public void init(AudioManager audioManager, String userID,
			GameModeChanger gameModeChanger) {

		Dimension frame;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		this.setSize(Constants.FRAMES_MAIN_WIDTH, Constants.FRAMES_MAIN_HEIGHT);
		frame = this.getSize();

		this.setLocation((int) (screen.getWidth() / 2 - frame.getWidth() / 2),
				(int) (screen.getHeight() / 2 - frame.getHeight() / 2));

		mainPanel = new MainPanel();
		this.add(mainPanel);

		System.out.println("첫째 ");
		mainPanel.init(gameModeChanger);

		System.out.println("둘쨰 ");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(false);

		this.audioManager = audioManager;
		audioManager.selectMusic(getPanel());
		System.out.println("GameFrameInitEnd");
	}

	public kr.ac.mju.oos.ui.panels.game.MainPanel getPanel() {
		return mainPanel;
	}

	public void start(ArrayList<String> users) {
		// TODO Auto-generated method stub
		for (int i = 0; i < users.size(); i++) {
			if (i == 0) {
				this.getPanel().getBottomPanel().getLeftPlayerListPanel()
						.setText(users.get(i), 1);
			} else if (i % 2 == 1) {
				this.getPanel().getBottomPanel().getRightPlayerListPanel()
						.setText(users.get(i), i / 2 + 1);
			} else if (i % 2 == 0) {
				this.getPanel().getBottomPanel().getLeftPlayerListPanel()
						.setText(users.get(i), i / 2 + 1);
			}
		}
	}

}

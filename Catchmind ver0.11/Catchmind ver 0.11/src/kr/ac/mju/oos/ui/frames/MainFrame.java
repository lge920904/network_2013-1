package kr.ac.mju.oos.ui.frames;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.ui.panels.game.MainPanel;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private MainPanel mainPanel;

	public MainFrame() {
		// TODO Auto-generated constructor stub
		super();
		this.init();
	}

	public void init() {

		Dimension frame;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		this.setSize(Constants.FRAMES_MAIN_WIDTH, Constants.FRAMES_MAIN_HEIGHT);
		frame = this.getSize();

		this.setLocation((int) (screen.getWidth() / 2 - frame.getWidth() / 2),
				(int) (screen.getHeight() / 2 - frame.getHeight() / 2));

		mainPanel = new MainPanel();
		this.add(mainPanel); // 추후 수정 되어야함

		this.setVisible(true);
	}
}

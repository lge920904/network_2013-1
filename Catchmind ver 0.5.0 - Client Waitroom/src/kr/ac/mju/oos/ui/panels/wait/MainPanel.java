package kr.ac.mju.oos.ui.panels.wait;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.uility.GameModeChanger;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private LeftPanel leftPanel;
	private RightPanel rightPanel;

	public MainPanel() {
		// TODO Auto-generated constructor stub
		super();
		leftPanel = new LeftPanel();
		rightPanel = new RightPanel();
	}

	public void init(GameModeChanger gameModeChanger) {
		this.setPreferredSize(new Dimension(Constants.FRAMES_MAIN_WIDTH,
				Constants.FRAMES_MAIN_HEIGHT));
		this.setLayout(new BorderLayout());

		this.add(leftPanel, BorderLayout.WEST);
		this.add(rightPanel, BorderLayout.EAST);

		leftPanel.init(gameModeChanger);
		rightPanel.init();
	}
}

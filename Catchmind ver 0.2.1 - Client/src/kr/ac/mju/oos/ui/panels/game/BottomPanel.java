package kr.ac.mju.oos.ui.panels.game;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;

public class BottomPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private PlayerListPanel leftPlayerListPanel;
	private PlayerListPanel rightPlayerListPanel;
	private ChatPanel chatPanel;

	public BottomPanel() {
	}

	public void init() {
		leftPlayerListPanel = new PlayerListPanel("Left");
		rightPlayerListPanel = new PlayerListPanel("Right");
		chatPanel = new ChatPanel();

		this.setPreferredSize(new Dimension(Constants.FRAMES_MAIN_WIDTH,
				Constants.PANELS_BOTTOM_HEIGHT));
		this.setLayout(new BorderLayout());

		this.add(leftPlayerListPanel, BorderLayout.WEST);
		this.add(rightPlayerListPanel, BorderLayout.EAST);
		this.add(chatPanel, BorderLayout.CENTER);

		leftPlayerListPanel.init();
		rightPlayerListPanel.init();
		chatPanel.init();
	}
}

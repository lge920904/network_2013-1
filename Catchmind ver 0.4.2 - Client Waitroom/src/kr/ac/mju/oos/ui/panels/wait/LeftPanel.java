package kr.ac.mju.oos.ui.panels.wait;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;

public class LeftPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private MenuPanel menuPanel;
	private RoomListPanel roomListPanel;

	public LeftPanel() {
		// TODO Auto-generated constructor stub
		menuPanel = new MenuPanel();
		roomListPanel = new RoomListPanel();
	}

	public void init() {
		this.setPreferredSize(new Dimension(Constants.PANELS_LEFT_WIDTH,
				Constants.FRAMES_MAIN_HEIGHT));
		this.setLayout(new BorderLayout());

		this.add(menuPanel, BorderLayout.NORTH);
		this.add(roomListPanel, BorderLayout.CENTER);

		menuPanel.init();
		roomListPanel.init();
	}
}

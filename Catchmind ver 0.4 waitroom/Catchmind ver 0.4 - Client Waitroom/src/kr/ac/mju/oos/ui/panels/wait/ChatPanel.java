package kr.ac.mju.oos.ui.panels.wait;

import java.awt.Dimension;

import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;

public class ChatPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public ChatPanel() {
		// TODO Auto-generated constructor stub
	}

	public void init() {
		this.setPreferredSize(new Dimension(Constants.PANELS_RIGHT_WIDTH,
				Constants.FRAMES_MAIN_HEIGHT / 2));
	}
}

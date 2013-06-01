package kr.ac.mju.oos.ui.panels.wait;

import java.awt.Dimension;

import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;

public class RoomListPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public RoomListPanel() {
		// TODO Auto-generated constructor stub
		super();
	}

	public void init() {
		this.setPreferredSize(new Dimension(Constants.PANELS_RIGHT_WIDTH,
				Constants.PANELS_ROOMLIST_HEIGHT));
		this.setBackground(Constants.DEFAULT_BACKGROUND_COLOR);
	}
}

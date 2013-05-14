package kr.ac.mju.oos.ui.panels.game;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;

public class PlayerListPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private String location;

	public PlayerListPanel(String location) {
		// TODO Auto-generated constructor stub
		this.location = location;
		init();
	}

	public void init() {
		// 나머지도 채우세요
		this.setPreferredSize(new Dimension(Constants.PANELS_PLAYER_WIDTH,
				Constants.PANELS_BOTTOM_HEIGHT));
		this.add(new JLabel(location));
	}
}

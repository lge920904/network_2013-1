package kr.ac.mju.oos.ui.panels.game;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.controller.FrontController;

public class PlayerListPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private String location;

	public PlayerListPanel(String location) {
		// TODO Auto-generated constructor stub
		this.location = location;
	}

	public void init(FrontController controller) {
		// �������� ä�켼��
		this.setPreferredSize(new Dimension(Constants.PANELS_PLAYER_WIDTH,
				Constants.PANELS_BOTTOM_HEIGHT));
		this.add(new JLabel(location));
	}
}

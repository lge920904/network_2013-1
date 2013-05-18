package kr.ac.mju.oos.ui.panels.game;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;

public class ChatPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public ChatPanel() {
		// TODO Auto-generated constructor stub
	}

	public void init() {
		// setSize �ؾ��ؿ� �������� ä�켼��~
		this.setPreferredSize(new Dimension(Constants.PANELS_CHAT_WIDTH,
				Constants.PANELS_BOTTOM_HEIGHT));
		this.add(new JLabel("Chat"));
	}
}

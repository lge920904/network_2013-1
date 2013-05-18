package kr.ac.mju.oos.ui.panels.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;

public class ChatPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ChatBox chatBox;
	private ChatText chatText;
	public ChatPanel() {
		// TODO Auto-generated constructor stub
		init();
	}

	public void init() {
		// setSize 해야해요 나머지도 채우세요~
		this.setPreferredSize(new Dimension(Constants.PANELS_CHAT_WIDTH,
				Constants.PANELS_BOTTOM_HEIGHT));
		this.setLayout(new BorderLayout());
		this.setSize(Constants.PANELS_CHAT_WIDTH,
				Constants.PANELS_BOTTOM_HEIGHT);
		this.add(new JLabel("Chat"), BorderLayout.NORTH);
		this.add(new ChatBox(), BorderLayout.CENTER);
		this.add(new ChatText(), BorderLayout.SOUTH);
	}
}

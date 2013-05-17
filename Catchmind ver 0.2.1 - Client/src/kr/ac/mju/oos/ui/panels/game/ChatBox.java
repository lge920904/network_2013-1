package kr.ac.mju.oos.ui.panels.game;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;

import kr.ac.mju.oos.constants.Constants;

public class ChatBox extends JPanel {
	private JTextArea chatArea;
	public ChatBox() {
		this.setSize(Constants.PANELS_CHAT_WIDTH, 100);
		this.setBackground(Color.BLACK);
		chatArea = new JTextArea();
		this.add(chatArea);
	}
	public void setChat(String chat){
		System.out.println(chat);
	}

}

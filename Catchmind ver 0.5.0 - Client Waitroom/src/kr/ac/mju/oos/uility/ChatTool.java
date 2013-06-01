package kr.ac.mju.oos.uility;

import java.util.ArrayList;

import javax.swing.JTextArea;

import kr.ac.mju.oos.controller.ChatController;
import kr.ac.mju.oos.controller.FrontController;
import kr.ac.mju.oos.ui.panels.game.ChatPanel;

public class ChatTool implements Tool {
	private String fromServer;
	private ChatPanel chatPanel;
	private ChatController chatController;
	private JTextArea textArea;

	public ChatTool(ChatPanel chatPanel, FrontController controller) {
		this.chatPanel = chatPanel;
		this.textArea = this.chatPanel.getTextArea();
		this.chatController = new ChatController(chatPanel);
		chatController.init(controller, this);
	}

	public void sendChat(String msg) {
		chatController.sendData("SEND:" + msg);
	}

	public void sendToTextArea(String msg) {
		fromServer = msg;
	}

	public String getMessage() {
		return fromServer;
	}

	public ChatController getChatController() {
		return chatController;
	}

	public void receiveMsg(ArrayList<String> msg) {
		// chatPanel.receiveMsg(msg);
		String id = msg.get(0);
		String chatContent = msg.get(1);
		System.out.println("in Chat Tool  id : " + id);
		registerMsg("name", chatContent);
	}

	public void registerMsg(String id, String msg) {
		textArea.append(id + " : " + msg + '\n');
	}
}

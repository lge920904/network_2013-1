package kr.ac.mju.oos.controller;

import java.util.ArrayList;

import kr.ac.mju.oos.ui.panels.game.ChatPanel;
import kr.ac.mju.oos.uility.ChatTool;

public class ChatController extends Controller {
	private ChatTool chatTool;
	private ChatPanel chatPanel;

	public ChatController(ChatTool chatTool) {
		this.chatTool = chatTool;
	}

	public ChatController(ChatPanel chatPanel) {
		this.chatPanel = chatPanel;
	}

	@Override
	public boolean sendData(String sendString) {
		// TODO Auto-generated method stub
		if (initFlag) {
			System.out.println(sendString);
			if (frontController.sendData("Chat:" + sendString))
				return true;
		}
		return false;
	}

	public void receiveMsg(ArrayList<String> msg) {
		// TODO Auto-generated method stub

		((ChatTool) tool).receiveMsg(msg);
		// 두가지방법
		// chatPanel.getChatTool().receiveMsg(msg);
	}

	public ChatTool getChatTool() {
		return chatTool;
	}

	public void setChatTool(ChatTool chatTool) {
		this.chatTool = chatTool;
	}

	public ChatPanel getChatPanel() {
		return chatPanel;
	}

	public void setChatPanel(ChatPanel chatPanel) {
		this.chatPanel = chatPanel;
	}

}

// public boolean sendChat(String msg) {
// try {
// out.writeUTF(msg);
// } catch (IOException e) {
// e.printStackTrace();
// }
// return true;
// }
package kr.ac.mju.oos.uility;

import kr.ac.mju.oos.controller.ChatController;

public class ChatTool {
	private ChatController chatCon;
	private String fromServer;
	
	public ChatTool(){
		chatCon = new ChatController(this);
	}
	public void sendChat(String msg){
		chatCon.sendChat(msg);
	}
	public void sendToTextArea(String msg){
		fromServer = msg;
	}
	public String getMessage(){
		return fromServer;
	}
}

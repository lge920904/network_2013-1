package kr.ac.mju.oos.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.uility.ChatTool;

public class ChatController implements Runnable{
	private Socket socket;
	private Thread th;
	private DataOutputStream out;
	private DataInputStream in;
	private ChatTool chatTool;
		
	public ChatController(ChatTool chatTool){
		try{
			this.chatTool = chatTool;
			socket = new Socket("localhost", Constants.PORT_NUMBER);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			th = new Thread(this);
			th.start();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public boolean sendChat(String msg){
		try {
			out.writeUTF(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void run() {
		try {
			while(true){
				in = new DataInputStream(socket.getInputStream());
				chatTool.sendToTextArea(in.readUTF());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

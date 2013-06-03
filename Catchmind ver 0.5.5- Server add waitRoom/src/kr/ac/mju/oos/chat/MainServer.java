package kr.ac.mju.oos.chat;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import kr.ac.mju.oos.constants.Constants;

public class MainServer implements Runnable {
	private static final long serialVersionUID = 1L;

	private ServerSocket serverSocket;
	private Socket socket;
	private ArrayList<ChatHandler> lists;

	public MainServer(String title) {
		lists = new ArrayList<>();

		try {
			serverSocket = new ServerSocket(Constants.CHAT_PORT_NUMBER);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Chat Server Construction");
	}

	public void printClientList() {
		for (int i = 0; i < lists.size(); i++) {
			System.out.println(lists.get(i));
		}
		System.out.println("현재 총 사용자 : " + lists.size());
	}

	public void sendAllChat(String msg, ChatHandler handler) {
		// TODO Auto-generated method stub
		ChatHandler temp;
		ObjectOutputStream tempStream;
		String sendMsg = Constants.CHAT_CONTROLLER + ":" + msg;
		System.out.println(sendMsg);
		try {
			System.out.println("at Send All Chat " + "사용자수 : " + lists.size());
			for (int i = 0; i < lists.size(); i++) {
				if (!(lists.get(i).equals(handler))) {
					temp = lists.get(i);
					tempStream = temp.getOutputStream();
					tempStream.writeObject(sendMsg);
					System.out.println("in Iteration :  " + sendMsg);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Chat Server - sendAllChat Error");
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				socket = serverSocket.accept();
				
				ChatHandler chatHandler = new ChatHandler(this, socket);
				lists.add(chatHandler);
				Thread thread = new Thread(chatHandler);
				thread.start();
				System.out.println("Chat Server Connection OK");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void init() {
		Thread t = new Thread(this);
		t.start();
	}

	public ArrayList<ChatHandler> getLists() {
		// TODO Auto-generated method stub
		return lists;
	}

}

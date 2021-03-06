package kr.ac.mju.oos.controller;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.uility.MessageParser;

public class FrontController {

	private Socket gameSocket;
	private PrintWriter gameWriter;
	private BufferedReader gameReader;
	private ObjectInputStream objectInputStream;

	private Socket chatSocket;
	private BufferedReader chatReader;
	private PrintWriter chatWriter;
	private ObjectInputStream chatInputStream;

	private CanvasController canvasController;
	private ChatController chatController;

	public FrontController() {
		try {
			gameSocket = new Socket("localhost", Constants.GAME_PORT_NUMBER);
			gameWriter = new PrintWriter(new OutputStreamWriter(
					gameSocket.getOutputStream()));
			gameReader = new BufferedReader(new InputStreamReader(
					gameSocket.getInputStream()));

			chatSocket = new Socket("localhost", Constants.CHAT_PORT_NUMBER);
			chatWriter = new PrintWriter(new OutputStreamWriter(
					chatSocket.getOutputStream()));
			chatReader = new BufferedReader(new InputStreamReader(
					chatSocket.getInputStream()));

			objectInputStream = new ObjectInputStream(
					gameSocket.getInputStream());
			chatInputStream = new ObjectInputStream(chatSocket.getInputStream());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("FrontController Construction Error");
			e.printStackTrace();
		}
	}

	public void init(CanvasController canvasController,
			ChatController chatController) {
		this.canvasController = canvasController;
		this.chatController = chatController;

		Thread gameThread = new Thread(new DrawReceiveThread());
		Thread chatThread = new Thread(new ChatReceiveThread());
		// Thread t = new Thread(this);
		// t.start();
		gameThread.start();
		chatThread.start();
	}

	// 메세지 보낼때
	public boolean sendData(String sendString) {
		String[] strings = sendString.split(":");
		String controllerName = strings[0];
		String sendMsg = strings[1] + ":" + strings[2];

		if (controllerName.equals("Canvas")) {
			gameWriter.println(sendMsg);
			gameWriter.flush();
		} else if (controllerName.equals("Chat")) {

			sendMsg += ":" + strings[3];
			System.out.println("FrontController SendString - " + sendString);
			System.out.println("FrontController SendMsg - " + sendMsg);
			chatWriter.println(sendMsg);
			chatWriter.flush();
		}
		return true;
	}

	private class ChatReceiveThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				try {
					String string = chatInputStream.readObject().toString();
					System.out
							.println("In FrontController : receive Message - "
									+ string);
					String[] tokens = string.split(":");
					System.out.println(tokens[0]);
					if (tokens[0].equals("Chat")) {
						ArrayList<String> msg = new ArrayList<String>();
						System.out.println("at FrontController Chat Receive");
						msg.add(tokens[2]);
						msg.add(tokens[3]);
						if (msg != null && msg.size() == 2) {
							chatController.receiveMsg(msg);
						}
						// msg의 첫번쨰는 id, 두번째는 채팅 내용으로 구성
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("FrontController Chat Part run Error");
				}
			}
		}
	}

	private class DrawReceiveThread implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				try {
					String tempString = objectInputStream.readObject()
							.toString();
					String[] tokens = tempString.split(":");
					System.out.println("In FrontController : receive Message");
					System.out.println(tokens[0]);
					if (tokens[0].equals("Canvas")) {
						ArrayList<Point> tempPoints = MessageParser
								.getInstance().parsePositionMsg(tokens[1]);
						// System.out.println(tempString);
						if (tempPoints != null) {
							canvasController.serverDrawing(tempPoints);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("FrontController Draw Part run Error");
				}
			}
		}
	}

}

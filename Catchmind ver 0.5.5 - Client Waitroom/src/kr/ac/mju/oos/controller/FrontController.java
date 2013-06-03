package kr.ac.mju.oos.controller;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.ui.main.Launcher;
import kr.ac.mju.oos.uility.MessageParser;

public class FrontController {
	private static FrontController instance = new FrontController();

	private Socket gameSocket;
	private PrintWriter gameWriter;
	private BufferedReader gameReader;
	private ObjectInputStream objectInputStream;

	private Socket chatSocket;
	private BufferedReader chatReader;
	private PrintWriter chatWriter;
	private ObjectInputStream chatInputStream;

	private Socket waitSocket;
	private BufferedReader waitReader;
	private PrintWriter waitWriter;
	private ObjectOutputStream waitOutputStream;
	private ObjectInputStream waitInputStream;

	private CanvasController canvasController;
	private ChatController chatController;
	private WaitController waitController;

	public static FrontController getInstance() {
		if (instance == null)
			instance = new FrontController();
		System.out.println("getInstance");
		return instance;
	}

	private FrontController() {
		System.out.println("FrontController");
		try {
			System.out.println("FrontController Socket Start");
			gameSocket = new Socket("localhost", Constants.GAME_PORT_NUMBER);
			gameWriter = new PrintWriter(new OutputStreamWriter(
					gameSocket.getOutputStream()));
			gameReader = new BufferedReader(new InputStreamReader(
					gameSocket.getInputStream()));
			System.out.println("FrontController Socket game");

			chatSocket = new Socket("localhost", Constants.CHAT_PORT_NUMBER);
			chatWriter = new PrintWriter(new OutputStreamWriter(
					chatSocket.getOutputStream()));
			chatReader = new BufferedReader(new InputStreamReader(
					chatSocket.getInputStream()));
			System.out.println("FrontController Socket chat");

			waitSocket = new Socket("localhost", Constants.WAIT_PORT_NUMBER);
			waitWriter = new PrintWriter(new OutputStreamWriter(
					waitSocket.getOutputStream()));
			waitReader = new BufferedReader(new InputStreamReader(
					waitSocket.getInputStream()));
			System.out.println("FrontController Socket wait");

			System.out.println("FrontController Socket Stream");
			objectInputStream = new ObjectInputStream(
					gameSocket.getInputStream());
			System.out.println("FrontController Socket input End");
			chatInputStream = new ObjectInputStream(chatSocket.getInputStream());
			System.out.println("FrontController chat input End");

			waitOutputStream = new ObjectOutputStream(
					waitSocket.getOutputStream());
			waitOutputStream.flush();
			System.out.println("FrontController wait output End");
			waitInputStream = new ObjectInputStream(waitSocket.getInputStream());
			System.out.println("FrontController wait input End");

			gameWriter.println("userid:" + Launcher.userID);
			gameWriter.flush();
			chatWriter.println("userid:" + Launcher.userID);
			chatWriter.flush();
			waitWriter.println("trash:1");// 버퍼 에러 ㅠㅠ
			waitWriter.println("userid:" + Launcher.userID);
			waitWriter.flush();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("FrontController Construction Error");
			e.printStackTrace();
		}
	}

	public WaitController getWaitController() {
		return waitController;
	}

	public void init(CanvasController canvasController,
			ChatController chatController) {
		this.canvasController = canvasController;
		this.chatController = chatController;

		Thread gameThread = new Thread(new DrawReceiveThread());
		Thread chatThread = new Thread(new ChatReceiveThread());
		Thread waitThread = new Thread(new WaitReceiveThread());
		// Thread t = new Thread(this);
		// t.start();
		System.out.println("FrontController Init");
		gameThread.start();
		chatThread.start();
		waitThread.start();
	}

	// 메세지 보낼때
	public boolean sendData(String sendString) {
		String[] strings = sendString.split(":");
		String controllerName = strings[0];

		String sendMsg /* = strings[1] + ":" + strings[2] */;
		sendMsg = strings[1];
		for (int i = 2; i < strings.length; i++) {
			sendMsg += ":" + strings[i];
		}

		if (controllerName.equals("Canvas")) {
			// System.out.println(sendMsg);
			gameWriter.println(sendMsg);
			gameWriter.flush();
		} else if (controllerName.equals("Chat")) {
			// System.out.println("FrontController SendString - " + sendString);
			// System.out.println("FrontController SendMsg - " + sendMsg);
			chatWriter.println(sendMsg);
			chatWriter.flush();
		} else if (controllerName.equals("Wait")) {
			waitWriter.println("trash:1");
			System.out.println("버퍼에러");
			waitWriter.println(sendMsg);
			System.out.println("Wait Writer Print");
			waitWriter.flush();
		}
		return true;
	}

	private class WaitReceiveThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				try {
					String string = waitInputStream.readObject().toString();
					System.out
							.println("In FrontController : receive Message - "
									+ string);
					String[] tokens = string.split(":");
					System.out.println(tokens[0]);
					if (tokens[0].equals("Wait")) {
						if (tokens[1].equals("CREATE")) {
							System.out
									.println("at FrontController Wait Receive");
							for (String s : tokens)
								System.out.println(s);
							System.out.println("Tokens");
							waitController.setRoomNumber(Integer
									.parseInt(tokens[3]));
							System.out
									.println("Set RoomNumber - WaitRecieveThread");
							sendData(new String("Chat:ROOM:CREATE:" + tokens[3]));
							sendData(new String("Canvas:ROOM:CREATE:"
									+ tokens[3]));
						} else if (tokens[1].equals("JOIN")) {
							System.out.println("in Join");
							String tempString = tokens[4];
							System.out.println(tempString);
							sendData(new String("Chat:ROOM:JOIN:") + tokens[3]);
							sendData(new String("Canvas:ROOM:JOIN:")
									+ tokens[3]);
							waitController.joinRoom(
									Integer.parseInt(tokens[3]), tempString);
						} else if (tokens[1].equals("ADD")) {
							//
							System.out.println("Message 도착");
							String[] msgs = tokens[3].split(" ");
							for (String s : msgs)
								System.out.println(s);
							waitController.addRoom(Integer.parseInt(tokens[2]),
									msgs);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					System.out.println("FrontController Wait Part run Error");
				}
			}

		}
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
					System.out
							.println("In FrontController : receive Message - DrawReceiveThread");
					System.out.println(tokens[0]);
					if (tokens[0].equals("Canvas")) {
						if (tokens[1].equals("SET")) {
							System.out.println("At Frontcontroller - Set");
							canvasController.setGraphicsFromServer(tempString);
						} else {
							for (String s : tokens)
								System.out.println(s);
							ArrayList<Point> tempPoints = MessageParser
									.getInstance().parsePositionMsg(tokens[1]);
							System.out.println("Draw Start" + tempPoints);
							for (int i = 0; i < tempPoints.size(); i++) {
								System.out.println(tempPoints.get(i));
							}
							// System.out.println(tempString);
							if (tempPoints != null) {
								System.out.println("드로잉 왜안해?");
								System.out.println(canvasController);
								canvasController.serverDrawing(tempPoints);
								System.out.println("드로잉 햇네?");
							}
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					// System.out.println("FrontController Draw Part run Error");
				}
			}
		}
	}

	public void setWaitController(WaitController waitController2) {
		// TODO Auto-generated method stub
		this.waitController = waitController2;
	}

}

package kr.ac.mju.oos.wait;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;

import kr.ac.mju.oos.chat.ChatHandler;
import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.tool.ServerManager;

public class MainServer extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;

	private kr.ac.mju.oos.chat.MainServer chatServer;
	private kr.ac.mju.oos.game.MainServer gameServer;

	private ServerSocket serverSocket;
	private Socket socket;
	private ArrayList<RoomHandler> lists;
	private ArrayList<StreamThread> threads;
	private int serialNumber;

	public MainServer(String title) {
		super(title);
		serialNumber = 0;
		lists = new ArrayList<>();
		threads = new ArrayList<>();
		gameServer = new kr.ac.mju.oos.game.MainServer("gameServer");
		chatServer = new kr.ac.mju.oos.chat.MainServer("chatServer");
		ServerManager.getInstance().setWaitServer(this);
		ServerManager.getInstance().setGameServer(gameServer);
		ServerManager.getInstance().setChatServer(chatServer);

		setBounds(0, 0, 400, 300);
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		try {
			serverSocket = new ServerSocket(Constants.WAIT_PORT_NUMBER);
			Thread t = new Thread(this);
			t.start();
			gameServer.init();
			chatServer.init();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public RoomHandler getRoomHandler(int serialNumber) {
		System.out.println("getRoomHandler = " + serialNumber);
		for (int i = 0; lists.size() > i; i++) {
			System.out.println("getRoomHandler Serial Number = "
					+ lists.get(i).getSerialNumber());
			if (lists.get(i).getSerialNumber() == serialNumber) {
				return lists.get(i);
				// 방만들떄마다 2씩 오르는 버그
			}
		}
		return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				socket = serverSocket.accept();
				System.out.println("메인 소켓");
				// for (int i = 0; threads.size() > i; i++) {
				// if (threads.get(i).getSocket().equals(socket))
				// System.out.println("같은 소켓 찾음");
				// }
				StreamThread streamThread = new StreamThread(this, socket);
				threads.add(streamThread);
				Thread thread = new Thread(streamThread);
				thread.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void matchingRoom(String[] recMsg) {
		// TODO Auto-generated method stub
	}

	public void exitRoom(String[] recMsg) {
		// TODO Auto-generated method stub

	}

	public int joinRoom(String roomNumber) {
		// TODO Auto-generated method stub
		// msg에서 방 이름 및 번호를 긁어온 후 해당 룸리스트를 찾아 가져오기
		RoomHandler roomHandler = searchRoom(roomNumber);
		return roomHandler.getSerialNumber();
	}

	public void createRoom(String[] recMsg) {
		this.serialNumber += 1;
		RoomHandler roomHandler = new RoomHandler(this, recMsg,
				this.getSerialNumber());
		lists.add(roomHandler);
	}

	public RoomHandler searchRoom(String roomNumber) {
		System.out.println("서치룸시작");
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i).getSerialNumber() == Integer.parseInt(roomNumber)) {
				System.out.println("찾음");
				return lists.get(i);
			}
			// if (lists.get(i).getRoomInformation().equals(roomNumber)) {
			// System.out.println("찾음");
			// return lists.get(i);
			// }
		}
		System.out.println("못찾음");
		return null;
	}

	public void init() {
	}

	public ArrayList<RoomHandler> getLists() {
		// TODO Auto-generated method stub
		return lists;
	}

	public ArrayList<StreamThread> getThreadLists() {
		// TODO Auto-generated method stub
		return threads;
	}

	public void printClientList() {
		for (int i = 0; i < threads.size(); i++) {
			System.out.println(threads.get(i));
		}
		System.out.println("현재 총 사용자 : " + threads.size());
	}

	public synchronized int getSerialNumber() {
		return serialNumber;
	}

	public void sendAllClient(int roomNumber, String[] recMsg,
			StreamThread thread) {
		// TODO Auto-generated method stub
		try {
			StreamThread temp;
			ObjectOutputStream tempStream;
			for (int i = 0; i < threads.size(); i++) {
				if (!(threads.get(i).equals(thread))) {
					// if (!(lists.get(i).equals(handler))) {
					// temp = lists.get(i);
					// tempStream = temp.getOutputStream();
					// tempStream.writeObject(sendMsg);
					// System.out.println("in Iteration :  " + sendMsg);
					// }
					temp = threads.get(i);
					tempStream = temp.getStream();
					if (recMsg.length == 5) {
						tempStream.writeObject("Wait:ADD:" + roomNumber + ":"
								+ recMsg[0] + " " + recMsg[1] + " " + recMsg[2]
								+ " " + recMsg[3] + " " + recMsg[4]);
					} else {
						tempStream.writeObject("Wait:ADD:" + roomNumber + ":"
								+ recMsg[0] + " " + recMsg[1] + " " + recMsg[2]
								+ " " + recMsg[3] + " " + recMsg[4] + " "
								+ recMsg[5]);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	// private class StreamThread implements Runnable {
	// private MainServer waitServer;
	// private Socket socket;
	// private ObjectOutputStream objectOutputStream;
	// private ObjectInputStream objectInputStream;
	// private BufferedReader reader;
	//
	// public StreamThread(MainServer waitServer, Socket socket) {
	// // TODO Auto-generated constructor stub
	// try {
	// System.out.println("접속시도");
	// this.waitServer = waitServer;
	// this.socket = socket;
	// reader = new BufferedReader(new InputStreamReader(
	// socket.getInputStream()));
	// this.objectOutputStream = new ObjectOutputStream(
	// socket.getOutputStream());
	// this.objectOutputStream.flush();
	// this.objectInputStream = new ObjectInputStream(
	// socket.getInputStream());
	// System.out.println(objectOutputStream);
	// } catch (Exception e) {
	// // TODO: handle exception
	// System.out.println("wait Stream Thread Error");
	// }
	// }
	//
	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	// String msg = "";
	// try {
	// while ((msg = reader.readLine()) != null) {
	// // System.out.println("RoomHandler " + msg);
	// String[] tokens = msg.split(":");
	// String[] recMsg = tokens[1].split(" ");
	//
	// if (tokens[0].equals("JOIN")) {
	// joinRoom(recMsg);
	// } else if (tokens[0].equals("CREATE")) {
	// System.out.println("--------Wait Server------");
	// System.out.println("Socket Channel : "
	// + socket.getChannel());
	// System.out.println("Socket Port : " + socket.getPort());
	// System.out.println("Socket InetAddress : "
	// + socket.getInetAddress());
	// createRoom(recMsg);
	// objectOutputStream
	// .writeObject("Wait:CREATE:roomNumber:"
	// + String.valueOf(getSerialNumber()));
	// objectOutputStream.flush();
	//
	// } else if (tokens[0].equals("EXIT")) {
	// exitRoom(recMsg);
	// } else if (tokens[0].equals("MATCHING")) {
	// matchingRoom(recMsg);
	// }
	// }
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
}

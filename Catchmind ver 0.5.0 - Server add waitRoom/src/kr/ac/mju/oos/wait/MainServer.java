package kr.ac.mju.oos.wait;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.tool.ServerManager;

public class MainServer extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;

	private kr.ac.mju.oos.chat.MainServer chatServer;
	private kr.ac.mju.oos.game.MainServer gameServer;

	private ServerSocket serverSocket;
	private Socket socket;
	private ArrayList<RoomHandler> lists;
	private BufferedReader reader;
	private ObjectOutputStream objectOutputStream;

	private int serialNumber;

	public MainServer(String title) {
		super(title);
		serialNumber = 0;
		lists = new ArrayList<>();
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
				reader = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				System.out.println("Room Server Connection OK");

				String msg = "";
				try {
					objectOutputStream = new ObjectOutputStream(
							socket.getOutputStream());
					objectOutputStream.flush();
					while ((msg = reader.readLine()) != null) {
						// System.out.println("RoomHandler " + msg);
						String[] tokens = msg.split(":");
						String[] recMsg = tokens[1].split(" ");

						if (tokens[0].equals("JOIN")) {
							joinRoom(recMsg);
						} else if (tokens[0].equals("CREATE")) {
							System.out.println("--------Wait Server------");
							System.out.println("Socket Channel : "
									+ socket.getChannel());
							System.out.println("Socket Port : "
									+ socket.getPort());
							System.out.println("Socket InetAddress : "
									+ socket.getInetAddress());
							createRoom(recMsg);
							objectOutputStream.writeObject("Wait:roomNumber:"
									+ String.valueOf(this.getSerialNumber()));

						} else if (tokens[0].equals("EXIT")) {
							exitRoom(recMsg);
						} else if (tokens[0].equals("MATCHING")) {
							matchingRoom(recMsg);
						}
					}
				} catch (Exception e) {
					System.out.println("WaitServer 에러");
				} finally {
					try {
						reader.close();
						socket.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void matchingRoom(String[] recMsg) {
		// TODO Auto-generated method stub
	}

	private void exitRoom(String[] recMsg) {
		// TODO Auto-generated method stub

	}

	private void joinRoom(String[] recMsg) {
		// TODO Auto-generated method stub
		// msg에서 방 이름 및 번호를 긁어온 후 해당 룸리스트를 찾아 가져오기
	}

	public void createRoom(String[] recMsg) {
		this.serialNumber += 1;
		RoomHandler roomHandler = new RoomHandler(this, recMsg,
				this.getSerialNumber());
		lists.add(roomHandler);
	}

	public RoomHandler searchRoom(String[] recMsg) {
		return null;
	}

	public void init() {
	}

	public ArrayList<RoomHandler> getLists() {
		// TODO Auto-generated method stub
		return lists;
	}

	public void printClientList() {
		for (int i = 0; i < lists.size(); i++) {
			System.out.println(lists.get(i));
		}
		System.out.println("현재 총 사용자 : " + lists.size());
	}

	public synchronized int getSerialNumber() {
		return serialNumber;
	}
}

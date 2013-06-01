package kr.ac.mju.oos.chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import kr.ac.mju.oos.tool.ServerManager;
import kr.ac.mju.oos.wait.RoomHandler;

public class ChatHandler implements Runnable {
	private Socket socket;
	private MainServer mainServer;
	private ObjectOutputStream outputStream;
	private DataInputStream inputStream;
	private PrintWriter writer;
	private BufferedReader reader;

	private int serialNumber;
	private int roomNubmer;
	private String userId;

	public ChatHandler(int serialNubmer, int roomNumber) {
		this.serialNumber = serialNubmer;
		this.roomNubmer = roomNumber;
	}

	public ChatHandler(MainServer mainServer, Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		this.mainServer = mainServer;
		try {
			inputStream = new DataInputStream(socket.getInputStream());
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			writer = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (Exception e) {
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String msg = "";
		try {
			while ((msg = reader.readLine()) != null) {
				// System.out.println("ChatHandler " + msg);
				String[] tokens = msg.split(":");

				if (tokens[0].equals("SEND")) {
					sendChat(msg);
				} else if (tokens[0].equals("MOVE")) {
					String[] xy = tokens[1].split(" ");
				} else if (tokens[0].equals("ROOM")) {
					if (tokens[1].equals("CREATE")) {
						// token2는 룸넘버
						System.out.println("ChatMsg" + tokens[2]);
						for (String s : tokens) {
							System.out.println(s);
						}
						RoomHandler tempRoom = ServerManager.getInstance()
								.getWaitServer()
								.getRoomHandler(Integer.parseInt(tokens[2]));
						if (tempRoom != null) {
							tempRoom.setChatOfUsers(this);
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("사용자 접속종료");
		} finally {
			try {
				mainServer.getLists().remove(this);
				mainServer.printClientList();
				reader.close();
				socket.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

	private void sendChat(String msg) {
		// TODO Auto-generated method stub
		mainServer.sendAllChat(msg, this);
	}

	public void sendChatMsg(String msg) {

	}

	public PrintWriter getWriter() {
		return writer;
	}

	public ObjectOutputStream getOutputStream() {
		return outputStream;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getRoomNubmer() {
		return roomNubmer;
	}

	public void setRoomNubmer(int roomNubmer) {
		this.roomNubmer = roomNubmer;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}

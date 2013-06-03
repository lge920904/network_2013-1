package kr.ac.mju.oos.wait;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import kr.ac.mju.oos.tool.MsgParser;

public class StreamThread implements Runnable {
	private MainServer waitServer;
	private Socket socket;
	private ObjectOutputStream objectOutputStream;
	private BufferedReader reader;
	private PrintWriter writer;
	private String userID;

	public StreamThread(MainServer waitServer, Socket socket) {
		// TODO Auto-generated constructor stub
		try {
			System.out.println("접속시도");
			userID = new String("");
			this.socket = socket;
			this.waitServer = waitServer;
			this.objectOutputStream = new ObjectOutputStream(
					socket.getOutputStream());
			this.objectOutputStream.flush();
			writer = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			System.out.println(objectOutputStream);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("wait Stream Thread Error");
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Stream Thread Run");
		String msg = "";
		try {
			System.out.println("입력대기");

			while ((msg = reader.readLine()) != null) {
				System.out.println(msg);
				System.out.println("입력종료");
				// System.out.println("RoomHandler " + msg);
				String[] tokens = msg.split(":");
				String[] recMsg = tokens[1].split(" ");
				System.out.println("in Stream Thread");

				for (String s : tokens)
					System.out.println(s);
				if (tokens[0].equals("JOIN")) {
					int joinRoomNumber = waitServer.joinRoom(recMsg[0]);
					ArrayList<String> users = waitServer.searchRoom(recMsg[0])
							.getRoomUsers();
					String userString = MsgParser.getInstance().parseUserList(
							users);
					System.out.println("Join Room - Stream Thread" + " "
							+ userID + userString);
					objectOutputStream.writeObject("Wait:JOIN:roomNumber:"
							+ joinRoomNumber + ":" + userID + userString);
					objectOutputStream.flush();
				} else if (tokens[0].equals("CREATE")) {
					System.out.println("--------Wait Server------");
					System.out.println("Socket Channel : "
							+ socket.getChannel());
					System.out.println("Socket Port : " + socket.getPort());
					System.out.println("Socket InetAddress : "
							+ socket.getInetAddress());
					waitServer.createRoom(recMsg);
					waitServer.joinRoom(String.valueOf(waitServer
							.getSerialNumber()));
					System.out.println("Create Room - Stream Thread");
					objectOutputStream.writeObject("Wait:CREATE:roomNumber:"
							+ String.valueOf(waitServer.getSerialNumber())
							+ ":" + userID);
					objectOutputStream.flush();

					sendAllClient(waitServer.getSerialNumber(), recMsg);

				} else if (tokens[0].equals("EXIT")) {
					waitServer.exitRoom(recMsg);
				} else if (tokens[0].equals("MATCHING")) {
					waitServer.matchingRoom(recMsg);
				} else if (tokens[0].equals("userid")) {
					this.userID = tokens[1];
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Stream Thread Error");
		} finally {
			waitServer.getThreadLists().remove(this);
			waitServer.printClientList();
			try {
				reader.close();
				socket.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Stream Thread Error");
			}
		}

	}

	public void sendAllClient(int roomNumber, String[] recMsg) {
		waitServer.sendAllClient(roomNumber, recMsg, this);
	}

	public Socket getSocket() {
		return socket;
	}

	public ObjectOutputStream getStream() {
		// TODO Auto-generated method stub
		return objectOutputStream;
	}
}
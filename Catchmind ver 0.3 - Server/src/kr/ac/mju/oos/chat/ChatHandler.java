package kr.ac.mju.oos.chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatHandler implements Runnable {
	private Socket socket;
	private MainServer mainServer;
	private ObjectOutputStream outputStream;
	private DataInputStream inputStream;
	private PrintWriter writer;
	private BufferedReader reader;

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
				System.out.println("ChatHandler " + msg);
				String[] tokens = msg.split(":");

				if (tokens[0].equals("SEND")) {
					sendChat(msg);
				} else if (tokens[0].equals("MOVE")) {
					String[] xy = tokens[1].split(" ");
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

}

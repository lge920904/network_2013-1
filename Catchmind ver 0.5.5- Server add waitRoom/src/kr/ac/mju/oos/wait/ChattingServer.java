package kr.ac.mju.oos.wait;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;

import kr.ac.mju.oos.constants.Constants;

public class ChattingServer {
	ServerSocket serverSocket;
	PrintWriter writer;
	ArrayList<PrintWriter> UserOutputStream;

	public void init() {
		UserOutputStream = new ArrayList<PrintWriter>();
		try {
			serverSocket = new ServerSocket(Constants.WAIT_CHAT_PORT_NUMBER);
			System.out.println("서버 구성완료.   클라이언트로부터 접속을 기다리는 중....");
			while (true) {
				Socket userSocket = serverSocket.accept();
				System.out.println("클라이언트가 접속함.");
				writer = new PrintWriter(userSocket.getOutputStream());
				UserOutputStream.add(writer); // 이것은 각 클라이언트에 대해 출력할 다리를 객체배열로
												// 삼음.
				Thread t = new Thread(new UserHandle(userSocket));
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void tellEveryone(String message) {
		Iterator<PrintWriter> it = UserOutputStream.iterator();
		while (it.hasNext()) {
			writer = (PrintWriter) it.next();
			writer.println(message);
			writer.flush();
		}
	}

	class UserHandle implements Runnable {
		Socket userSocket;
		BufferedReader reader;

		UserHandle(Socket socket) {
			try {
				userSocket = socket;
				InputStreamReader isr = new InputStreamReader(socket.getInputStream());
				reader = new BufferedReader(isr);
			} catch (IOException e) {
			}
		}

		public void run() {
			String message;
			try {
				while ((message = reader.readLine()) != null) {
					tellEveryone(message);
				}
			} catch (IOException e) {
			}
		}
	}
}
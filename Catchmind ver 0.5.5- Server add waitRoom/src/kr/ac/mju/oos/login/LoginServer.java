package kr.ac.mju.oos.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.model.user.UserDAO;

public class LoginServer implements Runnable {

	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader reader;
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;
	private UserDAO userManager;

	public LoginServer(String title) {

		try {
			serverSocket = new ServerSocket(Constants.LOGIN_PORT_NUMBER);
			userManager = new UserDAO();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void init() {
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("loginserver on");
		try {
			while (true) {
				socket = serverSocket.accept();
				objectOutputStream = new ObjectOutputStream(
						socket.getOutputStream());
				objectOutputStream.flush();
				objectInputStream = new ObjectInputStream(
						socket.getInputStream());
				reader = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				System.out.println("loginserver 연결됨");
				String msg = "";
				while ((msg = reader.readLine()) != null) {
					System.out.println("LoginServer readLine " + msg);
					String[] tokens = msg.split(":");
					if (tokens[0].equals("signup")) {

					} else if (tokens[0].equals("login")) {
						String tempid = tokens[1];
						String temppw = tokens[2];
						if (userManager.checkLogin(tempid, temppw)) {
							System.out.println("check true");
							objectOutputStream.writeObject("true");
						} else {
							objectOutputStream.writeObject(false);
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Socket close");
		}
	}

}

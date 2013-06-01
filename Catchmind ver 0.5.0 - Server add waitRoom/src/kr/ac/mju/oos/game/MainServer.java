package kr.ac.mju.oos.game;

import java.awt.Point;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import kr.ac.mju.oos.constants.Constants;

public class MainServer implements Runnable {
	private static final long serialVersionUID = 1L;
	private static ServerSocket serverSocket;
	private static Socket socket;
	private ArrayList<GameHandler> lists;

	public MainServer(String title) {
		// TODO Auto-generated constructor stub
		lists = new ArrayList<>();

		try {
			serverSocket = new ServerSocket(Constants.GAME_PORT_NUMBER);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Game Server Construction");
	}

	public void moving(int x, int y) {
	}

	public ArrayList<GameHandler> getLists() {
		return lists;
	}

	public void printClientList() {
		for (int i = 0; i < lists.size(); i++) {
			System.out.println(lists.get(i));
		}
		System.out.println("현재 총 사용자 : " + lists.size());
	}

	public void sendAllDrawPoint(Point p1, Point p2, GameHandler handler) {
		String points = Constants.CANVAS_CONTROLLER + ":" + p1.x + " " + p1.y
				+ " " + p2.x + " " + p2.y;
		this.sendData(points, "sendAllDrawPoint Error", handler);
	}

	public void setGraphicsToAllClient(String msg, GameHandler handler) {
		// TODO Auto-generated method stub
		System.out.println("setGraphicsToAllClient - " + msg);
		this.sendData(Constants.CANVAS_CONTROLLER + ":" + msg,
				"setGraphicsToAllClient Error", handler);
	}

	public void sendData(String msg, String errorMsg, GameHandler handler) {
		GameHandler temp;
		ObjectOutputStream tempStream;

		try {
			for (int i = 0; i < lists.size(); i++) {
				if (!(lists.get(i).equals(handler))) {
					temp = lists.get(i);
					tempStream = temp.getOutputStream();
					tempStream.writeObject(msg);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("at Main Server sendData \nDetail Method - "
					+ errorMsg);
			e.printStackTrace();
		}
	}

	public void init() {
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				socket = serverSocket.accept();
				GameHandler gameHandler = new GameHandler(this, socket);
				lists.add(gameHandler);
				Thread thread = new Thread(gameHandler);
				thread.start();
				System.out.println("연결됨");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
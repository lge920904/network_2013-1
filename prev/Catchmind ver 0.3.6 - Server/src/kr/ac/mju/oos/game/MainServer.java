package kr.ac.mju.oos.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import kr.ac.mju.oos.constants.Constants;

public class MainServer extends Frame implements Runnable {
	private static final long serialVersionUID = 1L;
	private static ServerSocket serverSocket;
	private static Socket socket;
	private ArrayList<GameHandler> lists;
	private Image img = null;
	private Graphics2D gImg = null;

	public MainServer(String title) {
		// TODO Auto-generated constructor stub
		super(title);
		lists = new ArrayList<>();

		setBounds(100, 100, 1024, 768);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		try {
			serverSocket = new ServerSocket(Constants.GAME_PORT_NUMBER);
			Thread t = new Thread(this);
			t.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void drawing(int x, int y, int x1, int y1) {
		gImg.drawLine(x, y, x1, y1);
	}

	public void setGraphics(String msg) {
		String[] tokens = msg.split(":");
		String color = tokens[2];
		String[] colors = color.split(" ");
		int strokeSize = Integer.parseInt(tokens[4]);
		int r, g, b;
		r = Integer.parseInt(colors[0]);
		g = Integer.parseInt(colors[1]);
		b = Integer.parseInt(colors[2]);

		System.out.println("Mainserver - setGraphics");
		System.out.println(strokeSize);
		this.gImg.setStroke(new BasicStroke(strokeSize));
		this.gImg.setColor(new Color(r, g, b));
		// gI
		// gImg.setColor(c);
	}

	public void moving(int x, int y) {
	}

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}

	public Graphics getgImg() {
		return gImg;
	}

	public void setgImg(Graphics gImg) {
		this.gImg = (Graphics2D) gImg;
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
		this.setGraphics(msg);
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
		img = createImage(1024, 768);
		gImg = (Graphics2D) img.getGraphics();
		repaint();
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
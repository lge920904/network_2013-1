package kr.ac.mju.oos.game;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class GameHandler implements Runnable {
	private MainServer mainServer;
	private Socket socket;
	private BufferedReader reader;
	private ObjectOutputStream objectOutputStream;
	private OutputStream outputStream;
	private int x, y;
	private boolean turnFlag;

	public GameHandler(MainServer mainServer, Socket socket) throws IOException {
		// TODO Auto-generated constructor stub
		this.mainServer = mainServer;
		this.socket = socket;
		this.turnFlag = false;

		reader = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		outputStream = socket.getOutputStream();
		objectOutputStream = new ObjectOutputStream(outputStream);
		objectOutputStream.flush();
	}

	public void sendDrawPoint(int x1, int y1) {
		mainServer.sendAllDrawPoint(new Point(x, y), new Point(x1, y1), this);
	}

	public void sendSetGraphics(String msg) {
		mainServer.setGraphicsToAllClient(msg, this);
	}

	public ObjectOutputStream getOutputStream() {
		return objectOutputStream;
	}

	@Override
	public void run() {
		String msg = "";
		try {
			while ((msg = reader.readLine()) != null) {
				// System.out.println("GameHandler" + msg);
				String[] tokens = msg.split(":");

				if (tokens[0].equals("DRAW")) {
					String[] xy = tokens[1].split(" ");
					mainServer.drawing(x, y, Integer.parseInt(xy[0]),
							Integer.parseInt(xy[1]));
					sendDrawPoint(Integer.parseInt(xy[0]),
							Integer.parseInt(xy[1]));
					x = Integer.parseInt(xy[0]);
					y = Integer.parseInt(xy[1]);
					mainServer.repaint();
				} else if (tokens[0].equals("MOVE")) {
					String[] xy = tokens[1].split(" ");
					x = Integer.parseInt(xy[0]);
					y = Integer.parseInt(xy[1]);
				} else if (tokens[0].equals("SET")) {
					for (String s : tokens) {
						System.out.println(s);
					}
					sendSetGraphics(msg);
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
}

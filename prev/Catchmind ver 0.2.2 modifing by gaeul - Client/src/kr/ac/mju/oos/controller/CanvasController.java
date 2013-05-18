package kr.ac.mju.oos.controller;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import kr.ac.mju.oos.constants.Constants;

public class CanvasController {
	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;

	public CanvasController() {
		try {
			socket = new Socket("localhost", Constants.PORT_NUMBER);
			writer = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));

		} catch (Exception e) {	}
	}

	public void init() {

	}

	public boolean sendDrawPoint(Point p) {
		writer.println("DRAW:" + p.x + " " + p.y);
		writer.flush();
		return true;
	}

	public boolean sendMovePoint(Point p) {
		writer.println("MOVE:" + p.x + " " + p.y);
		writer.flush();
		return true;
	}
}

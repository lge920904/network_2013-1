package kr.ac.mju.oos.controller;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import kr.ac.mju.oos.ui.panels.game.CanvasPanel;
import kr.ac.mju.oos.uility.MessageParser;

public class CanvasController extends Controller {
	private CanvasPanel canvas;

	public CanvasController(CanvasPanel canvasPanel) {
		// TODO Auto-generated constructor stub
		canvas = canvasPanel;
	}

	public void setCanvas(CanvasPanel canvasPanel) {
		this.canvas = canvasPanel;
	}

	// To Server

	public boolean sendDrawPoint(Point p) {
		if (this.sendData("DRAW:" + p.x + " " + p.y))
			return true;
		return false;
	}

	public boolean sendMovePoint(Point p) {
		// TODO Auto-generated method stub
		if (this.sendData("MOVE:" + p.x + " " + p.y))
			return true;
		return false;
	}

	public boolean sendData(String sendString) {
		if (initFlag) {
			frontController.sendData("Canvas:" + sendString);
			return true;
		}
		return false;
	}

	public boolean sendGraphicInformation(Graphics2D g2D) {
		// TODO Auto-generated method stub
		String graphicInf = MessageParser.getInstance()
				.analyzingGraphicInformation(g2D);
		if (this.sendData("SET:" + graphicInf)) {
			return true;
		}
		return false;
	}

	// From Server

	public void serverDrawing(ArrayList<Point> points) {
		canvas.serverDrawing(points);
	}

	public void setGraphicsFromServer(String tempString) {
		// TODO Auto-generated method stub
		canvas.setGraphicsFromServer(tempString);
	}
}

package kr.ac.mju.oos.controller;

import java.awt.Point;
import java.util.ArrayList;

import kr.ac.mju.oos.ui.panels.game.CanvasPanel;

public class CanvasController extends Controller {
	private CanvasPanel canvas;

	public CanvasController(CanvasPanel canvasPanel) {
		// TODO Auto-generated constructor stub
		canvas = canvasPanel;
	}

	public void setCanvas(CanvasPanel canvasPanel) {
		this.canvas = canvasPanel;
	}

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

	public void serverDrawing(ArrayList<Point> points) {
		canvas.serverDrawing(points);
	}

	public boolean sendData(String sendString) {
		if (initFlag) {
			frontController.sendData("Canvas:" + sendString);
			return true;
		}
		return false;
	}
}

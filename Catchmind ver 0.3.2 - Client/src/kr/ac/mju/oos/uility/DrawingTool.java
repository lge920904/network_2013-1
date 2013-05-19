package kr.ac.mju.oos.uility;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import kr.ac.mju.oos.controller.CanvasController;
import kr.ac.mju.oos.controller.FrontController;
import kr.ac.mju.oos.ui.panels.game.CanvasPanel;

public class DrawingTool implements Tool {
	private Graphics2D g2D;
	private Point prevPoint;
	private ArrayList<Point> pointList;

	private CanvasController canvasController;
	private CanvasPanel canvas;

	public DrawingTool(CanvasPanel canvas, FrontController controller) {
		// TODO Auto-generated constructor stub
		this.pointList = new ArrayList<>();

		this.canvas = canvas;
		canvasController = new CanvasController(canvas);
		canvasController.init(controller, this);
	}

	public void setGraphics(Graphics2D g2D) {
		this.g2D = g2D;
		g2D.setColor(Color.BLACK);
	}

	public void setPanel(CanvasPanel canvasPanel) {
		this.canvas = canvasPanel;
		if (canvasController != null)
			canvasController.setCanvas(canvasPanel);
	}

	public void startDrawing(Point p) {
		this.prevPoint = p;
		this.pointList.add(prevPoint);
		canvasController.sendDrawPoint(prevPoint);
	}

	public void runDrawing(Point p) {
		this.g2D.drawLine(prevPoint.x, prevPoint.y, p.x, p.y);
		this.prevPoint = p;
		this.pointList.add(p);
		canvasController.sendDrawPoint(prevPoint);
	}

	public void endDrawing(Point p) {
		this.g2D.drawLine(prevPoint.x, prevPoint.y, p.x, p.y);
		this.pointList.add(p);
		canvasController.sendDrawPoint(prevPoint);
	}

	public void beforeAct(Point p) {
		if (canvasController != null)
			canvasController.sendMovePoint(p);
	}

	public void sendPosition(String s, Point p) {

	}

	public void setColor(Color color) {
	}

	public void serverDrawing(ArrayList<Point> points) {
		this.g2D.drawLine(points.get(0).x, points.get(0).y, points.get(1).x,
				points.get(1).y);
	}

	public CanvasController getCanvasController() {
		return canvasController;
	}

	public void listClear() {
		// TODO Auto-generated method stub
		pointList.clear();
	}

}

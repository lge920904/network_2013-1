package kr.ac.mju.oos.uility;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import kr.ac.mju.oos.controller.CanvasController;

public class DrawingTool {
	private Graphics2D g2D;
	private Point prevPoint;
	private ArrayList<Point> pointList;

	private CanvasController canvasController;

	public DrawingTool() {
		// TODO Auto-generated constructor stub
		this.pointList = new ArrayList<>();

		canvasController = new CanvasController();
	}

	public void setGraphics(Graphics2D g2D) {
		this.g2D = g2D;
		g2D.setColor(Color.BLACK);
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
		canvasController.sendMovePoint(p);
	}

	public void setColor(Color color) {
	}

	public void clear() {
		// TODO Auto-generated method stub
		pointList.clear();
	}
}

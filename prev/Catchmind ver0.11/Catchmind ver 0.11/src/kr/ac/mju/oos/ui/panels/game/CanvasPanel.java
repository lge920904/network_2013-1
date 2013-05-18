package kr.ac.mju.oos.ui.panels.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import kr.ac.mju.oos.constants.Constants;

//미완성
public class CanvasPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Graphics2D g2D;
	private ArrayList<Point> pointList;
	private MouseDrawingListener drawingListener;
	private int pointSize;

	public CanvasPanel() {
		// TODO Auto-generated constructor stub
		init();
	}

	public void init() {
		drawingListener = new MouseDrawingListener();

		pointSize = 4; // default

		this.setPreferredSize(new Dimension(Constants.FRAMES_MAIN_WIDTH,
				Constants.PANELS_CANVAS_HEIGHT));

		pointList = new ArrayList<>();
		this.setBackground(Color.white);
		this.addMouseListener(drawingListener);
		this.addMouseMotionListener(drawingListener);
	}

	public void startDrawing(Point p) {
		g2D = (Graphics2D) getGraphics();
		g2D.setColor(Color.BLACK);
		g2D.fillOval((int) p.getX(), (int) p.getY(), pointSize, pointSize);
		pointList.add(p);
		System.out.println("Start");
	}

	public void runDrawing(Point p) {
		g2D = (Graphics2D) getGraphics();
		g2D.setColor(Color.BLACK);
		g2D.fillOval((int) p.getX(), (int) p.getY(), pointSize, pointSize);
		pointList.add(p);
		System.out.println("Running");
	}

	public void endDrawing(Point p) {
		g2D = (Graphics2D) getGraphics();
		g2D.setColor(Color.BLACK);
		g2D.fillOval((int) p.getX(), (int) p.getY(), pointSize, pointSize);
		pointList.add(p);
		System.out.println("End");
		// DB에 List 삽입후
		pointList.clear();
	}

	public void changePoint(int i) { // 추후개발
		pointSize = i;
	}

	private class MouseDrawingListener extends MouseInputAdapter {

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseDragged(e);
			System.out.println("run");
			runDrawing(new Point(e.getX(), e.getY()));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mousePressed(e);
			startDrawing(new Point(e.getX(), e.getY()));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseReleased(e);
			endDrawing(new Point(e.getX(), e.getY()));
		}
	}
}

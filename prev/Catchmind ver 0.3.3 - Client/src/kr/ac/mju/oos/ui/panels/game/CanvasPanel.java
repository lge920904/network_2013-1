package kr.ac.mju.oos.ui.panels.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.controller.FrontController;
import kr.ac.mju.oos.uility.DrawingTool;
import kr.ac.mju.oos.uility.ImageIniter;

public class CanvasPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Graphics2D g2D;
	private MouseDrawingListener drawingListener;
	private DrawingTool drawingTool;
	private boolean turnFlag;
	private Image img;

	public CanvasPanel() {
		// TODO Auto-generated constructor stub
	}

	public void init(FrontController controller) {
		turnFlag = false;
		drawingListener = new MouseDrawingListener();
		drawingTool = new DrawingTool(this, controller);

		this.setPreferredSize(new Dimension(Constants.FRAMES_MAIN_WIDTH,
				Constants.PANELS_CANVAS_HEIGHT));
		this.setBackground(Color.white);

		// 리스너는 턴구현되면 턴에따라 달리게 수정
		this.addMouseListener(drawingListener);
		this.addMouseMotionListener(drawingListener);
	}

	public void beforeAct(Point p) {
		drawingTool.beforeAct(p);
	}

	public void setGraphics() {
		g2D = (Graphics2D) img.getGraphics();
		drawingTool.setGraphics(g2D);
	}

	public void startDrawing(Point p) {
		this.setGraphics();
		drawingTool.startDrawing(p);
	}

	public void runDrawing(Point p) {
		drawingTool.runDrawing(p);
		repaint();
	}

	public void endDrawing(Point p) {
		drawingTool.endDrawing(p);
		repaint();
	}

	public void serverDrawing(ArrayList<Point> points) {
		this.setGraphics();
		drawingTool.serverDrawing(points);
		repaint();
	}

	public void clear() {
		drawingTool.listClear();
		repaint();
	}

	public DrawingTool getDrawingTool() {
		return drawingTool;
	}

	public void setTurn(boolean turnFlag) { // 턴나눠지면 사용
		this.turnFlag = turnFlag;
		if (turnFlag) {
			if (this.getMouseListeners() == null) {
				this.addMouseListener(drawingListener);
				this.addMouseMotionListener(drawingListener);
			}
			if (img == null)
				this.createImage();
		} else {
			if (getMouseListeners().length != 0) {
				this.removeMouseListener(drawingListener);
				this.removeMouseMotionListener(drawingListener);
			}
			img = null;
			this.clear();
			System.gc();
		}
	}

	public void createImage() {
		if (img == null) {
			img = createImage(Constants.FRAMES_MAIN_WIDTH,
					Constants.PANELS_CANVAS_HEIGHT);
			ImageIniter.getInstance().initImage(img);
		}
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		if (img != null)
			g.drawImage(img, 0, 0, this);
	}

	private class MouseDrawingListener extends MouseInputAdapter {

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			setTurn(true); // 임시로
			beforeAct(new Point(e.getX(), e.getY()));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			startDrawing(new Point(e.getX(), e.getY()));
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			runDrawing(new Point(e.getX(), e.getY()));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			endDrawing(new Point(e.getX(), e.getY()));
		}
	}
}

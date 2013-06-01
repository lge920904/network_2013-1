package kr.ac.mju.oos.uility;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class MessageParser {
	private static MessageParser msgParser = new MessageParser();

	private MessageParser() {

	}

	public static MessageParser getInstance() {
		return msgParser;
	}

	public ArrayList<Point> parsePositionMsg(String positions) {
		System.out.println(positions);
		String[] tokens = positions.split(" ");
		ArrayList<Point> points = new ArrayList<>();

		if (tokens.length == 4) {
			Point p1 = new Point(Integer.parseInt(tokens[0]),
					Integer.parseInt(tokens[1]));
			Point p2 = new Point(Integer.parseInt(tokens[2]),
					Integer.parseInt(tokens[3]));
			points.add(p1);
			points.add(p2);

			return points;
		}
		return null;
	}

	public String parseChattingMsg(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public String analyzingGraphicInformation(Graphics2D g2D) {
		int r, g, b;
		int lineWidth;
		String graphicInformation;

		r = g2D.getColor().getRed();
		g = g2D.getColor().getGreen();
		b = g2D.getColor().getBlue();
		lineWidth = (int) ((BasicStroke) g2D.getStroke()).getLineWidth();

		graphicInformation = new String();
		graphicInformation = "Color:" + r + " " + g + " " + b;
		graphicInformation += ":Stroke:" + lineWidth;
		System.out.println(graphicInformation);
		return graphicInformation;
	}

	public ArrayList<Integer> getGraphicInformation(String msg) {
		ArrayList<Integer> infList = new ArrayList<>();
		String[] tokens = msg.split(":");
		String color = tokens[3];
		String[] colors = color.split(" ");
		int strokeSize = Integer.parseInt(tokens[5]);
		int r, g, b;
		r = Integer.parseInt(colors[0]);
		g = Integer.parseInt(colors[1]);
		b = Integer.parseInt(colors[2]);

		infList.add(new Integer(strokeSize));
		infList.add(new Integer(r));
		infList.add(new Integer(g));
		infList.add(new Integer(b));
		return infList;
	}
}

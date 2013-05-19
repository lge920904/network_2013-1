package kr.ac.mju.oos.uility;

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
}

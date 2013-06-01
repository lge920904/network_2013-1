package kr.ac.mju.oos.uility;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

public class ImageIniter {

	private static ImageIniter imgMaker = new ImageIniter();

	private ImageIniter() {

	}

	public static ImageIniter getInstance() {
		return imgMaker;
	}

	public Image initImage(Image img) {
		Graphics2D g2D = (Graphics2D) img.getGraphics();
		g2D.setPaint(new Color(255, 255, 255));
		g2D.fillRect(0, 0, img.getWidth(null), img.getHeight(null));

		return null;
	}
}

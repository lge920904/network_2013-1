package kr.ac.mju.oos.uility;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.border.AbstractBorder;

public class RoundedTitleBorder extends AbstractBorder {
	public static final int LEFT = 0;
	public static final int CENTER = 1;
	public static final int RIGHT = 2;
	
	private String title;
	private Font font;
	private int cornerRadius;
	private Color textColor;
	private Color gradient1;
	private Color gradient2;
	private boolean gradient;
	private int pos;
	
	public RoundedTitleBorder(String title, Font font) {
		this(title, font, 15,
				new Color(47, 76, 102),
				new Color(162, 187, 207),
				new Color(199, 202, 208), 
				true, CENTER);
	}
	
	public RoundedTitleBorder(String title, Font font, int pos) {
		this(title, font, 15,
				new Color(47, 76, 102),
				new Color(162, 187, 207),
				new Color(199, 202, 208), 
				true, pos);
	}
	
	public RoundedTitleBorder(String title, Font font, int cornerRadius, 
			Color textColor, Color gradient1, Color gradient2, boolean gradient,
			int pos) {
		this.title = title;
		this.font = font;
		this.cornerRadius = cornerRadius;
		this.textColor = textColor;
		this.gradient1 = gradient1;
		this.gradient2 = gradient2;
		this.gradient = gradient;
		this.pos = pos;
	}
	
	public Insets getBorderInsets(Component c) {
		return getBorderInsets(c, new Insets(0, 0, 0, 0));
	}
	
	public Insets getBorderInsets(Component c, Insets insets) {
		insets.top = c.getFontMetrics(font).getHeight() + 10;
		insets.bottom = cornerRadius > 1 ? (cornerRadius / 2) : 1;
		insets.left = insets.right = 1;
		return insets;
	}
	
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		int fontHeight = c.getFontMetrics(font).getHeight();
		paintGradient(g2, width, fontHeight + 10);
		paintTitle(g2, width, c.getFontMetrics(font).stringWidth(title), fontHeight);
		Color color = deriveColorHSB(c.getBackground(), 0, 0, -.3f);
		g2.setColor(deriveColorAlpha(color, 40));
		g2.drawRoundRect(x, y + 2, width - 1, height - 3, cornerRadius, cornerRadius);
		g2.setColor(deriveColorAlpha(color, 90));
		g2.drawRoundRect(x, y + 1, width - 1, height - 2, cornerRadius, cornerRadius);
		g2.setColor(deriveColorAlpha(color, 255));
		g2.drawRoundRect(x, y, width - 1, height - 1, cornerRadius, cornerRadius);
		g2.dispose();
	}
	
	private void paintGradient(Graphics2D g2, int width, int height) {
		if(gradient) {
			GradientPaint gp = new GradientPaint(0, 0, gradient1,
					width, height, gradient2);
			g2.setPaint(gp);
			g2.fillRoundRect(0, 0, width, height, cornerRadius, cornerRadius);
                        g2.fillRect(0, height - cornerRadius / 2, width, cornerRadius / 2);
		} else {
			g2.setColor(gradient1);
			g2.fillRoundRect(0, 0, width, height, cornerRadius, cornerRadius);
		}
	}
	
	private void paintTitle(Graphics2D g2, int width, int fontWidth, int fontHeight) {
		int textX = 0;
		switch(pos) {
			case LEFT:
				textX = 10;
				break;
			case CENTER:
				textX = width / 2 - fontWidth / 2;
				break;
			case RIGHT:
				textX = width - fontWidth - 10;
				break;
		}
		g2.setFont(font);
		g2.setColor(textColor);
		g2.drawString(title, textX, fontHeight);
	}
	
	// Aus SwingSet3 Utilities.java
	private Color deriveColorAlpha(Color base, int alpha) {
		return new Color(base.getRed(), base.getGreen(), base.getBlue(), alpha);
	}
	
	// Aus SwingSet3 Utilities.java
	private Color deriveColorHSB(Color base, float dH, float dS, float dB) {
		float hsb[] = Color.RGBtoHSB(
				base.getRed(), base.getGreen(), base.getBlue(), null);
		hsb[0] += dH;
		hsb[1] += dS;
		hsb[2] += dB;
		return Color.getHSBColor(
				hsb[0] < 0 ? 0 : (hsb[0] > 1 ? 1 : hsb[0]), 
				hsb[1] < 0 ? 0 : (hsb[1] > 1 ? 1 : hsb[1]), 
				hsb[2] < 0 ? 0 : (hsb[2] > 1 ? 1 : hsb[2]));
	}
}
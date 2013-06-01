package kr.ac.mju.oos.ui.panels.wait;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;

public class MenuPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel label;

	public MenuPanel() {
		// TODO Auto-generated constructor stub
		label = new JLabel();
	}

	public void init() {
		this.setPreferredSize(new Dimension(Constants.FRAMES_MAIN_WIDTH,
				Constants.PANELS_MENU_HEIGHT));

		label.setText("menu");
		this.add(label);
	}
}

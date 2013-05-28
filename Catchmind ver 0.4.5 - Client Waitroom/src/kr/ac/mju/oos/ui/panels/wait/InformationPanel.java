package kr.ac.mju.oos.ui.panels.wait;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.ui.tabs.InformationTabs;

public class InformationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private InformationTabs infTabs;

	public InformationPanel() {
		// TODO Auto-generated constructor stub
		infTabs = new InformationTabs();
	}

	public void init() {
		this.setPreferredSize(new Dimension(Constants.PANELS_RIGHT_WIDTH,
				Constants.FRAMES_MAIN_HEIGHT / 2));

		this.setLayout(new BorderLayout());
		this.add(infTabs, BorderLayout.EAST);

		infTabs.init();
	}
}

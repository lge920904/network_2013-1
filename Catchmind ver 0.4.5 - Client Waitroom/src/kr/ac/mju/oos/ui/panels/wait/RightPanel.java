package kr.ac.mju.oos.ui.panels.wait;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;

public class RightPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private InformationPanel informationPanel;
	private ChatPanel chatPanel;

	public RightPanel() {
		// TODO Auto-generated constructor stub
		super();
		informationPanel = new InformationPanel();
		chatPanel = new ChatPanel();
	}

	public void init() {
		// TODO Auto-generated method stub
		this.setPreferredSize(new Dimension(Constants.PANELS_RIGHT_WIDTH,
				Constants.FRAMES_MAIN_HEIGHT));

		this.setLayout(new BorderLayout());

		this.add(informationPanel, BorderLayout.NORTH);
		this.add(chatPanel, BorderLayout.SOUTH);

		informationPanel.init();
		chatPanel.init();
	}

}

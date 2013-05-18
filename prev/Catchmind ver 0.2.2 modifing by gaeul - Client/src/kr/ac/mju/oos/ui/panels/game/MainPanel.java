package kr.ac.mju.oos.ui.panels.game;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private ToolboxPanel toolboxPanel; // ���
	private CanvasPanel canvasPanel; // ĵ����
	private BottomPanel bottomPanel; // ä��, �¿��� �÷��̾��Ʈ

	public MainPanel() {
		// TODO Auto-generated constructor stub
		super();
	}

	public void init() {

		// create Panels
		toolboxPanel = new ToolboxPanel();
		canvasPanel = new CanvasPanel();
		bottomPanel = new BottomPanel();

		// set Layout
		this.setPreferredSize(new Dimension(Constants.FRAMES_MAIN_WIDTH,
				Constants.FRAMES_MAIN_HEIGHT));
		this.setLayout(new BorderLayout());

		this.add(toolboxPanel, "North");
	//	this.add(canvasPanel, "Center");
		this.add(bottomPanel, "South");

		toolboxPanel.init();
		canvasPanel.init();
		bottomPanel.init();
		
	}
}

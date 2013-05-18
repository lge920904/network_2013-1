package kr.ac.mju.oos.ui.panels.game;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private ToolboxPanel toolboxPanel; // 툴바
	private CanvasPanel canvasPanel; // 캔버스
	private BottomPanel bottomPanel; // 채팅, 좌우측 플레이어리스트

	public MainPanel() {
		// TODO Auto-generated constructor stub
		super();
		init();
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
		this.add(canvasPanel, "Center");
		this.add(bottomPanel, "South");
		System.out.println("MainPanelSize" + this.getSize());
	}
}

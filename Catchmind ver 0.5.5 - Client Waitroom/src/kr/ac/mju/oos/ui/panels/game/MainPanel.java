package kr.ac.mju.oos.ui.panels.game;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.controller.ChatController;
import kr.ac.mju.oos.controller.FrontController;
import kr.ac.mju.oos.uility.GameModeChanger;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private ToolboxPanel toolboxPanel; // ���
	private CanvasPanel canvasPanel; // ĵ����
	private BottomPanel bottomPanel; // ä��, �¿��� �÷��̾��Ʈ
	private FrontController controller;
	private GameModeChanger gameModeChanger;

	public MainPanel() {
		// TODO Auto-generated constructor stub
		super();
	}

	public void init(GameModeChanger gameModeChanger) {

		// create Panels
		controller = FrontController.getInstance();
		System.out.println("getInstance ");
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

		canvasPanel.init(controller);
		bottomPanel.init(controller);
		toolboxPanel.init(canvasPanel.getDrawingTool());
		controller.init(canvasPanel.getDrawingTool().getCanvasController(),
				(ChatController) bottomPanel.getControllers().get(0));
	}

	public BottomPanel getBottomPanel() {
		return bottomPanel;
	}
}

package kr.ac.mju.oos.ui.panels.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.controller.ChatController;
import kr.ac.mju.oos.controller.FrontController;
import kr.ac.mju.oos.ui.main.AudioManager;

public class MainPanel extends JPanel implements KeyListener{
	private static final long serialVersionUID = 1L;
	private AudioManager audioManager;
	private ToolboxPanel toolboxPanel; // ���
	private CanvasPanel canvasPanel; // ĵ����
	private BottomPanel bottomPanel; // ä��, �¿��� �÷��̾��Ʈ
	private FrontController controller;

	public MainPanel() {
		// TODO Auto-generated constructor stub
		super();
	}

	public void init(AudioManager audioManager) {

		// create Panels
		controller = new FrontController();
		toolboxPanel = new ToolboxPanel();
		canvasPanel = new CanvasPanel();
		bottomPanel = new BottomPanel();
		this.audioManager = audioManager;
		
		// set Layout
		this.setPreferredSize(new Dimension(Constants.FRAMES_MAIN_WIDTH,
				Constants.FRAMES_MAIN_HEIGHT));
		this.setLayout(new BorderLayout());

		//this.addKeyListener(this);

		this.add(toolboxPanel, "North");
		this.add(canvasPanel, "Center");
		this.add(bottomPanel, "South");

		canvasPanel.init(controller);
		bottomPanel.init(controller);
		toolboxPanel.init(canvasPanel.getDrawingTool());
		controller.init(canvasPanel.getDrawingTool().getCanvasController(),
				(ChatController) bottomPanel.getControllers().get(0));
		//this.requestFocus();
	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_ESCAPE){
			audioManager.stopMusic();
		}
	}
	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_ESCAPE){
		}
	}
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_ESCAPE){
		}
	}	
}

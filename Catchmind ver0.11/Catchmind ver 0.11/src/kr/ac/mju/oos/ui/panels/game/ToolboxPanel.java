package kr.ac.mju.oos.ui.panels.game;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;

public class ToolboxPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public ToolboxPanel() {
		// TODO Auto-generated constructor stub
		init();
	}

	public void init() {
		this.setPreferredSize(new Dimension(Constants.FRAMES_MAIN_WIDTH,
				Constants.PANELS_TOOLBOX_HEIGHT)); // Constants에서 툴박스 크기 조정 하세요
		this.add(new JLabel("Toolbox")); // 이 밑으로 추가
	}
}

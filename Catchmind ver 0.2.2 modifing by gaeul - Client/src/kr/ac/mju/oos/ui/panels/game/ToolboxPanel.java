package kr.ac.mju.oos.ui.panels.game;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;

public class ToolboxPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public ToolboxPanel() {
		// TODO Auto-generated constructor stub
	}

	public void init() {
		this.setPreferredSize(new Dimension(Constants.FRAMES_MAIN_WIDTH,
				Constants.PANELS_TOOLBOX_HEIGHT)); // Constants���� ��ڽ� ũ�� ���� �ϼ���
		this.add(new JLabel("Toolbox")); // �� ������ �߰�
	}
}

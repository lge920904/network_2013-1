package kr.ac.mju.oos.ui.panels.wait;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.ui.dialogs.CreateRoomDialog;
import kr.ac.mju.oos.ui.dialogs.WaitDialog;
import kr.ac.mju.oos.uility.RoomListTool;

public class MenuPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JPanel leftPanel;
	private JPanel centerPanel;
	private JPanel rightPanel;

	private JLabel statusLabel;

	private JButton cRoom = new JButton("방 만들기");
	private JButton cStart = new JButton("빠른 시작");

	private JComboBox statusCombo;
	private String[] status = { "모든방", "대기방", "진행중" };

	private Dialog createRoomDialog;
	private Dialog waitDialog;
	private RoomListTool roomListTool;

	public MenuPanel(RoomListTool roomListTool) {
		// TODO Auto-generated constructor stub
		leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		createRoomDialog = new CreateRoomDialog(roomListTool);
		createRoomDialog.setVisible(false);
		waitDialog = new WaitDialog();
		waitDialog.setVisible(false);
		
		statusLabel = new JLabel("방 정렬 : ");
		statusCombo = new JComboBox(status);

		this.roomListTool = roomListTool;
		// this.add(leftPanel);
		// this.add(rightPanel);

		cRoom.addActionListener(this);
		cStart.addActionListener(this);
		this.init();
	}

	public void init() {
		this.setPreferredSize(new Dimension(Constants.FRAMES_MAIN_WIDTH,
				Constants.PANELS_MENU_HEIGHT));
		this.add(leftPanel);
		this.add(centerPanel);
		this.add(rightPanel);

		leftPanel.add(cRoom);
		centerPanel.add(cStart);
		rightPanel.add(statusLabel);
		rightPanel.add(statusCombo);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cRoom) {
			createRoomDialog.setVisible(true);
		} else if (e.getSource() == cStart) {
			waitDialog.setVisible(true);
			this.setVisible(true);
		}
	}
}

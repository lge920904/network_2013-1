package kr.ac.mju.oos.ui.panels.wait;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.ui.dialogs.CreateRoomDialog;
import kr.ac.mju.oos.ui.dialogs.StoreDialog;
import kr.ac.mju.oos.ui.dialogs.WaitDialog;
import kr.ac.mju.oos.uility.GameModeChanger;
import kr.ac.mju.oos.uility.RoomListTool;

public class MenuPanel extends JPanel implements ActionListener, ItemListener {
	private static final long serialVersionUID = 1L;

	private JPanel leftPanel;
	private JPanel centerPanel;
	private JPanel rightPanel;

	private JLabel statusLabel;

	private JButton cRoom = new JButton("방 만들기");
	private JButton cStart = new JButton("빠른 시작");
	private JButton cStore = new JButton("상점가기");

	private JComboBox statusCombo;
	private String[] status = { "모든방", "아템전방", "노템전방" };

	private Dialog createRoomDialog;
	private Dialog waitDialog;
	private Dialog storeDialog;
	private RoomListTool roomListTool;

	private GameModeChanger gameModeChanger;

	public MenuPanel(RoomListTool roomListTool) {
		// TODO Auto-generated constructor stub

		createRoomDialog = new CreateRoomDialog(roomListTool);
		createRoomDialog.setVisible(false);

		waitDialog = new WaitDialog();
		waitDialog.setVisible(false);

		storeDialog = new StoreDialog();
		storeDialog.setVisible(false);
		
		statusLabel = new JLabel("방 정렬 : ");
		statusCombo = new JComboBox(status);


		leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		leftPanel.setPreferredSize(new Dimension(280, 40));
		centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(144, 40));
		rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		rightPanel.setPreferredSize(new Dimension(260, 40));
		
		leftPanel.add(cRoom);
		leftPanel.add(cStore);
		centerPanel.add(cStart);
		rightPanel.add(statusLabel);
		rightPanel.add(statusCombo);

		this.roomListTool = roomListTool;

		cRoom.addActionListener(this);
		cStart.addActionListener(this);
		cStore.addActionListener(this);
	}

	public void init(GameModeChanger gameModeChanger) {
		this.setPreferredSize(new Dimension(Constants.FRAMES_MAIN_WIDTH,
				Constants.PANELS_MENU_HEIGHT));
		this.add(leftPanel, BorderLayout.WEST);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(rightPanel, BorderLayout.EAST);

		this.gameModeChanger = gameModeChanger;
		this.roomListTool.setGameModeChanger(gameModeChanger);
		statusCombo.addItemListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cRoom) {
			createRoomDialog.setVisible(true);
		} else if (e.getSource() == cStart) {
			waitDialog.setVisible(true);
			this.setVisible(true);
		} else if (e.getSource() == cStore) {
			storeDialog.setVisible(true);
			this.setVisible(true);
		}
	}

	public void itemStateChanged(ItemEvent e) {
		String statusmode = (String) e.getItem();
		if (statusmode.equals(status[0])) {
			roomListTool.showAllRoom();
		} else if (statusmode.equals(status[1])) {
			roomListTool.showItemRoom();
		} else if (statusmode.equals(status[2])) {
			roomListTool.showNoItemRoom();
		}
	}
}

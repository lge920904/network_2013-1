package kr.ac.mju.oos.ui.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.model.dao.RoomManager;
import kr.ac.mju.oos.model.dto.RoomDataBean;
import kr.ac.mju.oos.uility.RoomListTool;

public class CreateRoomDialog extends JDialog implements ActionListener,
		ItemListener, KeyListener {

	private int mode = 1; // mode = 공개방일때 1, 비공개방일때 2
	private RoomManager roomDB;

	private RoomListTool roomListTool;

	private JPanel borderPanel;
	private JPanel contentPanel;

	private JLabel titleImgLabel; // Image
	private JLabel RoomNameLabel;
	private JLabel RoomPrivateLabel;
	private JLabel RoomPasswordLabel;
	private JLabel RoomModeLabel;
	private JLabel RoomItemLabel;
	private JLabel RoomPersonLabel;

	private JTextField RoomNameField;
	private JTextField RoomPasswordField;

	private JButton confirm;
	private JButton exit;

	private JRadioButton single, multi;
	private JRadioButton item, noItem;

	private JComboBox secret;
	private JComboBox person;

	private ButtonGroup modeGroup;
	private ButtonGroup itemGroup;

	private String[] secretString = { "공개", "비공개" };
	private String[] personString = { "3명", "4명", "5명", "6명" };

	public CreateRoomDialog(RoomListTool roomListTool) {
		super(new JDialog(), Constants.DIALOG_CREATEROOM_NAME, true);

		this.roomListTool = roomListTool;
		roomDB = new RoomManager();

		// member Construction
		RoomNameLabel = new JLabel(Constants.DIALOG_CREATEROOM_ROOMNAME);
		RoomPrivateLabel = new JLabel(Constants.DIALOG_CREATEROOM_PRIVATE);
		RoomPasswordLabel = new JLabel(Constants.DIALOG_CREATEROOM_PASSWORD);
		RoomModeLabel = new JLabel(Constants.DIALOG_CREATEROOM_MODE);
		RoomItemLabel = new JLabel(Constants.DIALOG_CREATEROOM_ITEM);
		RoomPersonLabel = new JLabel(Constants.DIALOG_CREATEROOM_PERSON);

		confirm = new JButton(Constants.DIALOG_CREATEROOM_CONFIRM);
		exit = new JButton(Constants.DIALOG_CREATEROOM_CANCEL);

		RoomNameField = new JTextField();
		RoomPasswordField = new JTextField();

		single = new JRadioButton("개인전", true);
		multi = new JRadioButton("팀전");

		single.setActionCommand("개인전");
		multi.setActionCommand("단체전");

		modeGroup = new ButtonGroup();
		modeGroup.add(single);
		modeGroup.add(multi);

		item = new JRadioButton("아이템전");
		noItem = new JRadioButton("노템전", true);

		item.setActionCommand("아이템전");
		noItem.setActionCommand("노템전");

		itemGroup = new ButtonGroup();
		itemGroup.add(item);
		itemGroup.add(noItem);

		secret = new JComboBox(secretString);
		person = new JComboBox(personString);

		this.init();
	}

	private void init() {
		// TODO Auto-generated method stub

		// add component
		Dimension labelDimension = new Dimension(100, 22);
		Dimension inputDimension = new Dimension(100, 22);
		Dimension comboDimension = new Dimension(100, 22);
		Dimension btnDimension = new Dimension(80, 22);

		borderPanel = new JPanel(new BorderLayout());
		this.setContentPane(borderPanel);

		contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
		borderPanel.add(contentPanel);

		titleImgLabel = new JLabel();
		titleImgLabel.setPreferredSize(new Dimension(200, 30));
		contentPanel.add(titleImgLabel);

		// set preferredsize
		RoomNameLabel.setPreferredSize(labelDimension);
		RoomNameField.setPreferredSize(inputDimension);
		RoomPrivateLabel.setPreferredSize(labelDimension);
		secret.setPreferredSize(comboDimension);
		RoomPasswordLabel.setPreferredSize(labelDimension);
		RoomPasswordField.setPreferredSize(inputDimension);
		RoomPasswordField.setEditable(false);
		RoomModeLabel.setPreferredSize(labelDimension);
		person.setPreferredSize(comboDimension);
		RoomItemLabel.setPreferredSize(labelDimension);
		RoomPersonLabel.setPreferredSize(labelDimension);

		confirm.setPreferredSize(btnDimension);
		confirm.addActionListener(this);
		exit.setPreferredSize(btnDimension);
		exit.addActionListener(this);

		// add label, field, btn
		contentPanel.add(RoomNameLabel);
		contentPanel.add(RoomNameField);
		contentPanel.add(RoomPrivateLabel);
		contentPanel.add(secret);
		contentPanel.add(RoomPasswordLabel);
		contentPanel.add(RoomPasswordField);
		contentPanel.add(RoomModeLabel);
		contentPanel.add(single);
		contentPanel.add(multi);
		contentPanel.add(RoomItemLabel);
		contentPanel.add(item);
		contentPanel.add(noItem);
		contentPanel.add(RoomPersonLabel);
		contentPanel.add(person);

		contentPanel.add(confirm);
		contentPanel.add(exit);

		secret.addItemListener(this);
		person.addItemListener(this);

		contentPanel.addKeyListener(this);
		RoomNameField.addKeyListener(this);

		// set size, location
		Dimension frame;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		this.setSize(Constants.DIALOG_CREATEROOM_WIDTH,
				Constants.DIALOG_CREATEROOM_HEIGHT);
		frame = this.getSize();
		this.setLocation((int) (screen.getWidth() / 2 - frame.getWidth() / 2),
				(int) (screen.getHeight() / 2 - frame.getHeight() / 2));

		this.setResizable(false);
		this.setModal(true);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String roomName = RoomNameField.getText();
		String roomPassword = RoomPasswordField.getText();
		String secretMode = (String) secret.getSelectedItem();
		String personMode = (String) person.getSelectedItem();
		String gameMode = modeGroup.getSelection().getActionCommand();
		String itemMode = itemGroup.getSelection().getActionCommand();

		RoomDataBean room = new RoomDataBean();
		room.setRoomName(roomName);
		room.setSecretMode(secretMode);
		room.setRoomPassword(roomPassword);
		room.setGameMode(gameMode);
		room.setItemMode(itemMode);
		room.setPerson(personMode);

		if (e.getActionCommand().equals(Constants.DIALOG_CREATEROOM_CONFIRM)) {
			if (roomName.equals("")) { // 방제목 미입력시
				JOptionPane.showMessageDialog(this, "방 제목을 입력하세요! ");

			} else if ((mode == 2) && (roomPassword.equals(""))) { // 비공개방 선택후
																	// 비밀번호 미입력시
				JOptionPane.showMessageDialog(this, "방 비밀번호를 입력하세요! ");
			} else {
				try {
					// roomDB.insertRoom(room);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				roomListTool.setRoomInfo(RoomNameField.getText(), modeGroup
						.getSelection().getActionCommand(), itemGroup
						.getSelection().getActionCommand(), secret
						.getSelectedItem(), person.getSelectedItem(),
						RoomPasswordField.getText());
				System.out.println(RoomPasswordField.getText());
				RoomNameField.setText("");
				RoomPasswordField.setText("");
				RoomNameField.requestFocus();
				this.setVisible(false);

			}
		} else if (e.getActionCommand().equals(
				Constants.DIALOG_CREATEROOM_CANCEL)) {
			this.setVisible(false);
		}
	}

	public void itemStateChanged(ItemEvent e) {
		String secretmode = (String) e.getItem();
		if (secretmode.equals(secretString[1])) {
			mode = 2;
			RoomPasswordField.setEditable(true);
		} else if (secretmode.equals(secretString[0])) {
			mode = 1;
			RoomPasswordField.setText("");
			RoomPasswordField.setEditable(false);
		}

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			confirm.doClick();
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {
	}
}

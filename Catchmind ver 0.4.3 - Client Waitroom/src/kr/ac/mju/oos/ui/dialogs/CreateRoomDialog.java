package kr.ac.mju.oos.ui.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.ui.panels.wait.RoomListPanel;

public class CreateRoomDialog extends JDialog implements ActionListener,
		KeyListener {

	private RoomListPanel roomListPanel;
	
	private JPanel borderPanel;
	private JPanel contentPanel;

	private JLabel titleImgLabel; // Image
	private JLabel RoomNameLabel;
	private JLabel RoomPrivateLabel;
	private JLabel RoomPasswordLabel;
	private JLabel RoomModeLabel;
	private JLabel RoomItemLabel;
	private JLabel RoomNumberLabel;

	private JTextField RoomNameField;
	private JTextField RoomPasswordField;

	private JButton confirm;
	private JButton exit;

	private ButtonGroup modeGroup;
	private ButtonGroup itemGroup;
	
	private JRadioButton single, multi;
	private JRadioButton item, noItem;

	private JComboBox secret;
	private JComboBox number;

	private String[] secretString = { "공개", "비공개" };
	private String[] numberString = { "3명", "4명", "5명", "6명" };

	public CreateRoomDialog(RoomListPanel roomListPanel) {
		super(new JDialog(), Constants.DIALOG_CREATEROOM_NAME, true);
		
		this.roomListPanel = roomListPanel;
		// member Construction
		RoomNameLabel = new JLabel(Constants.DIALOG_CREATEROOM_ROOMNAME);
		RoomPrivateLabel = new JLabel(Constants.DIALOG_CREATEROOM_PRIVATE);
		RoomPasswordLabel = new JLabel(Constants.DIALOG_CREATEROOM_PASSWORD);
		RoomModeLabel = new JLabel(Constants.DIALOG_CREATEROOM_MODE);
		RoomItemLabel = new JLabel(Constants.DIALOG_CREATEROOM_ITEM);
		RoomNumberLabel = new JLabel(Constants.DIALOG_CREATEROOM_NUMBER);

		confirm = new JButton(Constants.DIALOG_CREATEROOM_CONFIRM);
		exit = new JButton(Constants.DIALOG_CREATEROOM_CANCEL);

		RoomNameField = new JTextField();
		RoomPasswordField = new JTextField();

		single = new JRadioButton("개인전", true);
		single.setActionCommand("개인전");
		multi = new JRadioButton("팀전");
		multi.setActionCommand("팀전");

	modeGroup = new ButtonGroup();
		modeGroup.add(single);
		modeGroup.add(multi);

		item = new JRadioButton("아이템전");
		item.setActionCommand("아이템전");
		noItem = new JRadioButton("노템전", true);
		noItem.setActionCommand("노템전");
		
		itemGroup = new ButtonGroup();
		itemGroup.add(item);
		itemGroup.add(noItem);

		secret = new JComboBox(secretString);
		number = new JComboBox(numberString);

		this.init();

	}

	private void init() {

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
		RoomModeLabel.setPreferredSize(labelDimension);
		number.setPreferredSize(comboDimension);
		RoomItemLabel.setPreferredSize(labelDimension);
		RoomNumberLabel.setPreferredSize(labelDimension);

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
		contentPanel.add(RoomNumberLabel);
		contentPanel.add(number);

		contentPanel.add(confirm);
		contentPanel.add(exit);

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
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(Constants.DIALOG_CREATEROOM_CONFIRM)) {
			roomListPanel.createRoom(RoomNameField.getText(), 
					modeGroup.getSelection().getActionCommand(),
					itemGroup.getSelection().getActionCommand(),
					secret.getSelectedItem());
			System.out.println(modeGroup.getSelection().getActionCommand());
			this.setVisible(false);
		} else if (e.getActionCommand().equals(
				Constants.DIALOG_CREATEROOM_CANCEL)) {
			this.setVisible(false);
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

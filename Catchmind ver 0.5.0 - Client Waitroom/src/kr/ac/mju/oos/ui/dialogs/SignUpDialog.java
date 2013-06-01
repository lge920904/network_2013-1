package kr.ac.mju.oos.ui.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.model.dao.UserManager;
import kr.ac.mju.oos.model.dto.UserDataBean;

public class SignUpDialog extends JDialog implements ActionListener,
KeyListener {

	private UserManager userDB;

	private JPanel borderPanel;
	private JPanel contentPanel;

	private JLabel titleImgLabel; // Image
	private JLabel idLabel;
	private JLabel pwLabel;
	private JLabel pwcheckLabel;
	private JLabel nameLabel;
	private JLabel snLabel;
	private JLabel lineLabel;
	private JLabel emailLabel;
	private JLabel imageLabel;

	private JTextField idField;
	private JPasswordField pwField;
	private JPasswordField pwcheckField;
	private JTextField nameField;
	private JTextField sn1Field;
	private JTextField sn2Field;
	private JTextField emailField;

	private JButton confirm;
	private JButton exit;
	private JButton imageButton;

	private JFileChooser chooser;

	public SignUpDialog() {

		super(new JDialog(), Constants.DIALOG_SIGNUP_NAME, true);
		// member Construction

		userDB = new UserManager();

		idLabel = new JLabel(Constants.DIALOG_SIGNUP_ID);
		pwLabel = new JLabel(Constants.DIALOG_SIGNUP_PASSWORD);
		pwcheckLabel = new JLabel(Constants.DIALOG_SIGNUP_PASSWORDCHECK);
		nameLabel = new JLabel(Constants.DIALOG_SIGNUP_USERNAME);
		snLabel = new JLabel(Constants.DIALOG_SIGNUP_SERIALNUMBER);
		lineLabel = new JLabel(Constants.DIALOG_SIGNUP_LINE);
		emailLabel = new JLabel(Constants.DIALOG_SIGNUP_EMAIL);
		imageLabel = new JLabel(Constants.DIALOG_SIGNUP_IMAGE);

		confirm = new JButton(Constants.DIALOG_SIGNUP_CONFIRM);
		exit = new JButton(Constants.DIALOG_SIGNUP_CANCEL);
		imageButton = new JButton(Constants.DIALOG_SIGNUP_SEARCH);

		idField = new JTextField();
		pwField = new JPasswordField();
		pwcheckField = new JPasswordField();
		nameField = new JTextField();
		sn1Field = new JTextField();
		sn2Field = new JTextField();
		emailField = new JTextField();

		this.setLayout(new BorderLayout());
		this.init();

	}

	private void init() {

		// add component
		Dimension slabelDimension = new Dimension(10, 22);
		Dimension labelDimension = new Dimension(100, 22);
		Dimension inputDimension = new Dimension(130, 22);
		Dimension serialDimension = new Dimension(57, 22);
		Dimension btnDimension = new Dimension(80, 22);

		borderPanel = new JPanel(new BorderLayout());
		this.setContentPane(borderPanel);

		contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
		borderPanel.add(contentPanel);

		titleImgLabel = new JLabel("회원가입");
		titleImgLabel.setPreferredSize(new Dimension(200, 30));
		contentPanel.add(titleImgLabel);

		// set preferredsize
		idLabel.setPreferredSize(labelDimension);
		idField.setPreferredSize(inputDimension);
		pwLabel.setPreferredSize(labelDimension);
		pwField.setPreferredSize(inputDimension);
		pwcheckLabel.setPreferredSize(labelDimension);
		pwcheckField.setPreferredSize(inputDimension);
		nameLabel.setPreferredSize(labelDimension);
		nameField.setPreferredSize(inputDimension);
		snLabel.setPreferredSize(labelDimension);
		lineLabel.setPreferredSize(slabelDimension);
		sn1Field.setPreferredSize(serialDimension);
		sn2Field.setPreferredSize(serialDimension);
		emailLabel.setPreferredSize(labelDimension);
		emailField.setPreferredSize(inputDimension);
		imageLabel.setPreferredSize(labelDimension);

		contentPanel.addKeyListener(this);
		idField.addKeyListener(this);
		pwField.addKeyListener(this);
		pwcheckField.addKeyListener(this);
		nameField.addKeyListener(this);

		confirm.setPreferredSize(btnDimension);
		confirm.addActionListener(this);
		exit.setPreferredSize(btnDimension);
		exit.addActionListener(this);
		imageButton.setPreferredSize(btnDimension);
		imageButton.addActionListener(this);

		// add label, field, btn
		contentPanel.add(idLabel);
		contentPanel.add(idField);
		contentPanel.add(pwLabel);
		contentPanel.add(pwField);
		contentPanel.add(pwcheckLabel);
		contentPanel.add(pwcheckField);
		contentPanel.add(nameLabel);
		contentPanel.add(nameField);
		contentPanel.add(snLabel);
		contentPanel.add(sn1Field);
		contentPanel.add(lineLabel);
		contentPanel.add(sn2Field);
		contentPanel.add(emailLabel);
		contentPanel.add(emailField);
		contentPanel.add(imageLabel);
		contentPanel.add(imageButton);

		contentPanel.add(confirm);
		contentPanel.add(exit);

		// set size, location
		Dimension frame;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		this.setSize(Constants.DIALOG_SIGNUP_WIDTH,
				Constants.DIALOG_SIGNUP_HEIGHT);
		frame = this.getSize();
		this.setLocation((int) (screen.getWidth() / 2 - frame.getWidth() / 2),
				(int) (screen.getHeight() / 2 - frame.getHeight() / 2));

		this.setResizable(false);
		this.setModal(true);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String userid = null;
		String userpw = null;
		String userpwcheck = null;
		String username = null;
		String usersn1 = null;
		String usersn2 = null;
		String useremail = null;
		String userImageUrl = null;
		
		if (e.getActionCommand().equals(Constants.DIALOG_SIGNUP_CONFIRM)) {
			userid = idField.getText();
			userpw = pwField.getText();
			userpwcheck = pwcheckField.getText();
			username = nameField.getText();
			usersn1 = sn1Field.getText();
			usersn2 = sn2Field.getText();
			useremail = emailField.getText();

			UserDataBean user = new UserDataBean();
			user.setUserid(userid);
			user.setPassword(userpw);
			user.setName(username);
			user.setSn1(usersn1);
			user.setSn2(usersn2);
			user.setEmail(useremail);
			user.setImageUrl(userImageUrl);

			if (userid.equals("")) { // 방제목 미입력시
				JOptionPane.showMessageDialog(this, "아이디를 입력하세요! ");
			} else if (userpw.equals("")) {
				JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요! ");
			} else if (userpwcheck.equals("")) {
				JOptionPane.showMessageDialog(this, "비밀번호 확인란을 입력하세요! ");
			} else if (!(userpw.equals(userpwcheck))) {
				JOptionPane.showMessageDialog(this, "두 비밀번호를 동일하게 입력하세요! ");
			} else if (username.equals("")) {
				JOptionPane.showMessageDialog(this, "이름을 입력하세요! ");
			} else if (usersn1.equals("")) {
				JOptionPane.showMessageDialog(this, "주민번호 앞자리를 입력하세요! ");
			} else if (usersn2.equals("")) {
				JOptionPane.showMessageDialog(this, "주민번호 뒷자리를 입력하세요! ");
			} else if (useremail.equals("")) {
				JOptionPane.showMessageDialog(this, "이메일을 입력하세요! ");
			} else {
				try {
					//	userDB.insertMember(user);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(this, " 회원가입을 완료했습니다. ");
				this.setVisible(false);
			}
		} else if (e.getActionCommand().equals(Constants.DIALOG_SIGNUP_CANCEL)) {
			this.setVisible(false);
		}else if(e.getActionCommand().equals(Constants.DIALOG_SIGNUP_SEARCH)){
			chooser = new JFileChooser("C:");
			for(Constants.IMAGE_FILE_EXTENSION ex : Constants.IMAGE_FILE_EXTENSION.values()){
				chooser.setFileFilter
				(new javax.swing.filechooser.FileNameExtensionFilter(ex.name(), ex.name()));
			}
			int returnVal = chooser.showDialog(this, "사진선택");
			if(returnVal==0){
				userImageUrl = chooser.getSelectedFile().getPath();
				//	File f = new File(path);
			}
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

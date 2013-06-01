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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.ac.mju.oos.constants.Constants;

public class SignUpDialog extends JDialog implements ActionListener,
		KeyListener {

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

	private JTextField idField;
	private JPasswordField pwField;
	private JPasswordField pwcheckField;
	private JTextField nameField;
	private JTextField sn1Field;
	private JTextField sn2Field;
	private JTextField emailField;

	private JButton confirm;
	private JButton exit;

	public SignUpDialog() {

		super(new JDialog(), Constants.DIALOG_SIGNUP_NAME, true);
		// member Construction
		idLabel = new JLabel(Constants.DIALOG_SIGNUP_ID);
		pwLabel = new JLabel(Constants.DIALOG_SIGNUP_PASSWORD);
		pwcheckLabel = new JLabel(Constants.DIALOG_SIGNUP_PASSWORDCHECK);
		nameLabel = new JLabel(Constants.DIALOG_SIGNUP_USERNAME);
		snLabel = new JLabel(Constants.DIALOG_SIGNUP_SERIALNUMBER);
		lineLabel = new JLabel(Constants.DIALOG_SIGNUP_LINE);
		emailLabel = new JLabel(Constants.DIALOG_SIGNUP_EMAIL);

		confirm = new JButton(Constants.DIALOG_SIGNUP_CONFIRM);
		exit = new JButton(Constants.DIALOG_SIGNUP_CANCEL);

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
		// TODO Auto-generated method stub

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

		titleImgLabel = new JLabel();
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

		confirm.setPreferredSize(btnDimension);
		confirm.addActionListener(this);
		exit.setPreferredSize(btnDimension);
		exit.addActionListener(this);

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

		contentPanel.add(confirm);
		contentPanel.add(exit);

		contentPanel.addKeyListener(this);
		idField.addKeyListener(this);
		pwField.addKeyListener(this);
		pwcheckField.addKeyListener(this);
		nameField.addKeyListener(this);
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
		// TODO Auto-generated method stub

		String UserId = idField.getText();
		String UserPw = pwField.getText();
		String UserPwcheck = pwcheckField.getText();
		String UserName = nameField.getText();
		String UserSn1 = sn1Field.getText();
		String UserSn2 = sn2Field.getText();
		String UserEmail = emailField.getText();

		if (e.getActionCommand().equals(Constants.DIALOG_SIGNUP_CONFIRM)) {
			if (UserId.equals("")) { // 방제목 미입력시
				JOptionPane.showMessageDialog(this, "아이디를 입력하세요! ");
			}
			else if(UserPw.equals("")) {
				JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요! ");
			}
			else if(UserPwcheck.equals("")) {
				JOptionPane.showMessageDialog(this, "비밀번호 확인란을 입력하세요! ");
			}
			else if(UserName.equals("")) {
				JOptionPane.showMessageDialog(this, "이름을 입력하세요! ");
			}
			else if(UserSn1.equals("")) {
				JOptionPane.showMessageDialog(this, "주민번호 앞자리를 입력하세요! ");
			}
			else if(UserSn2.equals("")) {
				JOptionPane.showMessageDialog(this, "주민번호 뒷자리를 입력하세요! ");
			}
			else if(UserEmail.equals("")) {
				JOptionPane.showMessageDialog(this, "이메일을 입력하세요! ");
			}
			else {
				JOptionPane.showMessageDialog(this, " 회원가입을 완료했습니다. ");
				this.setVisible(false);
			}
		} else if (e.getActionCommand().equals(Constants.DIALOG_SIGNUP_CANCEL)) {
			this.setVisible(false);
		}
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			confirm.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}

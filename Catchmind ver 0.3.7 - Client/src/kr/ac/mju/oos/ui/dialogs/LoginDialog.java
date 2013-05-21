package kr.ac.mju.oos.ui.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.ui.main.AudioManager;

public class LoginDialog extends JDialog implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private AudioManager audioManager;

	// Components
	private JPanel borderPanel;
	private JPanel contentPanel;

	private JLabel titleImgLabel; // Image
	private JLabel idLabel;
	private JLabel pwLabel;

	private JButton confirm;
	private JButton exit;

	private JTextField idField;
	private JTextField pwField;

	public LoginDialog(AudioManager audioManager) {
		// TODO Auto-generated constructor stub
		super(new JDialog(), Constants.DIALOG_LOGIN_NAME, true);
		this.audioManager = audioManager;
		audioManager.selectMusic(this);
		// member Construction
		idLabel = new JLabel(Constants.DIALOG_LOGIN_ID);
		pwLabel = new JLabel(Constants.DIALOG_LOGIN_PASSWORD);
		confirm = new JButton(Constants.DIALOG_LOGIN_CONFIRM);
		exit = new JButton(Constants.DIALOG_LOGIN_CANCEL);

		idField = new JTextField();
		pwField = new JTextField();

		this.setLayout(new BorderLayout());
		this.init();
	}

	private void init() {
		// TODO Auto-generated method stub

		// add component
		Dimension labelDimension = new Dimension(60, 22);
		Dimension inputDimension = new Dimension(120, 22);
		Dimension btnDimension = new Dimension(80, 22);

		borderPanel = new JPanel(new BorderLayout());
		this.setContentPane(borderPanel);

		contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 2));
		borderPanel.add(contentPanel);

		titleImgLabel = new JLabel();
		titleImgLabel.setPreferredSize(new Dimension(200, 40));
		contentPanel.add(titleImgLabel);

		// set preferredsize
		idLabel.setPreferredSize(labelDimension);
		pwLabel.setPreferredSize(labelDimension);
		idField.setPreferredSize(inputDimension);
		pwField.setPreferredSize(inputDimension);

		confirm.setPreferredSize(btnDimension);
		confirm.addActionListener(this);

		exit.setPreferredSize(btnDimension);
		exit.addActionListener(this);

		// add label, field, btn
		contentPanel.add(idLabel);
		contentPanel.add(idField);
		contentPanel.add(pwLabel);
		contentPanel.add(pwField);

		contentPanel.add(confirm);
		contentPanel.add(exit);

		contentPanel.addKeyListener(this);
		idField.addKeyListener(this);
		pwField.addKeyListener(this);
		// set size, location
		Dimension frame;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		this.setSize(Constants.DIALOG_LOGIN_WIDTH,
				Constants.DIALOG_LOGIN_HEIGHT);
		frame = this.getSize();
		this.setLocation((int) (screen.getWidth() / 2 - frame.getWidth() / 2),
				(int) (screen.getHeight() / 2 - frame.getHeight() / 2));

		this.setResizable(false);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		audioManager.stopMusic();
		if (e.getActionCommand().equals(Constants.DIALOG_LOGIN_CONFIRM)) {
			// get id, pw
			// System.out.println(e.);
			String id = new String(idField.getText());
			String pw = new String(pwField.getText());

			this.setVisible(false);
		} else if (e.getActionCommand().equals(Constants.DIALOG_LOGIN_CANCEL)) {
			System.exit(0);
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			confirm.doClick();
			audioManager.buttonSound();
			System.out.println("buttonclick");
		}else if(e.getKeyChar() == KeyEvent.VK_ESCAPE){
			audioManager.stopMusic();
		}
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
	}
}

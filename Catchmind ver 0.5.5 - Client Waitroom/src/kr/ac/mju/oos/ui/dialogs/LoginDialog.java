package kr.ac.mju.oos.ui.dialogs;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.ui.main.Launcher;
import kr.ac.mju.oos.uility.AudioManager;

public class LoginDialog extends JDialog implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;

	private Socket loginSocket;
	private PrintWriter loginWriter;
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream loginInputStream;
	private AudioManager audioManager;

	// Components
	private JPanel borderPanel;
	private JPanel contentPanel;

	private JLabel titleImgLabel; // Image
	private JLabel idLabel;
	private JLabel pwLabel;

	private JButton confirm;
	private JButton exit;
	private JButton signup; // 회원가입버튼추가

	private JTextField idField;
	private JTextField pwField;

	private Launcher launcher;

	public LoginDialog(AudioManager audioManager) {
		super(new JDialog(), Constants.DIALOG_LOGIN_NAME, true);
		System.out.println("Login Dialog Construction Start");
		this.audioManager = audioManager;
		audioManager.selectMusic(this);
		System.out.println("Login Dialog AudioManager");
		// member Construction
		idLabel = new JLabel(Constants.DIALOG_LOGIN_ID);
		pwLabel = new JLabel(Constants.DIALOG_LOGIN_PASSWORD);
		confirm = new JButton(Constants.DIALOG_LOGIN_CONFIRM);
		exit = new JButton(Constants.DIALOG_LOGIN_CANCEL);
		signup = new JButton(Constants.DIALOG_SIGNUP_NAME); // 회원가입버튼 추가

		try {
			System.out.println("Socket Start");
			loginSocket = new Socket("localhost", Constants.LOGIN_PORT_NUMBER);
			System.out.println("SocketConstruction");
			loginWriter = new PrintWriter(new OutputStreamWriter(
					loginSocket.getOutputStream()));
			System.out.println("WriterConstruction");

			objectOutputStream = new ObjectOutputStream(
					loginSocket.getOutputStream());
			objectOutputStream.flush();
			System.out.println("OutputStreamConstruction");
			loginInputStream = new ObjectInputStream(
					loginSocket.getInputStream());
			System.out.println("InputStreamConstruction");

			System.out.println("소켓 연결 완료");
		} catch (UnknownHostException e) {
			System.out.println("호스트 못찾음");
		} catch (IOException e) {
			System.out.println("입력 에러");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		idField = new JTextField();
		pwField = new JTextField();

		this.setLayout(new BorderLayout());
	}

	public void init(Launcher launcher) {

		this.launcher = launcher;
		// add component
		Dimension labelDimension = new Dimension(40, 22);
		Dimension inputDimension = new Dimension(120, 22);
		Dimension btnDimension = new Dimension(80, 22);
		Dimension longbtnDimension = new Dimension(163, 22); // 회원가입버튼길이추가

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

		signup.setPreferredSize(longbtnDimension); // 회원가입크기설정
		signup.addActionListener(this); // 회원가입액션설정

		contentPanel.addKeyListener(this);
		idField.addKeyListener(this);
		pwField.addKeyListener(this);

		// add label, field, btn
		contentPanel.add(idLabel);
		contentPanel.add(idField);
		contentPanel.add(pwLabel);
		contentPanel.add(pwField);

		contentPanel.add(confirm);
		contentPanel.add(exit);
		contentPanel.add(signup);

		// set size, location
		Dimension frame;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		this.setSize(Constants.DIALOG_LOGIN_WIDTH,
				Constants.DIALOG_LOGIN_HEIGHT);
		frame = this.getSize();
		this.setLocation((int) (screen.getWidth() / 2 - frame.getWidth() / 2),
				(int) (screen.getHeight() / 2 - frame.getHeight() / 2));

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// audioManager.stopMusic(); // 회원가입란 추가에 따른 위치 이동 (아래로)
		if (e.getActionCommand().equals(Constants.DIALOG_LOGIN_CONFIRM)) {
			try {
				String id = new String(idField.getText());
				String pw = new String(pwField.getText());
				if (id.length() > 4 && pw.length() > 4) {
					loginWriter.println("login:" + id + ":" + pw);
					loginWriter.flush();
					if (loginInputStream.readObject().equals("true")) {
						this.setVisible(false);
						launcher.setUserID(id);
						audioManager.stopMusic();
						loginWriter.close();
						loginSocket.close();
					}
				} else {
					JOptionPane
							.showMessageDialog(this, "ID 혹은 비밀번호를 모두 입력하세요.");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
			}

		} else if (e.getSource() == signup) { // 회원가입버튼 액션추가

			new SignUpDialog();
			this.setVisible(true);

		} else if (e.getActionCommand().equals(Constants.DIALOG_LOGIN_CANCEL)) {
			System.exit(0);
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			confirm.doClick();
		} else if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
			audioManager.stopMusic();
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
}
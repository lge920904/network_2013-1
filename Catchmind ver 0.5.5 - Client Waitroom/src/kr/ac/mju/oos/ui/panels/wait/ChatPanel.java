package kr.ac.mju.oos.ui.panels.wait;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import kr.ac.mju.oos.constants.Constants;

public class ChatPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private TextArea msgView; // 메시지를 보여주는 영역
	private TextField sendBox; // 보낼 메시지를 적는 상자
	private Socket chatSocket;
	private PrintWriter chatWriter;
	private BufferedReader chatReader;

	public ChatPanel() {
		// TODO Auto-generated constructor stub
		try {
			chatSocket = new Socket("localhost",
					Constants.WAIT_CHAT_PORT_NUMBER);
			chatWriter = new PrintWriter(new OutputStreamWriter(
					chatSocket.getOutputStream()));
			chatReader = new BufferedReader(new InputStreamReader(
					chatSocket.getInputStream()));
		} catch (Exception e) {
			System.out.println("ChatPanel");
			e.printStackTrace();
		}
	}

	public void init() {
		this.setPreferredSize(new Dimension(Constants.PANELS_RIGHT_WIDTH,
				Constants.FRAMES_MAIN_HEIGHT / 2));

		msgView = new TextArea("", 1, 1, 1);
		sendBox = new TextField("");
		this.setLayout(new BorderLayout());
		this.add(msgView, "Center");
		this.add(sendBox, "South");
		msgView.setEditable(false);
		this.add(new JScrollPane(msgView), BorderLayout.CENTER);
		sendBox.addKeyListener(new ChtListener());
		setNetworking();
		Thread t1 = new Thread(new ChtReader());
		t1.start();

	}

	public void setNetworking() {
		try {
			chatSocket = new Socket("localhost",
					Constants.WAIT_CHAT_PORT_NUMBER); // ex) "211.111.111.111"
			InputStreamReader isr = new InputStreamReader(
					chatSocket.getInputStream());
			chatReader = new BufferedReader(isr);
			chatWriter = new PrintWriter(chatSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	class ChtListener implements KeyListener {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				chatWriter.println(sendBox.getText());
				chatWriter.flush();
				sendBox.setText("");
				sendBox.requestFocus();
			}
		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
		}
	}

	class ChtReader implements Runnable {
		String message;

		public void run() {
			try {
				while ((message = chatReader.readLine()) != null) {
					msgView.append(message + "\n");
				}
			} catch (IOException e) {
			}
		}
	}
}

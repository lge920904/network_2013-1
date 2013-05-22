package kr.ac.mju.oos.ui.panels.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.controller.FrontController;
import kr.ac.mju.oos.uility.ChatTool;

public class ChatPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private ChatTool chatTool;
	private JTextArea area;

	public ChatPanel() {
		super();
	}
	public void init(FrontController controller) {
		this.setPreferredSize(new Dimension(Constants.PANELS_CHAT_WIDTH,
				Constants.PANELS_BOTTOM_HEIGHT));
		this.setLayout(new BorderLayout());
		this.setSize(Constants.PANELS_CHAT_WIDTH,
				Constants.PANELS_BOTTOM_HEIGHT);

		// setField();
		// setArea();
		// setField
		textField = new JTextField(Constants.PANELS_CHAT_TEXTFIELD_WIDTH);
		textField.addActionListener(new ChatHandler(textField));
		this.add(textField, BorderLayout.SOUTH);
		// setArea
		area = new JTextArea(Constants.PANELS_CHAT_TEXTAREA_WIDTH,
				Constants.PANELS_CHAT_TEXTAREA_HEIGHT);

		area.setEditable(false);
		this.add(new JScrollPane(area), BorderLayout.CENTER);
		chatTool = new ChatTool(this, controller);
	}

	// message sending for sending to server
	public void sendMessage(String message) {
		chatTool.sendChat("id:" + message); // id정보 가져와서 들어가도록
		chatTool.registerMsg("name", message);
	}

	public ChatTool getChatTool() {
		return chatTool;
	}

	public JTextArea getTextArea() {
		return area;
	}

	// event handler
	private class ChatHandler implements ActionListener {
		private JTextField textField;

		public ChatHandler(JTextField chatText) {
			this.textField = chatText;
		}

		public void actionPerformed(ActionEvent e) {
			sendMessage(textField.getText());
			textField.setText("");
			textField.requestFocus();
		}
	}

}

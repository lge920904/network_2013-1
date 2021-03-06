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
import kr.ac.mju.oos.uility.ChatTool;


public class ChatPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField field;
	private ChatTool chatTool;
	private JTextArea area;

	public ChatPanel() {
		super();
	}

	public void init() {
		this.setPreferredSize(new Dimension(Constants.PANELS_CHAT_WIDTH,
				Constants.PANELS_BOTTOM_HEIGHT));
		this.setLayout(new BorderLayout());
		this.setSize(Constants.PANELS_CHAT_WIDTH,
				Constants.PANELS_BOTTOM_HEIGHT);

		setField();
		setArea();
		this.add(new JLabel("Chat"), BorderLayout.NORTH);		
		chatTool = new ChatTool();
	}
	public void setField(){
		final int TEXTFIELDWIDTH = 40;

		field = new JTextField(TEXTFIELDWIDTH);
		field.addActionListener(new ChatHandler(field));
		this.add(field, BorderLayout.SOUTH);

	}
	public void setArea(){
		area = new JTextArea(10,50);
		area.setEditable(false); 
		this.add(new JScrollPane(area), BorderLayout.CENTER);
	}
	
	// message sending for sending to server
	public void chatMessage(String message){
		chatTool.sendChat(message);
		area.append("name : " + chatTool.getMessage()+'\n');
	}
	
	// event handler
	private class ChatHandler implements ActionListener{
		private JTextField chatText;
		public ChatHandler (JTextField chatText){
			this.chatText = chatText;
		}
		public void actionPerformed(ActionEvent e) {
			chatMessage(chatText.getText());
			chatText.setText("");	
			chatText.requestFocus();
		}
	}
}



package kr.ac.mju.oos.ui.panels.game;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.ac.mju.oos.constants.Constants;


public class ChatPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField field;
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
	}

	public void setField(){
		JButton enterBtn;
		final int TEXTFIELDWIDTH = 40;

		//field.setSize(Constants.PANELS_CHAT_WIDTH,50);
		field = new JTextField(TEXTFIELDWIDTH);
		field.addActionListener(new ChatHandler(field));
		this.add(field, BorderLayout.SOUTH);
		
	}
	public void setArea(){
		area = new JTextArea(Constants.PANELS_CHAT_WIDTH,100);
		this.add(area, BorderLayout.CENTER);
	}
}

class ChatHandler implements ActionListener{
	private JTextField chatText;
	public ChatHandler (JTextField chatText){
		this.chatText = chatText;
	}
	public void actionPerformed(ActionEvent e) {
	//	ClientSender cs = new ClientSender(socket, "name")
		//cs.send(chatText.getText());
		System.out.println(chatText.getText());
		chatText.setText("");	
		chatText.requestFocus();
	}
}



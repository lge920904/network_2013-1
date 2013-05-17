package kr.ac.mju.oos.ui.panels.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.ac.mju.oos.constants.Constants;
public class ChatText extends JPanel{
	private JButton enterBtn;
	private JTextField chatText;
	private static final int TEXTFIELDWIDTH = 40;
	
	public ChatText(){

		this.setSize(Constants.PANELS_CHAT_WIDTH,10);
		chatText = new JTextField(TEXTFIELDWIDTH);
		this.setTextField(chatText);
		
		enterBtn=new JButton("enter");
		this.add(enterBtn);		
	}
	
	public void setTextField(JTextField chatText){
		chatText.addActionListener(new ChatHandler(chatText));
		this.add(chatText);
	}
}
class ChatHandler implements ActionListener{
	private JTextField chatText;
	private ChatBox chatbox;
	public ChatHandler (JTextField chatText){
		this.chatText = chatText;
	}
	public void actionPerformed(ActionEvent e) {
		System.out.println(chatText.getText());
		chatbox.setChat(chatText.getText());
		chatText.setText("");
		
		
		
	}
}


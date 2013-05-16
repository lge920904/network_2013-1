package kr.ac.mju.oos.ui.panels.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kr.ac.mju.oos.constants.Constants;
public class ChatText extends JPanel{
	private JButton enterBtn;
	private ChatBox chatbox;
	private static final int textFieldWidth = 40;
	public ChatText(){
		enterBtn=new JButton("enter");
		enterBtn.addActionListener(new ChatHandler());
		this.add(new JTextField(textFieldWidth));
		this.add(enterBtn);
		this.setSize(Constants.PANELS_CHAT_WIDTH,20);
		
	}
}
class ChatHandler implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("enter")){
			System.out.println("click");
		}
	}
}

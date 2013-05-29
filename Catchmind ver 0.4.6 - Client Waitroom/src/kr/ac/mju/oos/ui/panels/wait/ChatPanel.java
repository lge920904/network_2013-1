package kr.ac.mju.oos.ui.panels.wait;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;

public class ChatPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
    private TextArea msgView;   // 메시지를 보여주는 영역
	private TextField sendBox;         // 보낼 메시지를 적는 상자

	public ChatPanel() {
		// TODO Auto-generated constructor stub
	}

	public void init() {
		this.setPreferredSize(new Dimension(Constants.PANELS_RIGHT_WIDTH,
				Constants.FRAMES_MAIN_HEIGHT / 2));
		
		msgView=new TextArea("", 1,1,1);
		sendBox=new TextField(""); 
	    this.setLayout(new BorderLayout());
	    this.add(msgView,"Center");
	    this.add(sendBox, "South");
	    //this.setBounds(480, 300, 250,250);
	    
	}
	public void actionPerformed(ActionEvent ae){
		    if(ae.getSource()==sendBox){             // 메시지 입력 상자이면
		      String msg=sendBox.getText();
		      if(msg.length()==0)return;
		      if(msg.length()>=30)msg=msg.substring(0,30);
		      try{  
		        //writer.println("[MSG]"+msg);
		        sendBox.setText("");
		      }catch(Exception ie){}
		    }
	 }
}

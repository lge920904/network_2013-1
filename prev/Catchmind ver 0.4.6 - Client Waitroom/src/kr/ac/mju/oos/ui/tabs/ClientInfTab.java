package kr.ac.mju.oos.ui.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.TextField;
//import java.awt.Label;
import java.awt.Panel;
import java.util.StringTokenizer;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClientInfTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel pInfo;
	private java.awt.List pList;
	private String userName=null;
	private TextField nameBox=new TextField();
	private TextField roomBox=new TextField("0");
	private TextArea msgView=new TextArea("", 1,1,1);

	public ClientInfTab() {


	}

	public void init() {
		// TODO Auto-generated method stub
		pInfo=new JLabel("대기실:    명");
		pList=new java.awt.List();
		this.add(new JLabel("ClientInformationTab"));
		this.setBackground(new Color(255,255,100));
		this.setLayout(new BorderLayout());
		Panel client=new Panel();
		this.add(pInfo,"North"); this.add(pList,"Center"); this.add(client,"South");
		this.setBounds(500,110,250,180);
	}
	public void nameList(String msg){
		pList.removeAll();
		StringTokenizer st=new StringTokenizer(msg, "\t");
		while(st.hasMoreElements())
			pList.add(st.nextToken());
		playersInfo();
	}
	public void namePrint(String id){
		pList.setName(id);
	}
	
	public void playersInfo(){                 // 방에 있는 접속자의 수를 보여준다.
		int count=pList.getItemCount();
		pInfo.setText("대기실: "+count+"명");

	}
}

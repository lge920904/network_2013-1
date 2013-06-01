package kr.ac.mju.oos.ui.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;

public class AllInfTab extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;

	private JLabel pInfo;
	private java.awt.List pList;
	private String userName=null;

	private Socket waitSocket;
	private PrintWriter waitWriter;
	private BufferedReader waitReader;

	public AllInfTab() {
		try {
			waitSocket = new Socket("localhost", Constants.WAIT_PORT_NUMBER);
			waitWriter = new PrintWriter(new OutputStreamWriter(
					waitSocket.getOutputStream()));
			waitReader = new BufferedReader(new InputStreamReader(
					waitSocket.getInputStream()));
			new Thread(this).start();
		} catch (Exception e) {
			System.out.println("FrontController Construction Error");
			e.printStackTrace();
		}
	}

	public void init() {
		// TODO Auto-generated method stub
		this.add(new JLabel("AllInformationTab"));
		pInfo=new JLabel("대기실:   명");
		pList=new java.awt.List();
		this.setBackground(new Color(255,255,100));
		this.setLayout(new BorderLayout());
		Panel client=new Panel();
		this.add(pInfo,"North"); this.add(pList,"Center"); this.add(client,"South");
		this.setBounds(500,110,250,180);
			
	}
	public void run(){
		String msg;                             // 서버로부터의 메시지

		try{
			while((msg=waitReader.readLine())!=null){


				// 방에 있는 사용자 명단
				System.out.println(msg);
				nameList(msg);					


				// 약속된 메시지가 아니면 메시지 영역에 보여준다.
				System.out.println(msg+"\n");
			}
		}catch(IOException ie){
			System.out.println(ie+"\n");
		}
		System.out.println("접속이 끊겼습니다.");
	}


	private void playersInfo(){                 // 방에 있는 접속자의 수를 보여준다.
		int count=pList.getItemCount();
		System.out.println(count);
		pInfo.setText("대기실: "+count+"명");
		// 대국 시작 버튼의 활성화 상태를 점검한다.
	}

	// 사용자 리스트에서 사용자들을 추출하여 pList에 추가한다.
	private void nameList(String msg){
		System.out.println(msg);
		pList.removeAll();
		StringTokenizer st=new StringTokenizer(msg, "\t");
		while(st.hasMoreElements())
			pList.add(st.nextToken());
		playersInfo();
	}
	public void goToWaitRoom(String id){                   // 대기실로 버튼을 누르면 호출된다.
		userName=id;
		//nameList(userName);
		waitWriter.println(userName);    		
	}  

}

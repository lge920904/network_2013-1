package kr.ac.mju.oos.wait;

import java.awt.Frame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.wait.RoomHandler;

public class MainServer extends Frame {
	private static final long serialVersionUID = 1L;
	private static ServerSocket serverSocket,serverSocket1;
	private static Socket socket,socket1;
	private ArrayList<RoomHandler> lists;
	private RoomHandler roomHandler;


	public MainServer(String title) {
		// TODO Auto-generated constructor stub
		super(title);
		lists = new ArrayList<>();

		setBounds(100, 100, 1024, 768);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		try {
			serverSocket = new ServerSocket(Constants.WAIT_PORT_NUMBER);			
			socket=new Socket();			
			socket=serverSocket.accept();			
			roomHandler = new RoomHandler(this, socket);			
			lists.add(roomHandler);			
			roomHandler.start();
			System.out.println("연결됨");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init() {
		
	}
}
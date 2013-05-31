package kr.ac.mju.oos.wait;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RoomHandler extends Thread{
	private MainServer mainServer;
	private BufferedReader reader;
	private PrintWriter writer;
	private String userName=null;       // 사용자 이름
	private Socket socket;              // 소켓



	public RoomHandler(MainServer mainServer, Socket socket) {
		// TODO Auto-generated constructor stub


		this.mainServer = mainServer;
		this.socket = socket;

	}

	public void run(){
		try{        

			String msg;                     // 클라이언트의 메시지
			reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			writer=new PrintWriter(socket.getOutputStream(), true);

			while((msg=reader.readLine())!=null){

				// msg가 "[NAME]"으로 시작되는 메시지이면

				System.out.println("hahaha");
				userName=msg;          // userName을 정한다.
				writer.println(userName);

			}
		}catch(Exception e){
		}finally{
			try{

				if(reader!=null) reader.close();
				if(writer!=null) writer.close();
				if(socket!=null) socket.close();
				reader=null; writer=null; socket=null;
				System.out.println(userName+"님이 접속을 끊었습니다.");

			}catch(Exception e){}
		}
	}

}

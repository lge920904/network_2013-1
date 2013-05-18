package kr.ac.mju.oos.game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;

public class ChatServer {
	private ServerSocket serverSocket;
	private Socket socket;
	public ChatServer(){
		//clients = Collections.synchronizedMap(
			//	new HashMap<String, DataOutputStream>());
	}
	public void start(){
		try{
			serverSocket = new ServerSocket(10002);
			System.out.println("server start");

			while (true){
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress() + " is connected");
				ServerThread th = new ServerThread(this, socket);
				th.start();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String args[]){
		new ChatServer().start();	
	}

//inner class
	class ServerThread extends Thread{
		Socket socket;
		ChatServer chatServer;
		DataOutputStream out;
		DataInputStream in;

		public ServerThread(ChatServer ch, Socket socket){
			this.socket = socket;
			this.chatServer = ch;
			try{
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());

			}catch(Exception e){
			}
		}

		public void run(){
			String name = "";
			try{
				//name = in.readUTF();
				//chatServer.sendToAll(name+" enter");

				//chatServer.clients.put(name, out);
				//System.out.println(chatServer.clients.size() + "people is in here");	
				while (in != null){
					out.writeUTF(in.readUTF()); 
				}
			}catch(Exception e){
			}
			finally{
				//chatServer.sendToAll( name +" exit");
				//chatServer.clients.remove(name);
				//System.out.println(socket.getInetAddress()+"bye");
				//System.out.println(chatServer.clients.size() + "people is in here");
			}
		}
	}
}

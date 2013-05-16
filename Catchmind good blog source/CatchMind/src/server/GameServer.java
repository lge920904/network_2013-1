package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import etc.Protocol;
import etc.User;

public class GameServer extends Thread {

	ArrayList<CopyClient> allUserList; // 접속자들이 저장되는 곳
	ArrayList<User> allUserInfo; // 접속자들의 정보가 저장되는 곳
	ArrayList<GameRoom> roomList; // 게임방 리스트가 저장되는 곳
	HashMap<String, User> db;
	ServerSocket server;
	
	
	public GameServer() {
		try {
			server = new ServerSocket(7717);
			System.out.println("서버 시작!");
			allUserList = new ArrayList<CopyClient>();
			allUserInfo = new ArrayList<User>();
			roomList = new ArrayList<GameRoom>();
			db = new HashMap<String, User>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new GameServer().start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				// 접속자가 발생 할 때까지 대기!
				Socket socket = server.accept();
				String ip = socket.getInetAddress().getHostAddress();
				CopyClient client = new CopyClient(socket, this);
				System.out.println(ip + ": 접속");
				client.start(); // 스레드 시작!
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 회원등록 기능
	public void addUser(User user) {
		db.put(user.getId(), user);
	}

	// 로그인 기능
	public void loginUser(CopyClient client, String id, String ps) {
		try {

			if (db.get(id) == null) {
				System.out.println("login false... id check false!");
				client.out.writeObject(new Protocol(250));
				client.out.flush();
			} else {
				User user = db.get(id);
				if (user.getPassword().equals(ps) == false) {
					System.out.println("login false... ps check false!");
					client.out.writeObject(new Protocol(250));
					client.out.flush();
				} else {
					System.out.println("login OK!!");
					client.setUser(user); // client에 사용자정보 등록
					client.out.writeObject(new Protocol(200));
					this.setList(client, user); // 접속자 리스트에 등록
					this.sendAllUserInfo(); // 접속자 리스트 전송
					this.send_room(); // 방 리스트 전송
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 접속자 리스트에 접속자 등록 기능
	public void setList(CopyClient client, User user) {
		System.out.println("addUser...");
		this.allUserList.add(client);
		this.allUserInfo.add(user);
	}
	// 접속자 리스트에 접속자 삭제 기능
	public void delList(CopyClient client, User user) {
		System.out.println("delUser...");
		this.allUserList.remove(client);
		this.allUserInfo.remove(user);
	}
	// 전체 접속자에게 프로토콜 전송 기능
	public void sendProtocol(Protocol ptc){
		for (CopyClient client : allUserList) {
			try {
				client.out.writeObject(ptc);
				client.out.flush();
				System.out.println("sendProtocol OK!!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	// 전체 접속자에게 접속자 정보 리스트 전송 기능
	public void sendAllUserInfo(){
		// 접속자 리스트를 배열로 변환
		User[] userList = new User[allUserInfo.size()];
		allUserInfo.toArray(userList);
		for (CopyClient client : allUserList) {
			try {
				client.out.writeObject(new Protocol(500, userList));
				client.out.flush();
				System.out.println("sendAllUserInfo OK!!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void send_room(){
		// 방의 정보를 배열에 넣어서 배열을 전송
		String[] room_Send = getRoomList(); 
		for (CopyClient client : allUserList) {
			try {
				client.out.writeObject(new Protocol(600, room_Send));
				client.out.flush();
				System.out.println("sendRoomList OK!!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public String[] getRoomList(){
		// 대기실의 방 이름을 반환하는 메서드
		
		String[] value = null;
		
		if (roomList.size() > 0) {
			value = new String[roomList.size()];
			int i = 0;
			for (GameRoom room : roomList) {
				value[i++] = room.toString();
			}
		}
		return value;
	}
	// 방 추가 기능
	public void addRoom(GameRoom room){
		System.out.println("roomAdd...");
		roomList.add(room);
	}
	
	// 방 삭제 기능
	public void delRoom(GameRoom room){
		System.out.println("roomDel...");
		roomList.remove(room);
	}
	
	// 사용자가 선택한 방을 찾는 기능
	public GameRoom checkRoom(int idx){
		System.out.println("roomCheck...");
		return roomList.get(idx);
	}
	// 접속자 종료 기능
	public void exit(CopyClient client){
		try {
			System.out.println("userExit...");
			client.in.close();
			client.out.close();
			client.socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
       
}

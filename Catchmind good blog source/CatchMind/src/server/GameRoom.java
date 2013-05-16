package server;

import java.io.Serializable;
import java.util.ArrayList;

import etc.Game_Data;
import etc.Protocol;
import etc.User;

public class GameRoom implements Serializable{
	String roomName; // 방 제목
	GameServer server;
	ArrayList<CopyClient> roomUserList; // 대화방 접속자들이 저장 되는 곳
	ArrayList<User> roomUserInfo; // 대화방 접속자들의 정보가 저장 되는 곳
	ArrayList<CopyClient> t1_UserList; // 1팀 접속자들이 저장 되는 곳
	ArrayList<User> t1_UserInfo; // 1팀 접속자들의 정보가 저장되는 곳
	ArrayList<CopyClient> t2_UserList; // 1팀 접속자들이 저장 되는 곳
	ArrayList<User> t2_UserInfo; // 2팀 접속자들의 정보가 저장되는 곳
        ArrayList<CopyClient> ready_list;
	int gameCount; // 게임의 카운트
	String answer; // 게임의 정답
        String question;//문제
        User roomcap;//방장
	
	public GameRoom(String roomName, GameServer server) {
		this.roomName = roomName;
		this.server = server;
		
		roomUserList = new ArrayList<CopyClient>();
		roomUserInfo = new ArrayList<User>();
		t1_UserInfo = new ArrayList<User>();
		t1_UserList = new ArrayList<CopyClient>();
		t2_UserInfo = new ArrayList<User>();
		t2_UserList = new ArrayList<CopyClient>();
		ready_list=new ArrayList<CopyClient>();
		
	}
	// 대화방 접속 기능
	public void joinUser(CopyClient client){
		System.out.println("roomJoin..");
		roomUserList.add(client);
		roomUserInfo.add(client.user);
		this.addTeamUser(client);
		          System.out.println("client list"+roomUserList.size());
                          System.out.println("user list"+roomUserInfo.size());
		// 퇴장 메시지 & 방 접속자 리스트 전달
		User[] user_Send = new User[roomUserInfo.size()];
                CopyClient[] copy=new CopyClient[roomUserList.size()];
		roomUserInfo.toArray(user_Send);
                roomUserList.toArray(copy);
		this.SendProtocol(new Protocol(650, user_Send ,"*** "+client.user+"님이 입장하였습니다. ***"));
                
	} 
	// 대화방 퇴장 기능
	public void outUser(CopyClient client){
		System.out.println("roomExit..");
		roomUserList.remove(client);
		roomUserInfo.remove(client.user);
		this.removeTeamUser(client);
		// 퇴장 메시지 & 방 접속자 리스트 전달
		User[] user_Send = new User[roomUserInfo.size()];
                CopyClient[] copy=new CopyClient[roomUserList.size()];
		roomUserInfo.toArray(user_Send);
                roomUserList.toArray(copy);
		this.SendProtocol(new Protocol(650, user_Send ,"*** "+client.user+"님이 퇴장하였습니다. ***"));
               
	}
	// 들어온 순서마다 팀 배분
	public void addTeamUser(CopyClient client){
		System.out.println("TeamSet..add..");
		int userCount = this.getUserCount();
		switch (userCount) {
		case 1:
			t1_UserList.add(client);
			t1_UserInfo.add(client.user);
			System.out.println("TeamSet..t1");
			break;
		case 2:
			t2_UserList.add(client);
			t2_UserInfo.add(client.user);
			System.out.println("TeamSet..t2");
			break;
		case 3:
			t1_UserList.add(client);
			t1_UserInfo.add(client.user);
			System.out.println("TeamSet..t1");
			break;
		case 4:
			t2_UserList.add(client);
			t2_UserInfo.add(client.user);
			System.out.println("TeamSet..t2");
			break;
		}
	}
	// 종료시 팀에서 나가기
	public void removeTeamUser(CopyClient client){
		System.out.println("TeamSet..remove..");
		if (t1_UserList.size() != 0) {
			for (CopyClient user : t1_UserList) {
				if (user == client){
					System.out.println("TeamSet..t1..");
					t1_UserList.remove(client);
					t1_UserInfo.remove(client.user);
					break;
					}
			}
		}
		if (t2_UserList.size() != 0) {
			for (CopyClient user : t2_UserList) {
				if (user == client){
					System.out.println("TeamSet..t2..");
					t2_UserList.remove(client);
					t2_UserInfo.remove(client.user);
					break;
					}
			}
		}
	}
	
	// 대화방 접속자들에게 메시지 전달
	public void SendMessage(Protocol ptc){
		int roomUserIdx = roomUserInfo.indexOf(ptc.getUser());
		System.out.println("SendMsg..."+roomUserIdx);
		this.SendProtocol(new Protocol(400, roomUserIdx, ptc.getMsg()));
	}
	// 대화방 접속자들에게 프로토콜 전달
	public void SendProtocol(Protocol ptc){
		try {
			for (CopyClient user : roomUserList) {
				user.out.writeObject(ptc);
				user.out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 게임 시작 기능
	public void startGame(){
		gameCount++;
		answer = this.setQuestion();
		System.out.println("GameStart...Count:"+gameCount+", Answer:"+answer);
		Game_Data g_Data = new Game_Data(gameCount, answer);
		//this.SendProtocol(new Protocol(1100, g_Data));
	}
        
        public void ready(CopyClient client){//레디한 유저 담기
            ready_list.add(client);
        }
        public void unready(CopyClient client){//레디 안한 유저
            ready_list.remove(client);
            ready_list.trimToSize();
        }
	// 문제를 뽑는 기능
	public String setQuestion(){
		String[] question = {"사과", "바나나", "딸기", "수박", "포도", "당근", "메론","시계","토끼","호랑이","알로에","휴대폰","아기"};
		int choic = (int) (Math.random()*question.length);
		return question[choic];
	}
	// 방 제목을 반환하는 기능
	@Override
	public String toString() {
		return roomName;
	}
	// 대화방 참여 인원수를 반환하는 기능
	public int getUserCount(){
		return roomUserList.size();
	}
       
	
}

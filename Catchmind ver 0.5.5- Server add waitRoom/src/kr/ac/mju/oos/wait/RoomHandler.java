package kr.ac.mju.oos.wait;

import java.util.ArrayList;

import kr.ac.mju.oos.chat.ChatHandler;
import kr.ac.mju.oos.game.GameHandler;

public class RoomHandler {

	private String[] roomInformation;
	private int serialNumber;
	private ArrayList<GameHandler> gameClients;
	private ArrayList<ChatHandler> chatClients;

	private String roomName;
	private String gameMode;
	private String itemMode;
	private boolean isSecret = false;
	private int maxUser;
	private String roomPassword;

	private ArrayList<String> userIDs;

	public int getSerialNumber() {
		return serialNumber;
	}

	public RoomHandler(kr.ac.mju.oos.wait.MainServer waitServer,
			String[] recMsg, int serialNumber) {
		// TODO Auto-generated constructor stub
		this.setRoomInformation(recMsg);
		this.setSerialNumber(serialNumber);
		gameClients = new ArrayList<>();
		chatClients = new ArrayList<>();
		userIDs = new ArrayList<>();
	}

	public void joinUser(GameHandler gameUser, ChatHandler chatUser) {
		gameClients.add(gameUser);
		chatClients.add(chatUser);
	}

	public void setRoomInformation(String[] infs) {
		this.roomInformation = infs;
		roomName = infs[0];
		gameMode = infs[1];
		itemMode = infs[2];
		if (infs[3].equals("비공개"))
			isSecret = true;
		String[] user = infs[4].split("명");
		maxUser = Integer.parseInt(user[0]);
		if (infs.length == 6)
			roomPassword = infs[5];
		System.out.println(user[0] + "setRoomIn of");
	}

	public String[] getRoomInformation() {
		return roomInformation;
	}

	public void sendChatMsg(String msg) {

	}

	public synchronized void setSerialNumber(int serialNumber) {
		// TODO Auto-generated method stub
		this.serialNumber = serialNumber;
	}

	public void printUser() {
		System.out.println("-- Print User -- ");
		for (int i = 0; i < gameClients.size(); i++) {
			System.out.println(gameClients.get(i));
		}
		for (int i = 0; i < chatClients.size(); i++) {
			System.out.println(chatClients.get(i));
		}
		for (int i = 0; i < userIDs.size(); i++) {
			System.out.println(userIDs.get(i));
		}
	}

	public synchronized void setGameOfUsers(GameHandler user) {
		printUser();
		this.userIDs.add(user.getUserId());
		this.gameClients.add(user);
	}

	public synchronized void setChatOfUsers(ChatHandler user) {
		printUser();
		this.chatClients.add(user);
	}

	public synchronized void setRoomUser(String userID) {
	}

	public synchronized ArrayList<String> getRoomUsers() {
		return userIDs;
	}
}

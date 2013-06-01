package kr.ac.mju.oos.wait;

import java.util.ArrayList;

import kr.ac.mju.oos.chat.ChatHandler;
import kr.ac.mju.oos.game.GameHandler;

public class RoomHandler {

	private kr.ac.mju.oos.wait.MainServer waitServer;

	private String[] roomInformation;
	private int serialNumber;
	private ArrayList<GameHandler> gameClients;
	private ArrayList<ChatHandler> chatClients;

	private String roomName;
	private String gameMode;
	private String itemMode;
	private boolean isSecret = false;
	private int maxUser;

	public int getSerialNumber() {
		return serialNumber;
	}

	public RoomHandler(kr.ac.mju.oos.wait.MainServer waitServer,
			String[] recMsg, int serialNumber) {
		// TODO Auto-generated constructor stub
		this.waitServer = waitServer;
		this.setRoomInformation(recMsg);
		this.setSerialNumber(serialNumber);
		gameClients = new ArrayList<>();
		chatClients = new ArrayList<>();
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
		System.out.println(user[0] + "setRoomIn of");
		maxUser = Integer.parseInt(user[0]);

	}

	public String[] getRoomINformation() {
		return roomInformation;
	}

	public void sendChatMsg(String msg) {

	}

	public synchronized void setSerialNumber(int serialNumber) {
		// TODO Auto-generated method stub
		this.serialNumber = serialNumber;
	}

	public synchronized void setGameOfUsers(GameHandler user) {
		System.out.println(user);
		this.gameClients.add(user);
	}

	public synchronized void setChatOfUsers(ChatHandler user) {
		System.out.println(user);
		this.chatClients.add(user);
	}
}

package kr.ac.mju.oos.uility;

import java.util.ArrayList;

import javax.swing.JPanel;

import kr.ac.mju.oos.controller.WaitController;
import kr.ac.mju.oos.ui.main.Launcher;
import kr.ac.mju.oos.ui.panels.wait.RoomListPanel;

public class RoomListTool implements Tool {

	private RoomListPanel roomListPanel;
	private GameModeChanger gameModeChanger;
	private ArrayList<JPanel> roomList;
	private int totalRoomNum;
	private WaitController waitController;

	public RoomListTool(RoomListPanel roomListPanel) {
		this.roomListPanel = roomListPanel;
		totalRoomNum = 0;

		roomList = new ArrayList<JPanel>();
		this.waitController = new WaitController(this.roomListPanel, this);
		System.out.println("WaitController");
	}

	public void setRoomInfo(String roomName, String gameMode, String itemMode,
			Object secret, Object person, String roomPassword) {
		Room room;
		room = new Room(roomName, gameMode, itemMode, secret, person,
				roomPassword);
		room.setRoomInfo();
		roomList.add(room);
		totalRoomNum++;
		roomListPanel.addRoom(room, totalRoomNum);

		String[] infs = new String[6];
		infs[0] = roomName;
		infs[1] = gameMode;
		infs[2] = itemMode;
		infs[3] = secret.toString();
		infs[4] = person.toString();
		infs[5] = roomPassword;

		waitController.sendCreateRoom(infs, room);
		System.out.println("SetRoomInfo");
		// this.joinRoom(room); // 여기부터 수정해야함
		ArrayList<String> users = new ArrayList<String>();
		users.add(Launcher.userID);
		gameModeChanger.joinGameRoom(users);
	}

	public void addRoomFromServer(String roomName, String gameMode,
			String itemMode, Object secret, Object person, String roomPassword,
			int roomNumber) {
		Room room;
		room = new Room(roomName, gameMode, itemMode, secret, person,
				roomPassword);
		room.setRoomInfo();
		room.setRoomNumber(roomNumber);
		roomList.add(room);
		// totalRoomNum = roomNumber;
		System.out.println("addRoomFromServer");
		System.out.println("RoomNumber - " + roomNumber);
		roomListPanel.addRoom(room, roomNumber);
	}

	public void showAllRoom() {
		for (int i = 0; i < totalRoomNum; i++) {
			roomList.get(i).setVisible(true);
		}
	}

	public void showItemRoom() {
		for (int i = 0; i < totalRoomNum; i++) {
			if (roomList.get(i).getComponent(2).getName().equals("아이템전")) {
				roomList.get(i).setVisible(true);
			} else {
				roomList.get(i).setVisible(false);
			}

		}
	}

	public void showNoItemRoom() {
		for (int i = 0; i < totalRoomNum; i++) {
			if (roomList.get(i).getComponent(2).getName().equals("노템전")) {
				roomList.get(i).setVisible(true);
			} else {
				roomList.get(i).setVisible(false);
			}
		}
	}

	public void setGameModeChanger(GameModeChanger gameModeChanger) {
		this.gameModeChanger = gameModeChanger;
	}

	public void joinRoom(Room roomPanel) {
		System.out.println("in join Room - " + roomPanel.getRoomNumber());
		waitController.sendJoinRoom(roomPanel.getRoomNumber());
	}

	public void setRoomListPanel(RoomListPanel roomListPanel2) {
		// TODO Auto-generated method stub
		this.roomListPanel = roomListPanel2;
	}

	public void fromServerJoinRoom(int roomNumber, String tempString) {
		String[] strings = tempString.split(" ");
		ArrayList<String> users = new ArrayList<>();
		for (int i = 0; i < strings.length; i++) {
			users.add(strings[i]);
		}
		gameModeChanger.joinGameRoom(users);
		// for (int i = 0; i < roomList.size(); i++) {
		// if (((Room) (roomList.get(i))).getRoomNumber() == roomNumber) {
		// // ((Room)roomList.get(i)).
		// }
		// }
	}
}

package kr.ac.mju.oos.uility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.ui.panels.wait.RoomListPanel;

public class RoomListTool implements Tool {

	private RoomListPanel roomListPanel;

	private ArrayList<JPanel> roomList;

	private int totalRoomNum;

	private JLabel roomName;
	private JLabel gameMode;
	private JLabel itemMode;
	private Object secret;
	private JLabel person;
	
	public RoomListTool(RoomListPanel roomListPanel) {
		this.roomListPanel = roomListPanel;
		totalRoomNum = 0;

		roomList = new ArrayList<JPanel>();
	}

	public void setRoomInfo(String roomName, String gameMode, String itemMode,
			Object secret, Object person) {
		this.roomName = new JLabel(" room name : " + roomName);
		this.roomName.setName(roomName);
		this.gameMode = new JLabel(" game mode : " + gameMode);
		this.gameMode.setName(gameMode);
		this.itemMode = new JLabel(" item mode : " + itemMode);
		this.itemMode.setName(itemMode);
		this.person = new JLabel(" person : " + (String) person);
		this.person.setName((String) person);

		this.secret = secret;
		this.createRoom();
	}

	public String getRoomName() {
		return roomName.getName();
	}

	public String getGameMode() {
		return gameMode.getName();
	}

	public String getItemMode() {
		return itemMode.getName();
	}

	public Object getSecret() {
		return secret;
	}

	public String getPerson() {
		return person.getName();
	}

	public void createRoom() {
		JPanel room = new JPanel();

		room.setPreferredSize(new Dimension(Constants.PANELS_ROOM_WIDTH,
				Constants.PANELS_ROOM_HEIGHT));
		room.setLayout(new GridLayout(4, 1, 1, 1));

		room.add(roomName);
		room.add(gameMode);
		room.add(itemMode);
		room.add(person);

		if (secret.equals("비공개")) {
			room.setBackground(Color.RED);
		} else if (secret.equals("공개")) {
			room.setBackground(Color.GREEN);
		}

		totalRoomNum++;
		roomList.add(room);
		roomListPanel.addRoom(room, totalRoomNum);

		// 방 정보
		System.out.println(roomList.get(totalRoomNum - 1).getComponent(0)
				.getName()); // room name
		System.out.println(roomList.get(totalRoomNum - 1).getComponent(1)
				.getName()); // game mode
		System.out.println(roomList.get(totalRoomNum - 1).getComponent(2)
				.getName()); // item mode
		System.out.println(roomList.get(totalRoomNum - 1).getComponent(3)
				.getName()); // person
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

}

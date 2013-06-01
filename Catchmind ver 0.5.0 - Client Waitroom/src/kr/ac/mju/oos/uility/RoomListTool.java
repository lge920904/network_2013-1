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
	private Room room;
	private int totalRoomNum;
	
	public RoomListTool(RoomListPanel roomListPanel) {
		this.roomListPanel = roomListPanel;
		totalRoomNum = 0;

		roomList = new ArrayList<JPanel>();
	}

	public void setRoomInfo(String roomName, String gameMode, String itemMode,
			Object secret, Object person) {
		room = new Room(roomName, gameMode, itemMode, secret, person);
		room.setRoomInfo();
		roomList.add(room);
		totalRoomNum++;
		roomListPanel.addRoom(room, totalRoomNum);
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

package kr.ac.mju.oos.uility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;

public class Room extends JPanel {
	private JLabel roomName;
	private JLabel gameMode;
	private JLabel itemMode;
	private Object secret;
	private JLabel person;
	private String RoomPassword;
	private int roomNumber;

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
		System.out.println("Room Number" + roomNumber);
	}

	public Room(String roomName, String gameMode, String itemMode,
			Object secret, Object person, String roomPassword) {

		super();
		this.setPreferredSize(new Dimension(Constants.PANELS_ROOM_WIDTH,
				Constants.PANELS_ROOM_HEIGHT));
		this.setLayout(new GridLayout(4, 1, 1, 1));

		this.roomName = new JLabel(" room name : " + roomName);
		this.roomName.setName(roomName);
		this.gameMode = new JLabel(" game mode : " + gameMode);
		this.gameMode.setName(gameMode);
		this.itemMode = new JLabel(" item mode : " + itemMode);
		this.itemMode.setName(itemMode);
		this.secret = secret;
		this.person = new JLabel(" person : " + (String) person);
		this.person.setName((String) person);
		this.RoomPassword = roomPassword;
	}

	public void setRoomInfo() {
		this.add(roomName);
		this.add(gameMode);
		this.add(itemMode);
		this.add(person);
		if (secret.equals("비공개")) {
			this.setBackground(Color.RED);
		} else if (secret.equals("공개")) {
			this.setBackground(Color.GREEN);
		}
	}

	public String[] getRoomInfo() {
		String[] infs = new String[5];
		infs[0] = roomName.getText();
		infs[1] = gameMode.getText();
		infs[2] = itemMode.getText();
		infs[3] = secret.toString();
		infs[4] = person.getText();
		return infs;
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

	public String roomPassword() {
		return RoomPassword;
	}

}

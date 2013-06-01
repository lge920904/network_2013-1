package kr.ac.mju.oos.model.dto;

public class RoomDataBean {

	private int roomNumber=1;
	private String roomName;
	private String secretMode;
	private String roomPassword;	
	private String gameMode;
	private String itemMode;
	private String person;

	public int getRoomNumber() {
		return roomNumber;
	}
	
	public void setRoomNumber() {
		// ??
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getSecretMode() {
		return secretMode;
	}

	public void setSecretMode(String secretMode) {
		this.secretMode = secretMode;
	}

	public String getRoomPassword() {
		return roomPassword;
	}

	public void setRoomPassword(String roomPassword) {
		this.roomPassword = roomPassword;
	}

	public String getGameMode() {
		return gameMode;
	}

	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}

	public String getItemMode() {
		return itemMode;
	}

	public void setItemMode(String itemMode) {
		this.itemMode = itemMode;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}
}

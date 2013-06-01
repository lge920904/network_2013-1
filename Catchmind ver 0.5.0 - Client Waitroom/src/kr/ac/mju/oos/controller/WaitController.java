package kr.ac.mju.oos.controller;

import kr.ac.mju.oos.ui.panels.wait.RoomListPanel;

public class WaitController extends Controller {
	private RoomListPanel roomListPanel;

	@Override
	public boolean sendData(String sendString) {
		// TODO Auto-generated method stub
		if (initFlag) {
			frontController.sendData("Wait:" + sendString);
			return true;
		}
		return false;
	}

	public WaitController(RoomListPanel roomPanel) {
		// TODO Auto-generated constructor stub
		this.roomListPanel = roomPanel;
	}

	public void setRoomPanel(RoomListPanel roomPanel) {
		this.roomListPanel = roomPanel;
	}

	public void sendJoinRoom(String[] joinRoom) {
		sendData(new String("temp"));
	}

	public void sendCreateRoom(String[] createRoom) {
		sendData("CREATE:" + createRoom[0] + " " + createRoom[1] + " "
				+ createRoom[2] + " " + createRoom[3] + " " + createRoom[4]);
	}

	public void sendExitRoom(String[] exitRoom) {

	}

	public void sendMatchingRoom(String[] matchingRoom) {

	}
}

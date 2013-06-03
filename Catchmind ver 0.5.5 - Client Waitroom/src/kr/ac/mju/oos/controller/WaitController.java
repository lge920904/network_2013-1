package kr.ac.mju.oos.controller;

import kr.ac.mju.oos.ui.panels.wait.RoomListPanel;
import kr.ac.mju.oos.uility.Room;
import kr.ac.mju.oos.uility.RoomListTool;

public class WaitController extends Controller {
	private RoomListPanel roomListPanel;
	private Room createRoom;
	private RoomListTool roomListTool;

	@Override
	public boolean sendData(String sendString) {
		// TODO Auto-generated method stub
		// if (initFlag) {
		if (this.frontController.getWaitController() == null) {
			frontController.setWaitController(this);
		}
		frontController.sendData("Wait:" + sendString);
		return true;
		// }
		// return false;
	}

	public WaitController(RoomListPanel roomPanel, RoomListTool roomListTool) {
		// TODO Auto-generated constructor stub
		this.roomListPanel = roomPanel;
		this.frontController = FrontController.getInstance();
		frontController.setWaitController(this);
		this.roomListTool = roomListTool;
		// this.frontController = FrontController.getInstance();
	}

	public void setRoomPanel(RoomListPanel roomPanel) {
		this.roomListPanel = roomPanel;
	}

	public void sendJoinRoom(int roomNumber) {
		sendData(new String("JOIN:" + roomNumber));
	}

	public void sendCreateRoom(String[] createRoom, Room room) {
		this.createRoom = room;
		sendData("CREATE:" + createRoom[0] + " " + createRoom[1] + " "
				+ createRoom[2] + " " + createRoom[3] + " " + createRoom[4]
				+ " " + createRoom[5]);
	}

	public void sendExitRoom(String[] exitRoom) {

	}

	public void sendMatchingRoom(String[] matchingRoom) {

	}

	public void setRoomNumber(int serialNumber) {
		if (createRoom != null) {
			createRoom.setRoomNumber(serialNumber);
		}
	}

	public void addRoom(int roomNumber, String[] msgs) {
		// TODO Auto-generated method stub
		System.out.println("addRoom");
		if (msgs.length == 5)
			roomListTool.addRoomFromServer(msgs[0], msgs[1], msgs[2], msgs[3],
					msgs[4], null, roomNumber);
		else if (msgs.length == 6)
			roomListTool.addRoomFromServer(msgs[0], msgs[1], msgs[2], msgs[3],
					msgs[4], msgs[5], roomNumber);

	}

	public void joinRoom(int roomNumber, String tempString) {
		// TODO Auto-generated method stub
		roomListTool.fromServerJoinRoom(roomNumber, tempString);
	}
}

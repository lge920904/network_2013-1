package kr.ac.mju.oos.uility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.ui.panels.wait.RoomListPanel;

public class RoomListTool implements Tool{

	private RoomListPanel roomListPanel;

	private JPanel page;

	private ArrayList<JPanel> roomList;
	private ArrayList<JPanel> pageList;

	private int totalRoomNum;

	public RoomListTool(RoomListPanel roomListPanel){
		this.roomListPanel = roomListPanel;
		totalRoomNum = 0;

		roomList = new ArrayList<JPanel>();
	}
	public void createRoom(String roomName, String gameMode, String itemMode, Object secret, Object person) {
		JPanel room = new JPanel();
		room.setPreferredSize(new Dimension(Constants.PANELS_ROOM_WIDTH,
				Constants.PANELS_ROOM_HEIGHT));
		room.setLayout(new GridLayout(4,1,1,1));
		room.add(new JLabel("room name : "+roomName));
		room.add(new JLabel("game mode : "+gameMode));
		room.add(new JLabel("item mode: "+itemMode));
		room.add(new JLabel("person : "+person));
		if(secret.equals("비공개")){
			room.setBackground(Color.RED);
		}else {
			room.setBackground(Color.GREEN);
		}
		totalRoomNum ++;
		roomList.add(room);
		roomListPanel.addRoom(room, totalRoomNum);
	}
	public void showAllRoom()
	{
		for(int i=0; i<totalRoomNum; i++) {
			// if(??) 
			roomList.get(i).setVisible(true);
		}
	}
	public void showItemRoom()
	{
		for(int i=0; i<totalRoomNum; i++) {
			// if(??) 
			roomList.get(i).setVisible(false);
		}
	}
	public void showNoItemRoom()
	{
		for(int i=0; i<totalRoomNum; i++) {
			// if(??) 
			roomList.get(i).setVisible(false);
		}
	}
}



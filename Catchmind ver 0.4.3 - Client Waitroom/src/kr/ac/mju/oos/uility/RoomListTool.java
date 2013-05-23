package kr.ac.mju.oos.uility;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;

public class RoomListTool implements Tool{

	private JPanel page;

	private ArrayList<JPanel> roomList;
	private ArrayList<JPanel> pageList;

	private int currentPage, totalPage, totalRoomNum;
		
	public RoomListTool(){
		currentPage = 1;
		totalPage = 0;
		totalRoomNum = 0;

		roomList = new ArrayList<JPanel>();
		pageList = new ArrayList<JPanel>();
	}

	public void setRoomName(String roomName) {
		this.createRoom(roomName);
	}

	public JPanel createRoom(String roomName) {
		JPanel room = new JPanel();
		room.setPreferredSize(new Dimension(Constants.PANELS_ROOM_WIDTH,
				Constants.PANELS_ROOM_HEIGHT));
		
		room.setBackground(Color.BLACK);
		room.add(new JLabel(roomName));
		totalRoomNum ++;
		roomList.add(room);

		if(totalRoomNum == (Constants.maxRoomNum*currentPage)+1){
			createPage();
		}
		page.add(roomList.get(totalRoomNum-1));
		System.out.println("createRoom : "+roomName);
		System.out.println("totalRoomNum : "+totalRoomNum);
		System.out.println("totalPage : "+totalPage);
		return pageList.get(totalPage-1);
	}

	public void createPage(){
		page = new JPanel();
		page.setPreferredSize(new Dimension(Constants.PANELS_RIGHT_WIDTH,Constants.PANELS_PAGE_HEIGHT));
		page.setBackground(Color.CYAN);
		totalPage ++;
		pageList.add(page);
	}
	public JPanel getPage(){
		return pageList.get(currentPage-1);
	}
	public JPanel nextPage(){
		if(currentPage < totalPage){
			currentPage ++;
			System.out.println(currentPage);
		}
		return this.getPage();
	}
	public JPanel prevPage(){
		if(currentPage > 1){
			currentPage --;
			System.out.println(currentPage);
		}
		return this.getPage();
	}
}

package kr.ac.mju.oos.uility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
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

	public JPanel createRoom(String roomName, String gameMode, String itemMode, Object scret) {
		JPanel room = new JPanel();
		totalRoomNum ++;
		room.setPreferredSize(new Dimension(Constants.PANELS_ROOM_WIDTH,
				Constants.PANELS_ROOM_HEIGHT));
		room.setBorder(new RoundedTitleBorder("NO."+totalRoomNum+" "+roomName, new Font("맑은고딕", Font.BOLD, 18)));
		room.setLayout(new GridLayout(2,2));
		//room.add(new JLabel("방 제목"));
		//room.add(new JLabel(roomName));
		room.add(new JLabel(" 게임 모드"));
		room.add(new JLabel(gameMode));
		room.add(new JLabel(" 아이템 모드"));
		room.add(new JLabel(itemMode));
		if(scret.equals("비공개")) {
			room.setBackground(Color.RED);
		}else {
			room.setBackground(Color.GREEN);
		}
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
		//page.setBackground(Color.CYAN);
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

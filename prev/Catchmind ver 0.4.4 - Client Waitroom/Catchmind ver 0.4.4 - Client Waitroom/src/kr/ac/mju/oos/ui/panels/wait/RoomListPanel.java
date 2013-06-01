package kr.ac.mju.oos.ui.panels.wait;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.uility.RoomListTool;

public class RoomListPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	private JPanel northLabel;
	private JPanel pageButtonPanel;
	private JPanel page;

	private JButton prev;
	private JButton next;

	private int totalPageNum, currentPageNum;
	private ArrayList<JPanel> pageList;;

	public RoomListPanel() {
		super();
		this.setPreferredSize(new Dimension(Constants.PANELS_RIGHT_WIDTH,
				Constants.PANELS_ROOMLIST_HEIGHT));
		this.setBackground(Constants.DEFAULT_BACKGROUND_COLOR);
		this.setLayout(new BorderLayout());
	}
	
	public void init() {
		pageList = new ArrayList<JPanel>();
		pageButtonPanel = new JPanel();
		northLabel = new JPanel();
		page = new JPanel();

		page.setPreferredSize(new Dimension(Constants.PANELS_RIGHT_WIDTH,Constants.PANELS_PAGE_HEIGHT));
		pageList.add(page);
		totalPageNum ++;

		prev = new JButton("prev");
		next = new JButton("next");

		prev.addActionListener(this);
		next.addActionListener(this);

		pageButtonPanel.add(prev);
		pageButtonPanel.add(next);

		northLabel.add(new JLabel("대기방 목록"));
		northLabel.setPreferredSize(new Dimension(Constants.PANELS_RIGHT_WIDTH,30));

		this.add(northLabel, BorderLayout.NORTH);
		this.add(page, BorderLayout.CENTER);
		this.add(pageButtonPanel, BorderLayout.SOUTH);

		totalPageNum = 1;
		currentPageNum = 1;
	}

	public void addRoom (JPanel room, int totalRoomNum) {
		if(totalRoomNum > totalPageNum*Constants.maxRoomNum){
			pageList.get(currentPageNum-1).setVisible(false);
			page = new JPanel();
			page.setPreferredSize(new Dimension(Constants.PANELS_RIGHT_WIDTH,Constants.PANELS_PAGE_HEIGHT));
			pageList.add(page);
			this.add(page, BorderLayout.CENTER);
			totalPageNum ++;
			currentPageNum ++;
		}
		page.add(room);
		page.updateUI();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == prev){
			if( currentPageNum > 1){
				for(int i=1; i<totalPageNum; i++){
					pageList.get(i).setVisible(false);
				}
				currentPageNum --;
				pageList.get(currentPageNum-1).setVisible(true);
				System.out.println("현재 페이지 : "+currentPageNum);
			}
		}else if(e.getSource() == next){
			if( currentPageNum < totalPageNum){
				for(int i=0; i<totalPageNum; i++){
					pageList.get(i).setVisible(false);
				}
				currentPageNum ++;
				pageList.get(currentPageNum-1).setVisible(true);
				System.out.println("현재 페이지 : "+currentPageNum);
			}
		}
		this.updateUI();
	}
}

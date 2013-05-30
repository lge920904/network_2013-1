package kr.ac.mju.oos.ui.panels.wait;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.uility.RoomListTool;

public class RoomListPanel extends JPanel implements ActionListener, KeyListener{
	private static final long serialVersionUID = 1L;
	private RoomListTool roomListTool;
	
	private JPanel northLabel;
	private JPanel pageButtonPanel;
	private JPanel page;
	
	private JButton prev;
	private JButton next;
	
	public RoomListPanel(RoomListTool roomListTool) {
		super();
		this.roomListTool = roomListTool;
	}

	public void init() {
		this.setPreferredSize(new Dimension(Constants.PANELS_RIGHT_WIDTH,
				Constants.PANELS_ROOMLIST_HEIGHT));
		this.setBackground(Constants.DEFAULT_BACKGROUND_COLOR);
		this.setLayout(new BorderLayout());
		
		pageButtonPanel = new JPanel();
		northLabel = new JPanel();
		page = new JPanel();
		
		prev = new JButton("prev");
		next = new JButton("next");
		
		prev.addActionListener(this);
		next.addActionListener(this);
		this.addKeyListener(this);
		northLabel.addKeyListener(this);
		pageButtonPanel.addKeyListener(this);
		page.addKeyListener(this);		
		
		pageButtonPanel.add(prev);
		pageButtonPanel.add(next);
		
		roomListTool.createPage();
		page = roomListTool.getPage();
		
		northLabel.add(new JLabel("대기방 목록"));
		northLabel.setPreferredSize(new Dimension(Constants.PANELS_RIGHT_WIDTH,30));
		
		this.add(northLabel, BorderLayout.NORTH);
		this.add(page, BorderLayout.CENTER);
		this.add(pageButtonPanel, BorderLayout.SOUTH);
	}
	public void createRoom(String roomName, String gameMode, String itemMode, Object scret){
		page = roomListTool.createRoom(roomName,gameMode,itemMode,scret);
		repaint();
		this.add(page, BorderLayout.CENTER);
	}
	
	public void keyTyped(KeyEvent e) {
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar()==KeyEvent.VK_LEFT){
			prev.doClick();
		}else if(e.getKeyChar()==KeyEvent.VK_RIGHT){
			next.doClick();			
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == prev){
			page = roomListTool.prevPage();
			repaint();
		}else if(e.getSource() == next){
			page = roomListTool.nextPage();
			repaint();
		}
		repaint();
	}
}

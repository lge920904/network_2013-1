package kr.ac.mju.oos.ui.panels.game;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.controller.FrontController;
import kr.ac.mju.oos.ui.dialogs.PlayerListDialog;
import kr.ac.mju.oos.model.dto.*;

public class PlayerListPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private String location;
	private PlayerListDialog pld;
	private ImageIcon player;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private UserDataBean user;

	public PlayerListPanel(String location) {
		// TODO Auto-generated constructor stub
		player = new ImageIcon("picture/missingAvatar.png");
		button1 = new JButton(player);
		button2 = new JButton(player);
		button3 = new JButton(player);
		user= new UserDataBean();
		this.location = location;
		this.setLayout(new GridLayout(3, 1));
		this.add(button1);
		this.add(button2);
		this.add(button3);
		setVisible(true);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1 || e.getSource() == button2
				|| e.getSource() == button3) {
			pld = new PlayerListDialog(user);// 아직
																		// 미완성
		}
	}

	public void init(FrontController controller) {
		this.setPreferredSize(new Dimension(Constants.PANELS_PLAYER_WIDTH,
				Constants.PANELS_BOTTOM_HEIGHT));
		/*
		 * ImageIcon player = new ImageIcon("picture/missingAvatar.png"); JLabel
		 * label1=new JLabel(); JLabel label2=new JLabel(); JLabel label3=new
		 * JLabel(); label1.setIcon(player); label2.setIcon(player);
		 * label3.setIcon(player); this.setLayout(new GridLayout(3,1));
		 * this.add(new JPanel().add(label1)); this.add(new
		 * JPanel().add(label2)); this.add(new JPanel().add(label3));
		 * setVisible(true); //setSize(300,150);
		 */

	}

}

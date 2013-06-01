package kr.ac.mju.oos.ui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.mju.oos.model.dto.UserDataBean;


public class PlayerListDialog extends JDialog {
	private JLabel jlb,jlbid,jlbrating;
	private JPanel playerlist;
	private JPanel content;
	private ImageIcon player;

	public PlayerListDialog(UserDataBean user) {
		playerlist = new JPanel(new BorderLayout());
		content = new JPanel();
		playerlist.add(content);
		player = new ImageIcon("picture/missingAvatar.png");	
		jlb= new JLabel();
		jlbid=new JLabel(user.getUserid());
		jlbrating=new JLabel(user.getSn1());
		jlb.setIcon(player);
		jlb.setPreferredSize(new Dimension(200, 40));
		jlbid.setPreferredSize(new Dimension(205, 40));
		jlbrating.setPreferredSize(new Dimension(210, 40));
		content.add(jlb);
		content.add(jlbid);
		content.add(jlbrating);
		
		this.setContentPane(playerlist);

		this.setSize(250, 200);
		this.setModal(true);
		this.setVisible(true);

	}

}

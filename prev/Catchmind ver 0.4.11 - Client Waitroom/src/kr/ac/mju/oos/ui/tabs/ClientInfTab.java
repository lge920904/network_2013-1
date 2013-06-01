package kr.ac.mju.oos.ui.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
//import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.mju.oos.model.dto.UserDataBean;


public class ClientInfTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel picture;
	private JPanel id;
	private JPanel rating;
	private ImageIcon player;
	private JLabel picturelabel;
	private JLabel idlabel,ratinglabel,emaillabel;

	public ClientInfTab(UserDataBean user) {
		picture = new JPanel();
		id =new JPanel();
		idlabel=new JLabel(user.getUserid());
		ratinglabel=new JLabel(user.getSn1());
		emaillabel=new JLabel(user.getEmail());
		rating = new JPanel();
		player = new ImageIcon("picture/missingAvatar.png");
		picturelabel= new JLabel();
		picturelabel.setIcon(player);
		picture.add(picturelabel);
		picture.setPreferredSize(new Dimension(100,115));
		id.setPreferredSize(new Dimension(100,115));
		id.add(idlabel);
		id.add(emaillabel);
		rating.setPreferredSize(new Dimension(100,115));
		rating.add(ratinglabel);
		id.setBackground(new Color(255,255,100));
		rating.setBackground(new Color(100,255,100));

	}

	public void init() {
		// TODO Auto-generated method stub
		this.add(new JLabel("AllInformationTab"));		
		this.setBackground(new Color(255,255,100));
		this.setLayout(new BorderLayout());		
		this.add(picture,BorderLayout.NORTH); this.add(id,BorderLayout.CENTER); this.add(rating,BorderLayout.SOUTH);
		this.setBounds(500,110,250,180);
		
	}
	
}

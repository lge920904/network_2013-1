package kr.ac.mju.oos.ui.tabs;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.model.dto.*;

public class InformationTabs extends JTabbedPane {
	private static final long serialVersionUID = 1L;

	private ClientInfTab clientInfTab;
	private AllInfTab allInfTab;
	private UserDataBean user;

	public InformationTabs() {
		// TODO Auto-generated constructor stub
		user=new UserDataBean();
		allInfTab = new AllInfTab();
		clientInfTab = new ClientInfTab(user);
	}

	public void init() {

		this.setPreferredSize(new Dimension(Constants.PANELS_RIGHT_WIDTH - 6,
				Constants.FRAMES_MAIN_HEIGHT / 2)); // Border 굵기가 3이라 양쪽 +6
		this.addTab(ClientInfTab.class.getSimpleName(),new ImageIcon("picture//001.jpg"), clientInfTab);
		this.addTab(AllInfTab.class.getSimpleName(), allInfTab);

		//Tab은 이미지로 크기 맞춰야함 
		
		clientInfTab.init();
		allInfTab.init();
	}
}

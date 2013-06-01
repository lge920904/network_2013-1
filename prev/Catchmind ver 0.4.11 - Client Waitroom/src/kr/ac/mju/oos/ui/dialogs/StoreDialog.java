package kr.ac.mju.oos.ui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.ui.panels.wait.MenuPanel;
import kr.ac.mju.oos.ui.panels.wait.RoomListPanel;
import kr.ac.mju.oos.ui.panels.wait.StoreMenuPanel;
import kr.ac.mju.oos.ui.panels.wait.StoreListPanel;

public class StoreDialog extends JDialog {

	private StoreMenuPanel storeMenuPanel;
	private StoreListPanel storeListPanel;

	public StoreDialog() {

		super(new JDialog(), Constants.DIALOG_STORE_NAME, true);
		storeMenuPanel = new StoreMenuPanel();
		storeListPanel = new StoreListPanel();

		this.init();
	}

	private void init() {

		Dimension frame;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		this.setSize(Constants.DIALOG_STORE_WIDTH, Constants.DIALOG_STORE_HEIGHT);
		frame = this.getSize();
		this.setLocation((int) (screen.getWidth() / 2 - frame.getWidth() / 2),
				(int) (screen.getHeight() / 2 - frame.getHeight() / 2));

		this.setLayout(new BorderLayout());

		this.add(storeMenuPanel, BorderLayout.NORTH);
		this.add(storeListPanel, BorderLayout.CENTER);

		storeMenuPanel.init();
		storeListPanel.init();

		this.setResizable(false);
		this.setModal(true);
	}

}

package kr.ac.mju.oos.ui.panels.wait;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.mju.oos.constants.Constants;

public class StoreMenuPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JLabel mainLabel;
	
	private JPanel leftPanel;
	private JPanel centerPanel;
	private JPanel rightPanel;

	private JButton item1;
	private JButton item2;
	private JButton item3;
	
	private JButton exit;
	
	public StoreMenuPanel() {
		// TODO Auto-generated constructor stub
		mainLabel = new JLabel("ITEM STORE");
		mainLabel.setPreferredSize(new Dimension( 150 , 25));
		item1 = new JButton("1");
		item2 = new JButton("2");
		item3 = new JButton("3");
		
		exit = new JButton("대기실");
		
		leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		leftPanel.setPreferredSize(new Dimension(200, 40));
		centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(150, 40));
		rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		rightPanel.setPreferredSize(new Dimension(200, 40));

		leftPanel.add(mainLabel);
		
		centerPanel.add(item1);
		centerPanel.add(item2);
		centerPanel.add(item3);
		
		rightPanel.add(exit);
		
		exit.addActionListener(this);
		this.init();
	}

	public void init() {
		this.setPreferredSize(new Dimension(Constants.DIALOG_STORE_WIDTH,
				Constants.PANELS_STOREMENU_HEIGHT));

		this.add(leftPanel, BorderLayout.WEST);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(rightPanel, BorderLayout.EAST);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {
		}
	}
}

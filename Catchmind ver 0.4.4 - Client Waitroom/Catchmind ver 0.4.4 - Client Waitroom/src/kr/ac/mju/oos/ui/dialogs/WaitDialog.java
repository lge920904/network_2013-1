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

public class WaitDialog extends JDialog implements ActionListener {
	private JLabel mainLabel;
	private JPanel waitPannel;
	private JPanel content;

	private JButton exit;

	public WaitDialog() {

		super(new JDialog(), Constants.DIALOG_WAIT_NAME, true);
		waitPannel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		content = new JPanel();
		waitPannel.add(content);

		mainLabel = new JLabel("게임 찾는중 ......");
		mainLabel.setPreferredSize(new Dimension(90, 40));

		exit = new JButton(Constants.DIALOG_WAIT_CANCEL);

		content.add(mainLabel);
		content.add(exit);

		exit.addActionListener(this);

		this.setContentPane(waitPannel);
		this.init();

	}

	private void init() {

		Dimension frame;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		this.setSize(Constants.DIALOG_WAIT_WIDTH, Constants.DIALOG_WAIT_HEIGHT);
		frame = this.getSize();
		this.setLocation((int) (screen.getWidth() / 2 - frame.getWidth() / 2),
				(int) (screen.getHeight() / 2 - frame.getHeight() / 2));

		this.setResizable(false);
		this.setModal(true);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(Constants.DIALOG_WAIT_CANCEL)) {
			this.setVisible(false);
		}
	}

}

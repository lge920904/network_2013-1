package kr.ac.mju.oos.ui.panels.wait;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import kr.ac.mju.oos.constants.Constants;

public class StoreListPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JPanel panel1, panel2, panel3, panel4;
	private JLabel label1, label2, label3, label4;
	private JButton button1, button2, button3, button4;
	private ImageIcon item1, item2, item3, item4;

	public StoreListPanel() {
		// TODO Auto-generated constructor stub\
		FlowLayout flowlayout = new FlowLayout();
		setLayout(flowlayout);

		item1 = new ImageIcon("picture/missingAvatar.png");
		label1 = new JLabel("경험치 2배 아이템 입니다.", item1, JLabel.LEFT);
		panel1 = new JPanel();
		button1 = new JButton("구매하기");
		panel1.setBackground(Constants.DEFAULT_BACKGROUND_COLOR);
		panel1.add(label1);
		panel1.add(button1);
		panel1.setBorder(BorderFactory.createTitledBorder(""));
		this.add(panel1);

		item2 = new ImageIcon("picture/missingAvatar.png");
		label2 = new JLabel("슈퍼 방장 아이템입니다. ", item1, JLabel.LEFT);
		panel2 = new JPanel();
		button2 = new JButton("구매하기");
		panel2.setBackground(Constants.DEFAULT_BACKGROUND_COLOR);
		panel2.add(label2);
		panel2.add(button2);
		panel2.setBorder(BorderFactory.createTitledBorder(""));
		this.add(panel2);

		item3 = new ImageIcon("picture/missingAvatar.png");
		label3 = new JLabel("키워드 변경 아이템입니다.", item1, JLabel.LEFT);
		panel3 = new JPanel();
		button3 = new JButton("구매하기");
		panel3.setBackground(Constants.DEFAULT_BACKGROUND_COLOR);
		panel3.add(label3);
		panel3.add(button3);
		panel3.setBorder(BorderFactory.createTitledBorder(""));
		this.add(panel3);

		item4 = new ImageIcon("picture/missingAvatar.png");
		label4 = new JLabel("기타 변경 아이템입니다.", item1, JLabel.LEFT);
		panel4 = new JPanel();
		button4 = new JButton("구매하기");
		panel4.setBackground(Constants.DEFAULT_BACKGROUND_COLOR);
		panel4.add(label4);
		panel4.add(button4);
		panel4.setBorder(BorderFactory.createTitledBorder(""));
		this.add(panel4);
		

		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
	}

	public void init() {

		Dimension labelDimension = new Dimension(450, 50);
		Dimension buttonDimension = new Dimension(100, 40);

		label1.setPreferredSize(labelDimension);
		label2.setPreferredSize(labelDimension);
		label3.setPreferredSize(labelDimension);
		label4.setPreferredSize(labelDimension);
		button1.setPreferredSize(buttonDimension);
		button2.setPreferredSize(buttonDimension);
		button3.setPreferredSize(buttonDimension);
		button4.setPreferredSize(buttonDimension);
		
		this.setPreferredSize(new Dimension(Constants.DIALOG_STORE_WIDTH,
				Constants.PANELS_STORELIST_HEIGHT));
		this.setBackground(Constants.DEFAULT_BACKGROUND_COLOR);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			
		} else if (e.getSource() == button2) {
			
		} else if (e.getSource() == button3) {
			
		} else if (e.getSource() == button4) {
		}
	}
}

package kr.ac.mju.oos.ui.panels.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.uility.DrawingTool;

public class ToolboxPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	JPanel center_Pn, color_Pn; // 패널생성

	// bin folder 밑에 image file 불러오기
	private ImageIcon ii_001 = new ImageIcon("picture//001.jpg");
	private ImageIcon ii_002 = new ImageIcon("picture//002.jpg");
	private ImageIcon ii_003 = new ImageIcon("picture//003.jpg");
	private ImageIcon ii_004 = new ImageIcon("picture//004.jpg");
	private ImageIcon ii_005 = new ImageIcon("picture//005.jpg");
	private ImageIcon ii_006 = new ImageIcon("picture//006.jpg");
	private ImageIcon ii_007 = new ImageIcon("picture//007.jpg");
	private ImageIcon ii_008 = new ImageIcon("picture//008.jpg");
	private ImageIcon ii_009 = new ImageIcon("picture//009.jpg");
	private ImageIcon ii_010 = new ImageIcon("picture//010.jpg");
	private ImageIcon ii_011 = new ImageIcon("picture//011.jpg");
	private ImageIcon ii_012 = new ImageIcon("picture//012.jpg");
	private ImageIcon ii_013 = new ImageIcon("picture//013.jpg");
	private ImageIcon ii_101 = new ImageIcon("picture//101.jpg");
	private ImageIcon ii_102 = new ImageIcon("picture//102.jpg");
	private ImageIcon ii_103 = new ImageIcon("picture//103.jpg");
	private ImageIcon ii_104 = new ImageIcon("picture//104.jpg");
	private ImageIcon ii_105 = new ImageIcon("picture//105.jpg");
	private ImageIcon ii_106 = new ImageIcon("picture//106.jpg");
	private ImageIcon ii_107 = new ImageIcon("picture//107.jpg");
	private ImageIcon ii_108 = new ImageIcon("picture//108.jpg");
	private ImageIcon ii_109 = new ImageIcon("picture//109.jpg");
	private ImageIcon ii_110 = new ImageIcon("picture//110.jpg");
	private ImageIcon ii_111 = new ImageIcon("picture//111.jpg");
	private ImageIcon ii_112 = new ImageIcon("picture//112.jpg");
	private ImageIcon ii_113 = new ImageIcon("picture//113.jpg");
	private ImageIcon ii_eraser = new ImageIcon("picture//eraser.jpg");
	private ImageIcon ii_eraser2 = new ImageIcon("picture//eraser2.jpg");

	// ToggleButton을 모을 버튼 그룹 (색상 버튼 그룹)
	private ButtonGroup bg_color = new ButtonGroup();
	private JToggleButton btn_black = new JToggleButton(ii_001);
	private JToggleButton btn_darkgray = new JToggleButton(ii_002);
	private JToggleButton btn_gray = new JToggleButton(ii_003);
	private JToggleButton btn_lightgray = new JToggleButton(ii_004);
	private JToggleButton btn_white = new JToggleButton(ii_005);
	private JToggleButton btn_red = new JToggleButton(ii_006);
	private JToggleButton btn_pink = new JToggleButton(ii_007);
	private JToggleButton btn_orange = new JToggleButton(ii_008);
	private JToggleButton btn_yellow = new JToggleButton(ii_009);
	private JToggleButton btn_green = new JToggleButton(ii_010);
	private JToggleButton btn_cyan = new JToggleButton(ii_011);
	private JToggleButton btn_blue = new JToggleButton(ii_012);
	private JToggleButton btn_magenta = new JToggleButton(ii_013);
	private JToggleButton btn_eraser = new JToggleButton(ii_eraser);

	private Label lb_thick = new Label("Thickenss", Label.LEFT); // 선굵기 글씨 새기기

	// ToggleButton을 모을 버튼 그룹 (선 굵기 버튼 그룹)
	private ButtonGroup bg_thick = new ButtonGroup();
	private JToggleButton btn_1 = new JToggleButton("1");
	private JToggleButton btn_2 = new JToggleButton("2");
	private JToggleButton btn_3 = new JToggleButton("3");
	private JToggleButton btn_4 = new JToggleButton("4");
	private JToggleButton btn_5 = new JToggleButton("5");

	private DrawingTool drawTool;

	private Label lb_color = new Label("     Colors", Label.LEFT); // colors 글씨
																	// 라벨 만들기

	private JButton btn_new = new JButton("C L E A R"); // 캔버스초기화 버튼

	public ToolboxPanel() {
		// TODO Auto-generated constructor stub

		this.add(center_Pn = new JPanel(new BorderLayout()));// 가운데에 사진과 색선택 버튼을
																// 올리기 위한 panel
		center_Pn.add(color_Pn = new JPanel(), BorderLayout.CENTER);// 좌측에 색상버튼을
																	// 올리기 위한
																	// panel
		center_Pn.setBackground(Color.white); // 배경 흰색으로

		color_Pn.add(lb_thick); // 선굴기 글씨 산입

		// 선굵기 버튼 산입
		color_Pn.add(btn_1);
		btn_1.setSelected(true); // 1번버튼을 초기 선택 값으로
		color_Pn.add(btn_2);
		color_Pn.add(btn_3);
		color_Pn.add(btn_4);
		color_Pn.add(btn_5);

		color_Pn.add(lb_color); // color 글씨 산입

		// color 버튼 산입
		color_Pn.add(btn_black);
		btn_black.setSelected(true); // Black을 초기 선택 색상으로
		color_Pn.add(btn_darkgray);
		color_Pn.add(btn_gray);
		color_Pn.add(btn_lightgray);
		color_Pn.add(btn_white);
		color_Pn.add(btn_red);
		color_Pn.add(btn_pink);
		color_Pn.add(btn_orange);
		color_Pn.add(btn_yellow);
		color_Pn.add(btn_green);
		color_Pn.add(btn_cyan);
		color_Pn.add(btn_blue);
		color_Pn.add(btn_magenta);
		color_Pn.add(btn_eraser);
		color_Pn.add(btn_new);

	}

	public void init(DrawingTool drawTool) {
		this.setPreferredSize(new Dimension(Constants.FRAMES_MAIN_WIDTH,
				Constants.PANELS_TOOLBOX_HEIGHT)); // Constants���� ��ڽ� ũ��
													// ���� �ϼ���
		// this.add(new JLabel("Toolbox")); // �� ������ �߰�

		this.drawTool = drawTool;

		// Rollover icon image 지정
		btn_black.setRolloverIcon(ii_101);
		btn_darkgray.setRolloverIcon(ii_102);
		btn_gray.setRolloverIcon(ii_103);
		btn_lightgray.setRolloverIcon(ii_104);
		btn_white.setRolloverIcon(ii_105);
		btn_red.setRolloverIcon(ii_106);
		btn_pink.setRolloverIcon(ii_107);
		btn_orange.setRolloverIcon(ii_108);
		btn_yellow.setRolloverIcon(ii_109);
		btn_green.setRolloverIcon(ii_110);
		btn_cyan.setRolloverIcon(ii_111);
		btn_blue.setRolloverIcon(ii_112);
		btn_magenta.setRolloverIcon(ii_113);
		btn_eraser.setRolloverIcon(ii_eraser2);

		// 색상 선택되었을때 icon image 지정
		btn_black.setSelectedIcon(ii_101);
		btn_darkgray.setSelectedIcon(ii_102);
		btn_gray.setSelectedIcon(ii_103);
		btn_lightgray.setSelectedIcon(ii_104);
		btn_white.setSelectedIcon(ii_105);
		btn_red.setSelectedIcon(ii_106);
		btn_pink.setSelectedIcon(ii_107);
		btn_orange.setSelectedIcon(ii_108);
		btn_yellow.setSelectedIcon(ii_109);
		btn_green.setSelectedIcon(ii_110);
		btn_cyan.setSelectedIcon(ii_111);
		btn_blue.setSelectedIcon(ii_112);
		btn_magenta.setSelectedIcon(ii_113);
		btn_eraser.setSelectedIcon(ii_eraser2);

		// 버튼의 경계는 Empty로
		btn_black.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_darkgray.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_gray.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_lightgray.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_white.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_red.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_pink.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_orange.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_yellow.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_green.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_cyan.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_blue.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_magenta.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_eraser.setBorder(new EmptyBorder(0, 0, 0, 0));

		// 선색상 하나만 선택되게 토글 지정?
		bg_color.add(btn_black);
		bg_color.add(btn_darkgray);
		bg_color.add(btn_gray);
		bg_color.add(btn_lightgray);
		bg_color.add(btn_white);
		bg_color.add(btn_red);
		bg_color.add(btn_pink);
		bg_color.add(btn_orange);
		bg_color.add(btn_yellow);
		bg_color.add(btn_green);
		bg_color.add(btn_cyan);
		bg_color.add(btn_blue);
		bg_color.add(btn_magenta);
		bg_color.add(btn_eraser);

		// 선굵기 하나만 선택되게 토글 지정?
		bg_thick.add(btn_1);
		bg_thick.add(btn_2);
		bg_thick.add(btn_3);
		bg_thick.add(btn_4);
		bg_thick.add(btn_5);

		btn_black.addActionListener(this);
		btn_blue.addActionListener(this);
		btn_cyan.addActionListener(this);
		btn_darkgray.addActionListener(this);
		btn_gray.addActionListener(this);
		btn_green.addActionListener(this);
		btn_lightgray.addActionListener(this);
		btn_magenta.addActionListener(this);
		btn_orange.addActionListener(this);
		btn_pink.addActionListener(this);
		btn_red.addActionListener(this);
		btn_white.addActionListener(this);
		btn_yellow.addActionListener(this);

		btn_1.addActionListener(this);
		btn_2.addActionListener(this);
		btn_3.addActionListener(this);
		btn_4.addActionListener(this);
		btn_5.addActionListener(this);

		btn_new.addActionListener(this);
		this.btn_black.doClick();
		this.btn_1.doClick();
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if (e.getSource() == btn_black) {
			drawTool.setColor(Color.BLACK);
		} else if (e.getSource() == btn_darkgray) {
			drawTool.setColor(Color.DARK_GRAY);
		} else if (e.getSource() == btn_gray) {
			drawTool.setColor(Color.GRAY);
		} else if (e.getSource() == btn_lightgray) {
			drawTool.setColor(Color.LIGHT_GRAY);
		} else if (e.getSource() == btn_red) {
			drawTool.setColor(Color.RED);
		} else if (e.getSource() == btn_pink) {
			drawTool.setColor(Color.PINK);
		} else if (e.getSource() == btn_orange) {
			drawTool.setColor(Color.ORANGE);
		} else if (e.getSource() == btn_yellow) {
			drawTool.setColor(Color.YELLOW);
		} else if (e.getSource() == btn_green) {
			drawTool.setColor(Color.GREEN);
		} else if (e.getSource() == btn_cyan) {
			drawTool.setColor(Color.CYAN);
		} else if (e.getSource() == btn_blue) {
			drawTool.setColor(Color.BLUE);
		} else if (e.getSource() == btn_magenta) {
			drawTool.setColor(Color.MAGENTA);
		} else if (e.getSource() == btn_white) {
			drawTool.setColor(Color.WHITE);
		} else if (e.getSource() == btn_1) {
			drawTool.setStroke(1);
			// CanvasPanel.pointSize = 4;
		} else if (e.getSource() == btn_2) {
			drawTool.setStroke(2);
			// CanvasPanel.pointSize = 9;
		} else if (e.getSource() == btn_3) {
			drawTool.setStroke(3);
			// CanvasPanel.pointSize = 13;
		} else if (e.getSource() == btn_4) {
			drawTool.setStroke(4);
			// CanvasPanel.pointSize = 18;
		} else if (e.getSource() == btn_5) {
			drawTool.setStroke(5);
			// CanvasPanel.pointSize = 23;
		} else if (e.getSource() == btn_new) {
			drawTool.clear();
		}

	}
}

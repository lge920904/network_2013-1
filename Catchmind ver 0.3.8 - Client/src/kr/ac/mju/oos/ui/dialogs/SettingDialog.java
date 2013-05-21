package kr.ac.mju.oos.ui.dialogs;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.ui.panels.game.ToolboxPanel;
import kr.ac.mju.oos.uility.Setting;

public class SettingDialog extends JDialog implements ActionListener,ChangeListener{
	private JPanel sliderPanel;
	private JPanel buttonPanel;
	private ToolboxPanel toolboxPanel;
	private JButton confirm;
	private Setting setting;
	private static final long serialVersionUID = 1L;

	public SettingDialog(ToolboxPanel toolboxPanelpanel){
		super(new JDialog(),"Setting");
		this.setBounds(Constants.SETTING_X,Constants.SETTING_Y,
				Constants.SETTING_WIDTH,Constants.SETTING_HEIGHT);
		this.setLayout(new BorderLayout());
		this.setVisible(false);

		setting = new Setting(toolboxPanelpanel);
		sliderPanel = new JPanel();
		buttonPanel = new JPanel();
		confirm = new JButton("confirm");

		sliderPanel.setLayout(new GridLayout(5,2,1,1));
		sliderPanel.setSize(Constants.SETTING_WIDTH, 250);

		confirm.addActionListener(this);
		confirm.setSize(10,30);

		buttonPanel.add(confirm);

		this.setSlide();

		this.add(sliderPanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}	
	public void optionVisible() {
		this.setVisible(true);
	}
	public void setThick(JToggleButton button){
		setting.setThick(button);
	}
	public void setSlide(){
		for(int i=0; i<Constants.BRUSH_NUMBER; i++){
			JSlider slider = new JSlider(1,80,1);
			slider.setPaintLabels(true);
			slider.setMajorTickSpacing(10);
			slider.addChangeListener(this);
			setting.setSliderList(slider);
		}
		for(int i=0; i<Constants.BRUSH_NUMBER; i++){
			JLabel lable = new JLabel('\t'+"Brush Size "+(i+1));
			sliderPanel.add(lable);
			sliderPanel.add(setting.getSliderList(i));
		}
	}
	public void setBrushSize(ChangeEvent e){
		setting.setBrushSize(e);
	}
	// action event	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("confirm")){
			this.setVisible(false);
			//setting.setVisibleFalse(this);
		}
	}
	// slider event
	public void stateChanged(ChangeEvent e) {
		setBrushSize(e);
	}
}


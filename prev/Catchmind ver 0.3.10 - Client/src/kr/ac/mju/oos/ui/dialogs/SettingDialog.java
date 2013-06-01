package kr.ac.mju.oos.ui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class SettingDialog extends JDialog implements ActionListener, ChangeListener, ItemListener{
	
	private Setting setting;
	
	private JPanel title;
	private JPanel settingPanel;
	private JPanel buttonPanel;
	
	// settingPanel
	private JPanel brushSize;
	private JPanel brushShape;
	
	private JButton confirm;
	private JSlider slider;
	private JComboBox<String> comboBox; 
	
	private String [] string = {"brush1", "brush2", "brush3", "brush4", "brush5"}; 
	
	private static final long serialVersionUID = 1L;

	public SettingDialog(ToolboxPanel toolboxPanelpanel){
		super(new JDialog(),"Setting");
		this.setBounds(Constants.SETTING_X,Constants.SETTING_Y,
				Constants.SETTING_WIDTH,Constants.SETTING_HEIGHT);
		this.setLayout(new BorderLayout());
		this.setVisible(false);

		setting = new Setting(toolboxPanelpanel);
		
		title = new JPanel();
		settingPanel = new JPanel();
		buttonPanel = new JPanel();
		brushSize = new JPanel();
		brushShape = new JPanel();
		
		comboBox = new JComboBox<String>();	
		slider = new JSlider(1,80,1);
		confirm = new JButton("confirm");
		
		title.add(new JLabel("Setting"));
				
		settingPanel.setSize(Constants.SETTING_WIDTH, 250);
		
		for(int i=0; i<5; i++){
			comboBox.addItem(string[i]);
		}
		comboBox.addItemListener(this);
		
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(10);
		slider.addChangeListener(this);
		
		brushSize.setLayout(new GridLayout(2,2,15,5));
		brushSize.add(new JLabel("Brush Size"));
		brushSize.add(new JLabel(" "));
		brushSize.add(comboBox);
		brushSize.add(slider);
		
		brushShape.setLayout(new GridLayout(1,2,15,5));
		brushShape.add(new JLabel("Brush Shape"));
		brushShape.add(new JLabel(" "));
		brushShape.setBackground(Color.GRAY);
		
		settingPanel.add(brushSize); 
		settingPanel.add(brushShape);		
		
		confirm.addActionListener(this);
		confirm.setSize(10,30);
		
		buttonPanel.add(confirm);

		this.add(title, BorderLayout.NORTH);
		this.add(settingPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}	
	public void optionVisible() {
		this.setVisible(true);
	}
	public void setCurrentBrush(String currentBrush){
		setting.setCurrentBrush(currentBrush);
	}
	public void setThickList(JToggleButton button){
		setting.setThickList(button);
	}
	public void setBrushSize(int brushSize){
		setting.setBrushSize(brushSize);
	}
	
	// Event Handler
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("confirm")){
			this.setVisible(false);
		}
	}
	public void stateChanged(ChangeEvent e) {
		JSlider s = (JSlider) e.getSource();
		this.setBrushSize(s.getValue());
	}
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED){
			this.setCurrentBrush(e.getItem().toString());
		}
	}
}


package kr.ac.mju.oos.uility;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import kr.ac.mju.oos.constants.Constants;
import kr.ac.mju.oos.ui.panels.game.ToolboxPanel;

public class Setting extends JDialog implements ActionListener,ChangeListener{
	JPanel sliderPanel;
	JPanel buttonPanel;

	ToolboxPanel toolboxPanel;

	JButton confirm;
	ArrayList<JSlider> sliderList;
	ArrayList<JToggleButton> thick;

	private static final long serialVersionUID = 1L;

	public Setting(ToolboxPanel toolboxPanelpanel){
		super();
		this.setBounds(Constants.SETTING_X,Constants.SETTING_Y,
				Constants.SETTING_WIDTH,Constants.SETTING_HEIGHT);
		this.setLayout(new BorderLayout());
		this.setVisible(false);

		sliderPanel = new JPanel();
		buttonPanel = new JPanel();
		thick = new ArrayList<JToggleButton>();
		sliderList = new ArrayList<JSlider>();

		sliderPanel.setLayout(new GridLayout(5,2,1,1));
		sliderPanel.setSize(Constants.SETTING_WIDTH, 250);

		confirm = new JButton("confirm");
		confirm.addActionListener(this);
		confirm.setSize(10,30);
		buttonPanel.add(confirm);

		this.toolboxPanel = toolboxPanelpanel;
		this.setSlide();

		this.add(sliderPanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}	

	public void optionVisible() {
		this.setVisible(true);
	}

	public void setThick(JToggleButton button){
		thick.add(button);
	}

	public void setSlide(){
		// sliderList add
		for(int i=0; i<Constants.BRUSH_NUMBER; i++){
			JSlider slider = new JSlider(1,80,1);
			slider.setPaintLabels(true);
			slider.setMajorTickSpacing(10);
			slider.addChangeListener(this);
			sliderList.add(slider);
		}
		// label, slider add
		for(int i=0; i<Constants.BRUSH_NUMBER; i++){
			JLabel lable = new JLabel('\t'+"Brush Size "+(i+1));
			sliderPanel.add(lable);
			sliderPanel.add(sliderList.get(i));
		}
	}

	// action event	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("confirm")){
			this.setVisible(false);
		}
	}

	// slider event
	public void stateChanged(ChangeEvent e) {
		JSlider s = (JSlider)e.getSource();
		int thickSize = s.getValue();		
		if(e.getSource() == sliderList.get(0)){
			toolboxPanel.setBrushSize(thick.get(0),thickSize);
			System.out.println(thickSize);
		}else if(e.getSource() == sliderList.get(1)){
			toolboxPanel.setBrushSize(thick.get(1),thickSize);
		}else if(e.getSource() == sliderList.get(2)){
			toolboxPanel.setBrushSize(thick.get(2),thickSize);
		}else if(e.getSource() == sliderList.get(3)){
			toolboxPanel.setBrushSize(thick.get(3),thickSize);
		}else if(e.getSource() == sliderList.get(4)){
			toolboxPanel.setBrushSize(thick.get(4),thickSize);
		}
	}
}


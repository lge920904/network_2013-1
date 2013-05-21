package kr.ac.mju.oos.uility;

import java.util.ArrayList;

import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;

import kr.ac.mju.oos.ui.dialogs.SettingDialog;
import kr.ac.mju.oos.ui.panels.game.ToolboxPanel;

public class Setting implements Tool{
	private ToolboxPanel toolboxPanel;
	private ArrayList<JSlider> sliderList;
	private ArrayList<JToggleButton> thick;

	public Setting(ToolboxPanel toolboxPanel){
		thick = new ArrayList<JToggleButton>();
		sliderList = new ArrayList<JSlider>();
		this.toolboxPanel = toolboxPanel;		
	}
	public void setVisibleFalse(SettingDialog s){
		s.setVisible(false);
	}
	public void setSliderList(JSlider slider){
		sliderList.add(slider);
	}
	public JSlider getSliderList(int index){
		return sliderList.get(index);
	}
	public void setThick(JToggleButton button){
		thick.add(button);
	}
	public void setBrushSize(ChangeEvent e){
		JSlider s = (JSlider)e.getSource();
		int thickSize = s.getValue();		
		if(e.getSource() == sliderList.get(0)){
			toolboxPanel.setBrushSize(thick.get(0),thickSize);
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
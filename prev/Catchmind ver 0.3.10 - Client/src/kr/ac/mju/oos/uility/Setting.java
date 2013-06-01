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
	private String currentBrush;

	public Setting(ToolboxPanel toolboxPanel){
		thick = new ArrayList<JToggleButton>();
		sliderList = new ArrayList<JSlider>();
		this.toolboxPanel = toolboxPanel;		
	}
	public void setSliderList(JSlider slider){
		sliderList.add(slider);
	}
	public JSlider getSliderList(int index){
		return sliderList.get(index);
	}
	public void setCurrentBrush(String currentBrush){
		this.currentBrush = currentBrush;
	}
	public void setThickList(JToggleButton button){
		thick.add(button);
	}
	public void setBrushSize(int brushSize){	
		toolboxPanel.setBrushSize(currentBrush,brushSize);
	}
}
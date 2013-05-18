package kr.ac.mju.oos.ui.main;

import kr.ac.mju.oos.ui.dialogs.LoginDialog;
import kr.ac.mju.oos.ui.frames.MainFrame;

public class Launcher{
	private LoginDialog loginDialog;
	private static MainFrame mainFrame;
	private static AudioManager audioManager;

	public Launcher (){
		audioManager = new AudioManager();
		audioManager.selectMusic(this);
		loginDialog = new LoginDialog();
		setMainFrame(new MainFrame());
		audioManager.stopMusic();
		getMainFrame().init();
		
	}
	public static MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		Launcher.mainFrame = mainFrame;
	}

	public static void main(String[] args) {
		Launcher launcher = new Launcher();
	}

}


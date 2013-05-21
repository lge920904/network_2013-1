package kr.ac.mju.oos.ui.main;

import kr.ac.mju.oos.ui.dialogs.LoginDialog;
import kr.ac.mju.oos.ui.frames.MainFrame;

public class Launcher {
	private LoginDialog loginDialog;
	private MainFrame mainFrame;
	private AudioManager audioManager;

	public Launcher() {
		this.audioManager = new AudioManager();
		audioManager.setCurrentAudio(this.audioManager);
		this.loginDialog = new LoginDialog(this.audioManager);
		this.mainFrame = new MainFrame();
		this.mainFrame.init(this.audioManager);
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public static void main(String[] args) {
		Launcher launcher = new Launcher();
	}
}

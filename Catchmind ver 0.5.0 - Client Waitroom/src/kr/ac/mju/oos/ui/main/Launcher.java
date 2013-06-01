package kr.ac.mju.oos.ui.main;

import kr.ac.mju.oos.ui.dialogs.LoginDialog;
import kr.ac.mju.oos.ui.frames.MainFrame;

public class Launcher {
	private LoginDialog loginDialog;
	private MainFrame mainFrame;
	private AudioManager audioManager;

	public Launcher() {
		this.audioManager = new AudioManager();
		this.loginDialog = new LoginDialog(this.audioManager);
		this.mainFrame = new MainFrame();
		this.mainFrame.init(this.audioManager);
	}

	public static void main(String[] args) {
		Launcher launcher = new Launcher();
	}
}

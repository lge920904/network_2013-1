package kr.ac.mju.oos.ui.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import kr.ac.mju.oos.ui.dialogs.LoginDialog;
import kr.ac.mju.oos.ui.frames.MainFrame;
import kr.ac.mju.oos.ui.panels.game.MainPanel;

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

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public static void main(String[] args) {
		Launcher launcher = new Launcher();
	}
}

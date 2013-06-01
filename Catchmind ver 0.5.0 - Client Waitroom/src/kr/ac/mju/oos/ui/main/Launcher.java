package kr.ac.mju.oos.ui.main;

import kr.ac.mju.oos.ui.dialogs.LoginDialog;
import kr.ac.mju.oos.ui.frames.MainFrame;
import kr.ac.mju.oos.uility.AudioManager;
import kr.ac.mju.oos.uility.GameModeChanger;

public class Launcher {
	private GameModeChanger gameModeChanger;
	private LoginDialog loginDialog;
	private MainFrame mainFrame;
	private kr.ac.mju.oos.ui.panels.game.MainFrame gameFrame;
	private AudioManager audioManager;
	private String userID;

	public Launcher() {
		this.audioManager = new AudioManager();
		this.loginDialog = new LoginDialog(this.audioManager);
		this.loginDialog.init(this);
		// this.mainFrame = new MainFrame();
		// this.mainFrame.init(this.audioManager);
	}	

	public void init() {
		System.out.println(this.getUserId());
		this.mainFrame = new MainFrame();
		this.gameFrame = new kr.ac.mju.oos.ui.panels.game.MainFrame();

		gameModeChanger = new GameModeChanger(mainFrame, gameFrame,
				audioManager);
		this.mainFrame.init(this.audioManager, this.userID, gameModeChanger);
		this.gameFrame.init(this.audioManager, this.userID, gameModeChanger);
	}

	private String getUserId() {
		return userID;
	}

	public void setUserID(String id) {
		this.userID = id;
	}

	public static void main(String[] args) {
		Launcher launcher = new Launcher();
		launcher.init();
	}

}

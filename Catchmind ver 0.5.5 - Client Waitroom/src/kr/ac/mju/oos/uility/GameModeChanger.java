package kr.ac.mju.oos.uility;

import java.util.ArrayList;

public class GameModeChanger {
	private kr.ac.mju.oos.ui.frames.MainFrame waitFrame;
	private kr.ac.mju.oos.ui.panels.game.MainFrame gameFrame;
	private AudioManager audioManager;

	public GameModeChanger(kr.ac.mju.oos.ui.frames.MainFrame waitFrame,
			kr.ac.mju.oos.ui.panels.game.MainFrame gameFrame,
			AudioManager audioManager) {
		// TODO Auto-generated constructor stub
		this.waitFrame = waitFrame;
		this.gameFrame = gameFrame;
		this.audioManager = audioManager;
	}

	public void joinGameRoom(ArrayList<String> users) {
		gameFrame.start(users);
		audioManager.stopMusic();
		audioManager.selectMusic(gameFrame.getPanel());
		waitFrame.setVisible(false);
		gameFrame.setVisible(true);
	}

	public void exitGameRoom() {
		audioManager.stopMusic();
		audioManager.selectMusic(waitFrame.getPanel());
		gameFrame.setVisible(false);
		waitFrame.setVisible(true);

	}
}

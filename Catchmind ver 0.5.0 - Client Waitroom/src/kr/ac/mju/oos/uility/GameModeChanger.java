package kr.ac.mju.oos.uility;


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

	public void joinGameRoom() {
		waitFrame.setVisible(false);
		gameFrame.setVisible(true);
		audioManager.stopMusic();
		audioManager.selectMusic(gameFrame.getPanel());
	}

	public void exitGameRoom() {
		gameFrame.setVisible(false);
		waitFrame.setVisible(true);
		audioManager.stopMusic();
		audioManager.selectMusic(waitFrame.getPanel());

	}
}

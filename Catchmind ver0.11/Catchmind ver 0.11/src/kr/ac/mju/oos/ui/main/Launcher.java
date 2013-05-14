package kr.ac.mju.oos.ui.main;

import kr.ac.mju.oos.ui.dialogs.LoginDialog;
import kr.ac.mju.oos.ui.frames.MainFrame;

public class Launcher {
	private LoginDialog loginDialog;
	private MainFrame mainFrame;

	public static void main(String[] args) {
		Launcher launcher = new Launcher();

		launcher.loginDialog = new LoginDialog();
		launcher.mainFrame = new MainFrame();
	}
}

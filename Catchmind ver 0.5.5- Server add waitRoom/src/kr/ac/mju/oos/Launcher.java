package kr.ac.mju.oos;

import kr.ac.mju.oos.login.LoginServer;

public class Launcher {
	private static kr.ac.mju.oos.wait.MainServer waitServer;
	private static LoginServer loginServer;
	private static kr.ac.mju.oos.wait.ChattingServer chatServer;

	public static void main(String[] args) {

		loginServer = new LoginServer("login");
		waitServer = new kr.ac.mju.oos.wait.MainServer("Wait");
		chatServer = new kr.ac.mju.oos.wait.ChattingServer();
		loginServer.init();
		waitServer.init();
		chatServer.init();
	}
}

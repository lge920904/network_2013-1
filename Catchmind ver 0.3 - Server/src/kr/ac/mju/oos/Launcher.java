package kr.ac.mju.oos;

import kr.ac.mju.oos.chat.MainServer;

public class Launcher {
	private static kr.ac.mju.oos.chat.MainServer chattingServer;
	private static kr.ac.mju.oos.game.MainServer gameServer;

	public static void main(String[] args) {
		chattingServer = new MainServer("Chating");
		gameServer = new kr.ac.mju.oos.game.MainServer("Game");
		chattingServer.init();
		gameServer.init();
	}
}

package kr.ac.mju.oos;

import kr.ac.mju.oos.chat.MainServer;
import kr.ac.mju.oos.wait.ChattingServer;

public class Launcher {
	private static kr.ac.mju.oos.chat.MainServer chattingServer;
	private static kr.ac.mju.oos.game.MainServer gameServer;
	private static kr.ac.mju.oos.wait.MainServer waitServer;
	private static kr.ac.mju.oos.wait.ChattingServer chtServer;

	public static void main(String[] args) {
		//chattingServer = new MainServer("Chating");
		//gameServer = new kr.ac.mju.oos.game.MainServer("Game");
		waitServer = new kr.ac.mju.oos.wait.MainServer("Wait");
		chtServer = new kr.ac.mju.oos.wait.ChattingServer();
		//chattingServer.init();
		//gameServer.init();
		waitServer.init();
		chtServer.go();
	}
}

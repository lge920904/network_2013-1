package kr.ac.mju.oos.tool;

import kr.ac.mju.oos.chat.MainServer;

public class ServerManager {
	private volatile static ServerManager instance;

	private kr.ac.mju.oos.wait.MainServer waitServer;
	private kr.ac.mju.oos.game.MainServer gameServer;
	private MainServer chatServer;

	private ServerManager() {
		// TODO Auto-generated constructor stub
	}

	public static ServerManager getInstance() {
		if (instance == null) {
			synchronized (ServerManager.class) {
				if (instance == null) {
					instance = new ServerManager();
				}
			}
		}
		return instance;
	}

	public synchronized void setWaitServer(
			kr.ac.mju.oos.wait.MainServer waitServer) {
		this.waitServer = waitServer;
	}

	public synchronized void setGameServer(
			kr.ac.mju.oos.game.MainServer gameServer) {
		this.gameServer = gameServer;
	}

	public synchronized void setChatServer(MainServer chatServer) {
		this.chatServer = chatServer;
	}

	public kr.ac.mju.oos.wait.MainServer getWaitServer() {
		return waitServer;
	}

	public kr.ac.mju.oos.game.MainServer getGameServer() {
		return gameServer;
	}

	public MainServer getChatServer() {
		return chatServer;
	}
}

package kr.ac.mju.oos.constants;

import java.awt.Color;

public class Constants {
	public static final int GAME_PORT_NUMBER = 10001;
	public static final int CHAT_PORT_NUMBER = 10002;

	public static final String IP_NUMBER = "";

	public static final String DIALOG_LOGIN_NAME = "로그인";
	public static final String DIALOG_LOGIN_CONFIRM = "확인";
	public static final String DIALOG_LOGIN_CANCEL = "취소";
	public static final String DIALOG_LOGIN_ID = " ID ";
	public static final String DIALOG_LOGIN_PASSWORD = " PW ";

	// dialog
	public static final int DIALOG_LOGIN_WIDTH = 200;
	public static final int DIALOG_LOGIN_HEIGHT = 150;

	// frame
	public static final int FRAMES_MAIN_WIDTH = 1024;
	public static final int FRAMES_MAIN_HEIGHT = 768;

	// panel height
	public static final int PANELS_TOOLBOX_HEIGHT = 40;
	public static final int PANELS_CANVAS_HEIGHT = 510;
	public static final int PANELS_BOTTOM_HEIGHT = 218;

	// panel width
	public static final int PANELS_MAIN_WIDTH = 1024;
	public static final int PANELS_CHAT_WIDTH = 500;
	public static final int PANELS_PLAYER_WIDTH = 220;

	// Chatting
	public static final int PANELS_CHAT_TEXTFIELD_WIDTH = 40;
	public static final int PANELS_CHAT_TEXTAREA_WIDTH = 10;
	public static final int PANELS_CHAT_TEXTAREA_HEIGHT = 50;

	// Toolbar
	public static final int BRUSH_NUMBER = 5;

	// Setting
	public static final int SETTING_X = 230;
	public static final int SETTING_Y = 290;
	public static final int SETTING_WIDTH = 400;
	public static final int SETTING_HEIGHT = 500;

	// Graphics
	public static final int DEFAULT_STROKE_SIZE = 1;
	public static final Color DEFAULT_STROKE_COLOR = Color.BLACK;
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;

	// audio playlist
	public static enum AUDIO_PLAYLIST {
		login("login", "background_music/login.mid"), wait("wait",
				"background_music/wait.mid"), game("game",
						"background_music/game.mid");

		private String title;
		private String url;

		private AUDIO_PLAYLIST(String title, String url) {
			this.title = title;
			this.url = url;
		}
		public String getAudio_Title() {
			return title;
		}
		public String getAudio_Url() {
			return url;
		}
	};


}

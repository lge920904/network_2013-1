package kr.ac.mju.oos.constants;

import java.awt.Color;

public class Constants {
	public static final int GAME_PORT_NUMBER = 10001;
	public static final int CHAT_PORT_NUMBER = 10002;
	public static final int WAIT_PORT_NUMBER = 10003;
	public static final int WAIT_CHAT_PORT_NUMBER = 10004;

	public static final String IP_NUMBER = "";

	public static final String DIALOG_LOGIN_NAME = "로그인";
	public static final String DIALOG_LOGIN_CONFIRM = "확인";
	public static final String DIALOG_LOGIN_CANCEL = "취소";
	public static final String DIALOG_LOGIN_ID = " ID ";
	public static final String DIALOG_LOGIN_PASSWORD = " PW ";
	
	// Wait string
	public static final String DIALOG_STORE_NAME = "아이템 상점";
	public static final String DIALOG_STORE_CANCEL = "취소";

	// Wait string
	public static final String DIALOG_WAIT_NAME = "게임 찾는중....";
	public static final String DIALOG_WAIT_CANCEL = "취소";

	// SignUp string
	public static final String DIALOG_SIGNUP_NAME = " 회원가입 ";
	public static final String DIALOG_SIGNUP_CONFIRM = "확인";
	public static final String DIALOG_SIGNUP_CANCEL = "취소";
	public static final String DIALOG_SIGNUP_IDCHECK = "중복확인";
	public static final String DIALOG_SIGNUP_ID = " ID ";
	public static final String DIALOG_SIGNUP_PASSWORD = " PW ";
	public static final String DIALOG_SIGNUP_PASSWORDCHECK = " PWcheck ";
	public static final String DIALOG_SIGNUP_USERNAME = " NAME ";
	public static final String DIALOG_SIGNUP_SERIALNUMBER = " SERIALNUMBER ";
	public static final String DIALOG_SIGNUP_LINE = " - ";
	public static final String DIALOG_SIGNUP_EMAIL = " E-MAIL ";

	// CreateRoom string
	public static final String DIALOG_CREATEROOM_NAME = "게임방 만들기";
	public static final String DIALOG_CREATEROOM_CONFIRM = "확인";
	public static final String DIALOG_CREATEROOM_CANCEL = "취소";
	public static final String DIALOG_CREATEROOM_ROOMNAME = "방제목";
	public static final String DIALOG_CREATEROOM_PRIVATE = "공개여부";
	public static final String DIALOG_CREATEROOM_PASSWORD = "비밀번호";
	public static final String DIALOG_CREATEROOM_MODE = "   게임모드";
	public static final String DIALOG_CREATEROOM_ITEM = "       아이템사용";
	public static final String DIALOG_CREATEROOM_PERSON = "게임인원";

	// dialog
	public static final int DIALOG_LOGIN_WIDTH = 200;
	public static final int DIALOG_LOGIN_HEIGHT = 200;
	
	// store dialog
	public static final int DIALOG_STORE_WIDTH = 600;
	public static final int DIALOG_STORE_HEIGHT = 400;	
	
	// wait dialog
	public static final int DIALOG_WAIT_WIDTH = 180;
	public static final int DIALOG_WAIT_HEIGHT = 100;

	// signup dialog
	public static final int DIALOG_SIGNUP_WIDTH = 250;
	public static final int DIALOG_SIGNUP_HEIGHT = 270;

	// createroom dialog
	public static final int DIALOG_CREATEROOM_WIDTH = 270;
	public static final int DIALOG_CREATEROOM_HEIGHT = 270;

	// frame
	public static final int FRAMES_MAIN_WIDTH = 1024;
	public static final int FRAMES_MAIN_HEIGHT = 758;

	// panel height
	public static final int PANELS_TOOLBOX_HEIGHT = 40;
	public static final int PANELS_MENU_HEIGHT = 40;
	public static final int PANELS_CANVAS_HEIGHT = 510;
	public static final int PANELS_BOTTOM_HEIGHT = 218;
	public static final int PANELS_ROOMLIST_HEIGHT = 738;
	public static final int PANELS_STOREMENU_HEIGHT = 45;
	public static final int PANELS_STORELIST_HEIGHT = 355;

	// panel width
	public static final int PANELS_MAIN_WIDTH = 1024;
	public static final int PANELS_CHAT_WIDTH = 500;
	public static final int PANELS_PLAYER_WIDTH = 220;
	public static final int PANELS_LEFT_WIDTH = 724;
	public static final int PANELS_RIGHT_WIDTH = 300;

	// Toolbar
	public static final int BRUSH_NUMBER = 5;

	// Setting
	public static final int SETTING_X = 230;
	public static final int SETTING_Y = 290;
	public static final int SETTING_WIDTH = 400;
	public static final int SETTING_HEIGHT = 500;

	// Chatting
	public static final int PANELS_CHAT_TEXTFIELD_WIDTH = 40;
	public static final int PANELS_CHAT_TEXTAREA_WIDTH = 10;
	public static final int PANELS_CHAT_TEXTAREA_HEIGHT = 50;
	
	// Wait Room
	public static final int maxRoomNum = 8;
	public static final int PANELS_PAGE_HEIGHT = 670;
	public static final int PANELS_ROOM_WIDTH = 270;
	public static final int PANELS_ROOM_HEIGHT = 150;

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

package kr.ac.mju.oos.tool;

import java.util.ArrayList;

public class MsgParser implements Tool {
	private static MsgParser msgParser = new MsgParser();

	private MsgParser() {
	}

	public static MsgParser getInstance() {
		return msgParser;
	}

	public ArrayList<String> parseMsg(String msg) {
		return null;
	}

	public String parseUserList(ArrayList<String> users) {
		String userString = new String("");
		for (int i = 0; i < users.size(); i++) {
			userString = userString + " " + users.get(i).toString();
		}
		return userString;
	}

}

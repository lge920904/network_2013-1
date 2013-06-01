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

	public ArrayList<String> parseGameHandlerMsg(String msg) {
		
		
		return null;
	}

}

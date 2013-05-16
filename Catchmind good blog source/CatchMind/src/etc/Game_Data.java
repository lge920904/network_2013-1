package etc;

import java.io.Serializable;

public class Game_Data implements Serializable{
	int gameCount;
	String gameAnswer;
	User userAnswer;
	
	public Game_Data(int gameCount, String gameAnswer) {
		this.gameCount = gameCount;
		this.gameAnswer = gameAnswer;
	}

	public void setUserAnswer(User userAnswer) {
		this.userAnswer = userAnswer;
	}

	public User getUserAnswer() {
		return userAnswer;
	}

	public int getGameCount() {
		return gameCount;
	}

	public String getGameAnswer() {
		return gameAnswer;
	}
	
	
}

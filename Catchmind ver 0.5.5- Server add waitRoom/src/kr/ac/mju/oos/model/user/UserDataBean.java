package kr.ac.mju.oos.model.user;

public class UserDataBean {
	private String userID;
	private String userPW;
	private String Rating;

	public UserDataBean() {
		// TODO Auto-generated constructor stub

	}

	public UserDataBean(String userID, String userPW, String rating) {
		// TODO Auto-generated constructor stub
		this.userID = userID;
		this.userPW = userPW;
		this.Rating = rating;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPW() {
		return userPW;
	}

	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}

	public String getRating() {
		return Rating;
	}

	public void setRating(String rating) {
		this.Rating = rating;
	}

}

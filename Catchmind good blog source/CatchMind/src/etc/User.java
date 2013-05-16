package etc;

import java.io.Serializable;

// 사용자의 정보를 저장하기 위한 클래스
public class User implements Serializable{
	
	String id, password, nickName, eMail, age, phone;
	
	public User(String id, String password, String nickName, String eMail,
			String age, String phone) {
		super();
		this.id = id;
		this.password = password;
		this.nickName = nickName;
		this.eMail = eMail;
		this.age = age;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return nickName;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String geteMail() {
		return eMail;
	}

	public String getAge() {
		return age;
	}

	public String getPhone() {
		return phone;
	}
}

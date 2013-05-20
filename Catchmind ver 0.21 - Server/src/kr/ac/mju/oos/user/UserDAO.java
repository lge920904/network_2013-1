package kr.ac.mju.oos.user;

import java.sql.ResultSet;
import java.util.ArrayList;

import kr.ac.mju.oos.connection.DBConnectionMaker;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


import kr.ac.mju.oos.user.UserDataBean;

public class UserDAO {
	private static UserDAO instance = new UserDAO();

	public static UserDAO getInstance() {
		return instance;
	}

	private UserDAO() {

	}

	private Connection getConnection() throws Exception {
		return DBConnectionMaker.getInstance().getConnection();
	}

	public void insertUser(UserDataBean user) throws Exception {
		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			con = this.getConnection();
			pstmt = (PreparedStatement) con
					.prepareStatement("insert into user values (?,?,?)");
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPW());
			pstmt.setString(3, user.getRating());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
	}

public UserDataBean getUser(String id) throws Exception {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	UserDataBean user = null;
	try {
		con =this.getConnection();

		pstmt = (PreparedStatement) con
				.prepareStatement("select * from user where userid = ?");
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			user = new UserDataBean();
			user.setUserID(rs.getString("userid"));
			user.setUserPW(rs.getString("password"));
		    user.setRating(rs.getString("rating"));
		}
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
		if (rs != null)
			rs.close();
	}
	return user;
}
public void updateUser(UserDataBean user) throws Exception {
	Connection con = null;
	PreparedStatement pstmt = null;

	try {
		con = this.getConnection();

		pstmt =(PreparedStatement) con
				.prepareStatement("update user set password=?,rating=? where userid=?");
		pstmt.setString(1, user.getUserPW());
		pstmt.setString(2, user.getRating());
		
		pstmt.executeUpdate();

	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
	}
}
public boolean confirmId(String id, String password) {
	try {
		UserDataBean user = this.getUser(id);
		if (user != null) {
			return user.getUserPW().equals(password);
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}

public ArrayList<UserDataBean> getUsers() throws Exception{
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	UserDataBean user = null;
	ArrayList<UserDataBean> users = new ArrayList<UserDataBean>();
	try {
		con = this.getConnection();
		pstmt = (PreparedStatement) con.prepareStatement("select * from user");
		rs = pstmt.executeQuery();
		while (rs.next()) {
			user = new UserDataBean();

			user.setUserID(rs.getString("userid"));
			user.setUserPW(rs.getString("password"));
			user.setRating(rs.getString("rating"));
		
			users.add(user);
		}
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
		if (rs != null)
			rs.close();
	}
	return users;
}

public void deleteUser(UserDataBean user) {

}

public boolean checkId(String id) {
	try {
		UserDataBean user = this.getUser(id);
		if (user != null)
			return false;
		return true;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return false;
}
}

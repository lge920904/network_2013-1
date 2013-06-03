package kr.ac.mju.oos.model.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException eDrv) {
			eDrv.printStackTrace();
		}
	}

	/**
	 * 회원가입
	 * 
	 * @param member
	 * @throws Exception
	 */
	public void insertMember(UserDataBean user) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:xe", "scott", "tiger");
			pstmt = conn
					.prepareStatement("INSERT INTO UserTable VALUES (?,?,?)"); // 테이블이름
																				// USERTABLE
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPW());
			pstmt.setString(3, user.getRating());
			System.out.println("ID : " + user.getUserID() + "   사용자 생성");
			pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ROOM DB를 저장할 테이블이 아직 없음");
		} finally {
			if (conn != null)
				conn.close();
			if (stmt != null)
				stmt.close();
			if (pstmt != null)
				pstmt.close();
		}
	}

	/**
	 * ID 중복 확인
	 * 
	 * @param userid
	 */
	public boolean checkID(String userid) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:xe", "scott", "tiger");
			String sql = "SELECT userid FROM usertable WHERE userid = '"
					+ userid + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return false;
	}

	/**
	 * 로그인시 DB에서 ID,PW 체크하기
	 * 
	 * @param user_id
	 * @param user_pw
	 */
	public boolean checkLogin(String userid, String userpw) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:xe", "scott", "tiger");
			System.out.println("UserDAO Input id " + userid);
			String sql = "SELECT userpw FROM usertable WHERE userid='" + userid
					+ "'";
			System.out.println(sql);
			// stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("들어오지도않지?");
				String pw = rs.getString(1);
				System.out.println("UserDao " + pw + "input PW" + userpw);
				if (pw.equals(userpw)) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return false;
	}
}

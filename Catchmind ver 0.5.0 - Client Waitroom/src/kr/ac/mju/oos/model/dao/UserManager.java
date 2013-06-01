package kr.ac.mju.oos.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import kr.ac.mju.oos.model.dto.UserDataBean;

public class UserManager {

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
					.prepareStatement("INSERT INTO UserTable VALUES (?,?,?,?,?,?)");	// 테이블이름 USERTABLE
			pstmt.setString(1, user.getUserid());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getSn1());
			pstmt.setString(5, user.getSn2());
			pstmt.setString(6, user.getEmail());
			System.out.println("ID : " + user.getUserid() + "   사용자 생성");
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
	public void checkID(String userid) {
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
				JOptionPane.showMessageDialog(null, userid + "가 존재합니다.",
						"ID중복", JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, userid + "는 사용가능합니다.",
						"ID사용가능", JOptionPane.WARNING_MESSAGE);
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
	}

	/**
	 * 로그인시 DB에서 ID,PW 체크하기
	 * 
	 * @param user_id
	 * @param user_pw
	 */
	public void checkLogin(String userid, String userpw) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:xe", "scott", "tiger");
			String sql = "SELECT userpw FROM usertable WHERE userid='" + userid
					+ "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String pw = rs.getString(1);
				if (pw.equals(userpw)) {
					JOptionPane.showMessageDialog(null, "로그인 되었습니다.", "로그인 확인",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호가 틀립니다.",
							"비밀번호경고", JOptionPane.WARNING_MESSAGE);
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
	}
}

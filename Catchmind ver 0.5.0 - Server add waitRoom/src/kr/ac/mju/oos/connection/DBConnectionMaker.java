package kr.ac.mju.oos.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnectionMaker {
	private static DBConnectionMaker instance = new DBConnectionMaker();

	public static DBConnectionMaker getInstance() {
		return instance;
	}

	private DBConnectionMaker() {

	}

	public Connection getConnection() {
		try {
			Connection con = null;
			con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost/catchmind", "root", "1234");
			return con;
		} catch (Exception e) {

		}
		return null;
	}

	public void close(PreparedStatement pstmt, Connection con) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package kr.ac.mju.oos.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import kr.ac.mju.oos.model.dto.RoomDataBean;

public class RoomManager {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException eDrv) {
			eDrv.printStackTrace();
		}
	}

	/**
	 * 방만들기
	 * 
	 * @param room
	 * @throws Exception
	 */
	public void insertRoom(RoomDataBean room) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:xe", "scott", "tiger");
			pstmt = conn
					.prepareStatement("INSERT INTO RoomTable VALUES (?,?,?,?,?,?,?)");	// 테이블 이름 : ROOMTABLE
			pstmt.setInt(1, room.getRoomNumber());
			pstmt.setString(2, room.getRoomName());
			pstmt.setString(3, room.getSecretMode());
			pstmt.setString(4, room.getRoomPassword());
			pstmt.setString(5, room.getGameMode());
			pstmt.setString(6, room.getItemMode());
			pstmt.setString(7, room.getPerson());
			System.out.println("방제목 : " + room.getRoomName() + "     room 생성");
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
}

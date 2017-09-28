package main.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.domain.Recording;
import main.java.domain.Recording;

public class RecordingDAO {
	
//	private static String databaseUrlBit = "";
//	private static final String DB_URL = "jdbc:mysql://localhost/" + databaseUrlBit + "?autoReconnect=true&useSSL=false";
//	private static final String DB_URL = "jdbc:mysql://localhost/kiem?autoReconnect=true&useSSL=false";
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "pass";

	public RecordingDAO() {
		try {
			Class.forName(DB_DRIVER).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Recording> retrieveDataForHouse(int huisid, String dbUrlBit) {
		String selectSQL = "select idrecording, data from recording where huis_idhuis = ?";
		PreparedStatement pstmt = null;
		List<Recording> recordinglist = new ArrayList<Recording>();
		final String DB_URL = "jdbc:mysql://localhost/"+ dbUrlBit +"?autoReconnect=true&useSSL=false";
		try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

			pstmt = (PreparedStatement) con.prepareStatement(selectSQL);
			pstmt.setInt(1, huisid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("idrecording");
				String data = rs.getString("data");
				
				Recording r = new Recording();
				r.setId(id);
				r.setData(data);
				
				System.out.println(r.getData());
				recordinglist.add(r);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return recordinglist;

	}
	
	public boolean insertData(int idrecording, String newdata, String dbUrlBit) {
		boolean bool = false;

		String insertDataSQL = "update recording set data = CONCAT(data,'; ', '?') where idsensor = ?;";
		PreparedStatement pstmt = null;

		final String DB_URL = "jdbc:mysql://localhost/"+ dbUrlBit +"?autoReconnect=true&useSSL=false";
		try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

			pstmt = (PreparedStatement) con.prepareStatement(insertDataSQL);
			pstmt.setString(1, newdata);
			pstmt.setInt(2, idrecording);
			pstmt.executeUpdate();
			bool = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					return bool;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return bool;
	}
	
	public boolean insertIntoRecording(String dbUrlBit, int idrecording, String newdata,  Date date, int huisid){
		boolean bool = false;
		
		String insertSQL = "insert into recording (idrecording, data, DateOfRec, huis_idhuis) values(?,?,?,?)"; 
		PreparedStatement pstmt = null;
		
		final String DB_URL = "jdbc:mysql://localhost/"+ dbUrlBit +"?autoReconnect=true&useSSL=false";
		try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

			pstmt = (PreparedStatement) con.prepareStatement(insertSQL);
			pstmt.setInt(1, idrecording);
			pstmt.setString(2, newdata);
			pstmt.setDate(3, (java.sql.Date) date);
			pstmt.setInt(4, huisid);
			pstmt.executeUpdate();
			bool = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					return bool;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return bool;
	}
	
	
}
 














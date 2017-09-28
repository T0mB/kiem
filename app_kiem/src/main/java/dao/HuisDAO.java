package main.java.dao; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.domain.Huis;
import main.java.domain.Sensor;

public class HuisDAO {

//	private static String databaseUrlBit = "";
//	private static final String DB_URL = "jdbc:mysql://localhost/"+ databaseUrlBit +"?autoReconnect=true&useSSL=false";
//	private static final String DB_URL = "jdbc:mysql://localhost/kiem?autoReconnect=true&useSSL=false";
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "pass";

	public HuisDAO() {
		try { 
			Class.forName(DB_DRIVER).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Huis> selectHuis(String dbUrlBit, String query){
		Statement stmt = null;
		List<Huis> HuisList = new ArrayList<Huis>();
		
		final String DB_URL = "jdbc:mysql://localhost/"+ dbUrlBit +"?autoReconnect=true&useSSL=false";
		System.out.println(DB_URL);
		try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME,
				DB_PASSWORD)) {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				
				int Huisid = rs.getInt("idhuis");
				
				Huis a = new Huis(Huisid);
				HuisList.add(a);
			} 

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return HuisList;
	}
	
	
	
	

}

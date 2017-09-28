package main.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.domain.Huis;
import main.java.domain.Sensor;

public class SensorDAO {

	// private static String databaseUrlBit = "";
	// private static final String DB_URL = "jdbc:mysql://localhost/" +
	// databaseUrlBit + "?autoReconnect=true&useSSL=false";
	// private static final String DB_URL =
	// "jdbc:mysql://localhost/kiem?autoReconnect=true&useSSL=false";
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "pass";

	public SensorDAO() {
		try {
			Class.forName(DB_DRIVER).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * TODO - add sensor to house - check if sensor doesnt exist yet; 1st add a
	 * search db func
	 */

	private Sensor searchSensor(int idsensor, String type, String dbUrlBit) {
		Sensor s = null;

		String sql = "select * from sensor where idsensor= ? AND type= ?";
		PreparedStatement pstmt = null;
		final String DB_URL = "jdbc:mysql://localhost/" + dbUrlBit + "?autoReconnect=true&useSSL=false";

		try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setInt(1, idsensor);
			pstmt.setString(2, type);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				int id = rs.getInt("idsensor");
				String typ = rs.getString("type");

				s = new Sensor(id, typ);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					return s;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return s;
	}

	public boolean CreateSensor(String dbUrlBit, String type) {
		boolean bool = false;

		String sql = "insert into sensor(idsensor, type) values(?,?)";
		PreparedStatement pstmt = null;
		final String DB_URL = "jdbc:mysql://localhost/" + dbUrlBit + "?autoReconnect=true&useSSL=false";
		System.out.println(DB_URL);
		try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
			Double d = Math.random() * 1000;
			System.out.println("" + d);
			int idsensor = d.intValue();
			if (searchSensor(idsensor, type.toLowerCase(), dbUrlBit) != null) {
				System.out.println("sensor already exists");
				return bool;
			} else {
				pstmt = (PreparedStatement) con.prepareStatement(sql);
				pstmt.setInt(1, idsensor);
				pstmt.setString(2, type.toLowerCase());
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					bool = true;
					return bool;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return bool;
	}

	public boolean deleteSensor(int idsensor, String dbUrlBit) {
		boolean bool = false;

		String sql = "Delete from sensor_has_huis where sensor_idsensor= '?'; Delete from sensor where idsensor = '?';";
		PreparedStatement pstmt = null;
		final String DB_URL = "jdbc:mysql://localhost/" + dbUrlBit + "?autoReconnect=true&useSSL=false";

		try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setInt(1, idsensor);
			pstmt.setInt(2, idsensor);
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

	public boolean updateSensor(int idsensor, String newtype, String dbUrlBit) {
		boolean bool = false;

		String sql = "update sensor set type=? " + "where idsensor= ?";
		PreparedStatement pstmt = null;
		final String DB_URL = "jdbc:mysql://localhost/" + dbUrlBit + "?autoReconnect=true&useSSL=false";

		try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, newtype);
			pstmt.setInt(2, idsensor);
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

	public boolean addSensorToHouse(String dbUrlBit, int idsensor, int idhuis) {
		boolean bool = false;
		String sql = "insert into sensor_has_huis(sensor_idsensor, huis_idhuis) values(?,?)";
		PreparedStatement pstmt = null;
		final String DB_URL = "jdbc:mysql://localhost/" + dbUrlBit + "?autoReconnect=true&useSSL=false";
		try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setInt(1, idsensor);
			pstmt.setInt(2, idhuis);
			pstmt.executeUpdate();

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

	public List<Sensor> getAllSensors(String dbUrlBit) {
		List<Sensor> sensorlist = new ArrayList<Sensor>();

		String sql = "select * from sensor";
		PreparedStatement pstmt = null;

		final String DB_URL = "jdbc:mysql://localhost/" + dbUrlBit + "?autoReconnect=true&useSSL=false";
		try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

			pstmt = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				int sensor = rs.getInt("idsensor");
				String type = rs.getString("type");
				Sensor s = new Sensor(sensor, type);

				sensorlist.add(s);
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

		return sensorlist;
	}

}

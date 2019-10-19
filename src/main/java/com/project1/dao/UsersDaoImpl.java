package com.project1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.project1.model.User;

public class UsersDaoImpl implements Dao {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static User getUserByUsername(String uName) {
		
		User user = new User();

		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ERS_USERS WHERE username='" + uName + "'";

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user.setUser_id(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setFirst_name(rs.getString(4));
				user.setLast_name(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setUser_role_id(rs.getInt(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public static String getHash(String uName, String pWord) {
		String hash = null;
		
		try(Connection conn = DriverManager.getConnection(url,  username, password)){
			
			String sql = "SELECT GET_USER_HASH('" + uName + "', '" + pWord + "') FROM DUAL";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				hash = rs.getString(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hash;
	}

}

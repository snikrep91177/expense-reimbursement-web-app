package com.project1.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.project1.model.JsonEmpReimbursement;
import com.project1.model.JsonManReimbursement;

public class ReimbursementDaoImpl implements Dao {
	final static Logger log = Logger.getLogger(ReimbursementDaoImpl.class);

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			log.error("Driver unavailable in ReimbursementDaoImpl", e);
			//e.printStackTrace();
		}
	}

	public static int getReimbTypeId(String type) {

		int typeId = 0;

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT reimb_type_id FROM ERS_REIMBURSEMENT_TYPE" + " WHERE reimb_type='" + type + "'";

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				typeId = rs.getInt(1);
			}
		} catch (SQLException e) {
			log.error("Error in select statement by id in getReimbTypeId", e);
			//e.printStackTrace();
		}
		return typeId;
	}

	public static int insertReimbursement(double amount, String description, int author, int type) {
		int status = 0;
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "{ call insert_reimb_null_id(?,?,?,?) }";

			CallableStatement cs = conn.prepareCall(sql);
			cs.setDouble(1, amount);
			cs.setString(2, description);
			cs.setInt(3, author);
			cs.setInt(4, type);
			status = cs.executeUpdate();

		} catch (SQLException e) {
			log.error("Error in callable statement insert_reimb_null_id in insertReimbursement", e);
			//e.printStackTrace();
		}
		return status;
	}

	public static ArrayList<JsonEmpReimbursement> getUserReimbursements(int usrId) {
		// creating an array to hold the records from
		// the future query
		ArrayList<JsonEmpReimbursement> reimbs = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			// query everything in the reimbursement table for a particular user
			String sql = "SELECT * FROM employee_reimb_view WHERE id='"
					+ usrId + "'";

			Statement ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(sql);

			// This while loop is responsible for loading up the
			// arrayList we created with the values we pulled from
			// from the query.
			// "rs.next()" is a method used to access each record
			// inside of a result set
			while (rs.next()) {
				reimbs.add(new JsonEmpReimbursement(rs.getInt(1), rs.getDouble(2),
							rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
				// we use "recipe" here instead of "rs.getString(3)"
				// in short, we use "recipe", aka the column name,
				// instead of getting the "3rd column" in the table
			}
		} catch (SQLException e) {
			log.error("Error in SQL statement by id in getUserReimbursements", e);
			//e.printStackTrace();
		}
		return reimbs;
	}

	public static ArrayList<JsonManReimbursement> getAllJsonReimbursements() {
		// creating an array to hold the records from
		// the future query
		ArrayList<JsonManReimbursement> reimbs = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			// query everything in the reimbursement table for a particular user
			String sql = "SELECT * FROM manager_reimb_view";

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			// This while loop is responsible for loading up the
			// arrayList we created with the values we pulled from
			// from the query.
			// "rs.next()" is a method used to access each record
			// inside of a result set
			while (rs.next()) {
				reimbs.add(new JsonManReimbursement(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDate(4),
						rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
				// we use "recipe" here instead of "rs.getString(3)"
				// in short, we use "recipe", aka the column name,
				// instead of getting the "3rd column" in the table
			}
		} catch (SQLException e) {
			log.error("Error in SQL prepared statement in getAllJsonReimbursements", e);
			//e.printStackTrace();
		}
		return reimbs;
	}

	public static void updateReimbStatus(int status, int resolver, int id) {
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			// query everything in the reimbursement table for a particular user
			String sql = "UPDATE ERS_REIMBURSEMENT SET reimb_status_id=?, resolver=?, resolved=CURRENT_TIMESTAMP WHERE reimb_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, status);
			ps.setInt(2, resolver);
			ps.setInt(3, id);

			status = ps.executeUpdate();

		} catch (SQLException e) {
			log.error("Error in update sql statement in updateReimbStatus", e);
			//e.printStackTrace();
		}
		//return status;
	}

	public static ArrayList<JsonManReimbursement> getPending() {
		ArrayList<JsonManReimbursement> reimbs = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			// query everything in the reimbursement table for a particular user
			String sql = "SELECT * FROM manager_reimb_view WHERE status=?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, "pending");

			ResultSet rs = ps.executeQuery();

			// This while loop is responsible for loading up the
			// arrayList we created with the values we pulled from
			// from the query.
			// "rs.next()" is a method used to access each record
			// inside of a result set
			while (rs.next()) {
				reimbs.add(new JsonManReimbursement(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDate(4),
						rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
				// we use "recipe" here instead of "rs.getString(3)"
				// in short, we use "recipe", aka the column name,
				// instead of getting the "3rd column" in the table
			}
		} catch (SQLException e) {
			log.error("Error in select statement by status in getPending", e);
			//e.printStackTrace();
		}
		return reimbs;
	}

	public static ArrayList<JsonManReimbursement> getApproved() {
		ArrayList<JsonManReimbursement> reimbs = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			// query everything in the reimbursement table for a particular user
			String sql = "SELECT * FROM manager_reimb_view WHERE status=?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, "approved");

			ResultSet rs = ps.executeQuery();

			// This while loop is responsible for loading up the
			// arrayList we created with the values we pulled from
			// from the query.
			// "rs.next()" is a method used to access each record
			// inside of a result set
			while (rs.next()) {
				reimbs.add(new JsonManReimbursement(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDate(4),
						rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
				// we use "recipe" here instead of "rs.getString(3)"
				// in short, we use "recipe", aka the column name,
				// instead of getting the "3rd column" in the table
			}
		} catch (SQLException e) {
			log.error("Error in select statement by status in getApproved", e);
			//e.printStackTrace();
		}
		return reimbs;
	}

	public static ArrayList<JsonManReimbursement> getDenied() {
		ArrayList<JsonManReimbursement> reimbs = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			// query everything in the reimbursement table for a particular user
			String sql = "SELECT * FROM manager_reimb_view WHERE status=?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, "denied");

			ResultSet rs = ps.executeQuery();

			// This while loop is responsible for loading up the
			// arrayList we created with the values we pulled from
			// from the query.
			// "rs.next()" is a method used to access each record
			// inside of a result set
			while (rs.next()) {
				reimbs.add(new JsonManReimbursement(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDate(4),
						rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
				// we use "recipe" here instead of "rs.getString(3)"
				// in short, we use "recipe", aka the column name,
				// instead of getting the "3rd column" in the table
			}
		} catch (SQLException e) {
			log.error("Error in select statement by status in getDenied", e);
			//e.printStackTrace();
		}
		return reimbs;
	}

}

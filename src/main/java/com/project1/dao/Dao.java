package com.project1.dao;

import com.project1.model.User;

public interface Dao {
	
	public static String url = "jdbc:oracle:thin:@revaturedb.c39ybozzntjs.us-east-2.rds.amazonaws.com:1521:ORCL";
	public static String username = "mgperkins1";
	public static String password = "p4ssw0rd";

	// CRUD METHODS ONLY

	// CREATE
	/*
	 * public int insertUser(User user);
	 * 
	 * // READ public List<User> selectAllUsers();
	 * 
	 * public User selectUserById(int id);
	 */

	//public User selectUserByUsername(String userName);

	/*
	 * public List<User> selectUserByBounty(int bounty);
	 * 
	 * // UPDATE public int updateUser(User sh);
	 * 
	 * // DELETE public void joinExamplePrint(String name);
	 * 
	 * public int deleteUser(User sh);
	 */

}

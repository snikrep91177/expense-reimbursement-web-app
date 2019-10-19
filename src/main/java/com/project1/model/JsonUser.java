package com.project1.model;

public class JsonUser {

	private int user_id;
	private String username;
	private String first_name;
	private String last_name;
	private String email;
	private int user_role_id;

	public JsonUser() {

	}
	
	

	/**
	 * @param first_name
	 * @param last_name
	 */
	public JsonUser(String first_name, String last_name) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
	}



	/**
	 * @param user_id
	 * @param username
	 * @param password
	 * @param first_name
	 * @param last_name
	 * @param email
	 */
	public JsonUser(int user_id, String username, String first_name, String last_name, String email) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
	}

	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the user_role_id
	 */
	public int getUser_role_id() {
		return user_role_id;
	}

	/**
	 * @param user_role_id the user_role_id to set
	 */
	public void setUser_role_id(int user_role_id) {
		this.user_role_id = user_role_id;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", first_name=" + first_name + ", last_name="
				+ last_name + ", email=" + email + "]";
	}
}

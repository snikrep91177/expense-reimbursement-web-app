package com.project1.service;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.project1.dao.UsersDaoImpl;
import com.project1.model.User;

public class UserService {
	
	final static Logger log = Logger.getLogger(UserService.class);

	@Test
	public static boolean validateLogin(String uName, String pWord) {

		boolean isMatch = false;

		String hash = UsersDaoImpl.getHash(uName, pWord);
		User user = UsersDaoImpl.getUserByUsername(uName);
		
//---------------------------------------------------JUNIT TEST------------------------------------------------------------------------------------------
		/assertEquals("Test conditional statement below", true, hash.contentEquals(user.getPassword()));
		if (hash.equals(user.getPassword())) {
			isMatch = true;
			log.info("Validation successfull");
		}
		return isMatch;
	}
	
	public static User getUser(String uName) {
		
		log.info("Successfully retrieved user");
		return UsersDaoImpl.getUserByUsername(uName);
	}
}

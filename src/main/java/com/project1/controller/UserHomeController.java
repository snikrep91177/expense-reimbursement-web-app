package com.project1.controller;

import javax.servlet.http.HttpServletRequest;

import com.project1.model.User;

public class UserHomeController {
	
	public static String toUserHome(HttpServletRequest request) {
		String uri = null;
		
		User user = (User) request.getSession().getAttribute("loggedUser");
		if(user.getUser_role_id() == 1) {
			uri = "/html/employeeHome.html";			
		} else {
			uri = "/html/managerHome.html";
		}
		return uri;
	}
}

package com.project1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.model.GenericResponse;
import com.project1.model.User;
import com.project1.service.UserService;

public class LoginController {

	public static String login(HttpServletRequest request) 
			throws ServletException, IOException {
		
		if (!request.getMethod().equals("POST")) {
			return "/html/index.html";
		}
		
		String uName = request.getParameter("username");
		String pWord = request.getParameter("password");
		
		boolean isValid = UserService.validateLogin(uName, pWord);
		
		if(isValid) {
			User user = UserService.getUser(uName);
			request.getSession().setAttribute("loggedUser", user);
		} else {
			return "/html/index.html";
		}
		return "/userHome.do";
	}
	
	public static void logout(HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException, IOException {
		boolean isInvalidated = false;
		
		try {
			request.getSession().invalidate();
			isInvalidated = true;
		}catch (IllegalStateException e) { 
			e.printStackTrace();
		}
		
		GenericResponse resp = new GenericResponse(isInvalidated);
		response.getWriter().write(new ObjectMapper().writeValueAsString(resp));
	}
}

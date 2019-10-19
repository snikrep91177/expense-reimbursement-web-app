package com.project1.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.model.JsonUser;
import com.project1.model.User;

public class JsonUserController {

	public static void getUser(HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException, IOException {
		User user = (User) request.getSession().getAttribute("loggedUser");
		if(user == null) {
			response.setStatus(400);
			return;
		}
		
		JsonUser juser = new JsonUser();
		juser.setEmail(user.getEmail());
		juser.setFirst_name(user.getFirst_name());
		juser.setLast_name(user.getLast_name());
		juser.setUser_id(user.getUser_id());
		juser.setUser_role_id(user.getUser_role_id());
		juser.setUsername(user.getUsername());
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(juser));
	}

}

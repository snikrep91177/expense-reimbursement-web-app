package com.project1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {

	public static String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		switch (request.getRequestURI()) {
		case "/Project1/login.do":
			return LoginController.login(request);
		case "/Project1/userHome.do":
			return UserHomeController.toUserHome(request);
		default:
			return "/html/index.html";
		}
	}
}

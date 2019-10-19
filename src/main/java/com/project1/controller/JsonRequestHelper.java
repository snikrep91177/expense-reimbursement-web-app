package com.project1.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

public class JsonRequestHelper {

	public static void process(HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException, IOException {
		
		switch (request.getRequestURI()) {
		case "/Project1/user.json":
			JsonUserController.getUser(request, response);
			break;
		case "/Project1/submitReimb.json":
			JsonReimbController.submitReimb(request, response);
			break;
		case "/Project1/retrieveReimb.json":
			JsonReimbController.retrieveReimb(request, response);
			break;
		case "/Project1/retrieveAllReimb.json":
			JsonReimbController.retrieveAllReimb(request, response);
			break;
		case "/Project1/updateReimbStatus.json":
			JsonReimbController.updateReimbStatus(request, response);
			break;
		case "/Project1/getPendingReimb.json":
			JsonReimbController.getPending(request,response);
			break;
		case "/Project1/getApprovedReimb.json":
			JsonReimbController.getApproved(request,response);
			break;
		case "/Project1/getDeniedReimb.json":
			JsonReimbController.getDenied(request,response);
			break;
		case "/Project1/logout.json":
			LoginController.logout(request, response);
			break;
		}
	}
}

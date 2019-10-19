package com.project1.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.model.GenericResponse;
import com.project1.model.JsonEmpReimbursement;
import com.project1.model.JsonManReimbursement;
import com.project1.model.User;
import com.project1.service.ReimbursementService;

import util.Lumberjack;

public class JsonReimbController {

	static Lumberjack lj = Lumberjack.getInstance();

	public static void submitReimb(HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException, IOException {
		lj.info("JsonReimbController.submitReimb(" + request + "," + response + ") called");
		User user = (User) request.getSession().getAttribute("loggedUser");
		int authId = user.getUser_id();

		// create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();

		// read JSON like DOM Parser
		JsonNode rootNode = objectMapper.readTree(request.getReader());
		String amount = rootNode.path("amount").asText();
		int type = rootNode.path("reimbType").asInt();
		String description = rootNode.path("description").asText();

		boolean success = ReimbursementService.inputReimb(amount, type, description, authId);

		GenericResponse resp = new GenericResponse(success);

		response.getWriter().write(new ObjectMapper().writeValueAsString(resp));

	}

	public static void retrieveReimb(HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException, IOException {
		lj.info("JsonReimbController.retrieveReimb(" + request + "," + response + ") called");
		User user = (User) request.getSession().getAttribute("loggedUser");
		if (user == null) {
			response.setStatus(400);
			return;
		}
		int usrId = user.getUser_id();
		ArrayList<JsonEmpReimbursement> reimbs = new ArrayList<>();
		reimbs = ReimbursementService.fetchReimbursements(usrId);
		reimbs.toArray(new JsonEmpReimbursement[0]);

		response.getWriter().write(new ObjectMapper().writeValueAsString(reimbs.toArray(new JsonEmpReimbursement[0])));
	}

	public static void retrieveAllReimb(HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException, IOException {

		ArrayList<JsonManReimbursement> reimbs = new ArrayList<>();
		reimbs = ReimbursementService.fetchAllReimbursements();
		System.out.println(reimbs);
		reimbs.toArray(new JsonManReimbursement[0]);

		response.getWriter().write(new ObjectMapper().writeValueAsString(reimbs.toArray(new JsonManReimbursement[0])));
	}

	public static void updateReimbStatus(HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException, IOException {

		User user = (User) request.getSession().getAttribute("loggedUser");
		int resolver = user.getUser_id();
		
		// create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();

		// read JSON like DOM Parser
		JsonNode rootNode = objectMapper.readTree(request.getReader());
		int status = rootNode.path("reimbStatus").asInt();
		int id = rootNode.path("reimbId").asInt();

		ReimbursementService.updateReimbStatus(status, resolver, id);
	}

	public static void getPending(HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException, IOException {
		
		ArrayList<JsonManReimbursement> reimbs = new ArrayList<>();
		reimbs = ReimbursementService.getPending();
		reimbs.toArray(new JsonManReimbursement[0]);

		response.getWriter().write(new ObjectMapper().writeValueAsString(reimbs.toArray(new JsonManReimbursement[0])));
	}

	public static void getApproved(HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException, IOException {
		
		ArrayList<JsonManReimbursement> reimbs = new ArrayList<>();
		reimbs = ReimbursementService.getApproved();
		reimbs.toArray(new JsonManReimbursement[0]);

		response.getWriter().write(new ObjectMapper().writeValueAsString(reimbs.toArray(new JsonManReimbursement[0])));
	}

	public static void getDenied(HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException, IOException {
		
		ArrayList<JsonManReimbursement> reimbs = new ArrayList<>();
		reimbs = ReimbursementService.getDenied();
		reimbs.toArray(new JsonManReimbursement[0]);

		response.getWriter().write(new ObjectMapper().writeValueAsString(reimbs.toArray(new JsonManReimbursement[0])));
	}

}

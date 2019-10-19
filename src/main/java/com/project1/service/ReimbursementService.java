package com.project1.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.project1.dao.ReimbursementDaoImpl;
import com.project1.model.JsonEmpReimbursement;
import com.project1.model.JsonManReimbursement;

public class ReimbursementService {
	
	final static Logger log = Logger.getLogger(ReimbursementService.class);

	@Test
	public static boolean inputReimb(String amount, int type, String description, int authId) {

		boolean success = false;

		Double damount = Double.parseDouble(amount);
		
//-----------------------------------------------------JUNIT TEST---------------------------------------------------------------------------------------------		
		//assertEquals("Testing inserReimbursement", 1, ReimbursementDaoImpl.insertReimbursement(damount, description, authId, type));
		
		int status = ReimbursementDaoImpl.insertReimbursement(damount, description, authId, type);

		if (status > 0) {
			success = true;
			log.info("Reimbursement inserted successfully");
		}
		return success;
	}

	public static ArrayList<JsonEmpReimbursement> fetchReimbursements(int usrId) {
		ArrayList<JsonEmpReimbursement> reimbs = new ArrayList<>();
		reimbs = ReimbursementDaoImpl.getUserReimbursements(usrId);
		log.info("Successfully retrieved reimbursements");
		return reimbs;
	}

	public static ArrayList<JsonManReimbursement> fetchAllReimbursements() {
		ArrayList<JsonManReimbursement> reimbs = new ArrayList<>();
		reimbs = ReimbursementDaoImpl.getAllJsonReimbursements();
		log.info("Successfully retrieved all reimbursements");
		return reimbs;
	}

	public static void updateReimbStatus(int status, int resolver, int id) {
		
		ReimbursementDaoImpl.updateReimbStatus(status, resolver, id);
		log.info("Successfully updated reimbursement status");
	}

	public static ArrayList<JsonManReimbursement> getPending() {
		ArrayList<JsonManReimbursement> reimbs = new ArrayList<>();
		reimbs = ReimbursementDaoImpl.getPending();
		log.info("Successfully retrieved pending reimbursements");
		return reimbs;
	}

	public static ArrayList<JsonManReimbursement> getApproved() {
		ArrayList<JsonManReimbursement> reimbs = new ArrayList<>();
		reimbs = ReimbursementDaoImpl.getApproved();
		log.info("Successfully retrieved approved reimbursements");
		return reimbs;
	}

	public static ArrayList<JsonManReimbursement> getDenied() {
		ArrayList<JsonManReimbursement> reimbs = new ArrayList<>();
		reimbs = ReimbursementDaoImpl.getDenied();
		log.info("Successfully retrieved denied reimbursements");
		return reimbs;
	}

}

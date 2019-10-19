package com.project1.model;

import java.util.Date;

public class JsonManReimbursement {

	private int reimb_id;
	private String author;
	private double amount;
	private Date submitted;
	private Date resolved;
	private String description;
	private String resolver;
	private String reimb_status;
	private String reimb_type;

	public JsonManReimbursement() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param reimb_id
	 * @param author
	 * @param amount
	 * @param submitted
	 * @param resolved
	 * @param description
	 * @param resolver
	 * @param reimb_status
	 * @param reimb_type
	 */
	public JsonManReimbursement(int reimb_id, String author, double amount, Date submitted, Date resolved,
			String description, String resolver, String reimb_status, String reimb_type) {
		super();
		this.reimb_id = reimb_id;
		this.author = author;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.resolver = resolver;
		this.reimb_status = reimb_status;
		this.reimb_type = reimb_type;
	}

	/**
	 * @return the reimb_id
	 */
	public int getReimb_id() {
		return reimb_id;
	}

	/**
	 * @param reimb_id the reimb_id to set
	 */
	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the submitted
	 */
	public Date getSubmitted() {
		return submitted;
	}

	/**
	 * @param submitted the submitted to set
	 */
	public void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}

	/**
	 * @return the resolved
	 */
	public Date getResolved() {
		return resolved;
	}

	/**
	 * @param resolved the resolved to set
	 */
	public void setResolved(Date resolved) {
		this.resolved = resolved;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the resolver
	 */
	public String getResolver() {
		return resolver;
	}

	/**
	 * @param resolver the resolver to set
	 */
	public void setResolver(String resolver) {
		this.resolver = resolver;
	}

	/**
	 * @return the reimb_status
	 */
	public String getReimb_status() {
		return reimb_status;
	}

	/**
	 * @param reimb_status the reimb_status to set
	 */
	public void setReimb_status(String reimb_status) {
		this.reimb_status = reimb_status;
	}

	/**
	 * @return the reimb_type
	 */
	public String getReimb_type() {
		return reimb_type;
	}

	/**
	 * @param reimb_type the reimb_type to set
	 */
	public void setReimb_type(String reimb_type) {
		this.reimb_type = reimb_type;
	}

	@Override
	public String toString() {
		return "JsonManReimbursement [reimb_id=" + reimb_id + ", author=" + author + ", amount=" + amount
				+ ", submitted=" + submitted + ", resolved=" + resolved + ", description=" + description + ", resolver="
				+ resolver + ", reimb_status=" + reimb_status + ", reimb_type=" + reimb_type + "]";
	}
	
	

}

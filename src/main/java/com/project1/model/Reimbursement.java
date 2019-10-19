package com.project1.model;

import java.util.Date;

public class Reimbursement {
	
	private int reimb_id;
	private double amount;
	private Date submitted;
	private Date resolved;
	private String description;
	private String receipt;
	private int author;
	private int resolver;
	private int reimb_status_id;
	private int reimb_type_id;

	public Reimbursement() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param reimb_id
	 * @param amount
	 * @param submitted
	 * @param resolved
	 * @param description
	 * @param receipt
	 * @param author
	 * @param resolver
	 * @param reimb_status_id
	 * @param reimb_type_id
	 */
	public Reimbursement(int reimb_id, double amount, Date submitted, Date resolved, String description,
			String receipt, int author, int resolver, int reimb_status_id, int reimb_type_id) {
		super();
		this.reimb_id = reimb_id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.reimb_status_id = reimb_status_id;
		this.reimb_type_id = reimb_type_id;
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
	 * @return the receipt
	 */
	public String getReceipt() {
		return receipt;
	}

	/**
	 * @param receipt the receipt to set
	 */
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	/**
	 * @return the author
	 */
	public int getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(int author) {
		this.author = author;
	}

	/**
	 * @return the resolver
	 */
	public int getResolver() {
		return resolver;
	}

	/**
	 * @param resolver the resolver to set
	 */
	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	/**
	 * @return the reimb_status_id
	 */
	public int getReimb_status_id() {
		return reimb_status_id;
	}

	/**
	 * @param reimb_status_id the reimb_status_id to set
	 */
	public void setReimb_status_id(int reimb_status_id) {
		this.reimb_status_id = reimb_status_id;
	}

	/**
	 * @return the reimb_type_id
	 */
	public int getReimb_type_id() {
		return reimb_type_id;
	}

	/**
	 * @param reimb_type_id the reimb_type_id to set
	 */
	public void setReimb_type_id(int reimb_type_id) {
		this.reimb_type_id = reimb_type_id;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimb_id=" + reimb_id + ", amount=" + amount + ", submitted=" + submitted + ", resolved="
				+ resolved + ", description=" + description + ", receipt=" + receipt + ", author=" + author
				+ ", resolver=" + resolver + ", reimb_status_id=" + reimb_status_id + ", reimb_type_id=" + reimb_type_id
				+ "]";
	}
	
	

}

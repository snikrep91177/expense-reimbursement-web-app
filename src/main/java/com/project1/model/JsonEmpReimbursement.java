package com.project1.model;

import java.util.Date;

public class JsonEmpReimbursement {

	private int id;
	private double amount;
	private Date submitted;
	private Date resolved;
	private String description;
	//private String receipt;
	private String resolver;
	private String reimb_statu;
	private String reimb_type;
	
	public JsonEmpReimbursement() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param amount
	 * @param submitted
	 * @param resolved
	 * @param description
	 * @param resolver
	 * @param reimb_statu
	 * @param reimb_type
	 */
	public JsonEmpReimbursement(int id, double amount, Date submitted, Date resolved, String description,
			String resolver, String reimb_statu, String reimb_type) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.resolver = resolver;
		this.reimb_statu = reimb_statu;
		this.reimb_type = reimb_type;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the reimb_statu
	 */
	public String getReimb_statu() {
		return reimb_statu;
	}

	/**
	 * @param reimb_statu the reimb_statu to set
	 */
	public void setReimb_statu(String reimb_statu) {
		this.reimb_statu = reimb_statu;
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
		return "JsonEmpReimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved="
				+ resolved + ", description=" + description + ", resolver=" + resolver + ", reimb_statu=" + reimb_statu
				+ ", reimb_type=" + reimb_type + "]";
	}

	
}

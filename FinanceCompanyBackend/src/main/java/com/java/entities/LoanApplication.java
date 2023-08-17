package com.java.entities;

import java.util.Date;

public class LoanApplication {
	private int applicationNumber;
	private int customerId;
	private int loanId;
	private double loanAmount;
	private String loanStatus;
	private Date applyDate;
	private int loanTenure;
	private double loanEmi;
	
	public LoanApplication() {}

	public LoanApplication(int applicationNumber, int customerId, int loanId, double loanAmount, String loanStatus,
			Date applyDate, int loanTenure, double loanEmi) {
		this.applicationNumber = applicationNumber;
		this.customerId = customerId;
		this.loanId = loanId;
		this.loanAmount = loanAmount;
		this.loanStatus = loanStatus;
		this.applyDate = applyDate;
		this.loanTenure = loanTenure;
		this.loanEmi = loanEmi;
	}

	public int getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(int applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public int getLoanTenure() {
		return loanTenure;
	}

	public void setLoanTenure(int loanTenure) {
		this.loanTenure = loanTenure;
	}

	public double getLoanEmi() {
		return loanEmi;
	}

	public void setLoanEmi(double loanEmi) {
		this.loanEmi = loanEmi;
	}
	
}

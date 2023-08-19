package com.java.entities;

import java.util.Date;

public class LoanApplication {
	private long applicationNumber;
	private long customerId;
	private int loanId;
	private double loanAmount;
	private String loanStatus;
	private Date applyDate;
	private int loanTenure;
	private double loanEmi;
	
	public LoanApplication() {}

	public LoanApplication(long applicationNumber, long customerId, int loanId, double loanAmount, String loanStatus,
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

	public long getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(long applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int l) {
		this.loanId = l;
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

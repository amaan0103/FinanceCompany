package com.java.entities;

import java.sql.Blob;
import java.util.Date;

public class FullApplication {
	private long applicationNumber;
	private long customerId;
	private int loanId;
	private double loanAmount;
	private String loanStatus;
	private Date applyDate;
	private int loanTenure;
	private double loanEmi;
	private String documents;
	
	public FullApplication() {}

	public FullApplication(long applicationNumber, long customerId, int loanId, double loanAmount, String loanStatus,
			Date applyDate, int loanTenure, double loanEmi, String documents) {
		this.applicationNumber = applicationNumber;
		this.customerId = customerId;
		this.loanId = loanId;
		this.loanAmount = loanAmount;
		this.loanStatus = loanStatus;
		this.applyDate = applyDate;
		this.loanTenure = loanTenure;
		this.loanEmi = loanEmi;
		this.documents = documents;
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

	public String getDocuments() {
		return documents;
	}

	public void setDocuments(String documents) {
		this.documents = documents;
	}
	
	
	
}

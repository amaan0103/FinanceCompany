package com.java.entities;

import java.sql.Blob;
import java.util.Date;

public class FullApplication {
	private int applicationNumber;
	private int customerId;
	private int loanId;
	private double loanAmount;
	private String loanStatus;
	private Date applyDate;
	private int loanTenure;
	private double loanEmi;
	private Blob documents;
	
	public FullApplication() {}

	public FullApplication(int applicationNumber, int customerId, int loanId, double loanAmount, String loanStatus,
			Date applyDate, int loanTenure, double loanEmi, Blob documents) {
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

	public Blob getDocuments() {
		return documents;
	}

	public void setDocuments(Blob documents) {
		this.documents = documents;
	}
	
	
	
}

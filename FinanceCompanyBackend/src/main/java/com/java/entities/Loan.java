package com.java.entities;

public class Loan {
	private int loanId;
	private String loanType;
	private double interestRate;
	
	public Loan() {}

	public Loan(int loanId, String loanType, double interestRate) {
		this.loanId = loanId;
		this.loanType = loanType;
		this.interestRate = interestRate;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
}

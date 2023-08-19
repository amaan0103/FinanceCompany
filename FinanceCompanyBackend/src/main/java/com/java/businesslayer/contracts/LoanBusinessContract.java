package com.java.businesslayer.contracts;

import java.util.List;

import com.java.entities.Loan;

public interface LoanBusinessContract {
	boolean addLoan(Loan loan) throws Exception;
	List<Loan> getLoanDetails() throws Exception;
}

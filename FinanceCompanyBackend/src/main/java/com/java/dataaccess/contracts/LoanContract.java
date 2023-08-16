package com.java.dataaccess.contracts;

import java.util.List;

import com.java.entities.Loan;

public interface LoanContract {
	boolean addLoan(Loan loan) throws Exception ;
	List<Loan> getAllLoans() throws Exception ;
	boolean removeLoan(int loan_id) throws Exception ;
}

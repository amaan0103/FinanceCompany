package com.java.businesslayer.implementations;

import java.util.List;

import com.java.businesslayer.contracts.LoanBusinessContract;
import com.java.dataaccess.contracts.LoanContract;
import com.java.entities.Loan;

public class LoanComponent<TContract, TImplementation> implements LoanBusinessContract {

	TContract dao;
	
	public LoanComponent(TContract dao) {
		super();
		this.dao = dao;
	}

	@Override
	public boolean addLoan(Loan loan) throws Exception {
		// TODO Auto-generated method stub
		return ((LoanContract) dao).addLoan(loan);
	}

	@Override
	public List<Loan> getLoanDetails() throws Exception {
		// TODO Auto-generated method stub
		return ((LoanContract) dao).getAllLoans();
	}

}

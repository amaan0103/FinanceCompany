package com.java.businesslayer.implementations;

import java.util.List;

import com.java.businesslayer.contracts.CustomerBusinessContract;
import com.java.dataaccess.contracts.DocumentContract;
import com.java.dataaccess.contracts.LoanApplicationContract;
import com.java.dataaccess.contracts.LoanContract;
import com.java.entities.Documents;
import com.java.entities.Loan;
import com.java.entities.LoanApplication;

public class CustomerComponent<TContract, TImplementation> implements CustomerBusinessContract {

	TContract dao;
	
	public CustomerComponent(TContract dao) {
		this.dao = dao;
	}



	@Override
	public List<Loan> getLoanDetails() throws Exception {
		// TODO Auto-generated method stub
		return ((LoanContract) dao).getAllLoans();
	}



	@Override
	public boolean addApplication(LoanApplication app) throws Exception {
		// TODO Auto-generated method stub
		return ((LoanApplicationContract) dao).addApplication(app);
	}



	@Override
	public boolean uploadDocuments(Documents docs) throws Exception {
		// TODO Auto-generated method stub
		return ((DocumentContract) dao).addDocument(docs);
	}



	@Override
	public boolean cancelLoan(int application_number) throws Exception {
		// TODO Auto-generated method stub
		return ((LoanApplicationContract) dao).removeApplication(application_number);
	}



	@Override
	public boolean deleteDocuments(int application_number) throws Exception {
		// TODO Auto-generated method stub
		return ((DocumentContract) dao).removeDocuments(application_number);
	}

}

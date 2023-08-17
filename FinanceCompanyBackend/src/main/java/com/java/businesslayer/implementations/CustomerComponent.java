package com.java.businesslayer.implementations;

import java.util.List;

import com.java.businesslayer.contracts.CustomerBusinessContract;
import com.java.dataaccess.contracts.DocumentContract;
import com.java.dataaccess.contracts.LoanApplicationContract;
import com.java.dataaccess.contracts.LoanContract;
import com.java.dataaccess.implementations.DocumentDataAccess;
import com.java.entities.Documents;
import com.java.entities.FullApplication;
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
	public boolean addApplication(FullApplication app) throws Exception {
		// TODO Auto-generated method stub
		LoanApplication loanApplication = new LoanApplication(app.getApplicationNumber(),app.getCustomerId(),app.getLoanId(),
		app.getLoanAmount(),app.getLoanStatus(),app.getApplyDate(),app.getLoanTenure(),app.getLoanEmi());
		boolean flag = ((LoanApplicationContract) dao).addApplication(loanApplication);
		if(!flag)	return false;
		Documents docs = new Documents(app.getApplicationNumber(),app.getDocuments());
		DocumentContract docdao = new DocumentDataAccess();
		flag = docdao.addDocument(docs);
		return flag;
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

	@Override
	public List<FullApplication> getAllApplications(int customer_id) throws Exception {
		// TODO Auto-generated method stub
		return  ((LoanApplicationContract) dao).getApplicationsById(customer_id);
	}

}

package com.java.businesslayer.implementations;

import java.util.Collections;
import java.util.Comparator;
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
		app.setApplicationNumber(System.currentTimeMillis());
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
	public boolean cancelLoan(long applicationNumber) throws Exception {
		// TODO Auto-generated method stub
		return ((LoanApplicationContract) dao).removeApplication(applicationNumber);
	}



	@Override
	public boolean deleteDocuments(long applicationNumber) throws Exception {
		// TODO Auto-generated method stub
		return ((DocumentContract) dao).removeDocuments(applicationNumber);
	}

	@Override
	public List<FullApplication> getAllApplications(long customerId) throws Exception {
		// TODO Auto-generated method stub
		return  ((LoanApplicationContract) dao).getApplicationsById(customerId);
	}
	
	@Override
	public List<FullApplication> getAllApplications(long customerId, int sort) throws Exception {
		// TODO Auto-generated method stub
		List<FullApplication> list = ((LoanApplicationContract) dao).getApplicationsById(customerId);
		Collections.sort(list, new Comparator<FullApplication>() {

			@Override
			public int compare(FullApplication o1, FullApplication o2) {
				// TODO Auto-generated method stub
				if(sort==1)	return o1.getLoanId()-o2.getLoanId();
				else if(sort==2)	return o1.getApplyDate().compareTo(o2.getApplyDate());
				else if(sort==3)	return o1.getLoanTenure()-o2.getLoanTenure();
				else	return (int) (o1.getLoanAmount()-o2.getLoanAmount());
			}
			
		});
		return list;
	}
}

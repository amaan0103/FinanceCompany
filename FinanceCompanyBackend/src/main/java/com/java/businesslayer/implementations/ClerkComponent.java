package com.java.businesslayer.implementations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.java.dataaccess.contracts.*;
import com.java.dataaccess.implementations.*;

import com.java.businesslayer.contracts.ClerkBusinessContract;
import com.java.dataaccess.contracts.CustomerContract;
import com.java.dataaccess.contracts.LoanApplicationContract;
import com.java.entities.Customer;
import com.java.entities.Documents;
import com.java.entities.FullApplication;
import com.java.entities.Loan;
import com.java.entities.LoanApplication;

public class ClerkComponent<TContract,Timplementation> implements ClerkBusinessContract {

	TContract dao;
	
	public ClerkComponent(TContract dao) {
		this.dao = dao;
	}

	@Override
	public boolean addCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		return ((CustomerContract) dao).addCustomer(customer);
	}

	@Override
	public List<Customer> getAllCustomers() throws Exception {
		// TODO Auto-generated method stub
		return ((CustomerContract) dao).getAllCustomers();
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
	public List<FullApplication> getAllApplications() throws Exception {
		// TODO Auto-generated method stub
		return  ((LoanApplicationContract) dao).getAllApplications();
	}

	@Override
	public boolean addLoan(Loan loan) throws Exception {
		// TODO Auto-generated method stub
		return ((LoanContract) dao).addLoan(loan);
	}

}

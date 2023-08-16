package com.java.businesslayer.implementations;

import java.util.List;

import com.java.businesslayer.contracts.ClerkBusinessContract;
import com.java.dataaccess.contracts.CustomerContract;
import com.java.dataaccess.contracts.LoanApplicationContract;
import com.java.dataaccess.contracts.LoanContract;
import com.java.entities.Customer;
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
	public boolean addApplication(LoanApplication app) throws Exception {
		// TODO Auto-generated method stub
		return ((LoanApplicationContract) dao).addApplication(app);
	}

	@Override
	public List<LoanApplication> getAllApplications() throws Exception {
		// TODO Auto-generated method stub
		return ((LoanApplicationContract) dao).getAllApplications();
	}

	@Override
	public List<Loan> getLoanDetails() throws Exception {
		// TODO Auto-generated method stub
		return ((LoanContract) dao).getAllLoans();
	}

}

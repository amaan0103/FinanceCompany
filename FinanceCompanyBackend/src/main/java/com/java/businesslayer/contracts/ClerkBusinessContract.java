package com.java.businesslayer.contracts;

import java.util.List;

import com.java.entities.Customer;
import com.java.entities.Loan;
import com.java.entities.LoanApplication;

public interface ClerkBusinessContract {
	boolean addCustomer(Customer customer) throws Exception;
	List<Customer> getAllCustomers() throws Exception;
	boolean addApplication(LoanApplication app) throws Exception;
	List<LoanApplication> getAllApplications() throws Exception;
	List<Loan> getLoanDetails() throws Exception;
}

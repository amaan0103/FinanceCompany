package com.java.businesslayer.contracts;

import java.util.List;

import com.java.entities.Customer;
import com.java.entities.FullApplication;
import com.java.entities.Loan;
import com.java.entities.LoanApplication;

public interface ClerkBusinessContract {
	boolean addCustomer(Customer customer) throws Exception;
	List<Customer> getAllCustomers() throws Exception;
	boolean addApplication(FullApplication app) throws Exception;
	List<FullApplication> getAllApplications() throws Exception;
}

package com.java.businesslayer.contracts;

import java.util.List;

import com.java.entities.Documents;
import com.java.entities.FullApplication;
import com.java.entities.Loan;
import com.java.entities.LoanApplication;

public interface CustomerBusinessContract {
	boolean addApplication(FullApplication app) throws Exception;
	boolean cancelLoan(long application_number) throws Exception;
	List<Loan> getLoanDetails() throws Exception;
	List<FullApplication> getAllApplications(long customer_id) throws Exception;
	boolean deleteDocuments(long application_number) throws Exception;
	List<FullApplication> getAllApplications(long customerId, int sort) throws Exception;
}

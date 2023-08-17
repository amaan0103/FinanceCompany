package com.java.businesslayer.contracts;

import java.util.List;

import com.java.entities.Documents;
import com.java.entities.Loan;
import com.java.entities.LoanApplication;

public interface CustomerBusinessContract {
	boolean addApplication(LoanApplication app) throws Exception;
	boolean uploadDocuments(Documents docs) throws Exception;
	boolean deleteDocuments(int application_number) throws Exception;
	boolean cancelLoan(int application_number) throws Exception;
	List<Loan> getLoanDetails() throws Exception;
}

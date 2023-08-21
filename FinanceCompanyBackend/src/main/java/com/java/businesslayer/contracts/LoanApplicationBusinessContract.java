package com.java.businesslayer.contracts;

import java.util.List;

import com.java.entities.FullApplication;

public interface LoanApplicationBusinessContract {
	boolean addApplication(FullApplication app) throws Exception;
	List<FullApplication> getAllApplications() throws Exception;
	List<FullApplication> getAllApplicationsSorted(int sort) throws Exception;
	List<FullApplication> getAllApplications(long customer_id) throws Exception;
	boolean cancelLoan(long application_number) throws Exception;
	List<FullApplication> getAllApplications(long customerId, int sort) throws Exception;
	boolean updataStatus(long application_number, String status) throws Exception;
	List<FullApplication> getStatus(String status) throws Exception;
}

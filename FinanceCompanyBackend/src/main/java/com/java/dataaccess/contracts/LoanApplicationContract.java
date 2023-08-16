package com.java.dataaccess.contracts;

import java.util.List;

import com.java.entities.LoanApplication;

public interface LoanApplicationContract {
	boolean addApplication(LoanApplication application) throws Exception ;
	List<LoanApplication> getAllApplications() throws Exception ;
	List<LoanApplication> getApplications(int customer_id) throws Exception ;
	boolean removeApplication(int application_number) throws Exception ;
	boolean approve(int application_number) throws Exception ;
	boolean reject(int application_number) throws Exception ;
}

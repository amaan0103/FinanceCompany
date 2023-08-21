package com.java.dataaccess.contracts;

import java.util.List;

import com.java.entities.FullApplication;
import com.java.entities.LoanApplication;

public interface LoanApplicationContract {
	boolean addApplication(LoanApplication application) throws Exception ;
	List<FullApplication> getAllApplications() throws Exception ;
	List<FullApplication> getApplicationsById(long customer_id) throws Exception ;
	boolean removeApplication(long application_number) throws Exception ;
	boolean approve(long application_number) throws Exception ;
	boolean reject(long application_number) throws Exception ;
	LoanApplication getApplication(long application_number) throws Exception;
	List<FullApplication> getStatus(String status) throws Exception;
}

package com.java.tests;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.java.dataaccess.implementations.DocumentDataAccess;
import com.java.dataaccess.implementations.LoanApplicationDataAccess;
import com.java.entities.Documents;
import com.java.entities.FullApplication;
import com.java.entities.LoanApplication;

public class LoanApplicationDataAccessTest {
	private static LoanApplicationDataAccess dao;
	
	public LoanApplicationDataAccessTest(){}
	
	@BeforeClass
	public static void initialize() throws SQLException, IOException, ClassNotFoundException {
		dao = new LoanApplicationDataAccess();
	}
	
	@Test
	public void insertTest() throws Exception {
		try {
		LoanApplication la = new LoanApplication(1L,1L,1,123,"approved",new java.sql.Date(System.currentTimeMillis()),12,1234);
		boolean val = dao.addApplication(la);
		Assert.assertTrue(val);
		}catch(Exception e) {	e.printStackTrace();}	
	}
	
	@Test
	public void NotNullTest() throws Exception {
		try {
		List<FullApplication> loanApps = dao.getAllApplications();
		Assert.assertNotNull(loanApps);
		}catch(Exception e) {	e.printStackTrace();}	
	}
	@Test
	public void emptyTest() throws Exception {
		try {
		List<FullApplication> loanApps = dao.getApplicationsById(123);
		Assert.assertEquals(loanApps, new ArrayList<>());
		}catch(Exception e) {	e.printStackTrace();}	
	}
}

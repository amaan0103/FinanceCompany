package com.java.tests;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.java.dataaccess.implementations.DocumentDataAccess;
import com.java.dataaccess.implementations.LoanApplicationDataAccess;
import com.java.entities.Documents;

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
		Documents doc = new Documents(999,null);
//		boolean value = dao.addDocument(doc);
//		Assert.assertTrue(value);
		}catch(Exception e) {	e.printStackTrace();}	
	}
}

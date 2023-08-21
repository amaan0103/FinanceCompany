package com.java.tests;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.java.dataaccess.implementations.CustomerDataAccess;
import com.java.dataaccess.implementations.DocumentDataAccess;
import com.java.entities.Documents;


public class DocumentDataAccessTest {
	private static DocumentDataAccess dao;
	
	public DocumentDataAccessTest(){}
	
	@BeforeClass
	public static void initialize() throws SQLException, IOException, ClassNotFoundException {
		dao = new DocumentDataAccess();
	}
	
	@Test
	public void insertTest() throws Exception {
		try {
		Documents doc = new Documents(999,null);
		boolean value = dao.addDocument(doc);
		Assert.assertTrue(value);
		}catch(Exception e) {	e.printStackTrace();}	
	}
	
	
	@Test
	public void NullTest() throws Exception {
		try {
		Documents docs = dao.getDocumentById(999);
		Assert.assertNull(docs);
		}catch(Exception e) {	e.printStackTrace();}	
	}
}

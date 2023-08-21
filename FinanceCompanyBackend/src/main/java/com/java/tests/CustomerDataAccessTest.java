package com.java.tests;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.*;

//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;

import com.java.dataaccess.implementations.CustomerDataAccess;
import com.java.entities.Customer;

public class CustomerDataAccessTest {
	private static CustomerDataAccess dao;
	
	public CustomerDataAccessTest() {}
	
	@BeforeClass
	public static void initialize() throws SQLException, IOException, ClassNotFoundException {
		dao = new CustomerDataAccess();
	}
	
	@Test
	public void insertTest() throws Exception {
		Customer customer = new Customer(2,"mmm","male","98765","gmail");
		try {
			boolean value = dao.addCustomer(customer);
			Assert.assertEquals(value, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getCustomerByIdTest() throws Exception{
		try {
			Customer value = dao.getCustomerById(1);
			Assert.assertNotNull(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void redundantCustomer() throws Exception {
		Customer customer = new Customer(1,"mmm","male","98765","gmail");
		try {
			boolean value = dao.addCustomer(customer);
			Assert.assertEquals(value, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

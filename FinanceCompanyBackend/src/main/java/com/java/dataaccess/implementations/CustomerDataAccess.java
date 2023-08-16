package com.java.dataaccess.implementations;

import com.java.dataaccess.contracts.CustomerContract;
import com.java.entities.Customer;

import java.io.*;
import java.sql.*;
import java.util.*;

public class CustomerDataAccess implements CustomerContract{
	
	Connection connectionInstance;
	PreparedStatement statement;
	ResultSet resultSet;
	FileReader reader;
	Properties properties;
	
	public CustomerDataAccess() throws SQLException, IOException {
		try {
			reader = new FileReader("C:\\FinanceCompany\\FinanceCompany\\FinanceCompanyBackend\\src\\main\\java\\com\\java\\dataaccess\\implementations\\db.properties");
			properties = new Properties();
			properties.load(reader);
			connectionInstance = createConnection(properties.getProperty("connectionString"),
					properties.getProperty("username"), properties.getProperty("password"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw e;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	private Connection createConnection(String connectionInfo, String userName, String password) throws SQLException {
		try {
			connectionInstance = DriverManager.getConnection(connectionInfo, userName, password);
			return connectionInstance;
		} catch (SQLException e) {
			throw e;
		} catch (Exception ex) {
			throw ex;
		}
	}
	private void closeConnection() throws SQLException {
		if (connectionInstance != null)
			connectionInstance.close();
	}
	
	@Override
	public boolean addCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		int result=0;
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("add_loan"));
			statement.setInt(1, customer.getCustomerId());
			statement.setString(2, customer.getCustomerName());
			statement.setString(3,customer.getGender());
			statement.setString(3,customer.getContact());
			result = statement.executeUpdate();
			closeConnection();
		}catch(Exception e) {
			throw e;
		}
		return result==0?false:true;
	}
	@Override
	public List<Customer> getAllCustomers() throws Exception {
		Customer customer = null;
		List<Customer> customers = new ArrayList<>();
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("get_all_loans"));
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				customer = new Customer();
				customer.setCustomerId(resultSet.getInt(1));
				customer.setCustomerName(resultSet.getString(2));
				customer.setGender(resultSet.getString(3));
				customer.setContact(resultSet.getString(4));
				customers.add(customer);
			}
		}catch(Exception e) {
			throw e;
		}
		return customers;
	}

}

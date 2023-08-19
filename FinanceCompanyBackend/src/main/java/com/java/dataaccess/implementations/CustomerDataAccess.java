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
	
	public CustomerDataAccess() throws SQLException, IOException, ClassNotFoundException {
		try {
			reader = new FileReader("C:\\FinanceCompany\\FinanceCompany\\FinanceCompanyBackend\\src\\main\\java\\com\\java\\dataaccess\\implementations\\db.properties");
			properties = new Properties();
			properties.load(reader);
			Class.forName(properties.getProperty("driver"));
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
			customer.setCustomerId(System.currentTimeMillis());
			statement = connectionInstance.prepareStatement(properties.getProperty("add_customer"));
			statement.setLong(1, customer.getCustomerId());
			statement.setString(2, customer.getCustomerName());
			statement.setString(3,customer.getGender());
			statement.setString(4,customer.getMobile());
			statement.setString(5,customer.getEmail());
			result = statement.executeUpdate();
			
		}catch(Exception e) {
			throw e;
		}
		finally {
//			closeConnection();
		}
		return result==0?false:true;
	}
	@Override
	public List<Customer> getAllCustomers() throws Exception {
		Customer customer = null;
		List<Customer> customers = new ArrayList<Customer>();
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("get_all_customers"));
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				customer = new Customer();
				customer.setCustomerId(resultSet.getLong(1));
				customer.setCustomerName(resultSet.getString(2));
				customer.setGender(resultSet.getString(3));
				customer.setMobile(resultSet.getString(4));
				customer.setEmail(resultSet.getString(5));
				customers.add(customer);
			}
		}catch(Exception e) {
			throw e;
		}
		finally {
//			closeConnection();
		}
		return customers;
	}
	@Override
	public boolean deleteCustomer(long customerId) throws Exception {
		// TODO Auto-generated method stub
		int result=0;
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("delete_customer"));
			statement.setLong(1, customerId);
			result = statement.executeUpdate();
			
		}catch(Exception e) {
			throw e;
		}
		finally {
//			closeConnection();
		}
		return result==0?false:true;
	}
	@Override
	public Customer getCustomerById(long id) throws Exception {
		// TODO Auto-generated method stub
		Customer customer = null;
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("get_customer"));
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				customer = new Customer();
				customer.setCustomerId(resultSet.getLong(1));
				customer.setCustomerName(resultSet.getString(2));
				customer.setGender(resultSet.getString(3));
				customer.setMobile(resultSet.getString(4));
				customer.setEmail(resultSet.getString(5));
			}
		}catch(Exception e) {
			throw e;
		}
		finally {
//			closeConnection();
		}
		return customer;
	}

}

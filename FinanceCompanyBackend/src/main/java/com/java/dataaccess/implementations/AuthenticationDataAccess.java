package com.java.dataaccess.implementations;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.java.dataaccess.contracts.AuthenticationContract;
import com.java.entities.Customer;
import com.java.entities.User;

public class AuthenticationDataAccess implements AuthenticationContract {
	Connection connectionInstance;
	PreparedStatement statement;
	ResultSet resultSet;
	FileReader reader;
	Properties properties;
	public AuthenticationDataAccess() throws SQLException, IOException, ClassNotFoundException {
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
	public User getUser(String username) throws Exception {
		User user = null;
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("get_user"));
			statement.setString(1, username);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				user = new User();
				user.setUsername(resultSet.getString(1));
				user.setPassword(resultSet.getString(2));
				user.setRole(resultSet.getInt(3));
			}
		}catch(Exception e) {
			throw e;
		}
		finally {
//			closeConnection();
		}
		return user;
	}
	@Override
	public User getClerk(String username) throws Exception {
		User user = null;
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("get_clerk"));
			statement.setString(1, username);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				user = new User();
				user.setUsername(resultSet.getString(1));
				user.setPassword(resultSet.getString(2));
				user.setRole(resultSet.getInt(3));
			}
		}catch(Exception e) {
			throw e;
		}
		finally {
//			closeConnection();
		}
		return user;
	}
	@Override
	public User getManager(String username) throws Exception {
		User user = null;
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("get_manager"));
			statement.setString(1, username);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				user = new User();
				user.setUsername(resultSet.getString(1));
				user.setPassword(resultSet.getString(2));
				user.setRole(resultSet.getInt(3));
			}
		}catch(Exception e) {
			
			throw e;
		}
		finally {
//			closeConnection();
		}
		return user;
	}
	@Override
	public boolean addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		int result=0;
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("add_user"));
			statement.setString(1, user.getUsername());
			statement.setString(2,user.getPassword());
			statement.setInt(3, user.getRole());
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
	public boolean changePassword(long customerId, String pass) throws Exception {
		// TODO Auto-generated method stub
		int result=0;
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("change_pass"));
			statement.setString(1, pass);
			statement.setLong(2,customerId);
			result = statement.executeUpdate();
			
		}catch(Exception e) {
			throw e;
		}
		finally {
//			closeConnection();
		}
		return result==0?false:true;
	}

}

package com.java.dataaccess.implementations;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.java.dataaccess.contracts.LoanApplicationContract;
import com.java.entities.FullApplication;
import com.java.entities.LoanApplication;

public class LoanApplicationDataAccess implements LoanApplicationContract {
	
	Connection connectionInstance;
	PreparedStatement statement;
	ResultSet resultSet;
	FileReader reader;
	Properties properties;
	public LoanApplicationDataAccess() throws SQLException, IOException, ClassNotFoundException {
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
	public boolean addApplication(LoanApplication app) throws Exception {
		// TODO Auto-generated method stub
		int result=0;
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("add_loan_app"));
			statement.setInt(1,app.getApplicationNumber());
			statement.setInt(2,app.getCustomerId());
			statement.setInt(3,app.getLoanId());
			statement.setDouble(4,app.getLoanAmount());
			statement.setInt(5,app.getLoanTenure());
			statement.setDouble(6,app.getLoanEmi());
//			statement.setDate(7,(Date) app.getApplyDate());
			statement.setString(7, app.getLoanStatus());
			result = statement.executeUpdate();
			closeConnection();
		}catch(Exception e) {
			throw e;
		}
		return result==0?false:true;
	}

	@Override
	public List<FullApplication> getAllApplications() throws Exception {
		// TODO Auto-generated method stub
		
		FullApplication app = null;
		List<FullApplication> apps = new ArrayList<>();
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("get_all_apps"));
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				app = new FullApplication();
				app.setApplicationNumber(resultSet.getInt(1));
				app.setCustomerId(resultSet.getInt(2));
				app.setLoanId(resultSet.getInt(3));
				app.setLoanAmount(resultSet.getDouble(4));
				app.setLoanTenure(resultSet.getInt(5));
				app.setLoanEmi(resultSet.getDouble(6));
				app.setApplyDate(resultSet.getDate(7));
				app.setLoanStatus(resultSet.getString(8));
				app.setDocuments(resultSet.getBlob(10));
				apps.add(app);
			}
		}catch(Exception e) {
			throw e;
		}
		return apps;
	}

	@Override
	public List<FullApplication> getApplicationsById(int customer_id) throws Exception {
		// TODO Auto-generated method stub
		FullApplication app = null;
		List<FullApplication> apps = new ArrayList<>();
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("get_all_apps_by_id"));
			statement.setInt(1, customer_id);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				ResultSetMetaData rsmd = resultSet.getMetaData();
				System.out.println(rsmd.getColumnCount());
				app = new FullApplication();
				app.setApplicationNumber(resultSet.getInt(1));
				app.setCustomerId(resultSet.getInt(2));
				app.setLoanId(resultSet.getInt(3));
				app.setLoanAmount(resultSet.getDouble(4));
				app.setLoanTenure(resultSet.getInt(5));
				app.setLoanEmi(resultSet.getDouble(6));
				app.setApplyDate(resultSet.getDate(7));
				app.setLoanStatus(resultSet.getString(8));
				app.setDocuments(resultSet.getBlob(10));
				apps.add(app);
			}
		}catch(Exception e) {
			throw e;
		}
		return apps;
	}

	@Override
	public boolean removeApplication(int application_number) throws Exception {
		// TODO Auto-generated method stub
		int result=0;
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("delete_app"));
			statement.setInt(1, application_number);
			result = statement.executeUpdate();
		}catch(Exception e) {
			throw e;
		}
		return result==0?false:true;
	}

	@Override
	public boolean approve(int application_number) throws Exception {
		// TODO Auto-generated method stub
		int result=0;
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("approve_app"));
			statement.setInt(1,application_number);
			result = statement.executeUpdate();
			closeConnection();
		}catch(Exception e) {
			throw e;
		}
		return result==0?false:true;
	}

	@Override
	public boolean reject(int application_number) throws Exception {
		// TODO Auto-generated method stub
		int result=0;
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("reject_app"));
			statement.setInt(1,application_number);
			result = statement.executeUpdate();
			closeConnection();
		}catch(Exception e) {
			throw e;
		}
		return result==0?false:true;
	}

}

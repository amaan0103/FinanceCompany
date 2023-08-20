package com.java.dataaccess.implementations;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.java.dataaccess.contracts.LoanContract;
import com.java.entities.Loan;

public class LoanDataAccess implements LoanContract{
	Connection connectionInstance;
	PreparedStatement statement;
	ResultSet resultSet;
	FileReader reader;
	Properties properties;
	public LoanDataAccess() throws SQLException, IOException, ClassNotFoundException {
		try {
			reader = new FileReader("C:\\Users\\Mourya\\Documents\\GitHub\\FinanceCompany\\FinanceCompanyBackend\\src\\main\\java\\com\\java\\dataaccess\\implementations\\db.properties");
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
	@Override
	public boolean addLoan(Loan loan) throws Exception {
		// TODO Auto-generated method 
		int result=0;
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("add_loan"));
			statement.setInt(1, loan.getLoanId());
			statement.setString(2, loan.getLoanType());
			statement.setDouble(3,loan.getInterestRate());
			result = statement.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
//			closeConnection();
		}
		return result==0?false:true;
	}
	@Override
	public List<Loan> getAllLoans() throws Exception {
		// TODO Auto-generated method stub
		Loan loan = null;
		List<Loan> loans = new ArrayList<>();
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("get_all_loans"));
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				loan = new Loan();
				loan.setLoanId(resultSet.getInt(1));
				loan.setLoanType(resultSet.getString(2));
				loan.setInterestRate(resultSet.getDouble(3));
				loans.add(loan);
			}
		}catch(Exception e) {
			throw e;
		}finally {
//			closeConnection();
		}
		return loans;
	}
	@Override
	public boolean removeLoan(int loan_id) throws Exception {
		int result=0;
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("delete_loan"));
			statement.setInt(1, loan_id);
			result = statement.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
//			closeConnection();
		}
		return result==0?false:true;
	}

	private void closeConnection() throws SQLException {
		if (connectionInstance != null)
			connectionInstance.close();
	}
}

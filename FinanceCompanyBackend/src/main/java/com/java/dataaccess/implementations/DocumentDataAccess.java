package com.java.dataaccess.implementations;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.java.dataaccess.contracts.DocumentContract;
import com.java.entities.Documents;

public class DocumentDataAccess implements DocumentContract{
	Connection connectionInstance;
	PreparedStatement statement;
	ResultSet resultSet;
	FileReader reader;
	Properties properties;
	public DocumentDataAccess() throws SQLException, IOException, ClassNotFoundException {
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
	public boolean addDocument(Documents docs) throws Exception {
		int result=0;
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("add_docs"));
			statement.setInt(1, docs.getApplicationNumber());
			statement.setBlob(2, docs.getDocuments());
			result = statement.executeUpdate();
			closeConnection();
		}catch(Exception e) {
			throw e;
		}
		return result==0?false:true;
	}
	@Override
	public boolean removeDocuments(int application_number) throws Exception {
		// TODO Auto-generated method stub
		int result=0;
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("delete_docs"));
			statement.setInt(1, application_number);
			result = statement.executeUpdate();
		}catch(Exception e) {
			throw e;
		}
		return result==0?false:true;
	}
}
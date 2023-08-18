package com.java.dataaccess.implementations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
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
		}finally {
			closeConnection();
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
			statement.setLong(1, docs.getApplicationNumber());
			
			Blob blob = connectionInstance.createBlob();
			blob.setBytes(1, docs.getDocuments().getBytes());
			statement.setBlob(2, blob);
			result = statement.executeUpdate();
			closeConnection();
		}catch(Exception e) {
			throw e;
		}finally {
			closeConnection();
		}
		return result==0?false:true;
	}
	@Override
	public boolean removeDocuments(long application_number) throws Exception {
		// TODO Auto-generated method stub
		int result=0;
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("delete_docs"));
			statement.setLong(1, application_number);
			result = statement.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			closeConnection();
		}
		return result==0?false:true;
	}
	@Override
	public List<Documents> getAllDocuments() throws Exception {
		Documents doc = null;
		List<Documents> docs = new ArrayList<>();
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("get_all_docs"));
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				doc = new Documents();
				doc.setApplicationNumber(resultSet.getLong(1));
				StringBuffer buf = new StringBuffer();
				String temp = null;
				BufferedReader reader = new BufferedReader(new InputStreamReader(resultSet.getBlob(2).getBinaryStream()));
				while((temp = reader.readLine())!=null) {
					buf.append(temp);
				}
				doc.setDocuments(buf.toString());
				docs.add(doc);
			}
		}catch(Exception e) {
			throw e;
		}finally {
			closeConnection();
		}
		return docs;
	}
	@Override
	public Documents getDocumentById(long application_number) throws Exception {
		// TODO Auto-generated method stub
		Documents doc = null;
		try {
			statement = connectionInstance.prepareStatement(properties.getProperty("get_docs_by_id"));
			statement.setLong(1, application_number);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				doc = new Documents();
				doc.setApplicationNumber(resultSet.getInt(1));
				StringBuffer buf = new StringBuffer();
				String temp = null;
				BufferedReader reader = new BufferedReader(new InputStreamReader(resultSet.getBlob(2).getBinaryStream()));
				while((temp = reader.readLine())!=null) {
					buf.append(temp);
				}
				doc.setDocuments(buf.toString());
			}
		}catch(Exception e) {
			throw e;
		}finally {
			closeConnection();
		}
		return doc;
	}
}

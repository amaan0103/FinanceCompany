package com.java.dataaccess.contracts;

import java.util.List;

import com.java.entities.Documents;

public interface DocumentContract {
	boolean addDocument(Documents docs) throws Exception ;
	boolean removeDocuments(long application_number) throws Exception ;
	List<Documents> getAllDocuments() throws Exception;
	Documents getDocumentById(long application_number) throws Exception;
}

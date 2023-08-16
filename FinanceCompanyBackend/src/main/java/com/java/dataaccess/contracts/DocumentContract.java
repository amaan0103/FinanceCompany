package com.java.dataaccess.contracts;

import com.java.entities.Documents;

public interface DocumentContract {
	boolean addDocument(Documents docs) throws Exception ;
	Documents removeDocuments(int application_number) throws Exception ;
}

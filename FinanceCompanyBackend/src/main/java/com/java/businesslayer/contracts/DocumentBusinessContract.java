package com.java.businesslayer.contracts;

import com.java.entities.Documents;

public interface DocumentBusinessContract {
	boolean deleteDocuments(long application_number) throws Exception;
	boolean addDocument(Documents docs) throws Exception;
}

package com.java.businesslayer.implementations;

import com.java.businesslayer.contracts.DocumentBusinessContract;
import com.java.dataaccess.contracts.DocumentContract;
import com.java.entities.Documents;

public class DocumentComponent<TContract, TImplementation> implements DocumentBusinessContract {

	TContract dao;
	public DocumentComponent(TContract dao) {
		super();
		this.dao = dao;
	}

	@Override
	public boolean deleteDocuments(long applicationNumber) throws Exception {
		// TODO Auto-generated method stub
		return ((DocumentContract) dao).removeDocuments(applicationNumber);
	}

	@Override
	public boolean addDocument(Documents docs) throws Exception {
		// TODO Auto-generated method stub
		return ((DocumentContract) dao).addDocument(docs);
	}

}

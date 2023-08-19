package com.java.businesslayer.implementations;

import com.java.businesslayer.contracts.DocumentBusinessContract;
import com.java.dataaccess.contracts.DocumentContract;

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

}

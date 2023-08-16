package com.java.services;

import java.lang.reflect.InvocationTargetException;

import com.java.businesslayer.contracts.ClerkBusinessContract;
import com.java.businesslayer.implementations.ClerkComponent;
import com.java.container.Injector;
import com.java.dataaccess.contracts.CustomerContract;

public class ClerkService {
	
	Injector injector;
	CustomerContract dataAccessObject;
	ClerkBusinessContract businessObject;
	
	public ClerkService() {
		injector = Injector.getInjector();
		try {
			dataAccessObject = injector.createInstance(CustomerContract.class);
			businessObject = injector.createInstance(ClerkComponent.class, businessObject, CustomerContract.class);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

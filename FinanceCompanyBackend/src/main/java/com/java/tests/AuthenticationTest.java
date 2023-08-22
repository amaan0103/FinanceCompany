package com.java.tests;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.*;

import com.java.businesslayer.implementations.AuthenticationComponent;
import com.java.dataaccess.contracts.AuthenticationContract;
import com.java.dataaccess.implementations.AuthenticationDataAccess;
import com.java.entities.AuthResponse;
import com.java.entities.User;

public class AuthenticationTest {
	private static AuthenticationComponent<AuthenticationContract,AuthenticationDataAccess > dao;
	
	public AuthenticationTest() throws ClassNotFoundException, SQLException, IOException {dao = new AuthenticationComponent<AuthenticationContract,AuthenticationDataAccess>(new AuthenticationDataAccess());}
	
	@Test
	public void loginTest() throws Exception {
		User user = new User("ManagerAccount","ManagerPassword","randommmm");
		AuthResponse flag = dao.login(user,"MANAGER");
		Assert.assertNotNull(flag);
	}
}

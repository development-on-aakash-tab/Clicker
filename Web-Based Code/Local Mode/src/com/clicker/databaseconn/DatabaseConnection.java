package com.clicker.databaseconn;

// This is database connection class

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseConnection {

	
	public Connection createDatabaseConnection(){
		DataSource dataSource;
		Connection connection = null;
		// Get DataSource
		Context initContext = null;
		Context envContext = null;
		try {
			initContext = new InitialContext();
			envContext = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/aakashdb");
			connection = dataSource.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return connection; 
	}
	
	public Connection createRemoteDatabaseConnection() {
		DataSource dataSource;
		Connection connection = null;
		// Get DataSource
		Context initContext = null;
		Context envContext = null;
		try {
			initContext = new InitialContext();
			envContext = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/remoteaakashdb");
			connection = dataSource.getConnection();			
		} catch (NamingException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return connection; 
	}
	
	
}

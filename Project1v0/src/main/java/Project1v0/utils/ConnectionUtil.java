package Project1v0.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private Connection conn;
	
	public Connection createConnection() throws SQLException {
		
	    conn = DriverManager.getConnection("jdbc:postgresql://trms-postgres-aholliday.postgres.database.azure.com:5432/postgres?", "aholliday@trms-postgres-aholliday", "CRedit6688");
		
	    //jdbc:postgresql://trms-postgres-aholliday.postgres.database.azure.com:5432/{your_database}?user=aholliday@trms-postgres-aholliday&password={your_password}&sslmode=require
	    
		return conn;
		
	}
}



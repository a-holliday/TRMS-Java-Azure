package Project1v0.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private Connection conn;
	
	public Connection createConnection() throws SQLException {
		
	    conn = DriverManager.getConnection("URL", "username", "password");
		
	    
		return conn;
		
	}
}



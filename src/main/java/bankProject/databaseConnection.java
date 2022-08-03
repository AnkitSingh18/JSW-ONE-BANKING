package bankProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {
	protected static Connection initializeDatabase() throws SQLException, ClassNotFoundException
		{
			// Initialize all the information regarding
			// Database Connection
			String dbDriver = "com.mysql.cj.jdbc.Driver";
			Class.forName(dbDriver);
			Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/jswbank","root","root");
			return con;
		}
	}

		


		




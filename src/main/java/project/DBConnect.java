package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
		protected static Connection jdbcConnect() throws SQLException, ClassNotFoundException
		{
		 Connection connection= null;
		 Class.forName("com.mysql.cj.jdbc.Driver");
		connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/practice","root","1234");
		//Returning object of connection class for it's use by other servlets
		return connection;
		}
}

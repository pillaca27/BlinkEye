package net.royal.spring.framework.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDbcTest {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		String dbURL = "jdbc:sqlserver://40.121.131.50:60581;databaseName=CajaCusco;user=usr_spring_portal;password=cajaCusco2021";
		//String dbURL = "jdbc:sqlserver://40.121.131.50:60581;databaseName=CajaCusco;user=caja;password=cajaCusco2021";
		
		Connection conn = DriverManager.getConnection(dbURL);
		if (conn != null) {
		    System.out.println("Connected");
		}
	}

}

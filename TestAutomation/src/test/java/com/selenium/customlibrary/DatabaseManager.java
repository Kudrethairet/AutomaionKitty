package com.selenium.customlibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

	private String databaseServerName = "localhost";
	private String databasePort = "1521";
	private String databaseName = "xe";
	private String userName = "hr";
	private String userPassword = "hr";

	private String connectionURL = "jdbc:oracle:thin:hr@//" + databaseServerName + ":" + databasePort + "/"
			+ databaseName;
	
	private ResultSet resultSet = null;
	private Statement statement = null;
	private Connection connection = null;
	
	private void connectToOracleDB() throws SQLException, ClassNotFoundException
	{
		//Load the driver
		Class.forName("oracle.jdbc.OracleDriver");
		//Create connection
		connection = DriverManager.getConnection(connectionURL, userName, userPassword);
		statement = connection.createStatement();
	}
	
	public ResultSet runSQLQuery(String sqlQuery) throws ClassNotFoundException, SQLException
	{
		connectToOracleDB();
		resultSet = statement.executeQuery(sqlQuery);
		return resultSet;
	}
	
	
	
	public static void main(String[] args) throws Exception, SQLException {
		DatabaseManager db = new DatabaseManager();
		ResultSet data = db.runSQLQuery("select * from COUNTRIES");
		//System.out.println("data: " + data );
		
		System.out.println("COUNTRY_ID"+ "\t COUNTRY_NAME" + "\t \t \t \t" + "REGION_ID" );
		System.out.println("----------------------------------------------------------------------------------" );
		while(data.next())
		{
			String countryID = data.getString("COUNTRY_ID");
			String countryName = data.getString("COUNTRY_NAME");
			int regionID = data.getInt("REGION_ID");
			System.out.println(countryID+ "\t" + countryName + "\t \t \t \t" + regionID );
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

package com.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
	private static Connection conn;
	
	public static Connection getConnection()
	{
		if(conn != null)
		{
			return conn;
		}
		else
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat","root","");
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
		}
		return conn;
	}
}
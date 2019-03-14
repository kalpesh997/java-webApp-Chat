<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.db.util.DBConnection" %>
<%@ page import="java.sql.*" %>

<%	
	try
	{
		Connection con = DBConnection.getConnection();
		Statement s  = con.createStatement();
		
		int i  = (Integer)session.getAttribute("userid");
				
		String q = "SELECT * FROM login WHERE user_id !="+i+" ";
		ResultSet rs = s.executeQuery(q);
		
		String op = "<table class='table table-bordered table-striped'><tr><td>Username</td><td>Status</td><td>Action</td></tr>";
		String op1="",op2=""; 
		
		while(rs.next())
		{
		 op1 = "<tr>"+
		  "<td>"+rs.getString(2)+"</td>"+
		  "<td>Online</td>"+
		  "<td><button type='button' class='btn btn-info btn-xs start_chat' data-touserid='"+rs.getInt(1)+"' data-tousername='"+rs.getString(2)+"'>Start Chat</button></td>"+
		  "</tr>";
		}
		op2 = "</table>";		
		System.out.print(op+op1+op2);
		out.print(op+op1+op2);
	}
	catch(Exception e){e.printStackTrace();}
%>
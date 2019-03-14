<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.db.util.DBConnection" %>
<%@ page import="java.sql.*" %>

<%

	String uname = request.getParameter("uname");
	String password = request.getParameter("password");
	
	try
	{
		Connection con = DBConnection.getConnection();
		Statement s  = con.createStatement();			
		
		PreparedStatement pr = con.prepareStatement("select *from login where username=? and password=?");
		pr.setString(1, uname);
		pr.setString(2, password);					
				
		ResultSet rs = pr.executeQuery();
		while(rs.next())
		{
			session.setAttribute("userid", rs.getInt(1));
			session.setAttribute("user_name", rs.getString(2));
			
			String q1 = "insert into login_details (user_id,is_type) values ("+rs.getInt(1)+",'')";
			s.execute(q1);
			
			response.sendRedirect("index.jsp");
		}
	}
	catch(Exception e){ e.printStackTrace(); }
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>  
    <head>  
        <title>Chat Application using java Ajax Jquery</title>  
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    </head>  
    <body>  
        <div class="container">
	   	<br/>   
	   <h3 align="center">Chat Application using java Ajax Jquery</a></h3><br />
	   <br />
	   <div class="panel panel-default">
	      <div class="panel-heading">Chat Application Login</div>
	    <div class="panel-body">
	     <form method="post">
	      <p class="text-danger"><% out.print(uname); out.print(password); out.print(session.getAttribute("userid")); %></p>
	      <div class="form-group">
	       <label>Enter Username</label>
	       <input type="text" name="uname" class="form-control" required />
	      </div>
	      <div class="form-group">
	       <label>Enter Password</label>
	       <input type="password" name="password" class="form-control" required />
	      </div>
	      <div class="form-group">
	       <input type="submit" name="login" class="btn btn-info" value="Login" />
	      </div>
	     </form>
	    </div>
	   </div>
	  </div>
   </body>  
</html>

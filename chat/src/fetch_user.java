import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.util.DBConnection;
import java.sql.*;

@WebServlet("/fetch_user")
public class fetch_user extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try
		{
			HttpSession session = request.getSession();
			PrintWriter out = response.getWriter();			
			Connection con = DBConnection.getConnection();
			Statement s  = con.createStatement();
			
			int i  = (Integer)session.getAttribute("userid");					
			String q = "SELECT * FROM login WHERE user_id !="+i+" ";
			ResultSet rs = s.executeQuery(q);			
			
			String op = "<table class='table table-bordered table-striped'><tr><td>Username</td><td>Status</td><td>Action</td></tr>";		 
			
			while(rs.next())
			{
			 op += "<tr>"+
			  "<td>"+rs.getString(2)+"</td>"+
			  "<td>Online</td>"+
			  "<td><button type='button' class='btn btn-info btn-xs start_chat' data-touserid='"+rs.getInt(1)+"' data-tousername='"+rs.getString(2)+"'>Start Chat</button></td>"+
			  "</tr>";
			}
			op += "</table>";			
			out.print(op);			
		}
		catch(Exception e){e.printStackTrace();}
		
	}

}

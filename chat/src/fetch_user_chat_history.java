import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.util.DBConnection;


@WebServlet("/fetch_user_chat_history")
public class fetch_user_chat_history extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession();
			Connection con = DBConnection.getConnection();
			PrintWriter out = response.getWriter();
			Statement s  = con.createStatement();
			
			int to_id = Integer.parseInt(request.getParameter("to_user_id"));		
			int from_id  = (Integer)session.getAttribute("userid");
			
			String query = "SELECT * FROM chat_message WHERE (from_user_id="+from_id+"  AND to_user_id="+to_id+") OR (from_user_id="+to_id+" AND to_user_id="+from_id+") ORDER BY timestamp DESC"; 					  					
			ResultSet rs = s.executeQuery(query);
			
			String op1 = "<ul class='list-unstyled'>";
			
			while(rs.next()) 
			{
				String uname = "";
				if(rs.getInt(3)==from_id)
				{
					uname = "<b class='text-success'>You</b>";
				}
				else
				{
					uname = "<b class='text-danger'>"+getUserName(rs.getInt(3))+"</b>";
				}
				op1 += "<li style='border-bottom:1px dotted #ccc'>";
				op1 += "<p>"+uname+"-"+rs.getString(4)+"";
				op1 += "<div align='right'>";
				op1 += "- <small><em>"+rs.getTimestamp(5)+"</em></small>";
				op1 += "</div></p></li>";				
			}								
			out.println(op1);			
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public static String getUserName(int user_id)
	{
		String un = "";
		try
		{
			Connection connn = DBConnection.getConnection();		
			Statement s  = connn.createStatement();			 
			String q11 = "select * from login where user_id="+user_id+"";			
			ResultSet rss = s.executeQuery(q11);			
			while(rss.next())
			{
				un = rss.getString(2);
			}		
		}
		catch(Exception e)
		{			
			e.printStackTrace();
		}
		return un;
	}
	
}

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.util.DBConnection;

@WebServlet("/insert_chat")
public class insert_chat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			HttpSession session = request.getSession();
			PrintWriter out = response.getWriter();			
			Connection con = DBConnection.getConnection();
			Statement s  = con.createStatement();					
			
			int to_user_id = Integer.parseInt(request.getParameter("to_user_id"));
			int from_user_id = (Integer)session.getAttribute("userid");
			String chat_message = request.getParameter("chat_message");
			int status = 1; 						
			
			String query = "INSERT INTO chat_message (to_user_id, from_user_id, chat_message, status) VALUES ("+to_user_id+","+from_user_id+","+chat_message+","+status+")";			
			s.executeUpdate(query);
																		
			String op = "fetch_user_chat_history("+from_user_id+","+to_user_id+")";			
			out.println(op);
		}
		catch(Exception e) {e.printStackTrace();}	
	}
}

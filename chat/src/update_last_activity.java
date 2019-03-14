import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.util.DBConnection;

@WebServlet("/update_last_activity")
public class update_last_activity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			HttpSession session = request.getSession();
			
			Connection con = DBConnection.getConnection();
			Statement s  = con.createStatement();
			
			int i  = (Integer)session.getAttribute("userid");					
			String q = "SELECT * FROM login WHERE user_id !="+i+" ";
			s.executeQuery(q);			
							
		}
		catch(Exception e){e.printStackTrace();}
	}
}

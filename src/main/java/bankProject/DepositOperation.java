package bankProject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DepositOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		Connection connection;
		connection = databaseConnection.initializeDatabase();
		int result=bankFunctionality.balance+ Integer.valueOf(request.getParameter("balance"));
	    PreparedStatement st = connection.prepareStatement("update cutomer set balance=("+result+") where Email=\""+bankFunctionality.email+"\"");
		st.executeUpdate(); 
	    st.close();
	    bankFunctionality.balance=result;
		PrintWriter out = response.getWriter();
	    out.println("<html><body style=\"background-image:url(insidebg.png);background-repeat:no-repeat;background-attachment:fixed;background-size:cover;\"><b><div style=\"text-align:center;\" class=\"card\" style=\"width: 18rem;\"><div class=\"card-body\">Amount has been successfully credited.<br>Your updated balance is Rs."+result+"\n"+"</b><form action=\"http://localhost:8081/JSW_ONE_BANKING/Landing.html\" method=\"post\"><input type=\"submit\" value=\"Exit\" style=\"background-color:red;\"/></div></div> </form></body></html>");
	} 
	catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
}
package bankProject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//Establishing Database Connection with mysql
			Connection connection;
			connection = databaseConnection.initializeDatabase();
		    PreparedStatement query = connection.prepareStatement("insert into cutomer(user_name,email,password) value(?,?,?)");
		    String query2="select email from cutomer";
		    ResultSet rs= query.executeQuery(query2);
		    boolean check=true;
		    //Check if customer email already exists
		    while(rs.next()) {
		    	if(request.getParameter("email").equals(rs.getString(1))){
		    		check= false;
		    	}
		    }
	    	PrintWriter out = response.getWriter();
	    	//If it doesn't exist, create the customer
		    if(check) {
		    	query.setString(1, request.getParameter("user_name"));
		    	query.setString(2, request.getParameter("email"));
		    	query.setInt(3, Integer.valueOf(request.getParameter("password")));
		    	query.executeUpdate();
			    out.println("<html><bodystyle=\\\"background-image:url(insidebg.png);background-repeat:no-repeat;background-attachment:fixed;background-size:cover;\\\"><b> You are now Smart JSW ONE Banker!! Congratulations"+"</b><br><form action=\"http://localhost:8081/JSW ONE BANKING/Landing.html\" method=\"post\"><input type=\"submit\" value=\"Exit\" style=\"background-color:red;\"/> </form></body></html>");
		    }
		    //If customer exists, show the message
		    else {
		    	out.println("User with same credentials already exists.");
		    }
		    query.close();
		    out.close();
		    rs.close();
			} 
		catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
			}
		
	}

}
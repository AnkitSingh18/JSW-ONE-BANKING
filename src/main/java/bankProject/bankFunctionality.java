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

/**
 * Servlet implementation class BankApplications
 */
public class bankFunctionality extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	 static String u_name=null;
	 static String email = null;
	 static String password = null;
	 static int balance=0;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
            // Initialize the database
			Connection conn = databaseConnection.initializeDatabase();
            System.out.println("connection");
            // Create a SQL query to insert data into demo table
            // demo table consists of two columns, so two '?' is used
            
            PreparedStatement st = conn.prepareStatement("insert into cutomer(email,password)"+" values(?,?)");
            // For the first parameter,
            // get the data using request object
            // sets the data to st pointer
            String name = request.getParameter("email");
            st.setString(1,name);
            // Same for second parameter
            String pw= request.getParameter("password");
            st.setString(2,pw);
            // Execute the insert command using executeUpdate()
            // to make changes in database
            //boolean valid=false;
            ResultSet rs= st.executeQuery("select user_name,email,password,balance from cutomer where email='"+name+"'");
			while(rs.next()) {
			u_name =rs.getString("user_name");
				email =rs.getString("email");
				password =rs.getString("password");
				balance =rs.getInt("balance");
			}
			 st.close();
	         conn.close();
				PrintWriter out = response.getWriter();
			if(pw.equals(password)) {
				// Get a writer pointer 
	            // to display the successful result
	            out.println("<html><head><link rel=\"stylesheet\" href=\"css/bootstrap.css\"></link></head><body style=\"background-image:url(insidebg.png);background-repeat:no-repeat;background-attachment:fixed;background-size:cover;\" ><h2 style=\"text-align:center;\">Welcome to JSW ONE Bank " +u_name+ "</h2><br><div style=\"text-align:center;\"class=\"card\" style =\"width:18rem;\"><div class=\"card-body\"><br><form class=\"card-title\" class=\"form card\" id=\"frm\" action=\"./DepositOperation\" method=\"post\"> Please Enter the amount to be credited :-<input  type=\"number\" name=\"balance\">"+
                        "<input type=\"submit\" value=\"Credit\" style=\"background-color:red;\"/></form></div></div><br><div style=\"text-align:center;\" class=\"card\" style=\"width: 18rem;\"><div class=\"card-body\"><form action=\"./withdraw\" method=\"post\"> Please Enter the amount to be debited :-<input type=\"number\" name=\"balance\">"
                        + "<input type=\"submit\" value=\"Debit\" style=\"background-color:red;\"/></form></div></div>"+
                        "<br><div style=\"text-align:center;\" class=\"card\" style=\"width: 18rem;\"><div class=\"card-body\"><form action=\"./checkBalance\" method=\"post\"> Press to Check Balance :-"
                        + "<input type=\"submit\" value=\"Check Balance\" style=\"background-color:red;\"/></form></div></div>"+
                        "<br><div style=\"text-align:center;\"><form action=\"http://localhost:8081/JSW_ONE_BANKING/Landing.html\" method=\"post\"><input class=\"btn btn-primary\" type=\"submit\" value=\"Exit\"/></div></body></html>");
            }
			else {
				out.println("<body><b>Please enter right credentials" + "</b></body>");
			}
            // Close all the connections
           
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}

}
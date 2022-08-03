package bankProject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class checkBalance
 */
public class checkBalance extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
	    out.println("<html><body style=\"background-image:url(insidebg.png);background-repeat:no-repeat;background-attachment:fixed;background-size:cover;\"><b><div style=\"text-align:center;\" class=\"card\" style=\"width: 18rem;\"><div class=\"card-body\">Your current balance is.</b>"+bankFunctionality.balance+"\n"+"<form action=\"http://localhost:8081/JSW_ONE_BANKING/Landing.html\" method=\"post\"><input type=\"submit\" value=\"Exit\" style=\"background-color:blue;\"/> </form></div></div></body></html>");
	} 
	

}

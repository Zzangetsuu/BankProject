package project;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CheckBalance extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try
  {
	PrintWriter out = response.getWriter();
	out.println("<body style=\"background-image:url(JswBank.jpg);background-repeat:no-repeat;background-attachment:fixed;"
	+"background-size:100%,100%;\"><br><br><br><br><br><br><b>Your account balance is Rs."+BankApplications.balance+"\n"+"</b><br><br><button onclick=\"history.back()\">Go Back</button><br><br><br><br><form action=\"http://localhost:8080/BankProject/FrontEnd.html\" method=\"post\"><input type=\"submit\" value=\"Logout\" style=\"background-color:red;\"/></form></body>");
	out.close();
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
 }
}

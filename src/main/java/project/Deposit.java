package project;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		//Establishing Database Connection
		Connection connection;
		connection = DBConnect.jdbcConnect();
		//Storing the updated value in another integer variable
		int result=BankApplications.balance+ Integer.valueOf(request.getParameter("Balance"));
	    PreparedStatement st = connection.prepareStatement("update bankdata set Balance=("+result+") where Email=\""
		+BankApplications.email+"\"");
		st.executeUpdate();
		//Updating the reference variable for 'balance' field in database
		BankApplications.balance=result;
	    st.close();
		PrintWriter out = response.getWriter();
		//Printing the output
	    out.println("<body style=\"background-image:url(JswBank.jpg);background-repeat:no-repeat;"
		+"background-attachment:fixed;background-size:100%,100%;\"><br><br><br><br><br><b>Amount has been successfully credited.<br>"
	    +"<br>Your updated balance is Rs."+result+"\n"+"</b><br><br><button onclick=\"history.back()\">Go Back</button><br><br><br><br><form action=\"http://localhost:8080/BankProject/FrontEnd.html\" method=\"post\"><input type=\"submit\" value=\"Logout\" style=\"background-color:red;\"/> </form></body>");
	    out.close();
	} 
	catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
		}
	}
}

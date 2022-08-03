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

public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	    //Establishing Database Connection
		Connection connection;
		connection = DBConnect.jdbcConnect();
	    PrintWriter out = response.getWriter();
	    int result=0;
	    
	    //Checking if we have enough funds to withdraw
		if(Integer.valueOf(request.getParameter("Balance"))<=BankApplications.balance)
		{
		result= BankApplications.balance-Integer.valueOf(request.getParameter("Balance"));
	    PreparedStatement st = connection.prepareStatement("update bankdata set Balance=("+result+") where Email=\""+BankApplications.email+"\"");
		st.executeUpdate(); 
		BankApplications.balance=result;
	    st.close();	    
	    //Getting the output
	    out.println("<body style=\"background-image:url(JswBank.jpg);background-repeat:no-repeat;background-attachment:fixed;background-size:100%,100%;\"><br><br><br><br><b>Amount has been successfully debited.<br>Your updated balance is: Rs."+ result +"\n"+"</b> <br><button onclick=\"history.back()\">Go Back</button><br><br><br><br><form action=\"http://localhost:8080/BankProject/FrontEnd.html\" method=\"post\"><input type=\"submit\" value=\"Logout\" style=\"background-color:red;\"/> </form></body>");
		}
		else 
		{
			out.println("<body style=\"background-image:url(JswBank.jpg);background-repeat:no-repeat;background-attachment:fixed;background-size:100%,100%;\"><br><br><br><br><b>Insufficient Funds</b>"+"\n"+"<br><br><br><br><form action=\"http://localhost:8080/BankProject/FrontEnd.html\" method=\"post\"><input type=\"submit\" value=\"Logout\" style=\"background-color:red;\"/> </form></body>");
		}
		out.close();
	} 
	catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
		}
		}
	}


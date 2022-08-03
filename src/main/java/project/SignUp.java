package project;

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
import java.sql.Statement;

public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//Establishing Database Connection
			Connection connection;
			connection = DBConnect.jdbcConnect();
			Statement st= connection.createStatement();
		    ResultSet rs= st.executeQuery("select Email,User_Name from bankdata");
		    boolean check = true;
		    //Checking if entered email already exists
		    while(rs.next()) {
		    	if(request.getParameter("Email").equals(rs.getString(1)) && request.getParameter("User_Name").equals(rs.getString(2))){
		    		check= false;
		    	}
		    }
	    	PrintWriter out = response.getWriter();
	    	//If it doesn't exist, create the user
		    if(check) {
		    	PreparedStatement query = connection.prepareStatement("insert into bankdata(User_Name,Email,Password) value(?,?,?)");
		    	query.setString(1, request.getParameter("User_Name"));
		    	query.setString(2, request.getParameter("Email"));
		    	query.setInt(3, Integer.valueOf(request.getParameter("Password")));
		    	query.executeUpdate();
			    out.println("<body style=\"background-image:url(JswBank.jpg);background-repeat:no-repeat;background-attachment:fixed;background-size:100%,100%;\"><br><br><br><br><br><br><b>Congrats! You are a member of JSW Bank Family"+"</b><br><br><br><br><form action=\"http://localhost:8080/BankProject/FrontEnd.html\" method=\"post\"><input type=\"submit\" value=\"Exit\" style=\"background-color:red;\"/> </form></body>");
			    rs.close();
		    }
		    //If user exists, show the message
		    else{
		    	PreparedStatement query2 = connection.prepareStatement("update bankdata set Password="+Integer.valueOf(request.getParameter("Password"))+" where Email=\""+request.getParameter("Email")+"\" and User_Name=\""+request.getParameter("User_Name")+"\"");
		    	query2.executeUpdate();
		    	out.println("<body style=\"background-image:url(JswBank.jpg);background-repeat:no-repeat;background-attachment:fixed;background-size:100%,100%;\"><br><br><br><br><br><br><b>Your password has been changed successfully.</b><br>"+"<br><br><button onclick=\"history.back()\">Go Back</button>");
		    	query2.close();
		    }
		    out.close();
			} 
		catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
			}		
	}
}

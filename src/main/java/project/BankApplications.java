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
/**
 * Servlet implementation class BankApplications
*/
public class BankApplications extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Creating static variables to store values in databases
	static String u_name=null;
	static String email = null;
	static int u_password = 0;
	static int balance=0;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            // Initialize the database
            Connection connection = DBConnect.jdbcConnect();
            PreparedStatement query = connection.prepareStatement("insert into bankdata(Email,Password)"+" values(?,?)");
            // For the first parameter,
            // get the data using request object
            // sets the data to query pointer
            String id = request.getParameter("Email");
            query.setString(1,id);
            // Same for second parameter
            int key= Integer.valueOf(request.getParameter("Password"));
            query.setInt(2,key);
            
            // Execute the insert command using executeQuery()
            // to make changes in database
			ResultSet rs= query.executeQuery("select User_Name,Email,Password,Balance from bankdata where Email='"+id+"'");
			//Storing values of field in databases in static variables for direct future reference
			while(rs.next()) {
				u_name =rs.getString("User_Name");
				email =rs.getString("Email");
				u_password =rs.getInt("Password");
				balance =rs.getInt("Balance");
			}
            // Close all the connections
			rs.close();
			query.close();
	        connection.close();
			PrintWriter out = response.getWriter();
			if(key==u_password) {
				// Get a writer pointer 
	            // to display the successful result
	            out.println("<html><head> <style type=\"text/css\">"
	            		+ "input[type=submit],input[type=number] {"
	            		+ "  background-color: #000080;"
	            		+ "  border: 1px;"
	            		+ "  color: white;"
	            		+ "  padding: 8px 16px;"
	            		+ "  margin: 4px 2px;"
	            		+ "  cursor: pointer;"
	            		+ "} </style> </head><body style=\"background-image:url(JswBank.jpg);background-repeat:no-repeat;background-attachment:fixed;background-size:100%,100%;\"><h2 style=\"text-align:center;\">Welcome to JSW Bank " +u_name+ "</h2><br><br><br><br><form action=\"./Deposit\" method=\"post\"><table><tr><td><b> Please Enter the amount to be credited :-</b></td><td><input type=\"number\" name=\"Balance\"></td>"+
	                         "<td><input type=\"submit\" value=\"Credit\" style=\"background-color:red;\"/></td></tr></table></form><br><form action=\"./Withdraw\" method=\"post\"><table><tr><td><b>Please Enter the amount to be debited :-</b></td><td><input type=\"number\" name=\"Balance\"></td>"
	                         + "<td><input type=\"submit\" value=\"Debit\" style=\"background-color:red;\"/></td></tr></table></form>"+
	                         "<br><form action=\"./CheckBalance\" method=\"post\"> <table><tr><td><b>Press to Check Balance :-</b></td>"
	                         + "<td><input type=\"submit\" value=\"Check Balance\" style=\"background-color:red;\"/></td></tr></table></form>"+
	                         "<form action=\"http://localhost:8080/BankProject/FrontEnd.html\" method=\"post\"><table><tr><td><b>Press here to logout </b></td><td><input type=\"submit\" value=\"Logout\" style=\"background-color:blue;\"/></td></tr></table></body></html>");
            }
			else {
				out.println("<body style=\"background-image:url(JswBank.jpg);background-repeat:no-repeat;background-attachment:fixed;background-size:100%,100%;\"><b>Please enter right credentials" + "</b></body>");
			}
			out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
}

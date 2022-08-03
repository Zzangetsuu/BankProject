# BankProject
-----------------------------------------------------------------------------------------------------

1. Firstly, install Tomcat and configure the class path in Eclipse.
2. Then mysql jdbc connector should be installed and stored in the bin folder of Tomcat application and configured with our project class path.
3. The name of my database is 'practice' and the table name is 'bankdata' with following fields:-
   User_Id(int, primary key,auto increment), User_Name(varchar), Password(int), Email(varchar,unique), Balance(int,default=0)
4. In case the project directly doesn't run on Eclipse, use FrontEnd.html file to run it on web browser.

the flow chart for the appliction:-<img width="346" alt="FlowChart" src="https://user-images.githubusercontent.com/104378105/182555241-e46730f5-5d26-445c-a8d4-0d27a4d5e057.PNG">

--------------------------------------------------------------------------------------------------------
This project is a web based application that can be used to allow users to sign up and log in using a user_id(email) and atm pin(password).

This project is a simple utilisation of connecting the functionalities of html to fetch user data and using servlet to pass them to Java variables and finally updating the data from database using jdbc and vice-versa.

#FrontEnd.html :- it calls the servlet 'BankApplications' , we divide the screen into two, on left side we perform login operation for existing users and on the right signup and forget password operation.

#BankApplication :- it's a servlet used to get user data from html page and validate user credentials. Depending on the action performed it further leads to different servlets

#DBConnect :- it's a java class file used to establish connection between database and java.

#Deposit :- it's a servlet used to deposit funds

#Withdraw :- it's a servlet used to withdraw funds with the condition that we have sufficient funds

#CheckBalance :- this servlet simply returns the balance in account

#SignUp :- it allows user to register if no user exists with that email id and also allows users to change their passwords if they enter correct User_Name and Email.

Features:-
The Project runs on Tomcat Server 10.0 and uses MySql JDBC for connection between java and database
Backend supported with Java Servlet
Frontend supported with HTML and CSS
Mysql is used for database
User can deposit, withdraw and check balance
User can signup account

Note:All the data displayed on the website is fetched from the server according to the user whose credentails have been logged in, running locally the server fetches all the data from database and then shows the result for the functionality requested by the user on the website panel from the server.

---------------------------------------------------------------------------------------------------------------------------------------
For more details and any suggestion/query,kindly reach me at:- aaditya.poonia@jsw.in

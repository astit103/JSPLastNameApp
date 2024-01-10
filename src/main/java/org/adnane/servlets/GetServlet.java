package org.adnane.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.io.Writer;
//import java.sql.Connection;
import java.text.MessageFormat;
import java.util.List;

import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.adnane.beans.Employee;
import org.adnane.dao.ApplicationDao;
//import org.adnane.dao.DBConnection;



@SuppressWarnings("serial")
// @WebServlet("/search") // http://localhost:8080/HelloWorld/getAdnane?fieldVar=Aksadur
public class GetServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Example-1: Use HttpServletResponse Writer object to write HTML back
//		String search = req.getParameter("fieldVar");
//		String response = "<html><h2>Hello Servlet World !<h2></html>";
//		Writer writer = resp.getWriter();
//		writer.write(response+"  You are searching for: "+search);

		// Example-2: Pull data from DAO (Data Access Object)
		String search = req.getParameter("SearchVar");
		ApplicationDao dao = new ApplicationDao();
		List<Employee> employeez = dao.searchEmployees(search);
		
		// writer.write("<html><h3>Employee Last Name is: "+employees.get(0).getLastName()+"<h3></html>"); 
		String result = getHTMLString(req.getServletContext().getRealPath("searchResults.html"), employeez );
		System.out.print("Result of Search Brah ! --->  \n\n" + result);
		resp.getWriter().write(result);
		
	} 
	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// Get User's browser inputs ('POST' request 'req')
//		String firstName = req.getParameter("fname");
//		String lastName = req.getParameter("lname");
//		
//		//Save parameters received into a new 'Employee' object
//		Employee employeeToRegister = new Employee(firstName, lastName);
//		
//		// DBConnection.java into the mysql 'helloDB' database
//		// Save the new Employee object into the helloDB.employees table
//		ApplicationDao dao = new ApplicationDao();
//		dao.saveEmployees(employeeToRegister);
//		
//		//response 'resp' to user with confirmation their Employee input was saved
//		resp.getWriter().write("<html><h1>"+employeeToRegister.getFirstName()+" was Saved/Registered !</h1></html>");
//	}
	
	public String getHTMLString(String filePath, List<Employee> employees) throws IOException{
		// Save entire HTML into 1 String variable
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line="";
		StringBuffer buffer = new StringBuffer();
		while((line=reader.readLine())!=null) {
			buffer.append(line);
		}
		
		reader.close();
		String page  = buffer.toString();
		
		// Replace Last name placeholder with Last Name (corresponding the First Name provided) 
		// retrieved from DB
		if(employees.size() != 0) {
			page = MessageFormat.format(page, employees.get(0).getLastName());
		}else {
			page = MessageFormat.format(page, "Unknown :(");
		}
		return page;
	}

}

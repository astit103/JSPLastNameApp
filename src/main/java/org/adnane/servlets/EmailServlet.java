package org.adnane.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
//import java.util.List;
import java.util.Properties;


import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.activation.DataHandler;

@SuppressWarnings("serial")
//@WebServlet("add")
public class EmailServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("Servlet 'doPost' method of 'PostEmailServlet' was called ");
		
		// Get Data From Email Form Submitted
		String to = req.getParameter("to");
		String subject = req.getParameter("subject");
		String messageText = req.getParameter("message");
		System.out.println("Email Form Contents Received: \n" + to + "\n " + subject + "\n " + messageText);
		
		// Setup 'Sender' Mail Server
		String host = "smtp.gmail.com";
		String port = "25";
		
		// Setup gmail sender Properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		// Setup 'Sender' Mail Credentials
		String from = "<Email>@gmail.com";
		String username = "<Email>@gmail.com";
		String password = "Password";
		
		// Create SMTP Session instance
		System.out.println("Creating SMTP Session...");
		Session session = Session.getInstance(props, 
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication(){
					return new PasswordAuthentication(username, password);
				}
			}
		);
		
		// Try to Send an Email Message
		try {
			System.out.println("Initialize MIME Message...");
			Message message = new MimeMessage(session);
			
			System.out.println("Setting Message...");
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(messageText);
			
			System.out.println("Transport Message...");
			Transport.send(message);
			
			resp.getWriter().println("Email sent successfully!");
			
		} catch (MessagingException e){
			throw new RuntimeException(e);
		}

		
		/*
		// writer.write("<html><h3>Employee Last Name is: "+employees.get(0).getLastName()+"<h3></html>"); 
		String result = getHTMLString(req.getServletContext().getRealPath("addEmployeeResults.html"), newEmployee );
		resp.getWriter().write(result);*/
		
		
	}
	

}

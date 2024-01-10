package org.adnane.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

public class brah extends DefaultServlet {

	/**
	 * TEST 'Request Dispatcher' AND ''
	 * Below is to clear Class Name Warning
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		request.getRequestDispatcher("");
		String st = request.getParameter("fname");
	}

}

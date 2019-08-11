package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTwo_SuccessAndLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletTwo_SuccessAndLogout() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username"); 
		response.setContentType("text/html");
		out.println("<h2>Welcome " + username + "! You are successfully logged in.</h2>");
		
		out.println("<form action = \"index.html\" method=\"post\">");
		out.println("<input type=\"submit\" value= \"Logout\" >");
		out.println("</form>");
	}
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

}

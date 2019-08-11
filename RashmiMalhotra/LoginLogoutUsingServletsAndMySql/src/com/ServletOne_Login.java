
package com;

import java.sql.Connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletOne_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletOne_Login() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String enteredUname = request.getParameter("username");
		String enteredPass = request.getParameter("password");

		RequestDispatcher rd = null;

		String dbUrl = "jdbc:mysql://localhost:3306/employeedb";
		String dbUsername = "root";
		String dbPassword = "gates_user";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			if (conn != null) {
				System.out.println("Successfully connected to db!!");

				// Statement stmt = conn.createStatement();
				// ResultSet rs = stmt.executeQuery("Select password from users where username =
				// '"+ enteredUname +"' ");

				PreparedStatement stmt = conn.prepareStatement("Select password from users where username = ?");
				ResultSet rs;
				stmt.setString(1, enteredUname);
				rs = stmt.executeQuery();

				if (rs.next()) {
					String correctPass = rs.getString(1);
					
					if (correctPass.equals(enteredPass)) {
						rd = request.getRequestDispatcher("ServletTwo_SuccessAndLogout");
						rd.forward(request, response);
					}

					else {
						rd = request.getRequestDispatcher("index.html");
						rd.include(request, response);
						response.setContentType("text/html");
						out.println("<h2> invalid credentials </h2>");
					}
				} else {
					out.println("<h2> this user does not exist or does not have a password </h2>");
				}

			}
		}

		catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		catch (Exception e) {
			out.print(e.getMessage());
		}

		finally {
			if (conn != null) {
				try {
					conn.close();
					System.out.println("Closed connection to db!!");
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}

		}
	}

}

package com.avi.web.JDBC;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Define dataSource / connection pool for Resource Injection
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Setup Content Type and PrintWriter
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		// Setup connection to Database
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			// Create SQL Statements
			String sql = "SELECT * FROM student";
			myStmt = myConn.createStatement();
			// Execute SQL Statement
			myRs = myStmt.executeQuery(sql);
			// Process Result Set
			while(myRs.next()) {
				String email = myRs.getString("email");
				out.println(email);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

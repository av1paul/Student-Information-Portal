package com.avi.web.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDBUtil {
	private DataSource dataSource;

	public StudentDBUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Student> getStudents() throws Exception {
		List<Student> students = new ArrayList<Student>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			// Get connection
			myConn = dataSource.getConnection();
			
			// Create SQL Statement
			String sql = "SELECT * FROM student ORDER BY last_name";
			myStmt = myConn.createStatement();
			
			// Execute Query
			myRs = myStmt.executeQuery(sql);
			
			// Process Result Set
			while(myRs.next()) {
				// Retrieve Data
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				
				// Create new student object
				Student tempStudent = new Student(id, firstName, lastName, email);
				
				// Add it to the list of students
				students.add(tempStudent);
			}
			return students;
		} finally {
			close(myConn, myStmt, myRs);
		}
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if(myConn != null)
				myConn.close();
			if(myStmt != null)
				myStmt.close();
			if(myRs != null)
				myRs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void addStudent(Student theStudent) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			// Get connection
			myConn = dataSource.getConnection();
			
			// Create SQL statement 
			String sql = "INSERT INTO student " +
						 "(first_name, last_name, email) " +
						 "VALUES (?, ?, ?)";
			myStmt = myConn.prepareStatement(sql);
			
			// Set the parameter values for the statement
			myStmt.setString(1, theStudent.getFirstname());
			myStmt.setString(2, theStudent.getLastname());
			myStmt.setString(3, theStudent.getEmail());
			
			// Execute SQL Insert
			myStmt.execute();			

		} finally {
			close(myConn, myStmt, null);
		}
	}

	public Student getStudent(String theStudentId) throws Exception {
		Student theStudent = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		
		try {
			// Convert student id to Integer
			studentId = Integer.parseInt(theStudentId);
			
			// Get connection
			myConn = dataSource.getConnection();
			
			// Create SQL statement 
			String sql = "SELECT * FROM student WHERE id=?";
			myStmt = myConn.prepareStatement(sql);
			
			// Set the parameter value for the statement
			myStmt.setInt(1, studentId);
			
			// Execute Query
			myRs = myStmt.executeQuery();
			
			// Process Result Set
			if (myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				
				// Create the student object
				theStudent = new Student(studentId, firstName, lastName, email);
			}
			else {
				throw new Exception("Could not find student id: " + studentId);
			}				
			
			return theStudent;
			
		} finally {
			close(myConn, myStmt, myRs);
		}
		
	}

	public void updateStudent(Student theStudent) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// Get connection
			myConn = dataSource.getConnection();
			
			// Create SQL statement 
			String sql = "UPDATE student "
						+ "SET first_name=?, last_name=?, email=? "
						+ "WHERE id=?";
			myStmt = myConn.prepareStatement(sql);
			
			// Set the parameter values for the statement
			myStmt.setString(1, theStudent.getFirstname());
			myStmt.setString(2, theStudent.getLastname());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setInt(4, theStudent.getId());
			
			// Execute SQL update
			myStmt.execute();
		} finally {
			close(myConn, myStmt, null);
		}
		
	}

	public void deleteStudent(String theStudentId) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		int studentId;
		
		try {
			// Convert student id to Integer
			studentId = Integer.parseInt(theStudentId);
			
			// Get connection
			myConn = dataSource.getConnection();
			
			// Create SQL statement 
			String sql = "DELETE FROM student WHERE id=?";
			myStmt = myConn.prepareStatement(sql);
			
			// Set the parameter value for the statement
			myStmt.setInt(1, studentId);
			
			// Execute Query
			myStmt.execute();
			
		} finally {
			close(myConn, myStmt, null);
		}
	}
	
}

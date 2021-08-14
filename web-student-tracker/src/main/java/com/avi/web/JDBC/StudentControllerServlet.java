package com.avi.web.JDBC;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDBUtil studentDBUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		// Pass the dataSource object to the studentDBUtil object
		try {
			studentDBUtil = new StudentDBUtil(dataSource);
		} catch (Exception e) {
			throw new ServletException();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// If the command is null, default to listing the students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// Route to appropriate method
			switch(theCommand) {
			case "LIST":
				listStudents(request, response);
				break;
			
			case "ADD":
				addStudent(request, response);
				break;
				
			case "LOAD":
				loadStudent(request, response);
				break;
				
			case "UPDATE":
				updateStudent(request, response);
				break;
				
			case "DELETE":
				deleteStudent(request, response);
				break;
				
			default:
				listStudents(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Read the student id 
		String theStudentId = request.getParameter("studentId");
		
		// Delete student from Database
		studentDBUtil.deleteStudent(theStudentId);
		
		// Send to JSP Page (View)
		listStudents(request, response);
		
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Read the student data 
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
				
		// Create a new student object
		Student theStudent = new Student(id, firstName, lastName, email);
				
		// Perform update on database
		studentDBUtil.updateStudent(theStudent);
				
		// Send to JSP Page (View)
		listStudents(request, response);
		
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Read the student id 
		String theStudentId = request.getParameter("studentId");
				
		// Get student from database
		Student theStudent = studentDBUtil.getStudent(theStudentId);
				
		// Place student in the request attribute
		request.setAttribute("THE_STUDENT", theStudent);
				
		// Send to JSP Page (View)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
		}


	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Read Student Information from Form Data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		// Creating new Student Object
		Student theStudent = new Student(firstName, lastName, email);
		
		// Add the student to the Database
		studentDBUtil.addStudent(theStudent);
		
		// Send back to the Main Page
		listStudents(request, response);
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// List Students from the DBUtil
		List<Student> students = studentDBUtil.getStudents();
		
		// Add students to request
		request.setAttribute("STUDENT_LIST", students);
		
		// Send to JSP Page (View)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
	}

}

<%@page import="java.util.*, com.avi.web.JDBC.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<!DOCTYPE html>
		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta http-equiv="X-UA-Compatible" content="IE=edge">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<title>Student Information Portal</title>
			<link type="text/css" rel="stylesheet" href="css/style.css">
		</head>

		<body>

			<header class="navbar">
				<div class="navbar__brand">
					<div class="navbar__brand-heading">Paul University</div>
					<div class="navbar__brand-subheading">Student Information Portal</div>
				</div>
			</header>

			<div class="main-table">
				<table class="table">
					<tr class="table__row">
						<th class="table__row-head">First Name</th>
						<th class="table__row-head">Last Name</th>
						<th class="table__row-head">Email</th>
						<th class="table__row-head">Action</th>
					</tr>
					<c:forEach var="tempStudent" items="${STUDENT_LIST}">
						<!-- Link to update a student -->
						<c:url var="tempLink" value="StudentControllerServlet">
							<c:param name="command" value="LOAD" />
							<c:param name="studentId" value="${tempStudent.id}" />
						</c:url>
						<!-- Link to delete a student -->
						<c:url var="deleteLink" value="StudentControllerServlet">
							<c:param name="command" value="DELETE" />
							<c:param name="studentId" value="${tempStudent.id}" />
						</c:url>
						<tr>
							<td class="table__row-entry">${tempStudent.firstname}</td>
							<td class="table__row-entry">${tempStudent.lastname}</td>
							<td class="table__row-entry">${tempStudent.email}</td>
							<td class="table__row-entry, table__row-link">
								<a class="table__link" href="${tempLink}">
									Update
								</a>
								 | 
								<a class="table__link" href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">
									Delete
								</a> 
							</td>
						</tr>
					</c:forEach>
				</table>
				<input type="button" value="Add Student"
					onclick="window.location.href='add-student-form.jsp'; return false;" class="add-student-button" />
			</div>

			<footer class="footer">
				<div class="footer__content">
					<section class="left">
						<a href="https://www.linkedin.com/in/avishek-paul-b275521b5/" class="footer__item">Made by <span
								class="nextline">Avishek Paul</span></a>
					</section>
					<section class="right">
						<a href="https://github.com/av1paul" class="footer__item">Check out my <span
								class="nextline">other projects</span></a>
					</section>
				</div>
			</footer>
		</body>

		</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.clicker.databaseconn.DatabaseQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility*/
	
	DatabaseConnection dbconn = new DatabaseConnection();
	DatabaseQueries dbqueries = new DatabaseQueries();
	
	Connection conn = dbconn.createDatabaseConnection();
	
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility

	String InstructorID = (String) session.getAttribute("InstructorID");

	System.out.println("Instructor ID is : " + InstructorID);


	if (InstructorID == null) {
		request.setAttribute("Error",
				"Your session has expired.");
		RequestDispatcher rd = request
				.getRequestDispatcher("../../jsp/instructorJspPages/InstructorError.jsp");
		rd.forward(request, response);
	}
	
	String[] Courses = dbqueries.getInstructorIDCourseID(conn, InstructorID);
	conn.close();
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<script src="../../javascript/jquery-1.3.2.min.js"></script>
<script src="../../javascript/jquery-spin.js"></script>
<script src="../../javascript/attendance.js"></script>
<title>Insert title here</title>
</head>
<title>Instructor Aakash Clicker</title>
</head>
<body>
	<center>
		<%@ include file="../newMenu/newMenuwithCSS.jsp"%> 
		 
		<div id="content_in">
			<div style="min-height: 300px; font-weight: bold">
				<center>
					<div style="height: 20px; text-align: left; font-size: 14px"></div>
					<h1>Attendance Module</h1>
					<div style="height: 50px"></div>
					Course ID :<%=session.getAttribute("courseID") %>		
					<br /><br />
					<!-- <form name="Timer">
    <br/>
    <div style="font-size:14px;font-weight: bold">
     Set Time</div>	
		<div style="font-weight: bold;">Minutes
		<input type="text" style="width: 30px" id="Minutes" value="0" />
		Seconds
		<input type="text" style="width: 30px" id="Seconds" value="30" /></div>		
	</form>  -->
					<p />
					<input type="button" value="Collect Attendance"
						onclick="launchAttendanceURL()" /> <br />
				</center>

			</div>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
</body>
</html>
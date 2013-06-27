<%@page import="com.clicker.databaseconn.DatabaseQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="java.sql.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility*/

String InstructorID = (String) session.getAttribute("InstructorID");

if (InstructorID == null) {
	request.setAttribute("Error",
			"Your session has expired.");
	RequestDispatcher rd = request
			.getRequestDispatcher("../../jsp/instructorJspPages/InstructorError.jsp");
	rd.forward(request, response);
}

DatabaseConnection dbconn = new DatabaseConnection();
DatabaseQueries dbqueries = new DatabaseQueries();

Connection conn = dbconn.createDatabaseConnection();
String[] Courses = dbqueries.getInstructorIDCourseID(conn, InstructorID);

session.setAttribute("InstructorCourse", Courses[0]);

conn.close();
%>
<html>
<head>
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../../javascript/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="../../javascript/QuizBank.js">
</script>

<title>Instructor Aakash Clicker</title>
<script type="text/javascript" src="../../javascript/QuizBank.js">
</script>

</head>
<body>
	<center>
		<%@ include file="../newMenu/newMenuwithCSS.jsp"%> 
		<div id="content_in">
			<center>
				<div style="height: 425px; font-weight: bold;">
					 <div style="height: 10px"></div>
					<div Style="float: left">
						Search Question Available for creating new quiz.<br />
						<br /> <label for="text1">Enter keyword : </label><input
							type="text" value="" id="searchKeyword" name="searchKeyword">&nbsp;&nbsp;<input
							type="submit" value="Search" style="background-color: #3366CC"
							onclick="getquestion(document.getElementById('searchKeyword').value);" />
						<br />
						<div id="listOfQuestion"></div>
					</div>
					<div></div>

				</div>

				<div style="height: 10px; width: 200px; padding-top: 10px"></div>
			</center>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
</body>
</html>
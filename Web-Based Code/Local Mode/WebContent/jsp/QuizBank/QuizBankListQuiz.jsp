<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.clicker.databaseconn.DatabaseQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="com.clicker.wrappers.*"%>

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
	return;
}


String CourseID = request.getParameter("CourseID").toString();

DatabaseConnection dbconn = new DatabaseConnection();
DatabaseQueries dbqueries = new DatabaseQueries();

Connection conn = dbconn.createDatabaseConnection();

Vector<Quiz> Quiz_Details = dbqueries.getQuizIDName(conn, CourseID, InstructorID);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../../javascript/QuizBank.js">
</script>
<script type="text/javascript">

</script>
<title>Aakash Clicker Quiz</title>
</head>
<body>
	<form id="QuizBankForm"
		style="margin-left: 0px; margin-right: 0px; padding-left: 0px; padding-right: 0px">
		<div style="font-weight: bold;">

			<select name="QuizNameSelect" id="QuizNameSelect" size="12"
				style="width: 200px;" onchange="showquestion(QuizNameSelect.value);">
				<%
for (int i=0; i<Quiz_Details.size(); i++)
{%>
				<option><%=Quiz_Details.elementAt(i).getQuizName()%></option>
				<%} conn.close();
%>
			</select>

		</div>
	</form>
</body>
</html>
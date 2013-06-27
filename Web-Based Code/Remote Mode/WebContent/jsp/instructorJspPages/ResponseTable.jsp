<!-- In this JSP, responses are got from database 
which were recorded by their on tablets in local mode.-->

<%@page import="com.clicker.wrappers.StudentResponse"%>
<%@page import="java.sql.*"%>
<%@page import="com.clicker.databaseconn.StudentResponses"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="com.clicker.util.StudentResponsesTableData"%>
<%@page import="java.util.*"%>

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
StudentResponses responses = new StudentResponses();

Connection conn = dbconn.createDatabaseConnection();

int QuizRecordID = Integer.parseInt(session.getAttribute("QuizRecordID").toString());

String QuestionID = (String)request.getParameter("QuestionID");
Vector<StudentResponse> StudentResponses = responses.getStudentResponses(conn, QuizRecordID, QuestionID);
//Vector<StudentResponse>StudentResponses = responses.getDemoStudentResponses(conn, QuizRecordID, QuestionID);

String table_data = "<table id='restbl' border='1' style='width: 580px;'><tr><td>Student ID</td><td>Student Name</td><td>Response</td></tr>";
// String table_data = "<table id='restbl' border='1' style='width: 580px;'><tr><td>Student ID</td><td>Response</td></tr>";

String table_response_data = StudentResponsesTableData.getStudentResponsesTableData(StudentResponses);
//String table_response_data = StudentResponsesTableData.getDemoStudentResponsesTableData(StudentResponses);

table_data = table_data + table_response_data;
table_data = table_data + "</table>";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Responses</title>
</head>
<body>
	<center>
		<div style="height: 20px"></div>
		<% 
String Query = "SELECT count(*) as NoofStudent FROM studentcourse dsc, instructorcourse ic  where dsc.CourseID = ic.CourseID and ic.InstrID = '"+InstructorID+"'";


Statement st = conn.createStatement();
ResultSet rs = st.executeQuery(Query);
String numberofStudent = "";
try {
	if (rs.next()) {
		numberofStudent = rs.getString("NoofStudent");
	}
} catch (SQLException e) {
	e.printStackTrace();
}
out.println(table_data);
conn.close();
%>
	</center>
</body>
</html>
@#<%=numberofStudent%>
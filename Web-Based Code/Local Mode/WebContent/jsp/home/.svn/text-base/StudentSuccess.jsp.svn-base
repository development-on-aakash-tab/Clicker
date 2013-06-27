<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@page import="com.clicker.databaseconn.DatabaseQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="java.util.*"%>
<%@page import="com.clicker.wrappers.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String StudentID =(String) session.getAttribute("StudentID");
	DatabaseConnection dbconn = new DatabaseConnection();
	DatabaseQueries dbqueries = new DatabaseQueries();
	System.out.println("Student id is : " + StudentID);
	if (StudentID == null) {
		request.setAttribute("Error", "Session has ended.  Please login.");
		RequestDispatcher rd = request.getRequestDispatcher("../../jsp/errorpages/Error.jsp");
		rd.forward(request, response);
		return;
	}
	Connection conn = null;
	String CourseIDs[] =null;
	try{
	conn = dbconn.createDatabaseConnection();
	CourseIDs = dbqueries.getStudentCourses(conn, StudentID);	
	}catch(Exception ex){
		ex.printStackTrace();
	}finally{
		if(conn!=null)conn.close();
	}
%>
<html>
<head>
<script type="text/javascript">
function validateCourseID(){	
	try{
		if(document.forms["CourseIDform"].elements["courseID"].value=="Select Course"){
			alert("Select a valid course");
			return false;
		}
		else{
			return true;
		}
	}
	catch(err){
		alert(err.message);
	}
}
</script>
<SCRIPT type="text/javascript">
    window.history.forward(1);
</script>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Student</title>
</head>
<body style="background-color:#99C2FF ;" onload="javascript:window.history.forward();">
	<div id="content_in" style="text-align: center;">
		<h1>Select your course ID !</h1>
		<br />
		<form id="CourseIDform" action="../../setCourse" method="GET" onsubmit="return validateCourseID()">
			<pre style="font-size: 20px; display: inline;">Select Course </pre> <select id="StudentCourseSelect" style="height: 45px; background-color:#E0E0E0; font-size: 20px;"	name="courseID">
				<option>Select Course</option>
					<%for (int i=0; i<CourseIDs.length; i++){%>
				<option><%=CourseIDs[i]%></option>
			<%}%>
			</select> <br>
			<br> <input type="submit" style="height: 60px; font-size: 20px;" value=" Set Course " />
		</form>				
	</div>
</body>
</html>

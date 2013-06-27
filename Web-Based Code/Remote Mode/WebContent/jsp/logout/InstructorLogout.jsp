<%@page import="com.clicker.databaseconn.QuizRecordQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="java.sql.*"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.Writer"%>
<%@page import="java.io.File"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility

String InstructorID =(String) session.getAttribute("InstructorID");

System.out.println("Instructor ID is : " + InstructorID);

if (InstructorID == null) {
	request.setAttribute("Error", "Session has ended.  Please login.");
	RequestDispatcher rd = request.getRequestDispatcher("../../jsp/instructorJspPages/InstructorError.jsp");
	rd.forward(request, response);
	return;
}

/*String filepath = getServletContext().getRealPath("/");
File file = new File(filepath + "sversion.txt");

if (!file.exists()) {
    try {
        file.createNewFile();
        Writer output = new BufferedWriter(new FileWriter(file));
        output.write("0");
        output.close();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}
else
{
	 try {
	        file.delete();
	        file.createNewFile();
		 	Writer output = new BufferedWriter(new FileWriter(file));
	        output.write("0");
	        output.close();
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }	
}

String CourseID = (String)session.getAttribute("InstructorCourse");
DatabaseConnection dbconn = new DatabaseConnection();
DatabaseQueries dbqueries = new DatabaseQueries();
Connection conn = dbconn.createDatabaseConnection();
dbqueries.updateCourseStatus(conn, 0, CourseID);
conn.close();

application.removeAttribute(InstructorID+"QuizStatus");
application.removeAttribute(InstructorID+"QuizName");
application.removeAttribute(InstructorID+"QuizID");
application.removeAttribute(InstructorID+"Questiondetails");
application.removeAttribute(InstructorID+"minutes");
application.removeAttribute(InstructorID+"seconds");
application.removeAttribute(InstructorID+"AttendanceStatus");
application.removeAttribute(InstructorID+"Attendance_TS");
session.removeAttribute("InstructorID");
session.removeAttribute("InstructorCourse");
session.removeAttribute("Error");
session.removeAttribute("QuizRecordID");
session.removeAttribute("CorrectAnswer");
session.removeAttribute("Usertype");
session.removeAttribute("Mode");
session.removeAttribute("QuizTimestamp");*/
session.invalidate();
%>

<html>
<head>
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<meta http-equiv="Refresh" content="2;url=../../Login.jsp">
<title>Instructor-Logout</title>
</head>
<body>
	<center>
		<%@ include file="../../jsp/includes/header.jsp"%>
		<div id="content_in">
			<div style="min-height: 425px">
				<center>
					<div id="log_out">
						<br> <br> <br> <br>
						<h2 style="color:">
							You have successfully logged out.<br> Thank you
						</h2>
						<br />
					</div>
				</center>
			</div>
		</div>
	</center>
	<%@ include file="../../jsp/includes/footer.jsp"%>
</body>
</html>
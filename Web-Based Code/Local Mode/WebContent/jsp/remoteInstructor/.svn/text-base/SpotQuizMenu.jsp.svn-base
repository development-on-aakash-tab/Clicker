<%@page import="com.clicker.databaseconn.DatabaseQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="com.clicker.util.RemoteXML"%>
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
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility*/

String InstructorID = (String) session.getAttribute("InstructorID");

if (InstructorID == null) {
	request.setAttribute("Error",
			"Your session has expired.");
	RequestDispatcher rd = request
			.getRequestDispatcher("../../jsp/remoteInstructor/InstructorError.jsp");
	rd.forward(request, response);
}

DatabaseConnection dbconn = new DatabaseConnection();
DatabaseQueries dbqueries = new DatabaseQueries();

String filepath = getServletContext().getRealPath("/");
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

RemoteXML remoteXML = new RemoteXML();
remoteXML.makeClickerRemoteResponseArchive();

Connection conn = dbconn.createDatabaseConnection();
String[] Courses = dbqueries.getInstructorIDCourseID(conn, InstructorID);

session.setAttribute("InstructorCourse", Courses[0]);
System.out.println("Course name in spotquizmenu"+Courses[0]);
conn.close();
%>
<html>
<head>
<link href="../../styles.css" rel="stylesheet" type="text/css"
	media="screen" />
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../../javascript/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="../../javascript/RemoteQuiz.js">
</script>

<title>Instructor Aakash Clicker</title>
</head>
<body onload="showSpotQuiz('<%=Courses[0]%>')">
	<center>
		<%@ include file="../../jsp/newMenu/newMenuForRemoteInst.jsp"%>
		<div id="content_in">
			<div style="min-height: 425px; font-weight: bold">
				<center>
					<div style="height: 20px; text-align: left">Spot Quiz Module</div>
					<h1>Spot Quiz Module</h1>
					<div style="height: 50px"></div>
					<p />
					<br />
					<div id="SpotQuizList"></div>
					<br />
				</center>
			</div>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
</body>
</html>
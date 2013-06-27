<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.clicker.databaseconn.DatabaseQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="com.clicker.wrappers.*"%>
<%@page import="com.clicker.CreateQuiz.*"%>
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

int qz = Integer.parseInt(request.getParameter("quiztype"));
String CourseID = request.getParameter("CourseID").toString();
QuizNameSelect qns = new QuizNameSelect( );
String quiz_names[ ] = qns.quiz_Name(qz, CourseID);

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../../javascript/Quiz.js">
</script>
<script type="text/javascript">

</script>
<script type="text/javascript">
	var qz=<%=qz%>;
</script>
<title>Aakash Clicker Quiz</title>
</head>
<body>
	<form id="ListQuizForm">
		<div style="font-weight: bold; text-align: center;">
			Select Quiz : <select name="QuizNameSelect" id="QuizNameSelect">
				<option>Select Quiz</option>
				<%
				for (int i=0; i < quiz_names.length; i++)
				{
				System.out.println("quiz name......"+quiz_names[i]);
				%>
				<option><%=quiz_names[i]%></option>
				<%}%>

			</select>
			<p />
			 <br /> <input type="button" value="Next"
				onclick="showQuizdlg(QuizNameSelect.value,'2');" />
		</div>
	</form>
</body>
</html>
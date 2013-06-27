<%@page import="com.clicker.report.ReportHelper"%>
<%@page import="com.clicker.util.GenerateRandomChar"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="com.clicker.wrappers.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% 
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility

String InstructorID = (String) session.getAttribute("InstructorID");

if (InstructorID == null) {
	request.setAttribute("Error",   
			"Your session has expired.");
	RequestDispatcher rd = request
			.getRequestDispatcher("../../jsp/instructorJspPages/InstructorError.jsp");
	rd.forward(request, response);
	return;
}
String qid = session.getAttribute("QuizID").toString();
String qts = session.getAttribute("QuizRecordTS").toString();
ReportHelper reportHelper = new ReportHelper();
String noofQuestions = reportHelper.getQuestionIDs(qid);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../../javascript/Responses.js"></script>
<script src="../../jquery/jquery-1.5.min.js"></script>
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<script src="../../jquery/jquery-1.8.19-ui.min.js"></script>
<link href="../../jquery/jquery-ui.css" rel="stylesheet" type="text/css" />
<title>Responses</title>
</head>
<body onload="getChart('<%=qid%>','<%=qts%>','<%=noofQuestions%>','<%=InstructorID%>' )">
	<center>
		<%@ include file="../newMenu/newMenuwithCSS.jsp"%> 
		<div id="content_in" style="overflow: auto;">
		<a href="Responses.jsp">View in Details</a> <br/>
		<div id="responseTimes" style="color: white;"><%=request.getParameter("resp_dbtime")%></div>
		<div id="responseChart" style="min-height: 325px; font-weight: bold; margin-top: 30px;">
			<center>Waiting for get response ...
				<br/><br/><br/>
				<img alt="Loading..." src="../../images/loading_transparent1.gif">					
			</center>
		</div>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
	<div id="ResponseDialog"></div>
</body>
</html>
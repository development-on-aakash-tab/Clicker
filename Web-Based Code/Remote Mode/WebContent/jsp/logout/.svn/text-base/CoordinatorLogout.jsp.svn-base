<%-- Author : Manjur and  Dipti.G --%>
<%@page import="com.clicker.databaseconn.QuizRecordQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="java.sql.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility

String CoordinatorID=(String) session.getAttribute("CoordinatorID");

System.out.println("Logout Coordinator ID is : " + CoordinatorID);

if (CoordinatorID == null) {
	request.setAttribute("Error", "Session has ended.  Please login.");
	RequestDispatcher rd = request.getRequestDispatcher("../../jsp/CoordinatorJspPages/CoordinatorError.jsp");
	rd.forward(request, response);
	return;
}
session.removeAttribute("CoordinatorID");
application.removeAttribute(CoordinatorID+"QuizID");
application.removeAttribute(CoordinatorID+"Questiondetails");
application.removeAttribute(CoordinatorID+"QuizStatus");
application.removeAttribute(CoordinatorID+"minutes");
application.removeAttribute(CoordinatorID+"seconds");
application.removeAttribute(CoordinatorID+"RemoteResponseXML");
session.removeAttribute("CoordinatorUN");
session.removeAttribute("Error");
session.invalidate();
System.out.println("Session removed ");
%>

<html>
<head>
<link href="styles.css" rel="stylesheet" type="text/css"
	media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Refresh" content="2;url=../../Login.jsp">
<title>Instructor-Logout</title>
</head>
<body>
<center>
<%@ include file="../../jsp/includes/header.jsp" %>
<div id="content_in">
<div style="min-height: 425px">
<center>
<div id="log_out">
<br>
<br>
<br>
<br>
<h2 style="color: #FFFFFF">You have successfully logged out.<br> Thank you</h2>
<br/>
</div>
</center>
</div>
</div>
</center>
<%@ include file="../../jsp/includes/footer.jsp" %>
</body>
</html>
<%-- Author : Manjur and  Dipti.G --%>
<%@page import="com.clicker.global.AakashClickerGlobal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility

String ParticipantID =(String) session.getAttribute("ParticipantID");

System.out.println("Student id is : " + ParticipantID );

if (ParticipantID == null) {
	request.setAttribute("Error", "Session has ended.");
	RequestDispatcher rd = request.getRequestDispatcher("../../jsp/ParticipantJspPages/ParticipantError.jsp");
	rd.forward(request, response);
	return;
}

AakashClickerGlobal.ParticipantIDs.remove(ParticipantID);
System.out.println("Participant Session removed from List");
session.removeAttribute("Usertype");
session.removeAttribute("CoordinatorID");
session.removeAttribute("ParticipantID");
session.removeAttribute("Error");
session.invalidate();
%>
<html>
<head>
<link href="styles.css" rel="stylesheet" type="text/css"
	media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta http-equiv="Refresh" content="2;url=../../Login.jsp"> -->
<title>Student Logout</title>
</head>
<body>
<center>
<%@ include file="../../jsp/includes/header.jsp" %>
<div id="content_in">
<div style="min-height: 300px">
<center>
<div id="log_out">
<br>
<br>
<br>
<br>
<h2 style="color: #FFFFFF">You have successfully logged out.<br> Thank you</h2>
<br>
<br/>
</div>
</center>
</div>
</div>
<%@ include file="../../jsp/includes/footer.jsp" %>
</center>
</body>
</html>
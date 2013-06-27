<%@page import="com.clicker.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility

	String Coordinator = (String) session.getAttribute("CoordinatorID");
	System.out.println("Coordinator ID is : " +  Coordinator);
	if (Coordinator == null) {
		request.setAttribute("Error","Your session has expired.");
		RequestDispatcher rd = request.getRequestDispatcher("../../jsp/errorpages/Error.jsp");
		rd.forward(request, response);
		return;
	}	
%>
<html>
<head>
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Refresh" content="2;url=../../jsp/CoordinatorJspPages/QuizStatusListener.jsp">
<title>Instructor</title>
</head>
<body>
<center>
<%@ include file="../../jsp/includes/menuheaderForCoordinator.jsp" %>
<div id="content_in">
<div style="min-height: 425px">
<div style="height:20px"> </div>
	<h1>Welcome Coordinator !</h1>	
	<br/>	
	</div>
</div>
<%@ include file="../../jsp/includes/footer.jsp" %>
</center>
</body>
</html>
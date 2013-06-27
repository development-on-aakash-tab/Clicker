<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility*/

String message = request.getAttribute("Error").toString();
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Session End</title>
</head>
<body>
<center>
<%@ include file="../../jsp/includes/header.jsp" %>
<div id="content_in">
<div style="min-height: 300px; font-weight: bold">
 <center> 
 <div style="height:20px"></div>
 <font color="#fff" size="12px"><%=message%></font> 
 <div style="height:50px"></div>
 <h2 style="color:#fff">Close the Browser and Login by Clicker App.</h2>
 </center>
</div>
</div>
<%@ include file="../../jsp/includes/footer.jsp" %>
</center>
</body>
</html>
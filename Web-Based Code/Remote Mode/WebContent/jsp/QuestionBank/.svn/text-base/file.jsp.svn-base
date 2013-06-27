<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility*/
String insid=(String)session.getAttribute("instructorid");
session.setAttribute("instrid",insid);
if (insid == null) {
	request.setAttribute("Error","Your session has expired.");
	RequestDispatcher rd = request.getRequestDispatcher("../../jsp/errorpages/Error.jsp");
	rd.forward(request, response);
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>XLS Upload file </title>
</head>
<body>
<form action="XLSUpload.jsp" method="get">
<input type="file" name="xls"></input>
<input type="submit" value="send" /></form>
</body>
</html>
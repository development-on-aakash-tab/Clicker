<%@page import="javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
int qtype = Integer.parseInt(request.getParameter("qtype"));

switch(qtype)
{
case 1:
	response.sendRedirect("singleanswer.jsp");
	break;
case 2:
	response.sendRedirect("MultChoice.jsp");
	break;
case 3:
	response.sendRedirect("trueFalse.jsp");
	break;
case 4:
	response.sendRedirect("integer.jsp");
	break;
}
//response.sendRedirect("questionbak.jsp");
%>
</body>
</html>
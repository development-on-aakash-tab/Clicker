<%-- Author : Manjur and  Dipti.G --%>
<%@ page language="java" isErrorPage="true"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
String message = request.getParameter("Error").toString();
System.out.println(message);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Session End</title>
</head>
<body>
	<center>
		<%@ include file="../../jsp/includes/header.jsp"%>
		<div id="content_in">
			<div style="min-height: 300px">
				<center>
					<font color="" size="6px"><%=message %></font> <br> 
					<!-- <a href="../../Login.jsp">Please Login</a>-->
				</center>
			</div>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
</body>
</html>
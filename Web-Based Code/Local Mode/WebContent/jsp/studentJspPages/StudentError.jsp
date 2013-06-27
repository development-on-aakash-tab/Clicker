<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String message = request.getAttribute("Error").toString();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Session End</title>
</head>
<body>
	<div id="content_in" style="text-align: center;">
		<div style="min-height: 300px; font-weight: bold">
				<div style="height: 20px"></div>
				<font color="#fff" size="12px"><%=message%></font>
			<div style="height: 50px"></div>
				<h2>Close the Browser and Login by Clicker App.</h2>
		</div>
	</div>
</body>
</html>
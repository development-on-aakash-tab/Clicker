<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Multiple Login is not allowed</title>
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<center>
<%@ include file="./jsp/includes/header.jsp" %>
<div id="content_in">
<div style="min-height: 300px;text-align: center" >
<div style="height: 40px"></div>
<h1>Sorry, Multiple Login Session Found.</h1>
Your Login Session has been already created with Same Participant ID.
</div>
</div>
<%@ include file="./jsp/includes/footer.jsp" %>
</center>
</body>
</html>
<%@ page language="java" isErrorPage="true"
	contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Multiple Login is not allowed</title>
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
	<center>
		<%@ include file="./jsp/includes/header.jsp"%>
		<div id="content_in">
			<div style="min-height: 300px; font-weight: bold;">
				<center>
					<div style="height: 40px"></div>
					<h1>Sorry, Multiple Login Session Found.</h1>
					Your Login Session has been already created with Same StudentID.
				</center>
			</div>
		</div>
		<%@ include file="./jsp/includes/footer.jsp"%>
	</center>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>    
<!-- Referance :
Server side Event :
http://www.w3schools.com/html5/html5_serversentevents.asp
http://www.w3schools.com/html5/tryit.asp?filename=tryhtml5_sse

For firefox old version
http://weblog.bocoup.com/javascript-eventsource-now-available-in-firefox/
 -->        
<!DOCTYPE html>
<%
response.setHeader("pragma", "no-cache,no-store");  
response.setHeader("cache-control", "no-cache,no-store,max-age=0,max-stale=0");  
String StudentID =(String) session.getAttribute("StudentID");
if (StudentID == null) {
	request.setAttribute("Error", "Session has ended.  Please login.");
	RequestDispatcher rd = request.getRequestDispatcher("../../jsp/studentJspPages/StudentError.jsp");
	rd.forward(request, response);
	return;
}
%>
<html>
<head>
<title>Quiz Listener</title>
<link href="../../css/style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body style="text-align: center;">
		<%@include file="../newMenu/newMenuForStudent.jsp"%>
		<div id="content_in" >
			<div style="min-height: 350px; text-align:center; font-weight: bold;">				
					<div style="height: 70px; text-align: left;">Quiz Module</div>
					<div id="quizStatus"></div>
					<br/><br/><br/>
					<img alt="Loading..." src="../../images/loading_transparent1.gif">				
			</div>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>	
</body>
</html>
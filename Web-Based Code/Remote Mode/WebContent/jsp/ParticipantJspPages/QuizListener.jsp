<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
if (session.getAttribute("ParticipantID") == null) {
	request.setAttribute("Error", "Session has ended.  Please login.");
	RequestDispatcher rd = request.getRequestDispatcher("ParticipantError.jsp");
	rd.forward(request, response);	
	return;
}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quiz Listener</title>
</head>
<body>
		<%@ include file="../../jsp/includes/menuheaderForParticipant.jsp" %>
		<div id="content_in" style="text-align: center;">
			<div style="min-height: 300px; font-weight: bold;">
					<div id="quizStatus"></div>
					<br/><br/><br/>
					<img alt="Loading..." src="../../images/loading_transparent1.gif">				
			</div>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>	
</body>
</html>
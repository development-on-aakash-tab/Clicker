<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
	String CoordinatorID = (String) session.getAttribute("CoordinatorID");
	if (CoordinatorID == null) {
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
<title>Response Chart</title>
<script src="../../javascript/Responses.js"></script>
<script src="../../jquery/jquery-1.5.min.js"></script>
<script src="../../jquery/jquery-1.8.19-ui.min.js"></script>
<link href="../../jquery/jquery-ui.css" rel="stylesheet" type="text/css" />

</head>
<body onload="generateChart('<%=CoordinatorID%>')">
	<%@ include file="../../jsp/includes/menuheaderForCoordinator.jsp" %>
	<div id="content_in" style="height:auto;">
		<div id="responseChart" align="center" style="min-height: 325px; font-weight: bold; margin-top: 30px;">	
			Generating Chart...<br/><br/><br/>
			<img alt="Loading..." src="../../images/loading_transparent1.gif">			
		</div>
	</div>
	<%@ include file="../../jsp/includes/footer.jsp" %>
	<div id="ResponseDialog"></div>
</body>
</html>
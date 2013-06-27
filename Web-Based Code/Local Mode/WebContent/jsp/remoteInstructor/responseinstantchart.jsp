<%@page import="com.clicker.report.ReportHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility

String InstructorID = (String) session.getAttribute("InstructorID");

if (InstructorID == null) {
	request.setAttribute("Error",
			"Your session has expired."); 
	RequestDispatcher rd = request.getRequestDispatcher("../../jsp/remoteInstructor/InstructorError.jsp");
	rd.forward(request, response);
	return;
}
String responseType = request.getParameter("responseType").toString();
String centerid="";
//String filenames="";
String quizID="";
if(responseType.equals("response")){
	centerid = request.getParameter("centerid").toString();
	quizID = request.getParameter("quizid").toString();	
}else if(responseType.equals("allresponse")){
	centerid = "all";
	quizID = request.getParameter("quizid").toString();	
}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<title>Insert title here</title>
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<script type="text/javascript" src="../../javascript/RemoteResponses.js"></script>
<script src="../../jquery/jquery-1.5.min.js"></script>
<script src="../../jquery/jquery-1.8.19-ui.min.js"></script>
<link href="../../jquery/jquery-ui.css" rel="stylesheet" type="text/css" />
</head>

<body onload="showInstantChart('<%=responseType%>','<%=centerid%>','<%=quizID%>', '<%=InstructorID%>')">
	<center>
		<%@ include	file="../../jsp/newMenu/newMenuForRemoteInst.jsp"%>
		<div id="content_in">
		<a href="#" onclick="back(); return false;"> &lt;&lt; Back </a>
			<div id="remoteresp_Chart" style="max-height: 450px; overflow:auto; font-weight: bold; margin-top: 50px;">
				<center>
					<img alt="Loading" src="../../images/loading_transparent.gif">
				</center>	
			</div>
			<div id="rrdialog"></div>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>	
</body>
</html>
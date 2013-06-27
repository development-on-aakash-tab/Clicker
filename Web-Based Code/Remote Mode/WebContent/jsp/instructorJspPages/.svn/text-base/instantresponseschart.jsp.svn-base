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
	RequestDispatcher rd = request
			.getRequestDispatcher("../../jsp/instructorJspPages/InstructorError.jsp");
	rd.forward(request, response);
	return;
}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../../jquery/jquery-1.5.min.js"></script>
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<script src="../../jquery/jquery-1.8.19-ui.min.js"></script>
<link href="../../jquery/jquery-ui.css" rel="stylesheet" type="text/css" />
<title>Responses</title>
<script type="text/javascript">
function getXMLhttp() {
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE
		try {
			xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
}
var instID = "<%=InstructorID%>";
function generateChart(){
	getXMLhttp();
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			//alert(chartSec);
			var images="";
			images += "<center><img alt='Loading...' src='../../"+instID+"/InstantChart.jpeg?"+new Date().getTime()+"' onclick='showResponsesDialog()'></center>";
			document.getElementById("responseChart").innerHTML = images;
			//storeResponse();			
		}
	};
	
	xmlhttp.open("GET", "../../InstantChart", true);
	xmlhttp.send();
}


function showResponsesDialog() {	
	getXMLhttp();
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			document.getElementById("ResponseDialog").innerHTML = xmlhttp.responseText;
			document.getElementById("ResponseDialog").style.visibility = 'visible';
			document.getElementById("ResponseDialog").title ="Responses";
			$("#ResponseDialog").dialog({height: 400, width: 600, modal: true});
		}
	};	
	xmlhttp.open("GET", "../../jsp/instructorJspPages/responsehelper.jsp?responseType=insatantResponse", true);
	xmlhttp.send();
}

</script>
</head>
<body onload="generateChart()">
	<center>
		<%@ include file="../newMenu/newMenuwithCSS.jsp"%> 
		<div id="content_in" style="overflow: auto;">
		<div id="responseChart" style="min-height: 325px; font-weight: bold; margin-top: 30px;">
			<center>Waiting for get response ...
				<br/><br/><br/>
				<img alt="Loading..." src="../../images/loading_transparent1.gif">					
			</center>
		</div>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
	<div id="ResponseDialog"></div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility*/
String insid=(String)session.getAttribute("instructorid");
session.setAttribute("instrid",insid);
if (insid == null) {
	request.setAttribute("Error","Your session has expired.");
	RequestDispatcher rd = request.getRequestDispatcher("../../jsp/errorpages/Error.jsp");
	rd.forward(request, response);
}
String Usertype = (String) session.getAttribute("Usertype");
%>
<html>
<head>
<!-- djstyles.css contains styling only for Question Bank Module! -->
<link href="../../djstyles.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" language="JavaScript">
function validateForm1()
{
	//var reg=new RegExp("^[a-zA-Z0-9]+[,.!@#$%^&*()_+-=:;,./<>?a-zA-Z0-9]*");
	var x=document.forms["form1"]["question"].value;
	if (x==null || x=="" || x.trim()=="")
	{
  		alert("Question must be filled out!!!");
  		return false;
  	}
		if ((document.getElementById("t").checked==false) && (document.getElementById("f").checked== false) )
	{
		alert("No Answer selected..."); 
		return false;
	}
		alert("Question Submitted successfully");
	return true;
}
</script>

<title>Instructor Aakash Clicker</title>
</head>
<body>
<center>
<% if(session.getAttribute("Mode").equals("Remote")){ %>
<%@ include
			file="../newMenu/newMenuForRemoteInst.jsp"%>
<%}else{ %>
	<%@ include file="../newMenu/newMenuwithCSS.jsp"%>
<%}%>
<div id="content_in">

<div style="margin-left: 0px 0px; clear: both;" align="left" id="addnewQ" >
<div style="float:left; margin: 0px 0px 0px;"><p> Select Question type:</p></div><br>
<div style="float:left; margin: 0px 0px 0px;">
<form id="quesform" action="selectQ.jsp" >
<input type="radio" name="qtype" value="1" onClick="document.getElementById('quesform').submit()"/>Single Choice Correct &nbsp;
<input type="radio" name="qtype" value="2" onClick="document.getElementById('quesform').submit()"/>Multiple Choice Correct &nbsp;
<input type="radio" name="qtype" value="4" onClick="document.getElementById('quesform').submit()"/>Numeric Answer 
&nbsp;&nbsp;&nbsp;
</form>
</div>
<br><br><br><br>
</div>


<h2 class="pageheader" >True or False Questions</h2>
<form action="databaseConn.jsp" name="form1"  method="post" onsubmit="return validateForm1()">
	<p>Your Question here:</p>
	<textarea name="question" cols="20" rows="5" id="Question"></textarea>
	
	
	<br><br>
	<p><input type = "radio" name = "op" id="t" value = "True"> True</p>
	<p><input  type = "radio" name = "op" id="f" value = "False"> False</p><br>

<center><input id="submitB" type = "submit" value = "  Submit  " ></center>
</form>
</div>

<%@ include file="../../../jsp/includes/footer.jsp" %>
</center>
</body>
</html>
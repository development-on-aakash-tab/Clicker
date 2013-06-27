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
<link rel="stylesheet" type="text/css" href="../../styles.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="../../javascript/QuestionBank.js" > </script> 

<title>Multiple Choice Correct</title>
</head>
<body >

<form id="form" method="post" action="Test.jsp" onsubmit="return validateForm()">
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
<input type="radio" name="qtype" value="3" onClick="document.getElementById('quesform').submit()"/>True or False &nbsp;
<input type="radio" name="qtype" value="4" onClick="document.getElementById('quesform').submit()"/>Numeric Answer 
&nbsp;&nbsp;&nbsp;
</form>
</div>
<br><br><br><br>
</div>

	<h2 class="pageheader" >Multiple Choice Questions, Multiple Choice Correct</h2>
	<p>Your Question here:</p>
	<textarea name="Question" cols="20" rows="5" id="Question" ></textarea>	
	
	<br><br>
	A. <input style="margin-left: 5px" type="checkbox" name="A" value="A" id="check1"/> <input class="inputtext" name="1" type="text" id="txt1" /> <input class="remove" id="remove1" type="button" value="  Remove  " onclick="removeOption(1)" /><br><br>
	B. <input style="margin-left: 5px" type="checkbox" name="B" value="B" id="check2"/> <input class="inputtext" name="2" type="text" id="txt2" /> <input class="remove" id="remove2" type="button" value="  Remove  " onclick="removeOption(2)" /><br><br>
	C. <input style="margin-left: 5px" type="checkbox" name="C" value="C" id="check3"/> <input class="inputtext" name="3" type="text" id="txt3" /> <input class="remove" id="remove3" type="button" value="  Remove  " onclick="removeOption(3)" /><br><br>
	D. <input style="margin-left: 5px" type="checkbox" name="D" value="D" id="check4"/> <input class="inputtext" name="4" type="text" id="txt4" /> <input class="remove" id="remove4" type="button" value="  Remove  " onclick="removeOption(4)" /><br>
	<br>
	<input type="hidden" id="hidden_count" name="Count" value="0" />
	<input type="button" id="add" onclick="addOption()" value="  Add Option  " />
	
	<center><input id="submitB" type="submit" value="  Submit  " /></center>
</div>

<%@ include file="../../jsp/includes/footer.jsp" %>
</center>
</form>
</body>
</html>

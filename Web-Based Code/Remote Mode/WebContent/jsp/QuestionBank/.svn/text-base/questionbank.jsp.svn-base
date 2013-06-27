<%@ page language="java" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.sql.*" %>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import ="com.clicker.report.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%!String InstrID=null; %>
<%
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility*/
String InstructorID = (String) session.getAttribute("insid");
session.setAttribute("instructorid",InstructorID);
InstrID=InstructorID;
System.out.println("Instructor ID is : " + InstructorID);

if (InstructorID == null) {
	request.setAttribute("Error","Your session has expired.");
	RequestDispatcher rd = request.getRequestDispatcher("../../jsp/errorpages/Error.jsp");
	rd.forward(request, response);
}

System.out.println((String)session.getAttribute("Usertype"));
%>
<HTML>
<head>
<!-- djstyles.css contains styling only for Question Bank Module! -->
<link href="../../djstyles.css" rel="stylesheet" type="text/css" media="screen" />
<link href="../../jquery-ui-1.8.21.custom.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<script type="text/javascript" src="../../javascript/jquery.js"></script>
<script type="text/javascript" src="../../javascript/jquery-ui.min.js"></script>
<title>Question Bank</title>
<script type="text/javascript" src="questionbank.js"></script>
<script type="text/javascript">
   
$(document).ready(function(){
	<%if(request.getParameter("fileUploadStatus")!=null){%>
	alert('<%=request.getParameter("fileUploadStatus")%>');
	<%}%>
});

function validate()
{
	if(document.frm.question.value=="")
	{
		alert("Please enter question");
		document.frm.question.focus();
		return false;
	}
}

</script>

</head>
<BODY onload=" qb(); loadQuestion('<%=InstrID%>');">
<% if(session.getAttribute("Mode").equals("Remote")){ 
System.out.println("RaiseHand is not for remote Mode");
%>
<%}else{ %>
<%@ include file="../../jsp/raisehand/RaiseHandSideBar.jsp" %>
<%}%>
<%
	int sumcount=0; 	
	String question= "";
	if(request.getParameter("question")!=null && request.getParameter("question")!="")
    {
		question = request.getParameter("question").toString();
	}
%>

<FORM NAME="frm" METHOD="post" ACTION="" onsubmit="return validate();">
<center>
<% if(session.getAttribute("Mode").equals("Remote")){ %>
<%//@ include file="../newMenu/newMenuForRemoteInst.jsp"%>

<%}else{ %>
	<%@ include file="../newMenu/newMenuwithCSS.jsp"%>
	
<%}%></center>
<div id="content_in">
<div id="qbhead" style="width: auto; text-shadow:3px 3px 3px #88FFFF; float: left; margin: 10px 100px; cursor: pointer;" onclick="qb();" >Question Bank </div>
<div id="addnewQhead" style="width: auto; text-shadow:3px 3px 3px #88FFFF; float: left; margin: 10px 100px; cursor: pointer;" onclick="addQs()" >Add new Questions</div>

<div id="qb" style="clear: both;">

<p> Enter Keyword to search Question Bank: <INPUT class="inputtext" TYPE="TEXT" NAME="question" id="e" value="<%=question%>" onkeyup="filterQuestions();"> </p>
<div> <br/> Select the type of question: <select id = "questiontype" onchange = "loadQuestion('<%=InstrID%>');" >
											<option value = "0" > All Questions </option>
											<option value = "1"> Single Choice correct </option>
											<option value = "2" > Multiple Choice correct </option>
											<option value = "3" > Numerical Answer </option>
											<option value = "4" > True or False </option>
									  	</select> </div>
<div style="float: left; margin: 10px 0px;" >
<select size="10" multiple="multiple" name="quest" id="quest" style="width: 400px;" onchange="loadOptions()">
<option value="-1"></option>
</select>
<div style="clear: both;" align="center" >
<br>
<input type="checkbox" id="archived" onclick="loadQuestion('<%=InstrID%>')" />&nbsp; Show Archived<br><br>

<INPUT class="buttons" TYPE="button" NAME="delete" VALUE="Delete" onclick="User(this.value);">
<INPUT class="buttons" TYPE="button" NAME="edit" VALUE="Edit" onclick="User(this.value);">
<INPUT class="buttons" TYPE="button" NAME="history" VALUE= "History" onclick= "User (this.value);">
</div>
</div>
<div id="options"  >
<p>Options:</p>
</div>
</div>


<div style="margin-left: 0px 0px; clear: both;" align="left" id="addnewQ" >

<div style="float:left; margin: 10px 10px 10px;"><p> Select Question type:</p></div>   
<div style="float:left; margin: 15px 10px 10px;">
<form id="quesform" action="selectQ.jsp">
<input type="radio" name="qtype" value="1" checked="checked" />Single Choice Correct<br><br>
<input type="radio" name="qtype" value="2" />Multiple Choice Correct<br><br>
<input type="radio" name="qtype" value="3" />True or False<br><br>
<input type="radio" name="qtype" value="4" />Numeric Answer<br><br>
<!-- 
<select name="qtype" id="qtype" size="4" style="width: 200px;"> 
   <option value="0">Single Choice Correct</option>
   <option value="1">Multiple Choice Correct</option>
   <option value="2">True or False</option>
   <option value="3">Numeric Answer</option>
</select>
 -->
<INPUT class="buttons" TYPE="submit" NAME="addnew" VALUE="  ADD  " />
</form>

</div>   



<div style="clear: both;">
<p> Add Questions from .xls file: </p>
</div>
<div style="margin: 10px 0px;">

<input style="height: 25px; font-size: medium;" type="file" id="file" name="xls"></input>
<input style="height: 25px; font-size: medium;" type="button" value="  Preview  " onclick="uploadXLS()"/>
<a href="../../questiontemplate/Question_Template.xls">Question Template</a>

</div>
</div>

<!--</center></div></div>-->
<%@ include file="../../jsp/includes/footer.jsp" %>
</FORM>
<div id="dialog" title="File Preview">
<form action="XLSUpload.jsp" method="get" id="uploadForm">
	<iframe id="frame" >
	</iframe ><br/><br/>
	<center>
		<input type="hidden" name=xls id="xls">
		<input type="submit" value=" Add Questions "/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value=' Cancel ' onclick="closeDialog()"/>
	</center>
</form>
</div>
</BODY>
</HTML>
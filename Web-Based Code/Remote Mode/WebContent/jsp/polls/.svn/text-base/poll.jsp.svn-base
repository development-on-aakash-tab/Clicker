<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.clicker.raisehand.*" %>
<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility

	String InstructorID = (String) session.getAttribute("InstructorID");
    session.setAttribute("insid",InstructorID);
	System.out.println("Instructor ID is : " + InstructorID);

	if (InstructorID == null) {
		request.setAttribute("Error","Your session has expired.");
		RequestDispatcher rd = request.getRequestDispatcher("../../jsp/errorpages/Error.jsp");
		rd.forward(request, response);
	}
	System.out.println("Course ID :"+(String)session.getAttribute("courseID"));
	System.out.println((String)session.getAttribute("Usertype"));
String usertype=session.getAttribute("Usertype").toString();
session.setAttribute("UserType",usertype);
//String courseid=session.getAttribute("courseid").toString();
//session.setAttribute("courseID",courseid);
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="../../djstyles.css" rel="stylesheet" type="text/css" media="screen" />
<link href="../../styles.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Polls</title>
</head>
<script type="text/javascript">
var call;
var callstop = 0;
function load()
{
	if((document.getElementById("poll_question").value).trim()!="" && (document.getElementById("poll_question").value).trim()!=null)
	{
		call=setInterval(function (){poll_res();}, 2000);
		document.getElementById("poll_question").disabled="disabled";
		document.getElementById("submitB").disabled="disabled";
	}
	else
		{
			alert("Please Enter a valid question.");
		}
}
function check()
{
	//alert("In Check");
	var xmlhttp;
	if(window.XMLHttpRequest){
		xmlhttp=new XMLHttpRequest();
	}
	else{
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
// 	 		alert(xmlhttp.status);
			if(xmlhttp.readyState==4 && xmlhttp.status==200){
				/*
				 Response of the form boolean~poll_question~callstop.
				*/
			var result=xmlhttp.responseText.split("~");
// 				alert(result[0]+":"+result[1]+":"+result[2]);
				
			if(result[0]=="true")
				{
					callstop=result[2];
					if(callstop++<15)
					{
						document.getElementById("poll_question").value=result[1];
						document.getElementById("poll_question").disabled="disabled";
						document.getElementById("submitB").disabled="disabled";
						load();
					}
					else
					{
// 						alert("callstop>=7");
						//document.getElementById("poll_question").disabled="disabled";
						//document.getElementById("submitB").disabled="disabled";
						document.getElementById("poll_question").value=result[1];
						document.getElementById("poll_question").disabled="disabled";
						document.getElementById("submitB").disabled="disabled";
						alert("Poll has ended.! Wait here to see the result. ");
						load();
					}
				}
				
				
					
	}
	}
	xmlhttp.open("GET","../../CheckActive",true);
	xmlhttp.send();
}
function poll_res()
{
	var xmlhttp;
	if(window.XMLHttpRequest){
		xmlhttp=new XMLHttpRequest();
	}
	else{
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
	// 		alert(xmlhttp.status);
			if(xmlhttp.readyState==4 && xmlhttp.status==200){
			
				document.getElementById("poll_results").innerHTML=xmlhttp.responseText;
	}
	}
	var poll_question = document.getElementById("poll_question").value;
	// alert("-"+poll_question+"`");
	if(poll_question!="" && poll_question!=null)
	{
// 		alert(poll_question!="");
		if(callstop++ >= 15)
		{
 			//alert(callstop);
			clearInterval(call);
		}
		xmlhttp.open("GET","../../Poll?poll_question="+poll_question,true);
		xmlhttp.send();
		
	}
}
function reset()
{
// 	alert("reset");
	document.getElementById("poll_question").disabled=false;
	document.getElementById("submitB").disabled=false;
	document.getElementById("poll_question").value="";
	callstop = 0;
}
</script>
<body onload="check();">



<center>
<%@ include file="../newMenu/newMenuwithCSS.jsp"%> 
<div id="content_in" >
<form method="get" action="../../Poll" id="polls">
<br/>
<h2 class="pageheader" >Welcome to Polls</h2>
<p>Enter your poll Question: </p>
<textarea rows="2" cols="60" name="poll_question" id="poll_question" ></textarea>
<center><input id="submitB" type="button" value="  Conduct Poll  " onclick="load()" /></center>
	
</form>
<h2 class="pageheader">Poll Results</h2>

<div id="poll_results">
<!-- <p> Total Students participated: 50</p> -->
<!-- <br> -->
<%-- <div style="float: left; width: 170px;" >YES<br>(<%=Math.round(no_yes) %>)</div><div style="margin-left: 50px; height: 50px; width: <%=5*no_yes %>px; background-color: green; opacity: 0.70;"></div> --%>
<!-- <br> -->
<%-- <div style="float: left; width: 70px;" >NO<br>(<%=Math.round(no_no) %>)</div><div style="margin-left: 50px; height: 50px; width: <%=5*no_no%>px; background-color: red; opacity: 0.70;"></div> --%>
</div>
</div>
<%@ include file="../../jsp/includes/footer.jsp" %>
</center>
</body>
</html>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.clicker.raisehand.*" %>
<% String courseid=(String)session.getAttribute("courseID"); 
session.setAttribute("courseID",courseid);
String UserType=(String)session.getAttribute("Usertype"); 
session.setAttribute("UserType",UserType);
%>

<html>
<head>
<link href="../../css/style.css" rel="stylesheet" type="text/css" media="screen" />
<style type="text/css">
/* RADIO */
.regular-radio {
	display: none;
}
.regular-radio + label {
	-webkit-appearance: none;
	background-color: #fafafa;
	border: 1px solid #cacece;
	box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0px -13px 8px -10px rgba(0,0,0,0.05);
	padding: 9px;
	border-radius: 35px;
	display: inline-block;
	position: relative;
}
.regular-radio:checked + label:after {
	content: ' ';
	width: 10px;
	height: 10px;
	border-radius: 30px;
	position: absolute;
	top: 3px;
	background: #00FF00;
	box-shadow: inset 0px 0px 8px rgba(0,0,0,0.3);
	text-shadow: 0px;
	left: 3px;
	font-size: 32px;
}
.regular-radio:checked + label {
	background-color: #e9ecee;
	color: #99a1a7;
	border: 1px solid #adb8c0;
	box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0px -13px 8px -10px rgba(0,0,0,0.05), inset 13px 8px -10px rgba(255,255,255,0.1), inset 0px 0px 8px rgba(0,0,0,0.1);
}
.regular-radio + label:active, .regular-radio:checked + label:active {
	box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0px 1px 3px rgba(0,0,0,0.1);
}
.big-radio + label {
	padding: 15px;
}
.big-radio:checked + label:after {
	width: 15px;
	height: 15px;
	left: 8px;
	top: 8px;
}
</style>
<script type="text/javascript">
var myvar;
//timer is for setting the time for which the poll will be conducted.
var timer=30;
/*
 * The following method loads the questions that teacher/instructor will give to be polled.
 	This is done through a Ajax script which continously fetches the data from the server.
 */
 var variable= setInterval(function(){loadQuestion();},1000);
function loadQuestion()
{
	var index1,index2,questions;
	var xmlhttp;
	if(window.XMLHttpRequest)
		{
			xmlhttp=new XMLHttpRequest();
		}
	else
		{
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
	xmlhttp.open("GET","../../Poll?time="+new Date(),true);
	xmlhttp.send();
	xmlhttp.onreadystatechange=function()
	{
		if(xmlhttp.readyState==4 && xmlhttp.status==200)
			{
			index_1=xmlhttp.responseText.indexOf("<body>", 0);
			index_2=xmlhttp.responseText.indexOf("</body>",index_1);
			questions=xmlhttp.responseText;
			questions=questions.replace(/(\r\n|\n|\r)/gm, "");
			if(questions=="You cannot cast your vote twice" || questions=="No Live Poll Currently")
				{
					 document.getElementById("poll_quest").innerHTML=questions;
				}
			else if(questions!="")
				{	questions=xmlhttp.responseText.split("~");
					document.getElementById("poll_quest").innerHTML= "<p>Your Poll Question:</p><br><p>"+questions[0]+"</p>";
					timer=parseInt(questions[1],10);
				var radioInput1=document.createElement("input");
				radioInput1.setAttribute("type","radio");
				radioInput1.setAttribute("name","vote");
				radioInput1.setAttribute("value", "  Yes");
				radioInput1.setAttribute("id", "  Yes");
				radioInput1.setAttribute("class", "regular-radio big-radio");
				var bigradio1=document.createElement("label");
				bigradio1.setAttribute("for","radio-2-1");
				bigradio1.innerHTML="";
				var label1=document.createElement("label");
				label1.innerHTML=" Yes";
				var radioInput2=document.createElement("input");
				radioInput2.setAttribute("type","radio");
				radioInput2.setAttribute("name","vote");
				radioInput2.setAttribute("value", "  No");
				radioInput2.setAttribute("id", "  No");
				radioInput2.setAttribute("class", "regular-radio big-radio");
				var bigradio2=document.createElement("label");
				bigradio2.setAttribute("for","radio-2-1");
				bigradio2.innerHTML="";				
				var label2=document.createElement("label");
				label2.innerHTML=" No";
				var opt=document.getElementById("option");
				opt.setAttribute("method", "post");
				opt.setAttribute("action", "../../Poll");
				var submit_button=document.createElement("input");
				submit_button.setAttribute("type","submit");
				submit_button.setAttribute("class", "buttons");
				opt.appendChild(document.createElement("br"));
				opt.appendChild(document.createElement("br"));
				label1.appendChild(radioInput1);
				label1.appendChild(bigradio1);				
				opt.appendChild(label1);					
				opt.appendChild(document.createElement("br"));
				opt.appendChild(document.createElement("br"));
				label2.appendChild(radioInput2);
				label2.appendChild(bigradio2);				
				opt.appendChild(label2);
				opt.appendChild(document.createElement("br"));
				opt.appendChild(document.createElement("br"));
				opt.appendChild(submit_button);
				myvar=setInterval(function(){decrementTimer();},1000);
				clearInterval(variable);
				}				
			}
	}
	function timedRefresh(timeoutPeriod)
	{
		setTimeout("location.reload(true)", timeoutPeriod);
	}
	function decrementTimer()
	{
		
		if(timer>0)
		{
			document.getElementById("time").innerHTML="Time left to vote: "+timer;
			timer--;
		}
		else
			{
				clearInterval(myvar);
				document.getElementById("time").innerHTML="";
				document.getElementById("poll_quest").innerHTML="Poll Timeout";
				document.getElementById("option").innerHTML="";
			}
			
	}
	 }
function validateVote()
{
	if(document.getElementById("  Yes").checked || document.getElementById("  No").checked)
	{
		alert("You have voted successfully.!");
		return true;		
	}
	else
	{
		alert("Please give vote first.");
		return false;
	}
}
</script>
<title>Polls</title>
<style type="text/css">
    textarea { border: none;background-color: transparent;  }
</style>
</HEAD>
<body onload="loadQuestion();">
<%@include file="../newMenu/newMenuForStudent.jsp"%>
<div id="content_in" style="min-height:350px;" >
<br/>
<h2 class="pageheader" >Welcome to Polls</h2>
<SCRIPT type="text/javascript">
    window.history.forward(1);
</script>
<div id="poll_quest" style="height:200;width:200;overflow:hidden;word-wrap:break-word;text-align:center;"></div>
<form id="option" onsubmit="return validateVote();"></form>
<label id="time"></label>
</div>

<%@ include file="../../jsp/includes/footer.jsp" %>
</body>
</html>
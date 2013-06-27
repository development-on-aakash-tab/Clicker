<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
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
String courseID = session.getAttribute("courseID").toString();
%>
<!DOCTYPE html>
<html>
<head>
<title>Quiz</title>
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
	padding: 20px;
}
.big-radio:checked + label:after {
	width: 20px;
	height: 20px;
	left: 10px;
	top: 10px;
}
</style>
<script>
var flag = false;
var qflag =true;
String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}; 

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

var disableflag=false;
function updateTimerCount(){	
	var time = document.getElementById("quizStatus").innerHTML.trim();
	var cmin=parseInt(time.split(":")[0]);
	var csec=parseInt(time.split(":")[1]);
	csec--;        
	if(csec==-1) { 
	csec=59; cmin--; 
	}	
	if((csec==0 && cmin==0) || cmin<0)
	{
		clearInterval(down);
		storeResponse();
	}
	document.getElementById("quizStatus").innerHTML = cmin+":"+csec;
}

function calUpdate(){	
	down=setInterval(function(){updateTimerCount();},1000);
}

function goQuizListener(){
	clearInterval(stopwait);
	window.location.href="StudentQuizListener.jsp";
}

function storeResponse(){
	getXMLhttp();
	var radios = document.getElementsByName("question");
	var resp = "NR";
	for(var i = 0; i < radios.length; i++) {
	    if(radios[i].checked)
		    {
	    	resp = String.fromCharCode(65 + i);		    
		    }
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.getElementById("quiz").innerHTML = "<img alt='Loading...' src='../../images/loading_transparent1.gif'>";
			stopwait=setInterval(function(){goQuizListener();},5000);
		}		
	};	
	xmlhttp.open("GET", "QuizResponse.jsp?resp="+resp, false);
	xmlhttp.send();
}

function loadQuestion(){
	var options ="";
	flag=true;
	for(var i=0;i<noofQ;i++){
		options +="<label style='margin-left: 25px;'><input type='radio' name=question onchange='isAnswered(qtype)' class='regular-radio big-radio' value='"+String.fromCharCode(65 + i)+"'/><label for='radio-2-1'></label><span style='font-size: 24px'> "+String.fromCharCode(65 + i)+" </span></label>";
	}
	document.getElementById("quiz").innerHTML = options;
	getInstantQuizTime();
}

function getInstantQuizTime(){
	getXMLhttp();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {	
			var content =xmlhttp.responseText;	
			document.getElementById("quizStatus").innerHTML = content.split(":")[1] + ":"  + content.split(":")[2];
			calUpdate();			
		}		
	};	
	xmlhttp.open("GET", "../../jsp/instructorJspPages/InstantSSE.jsp", true);
	xmlhttp.send();
}
var noofQ = "<%=application.getAttribute(courseID + "optiontime").toString().split(":")[0]%>";
</script> 
</head>
<body onload="loadQuestion()">
	<div id="content_in">
		<div id="quizStatus" style="font-size: 38px; color: #fff"></div><br/><br/>
		<div id="quiz"></div>
	</div>
</body>
</html>
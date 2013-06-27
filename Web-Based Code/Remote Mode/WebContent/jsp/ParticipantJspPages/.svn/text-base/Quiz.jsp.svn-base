<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="com.clicker.wrappers.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
String ParticipantID =(String) session.getAttribute("ParticipantID");
String value1;

if (ParticipantID == null) {
	request.setAttribute("Error", "Session has ended.");
	RequestDispatcher rd = request.getRequestDispatcher("ParticipantError.jsp");
	rd.forward(request, response);
	return;
}
System.out.println("Quiz Started : " + ParticipantID);
String QuizStatus = (String) application.getAttribute("QuizStatus");
System.out.println("QuizStatus is : "+QuizStatus); 
String minutes = application.getAttribute("minutes").toString();
String seconds = application.getAttribute("seconds").toString();	

if (QuizStatus!="START")
{
	response.sendRedirect("./QuizListener.jsp");
	return;	
}	 
String rightTime = minutes+":"+seconds;
System.out.println("Quiz Time "+rightTime);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../../javascript/jquery-1.3.2.min.js" type="text/javascript"></script>
<link href="../../styles.css" rel="stylesheet" type="text/css"
	media="screen" />
<script type="text/javascript">
var quiz_questions = "<%= application.getAttribute("quizQuestions").toString()%>";
var disableflag=false;
var totalQuestions=0;
var allQuestionsType="";
var currentQuestion=0;
function updateTimerCount(){	
	var time = document.timerForm.timetxt.value;
	var cmin=parseInt(time.split(":")[0]);
	var csec=parseInt(time.split(":")[1]);
	if((csec<=2 && cmin==0) && !disableflag){
		var qdiv=document.getElementById("questions");
		var inputs = qdiv.getElementsByTagName("INPUT");
		for (var i = 0; i < inputs.length; i++) {
		        inputs[i].disabled = true;
		}
		var imgeles = document.getElementById('previous');
		imgeles.onclick = function() {
		     return false;
		};
		imgeles = document.getElementById('next');
		imgeles.onclick = function() {
		     return false;
		};
		var diveles = document.getElementById('questionsnumbers').getElementsByTagName('div');
		for (var i=0; i < diveles.length; i++){
			diveles[i].onclick = function() {
		     return false;
		   };
		}
		disableflag=true;
	}
	csec--;
	if(csec==-1) { 
	csec=59; cmin--; 
	}	
	if((csec==0 && cmin==0) || cmin < 0)
		{
		clearInterval(down);
		//getResponse();
		submitResponse(quizresp);
	}else{
		document.timerForm.timetxt.value = cmin+":"+csec;
	}
	
}

function calUpdate(){	
	showQuestions();
	getQuizTime();
	down=setInterval(function(){updateTimerCount();},1000);
}

function getQuizTime(){
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
	xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.timerForm.timetxt.value=xmlhttp.responseText;	
		}
	};
	xmlhttp.open("POST", "../../jsp/CoordinatorJspPages/QuizTimeUpdate.jsp", true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send("requestfrom=client");
}

function goQuizResult(){
	clearInterval(calAfterFiveSec);
	window.location.href="./QuizResult.jsp";
}

function submitResponse(quizresp)
{	
	var response = "";
	for(var i=0;i<quizresp.length;i++){
		response += quizresp[i] + "@@";
	}
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
	xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.getElementById("questions").innerHTML = "<img alt='Loading...' src='../../images/loading_transparent1.gif'>";
			calAfterFiveSec=setInterval(function(){goQuizResult();},5000);			
		}
	};
	xmlhttp.open("POST", "../../jsp/ParticipantJspPages/QuizEnd.jsp", false);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send("response="+response + "&quizresp=" + quizresp );
}

/*function getResponse(){
	var response="";
	var allQType = allQuestionsType.split(";");
	for(var i=0;i<totalQuestions;i++){
		var questionType =  allQType[i].split("-")[1]
		if(questionType=="1" || questionType=="4"){
			var questionname = "question" + i;
			var inputs = document.getElementsByName(questionname);
			var flag=true;
	        for (var j = 0; j < inputs.length; j++) {
	             if (inputs[j].checked) {
	               response+=String.fromCharCode(65 + j) + "@@";
	               flag=false;
	             }
	        }
	        if(flag){
	        	response+="Z@@";
		    }
		}else if(questionType=="3"){
			var questionname = "numeric" + i;
			var num_resp = document.getElementById(questionname).value;
			if(num_resp==""){
				response+= "Z@@";
			}else{
				response+= num_resp + "@@";
			}
			
		}else if(questionType=="2"){
			var questionname = "question" + i;
			var inputs = document.getElementsByName(questionname);
			var flag=true;
			var resp ="";
	        for (var j = 0; j < inputs.length; j++) {
	             if (inputs[j].checked) {
	           	  resp+=String.fromCharCode(65 + j);
	           	  flag=false;
	             }
	        }
	        if(flag){
		       	response+="Z@@";
			}else{
	        	response+=resp+"@@";
			}
		}
	}
	submitResponse(response);
}*/

function showQuestion(questionIndex){
	currentQuestion = questionIndex;
	var qdiv_id;
	var qnumber_id;
	for(var i=0;i<totalQuestions;i++){
		qdiv_id = "question_div" + i;
		qnumber_id = "qnumber" +i;
		document.getElementById(qdiv_id).style.display="none";
		document.getElementById(qnumber_id).style.background= "#556677";
	}
	qdiv_id = "question_div" + questionIndex;
	qnumber_id = "qnumber" +questionIndex;
	document.getElementById(qdiv_id).style.display = "block";
	document.getElementById(qnumber_id).style.background= "#677AD4";
}

var quizresp = new Array();
function showQuestions(){
	var questions = quiz_questions.split("@@");	
	var questions_content = "";
	var questionsnumbers_content="";
	totalQuestions=(questions.length -1);
	for(var i=0;i<totalQuestions;i++){	
		quizresp[i] = "Z";
		questionsnumbers_content += "<div id='qnumber"+i+"' onClick= 'showQuestion("+i+")' style='text-align: center; font-size:25px; background-color:#556677; width: 40px; float: left;'>" + (i+1) + "</div>";
		var questionArray = questions[i].split("@;");
		var qtype = questionArray[2];
		allQuestionsType += i + "-" +qtype + ";";
		questions_content += "<div id='question_div"+i+"'>";
		if(qtype == "1" || qtype == "4"){
			questions_content += (i+1) + ". " + questionArray[1] + "</br>";
			var options = questionArray[3].split("@,");
			for(var j=0;j<(options.length-1);j++){
				questions_content += "<label style='margin-left: 25px;'><input type='radio' name='question" + i + "' onchange='isAnswered("+i+", "+qtype+")' class='regular-radio big-radio' value='"+options[j]+"'/><label for='radio-2-1'></label> " + String.fromCharCode(65 + j) + " . " +options[j] +"</label> <br/> <br/>";
			}
		}else if(qtype == "3"){
			questions_content += (i+1) + ". " + questionArray[1] + "</br>";
			questions_content += "ANSWER : <input type='text' name='numeric"+i+"' id='numeric"+i+"' onkeyup='isAnswered("+i+", "+qtype+")'> </br></br>";
		}else if(qtype == "2"){
			questions_content += (i+1) + ". " + questionArray[1] + "</br>";
			var options = questionArray[3].split("@,");
			for(var j=0;j<(options.length-1);j++){
				questions_content += "<label style='margin-left: 25px;'><input type='checkbox' name='question" + i + "' onchange='isAnswered("+i+", "+qtype+")' class='regular-checkbox big-checkbox' value='"+options[j]+"'/><label for='checkbox-2-1'></label> " + String.fromCharCode(65 + j) + " . " +options[j] +"</label> <br/> <br/>";
			}
		}		
		questions_content += "</div>";		
	}
	document.getElementById("questionsnumbers").innerHTML = questionsnumbers_content;
	document.getElementById("questions").innerHTML = questions_content;	
	showQuestion("0");	
}

function previousQuestion(){
	if(currentQuestion!=0){
	showQuestion(--currentQuestion);
	}else{
		showQuestion(totalQuestions-1);
		}
}
function nextQuestion(){
	//alert(totalQuestions + "-" + currentQuestion);
	if(currentQuestion!=(totalQuestions-1)){
	showQuestion(++currentQuestion);
	}else{
		showQuestion(0);
		}
}

function isAnswered(qindex, qtype){
	var flag =0;
	if(qtype == 1){
		var radios = document.getElementsByName("question" + qindex);
		for(var i = 0; i < radios.length; i++) {
		    if(radios[i].checked)
			    {
		    	quizresp [qindex] = String.fromCharCode(65 + i);
			    flag=1;
			    }
		}
	}
	else if(qtype == 4){
		var radios = document.getElementsByName("question" + qindex);
		for(var i = 0; i < radios.length; i++) {
			if(radios[i].checked)
		    {
	    	quizresp [qindex] = String.fromCharCode(65 + i);
		    flag=1;
		    }
		}
	}
	else if(qtype==3){
		var numtext = document.getElementById("numeric" + qindex).value;
		if(numtext.trim().length>=1){
			quizresp [qindex] = numtext.trim();
			flag=1;
		}
	}
	else if(qtype==2){
		var checks = document.getElementsByName("question" + qindex);
		quizresp [qindex] = "";
		for(var i = 0; i < checks.length; i++) {
			if(checks[i].checked)
		    {
	    	quizresp [qindex] += String.fromCharCode(65 + i);
		    flag=1;
		    }
		}
	}
	if(flag ==1){		
		document.getElementById("qnumber" + qindex).style.color= "#ffffff";		
	}else{
		document.getElementById("qnumber" + qindex).style.color= "#000000";		
		quizresp [qindex] ="Z";
	}
}

</script>

<title>Participant Quiz URL</title>

</head>
<body onload="calUpdate();">	

		<div style="min-height: 300px; font-size:14px; font-weight: bold">
		<div style="float: left;"><img id="previous" src="../../images/arrow_left_thin.png" onclick="previousQuestion()" style="width: 60px; height:400px; margin-top: 40px;"></img> </div>
		<div style="height: 45px ">
			<div id="questionsnumbers" style="float:left; background-color: #677AD4; height: 40px;"></div>
			<div id="timer_align" style="float: right;height: 40px;">
				<form id="timerForm" name="timerForm">
					<input type=text id=timetxt name=timetxt Style="text-align:center;font-size:24px;" size=8 value='<%=rightTime%>' readonly="readonly" />
				</form>
			</div>
		</div>
			<div>
				
				<div id="questions" style="float:left; width: 620px; margin-top :20px; font-size:22px; font-weight: bold; text-align: justify; padding-left: 10px">
				</div>
				
			</div>			
			<div style="float: right;"><img id="next" src="../../images/arrow_right_thin.png" onclick="nextQuestion()" style="width: 60px; height:400px;"></img> </div>	
		</div>	
</body>
</html>
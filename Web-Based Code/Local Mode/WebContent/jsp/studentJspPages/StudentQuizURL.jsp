<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
String StudentID =(String) session.getAttribute("StudentID");
String value1;

if (StudentID == null) {
	request.setAttribute("Error", "Your session has expired.");
	RequestDispatcher rd = request.getRequestDispatcher("../../jsp/studentJspPages/StudentError.jsp");
	rd.forward(request, response);
	return;
}

String InstructorID = (String) session.getAttribute("InstructorID");
String QuizStatus = (String) application.getAttribute(InstructorID+"QuizStatus");
System.out.println("Instructor Id at instructor:"+InstructorID);

String minutes = application.getAttribute(InstructorID+"minutes").toString();
String seconds = application.getAttribute(InstructorID+"seconds").toString();
			
String rTime = request.getParameter("timetxt");

if(rTime!=null)
{
	session.setAttribute("QuizTime",rTime);
}
else
{
	session.setAttribute("QuizTime",minutes+":"+seconds);
}
String rightTime = (String)session.getAttribute("QuizTime");
if(session.getAttribute("ResultAccessCount")!=null){
	session.removeAttribute("ResultAccessCount");
}
%>
<html>
<head>
<title>Student Quiz URL</title>
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
/* Check box*/
.regular-checkbox {
	display: none;
}
.regular-checkbox + label {
	background-color: #fafafa;
	border: 1px solid #cacece;
	box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0px -15px 10px -12px rgba(0,0,0,0.05);
	padding: 9px;
	border-radius: 2px;
	display: inline-block;
	position: relative;
}
.regular-checkbox + label:active, .regular-checkbox:checked + label:active {
	box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0px 1px 3px rgba(0,0,0,0.1);
}
.regular-checkbox:checked + label {
	background-color: #e9ecee;
	border: 1px solid #adb8c0;
	box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0px -15px 10px -12px rgba(0,0,0,0.05), inset 15px 10px -12px rgba(255,255,255,0.1);
	color: #99a1a7;
}
.regular-checkbox:checked + label:after {
	content: '\2714';
	font-size: 12px;
	position: absolute;
	top: 0px;
	left: 2px;
	color: #00FF00;
}
.big-checkbox + label {
	padding: 13px 13px;
}
.big-checkbox:checked + label:after {
	font-size: 26px;
	left: 2px;
}
.tag {
	font-family: Arial, sans-serif;
	width: 200px;
	position: relative;
	top: 5px;
	font-weight: bold;
	text-transform: uppercase;
	display: block;
	float: left;
}
.radio-1 {
	width: 193px;
}
.button-holder {
	float: left;
}
</style>

<link href="../../css/style.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript">
var disableflag=false;
function updateTimerCount(){	
	var time = document.timerForm.timetxt.value;
	var cmin=parseInt(time.split(":")[0]);
	var csec=parseInt(time.split(":")[1]);
	csec--;        
	if(csec==-1) { 
	csec=59; cmin--; 
	}	
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
	if((csec==0 && cmin==0) || cmin<0)
	{
		clearInterval(down);
		//getResponse();
		submitResponse(quizresp);
	}
	document.timerForm.timetxt.value = cmin+":"+csec;
}

function calUpdate(){	
	showQuestions();
	down=setInterval(function(){updateTimerCount();},1000);
}

function goQuizResult(){
	clearInterval(calAfterFiveSec);
	window.location.href="./StudentQuizResult.jsp";
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
	xmlhttp.open("POST", "../../jsp/studentJspPages/EndQuizURL.jsp", false);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send("response="+response);
}

function showQuestion(questionIndex){	
	var qdiv_id;
	var qnumber_id;
	if(currentQuestion!=questionIndex){
		qdiv_id = "question_div" + currentQuestion;
		qnumber_id = "qnumber" +currentQuestion;
		document.getElementById(qdiv_id).style.display="none";
		document.getElementById(qnumber_id).style.background= "#556677";
	}
	qdiv_id = "question_div" + questionIndex;
	qnumber_id = "qnumber" +questionIndex;
	document.getElementById(qdiv_id).style.display = "block";
	document.getElementById(qnumber_id).style.background= "#677AD4";
	currentQuestion = questionIndex;
}

var totalQuestions=0;
var allQuestionsType="";
var currentQuestion=0;
var quizresp = new Array();
function showQuestions(){
	var questions = quiz_questions.split("@@");	
	var questions_content = "";
	var questionsnumbers_content="";
	totalQuestions=(questions.length -1);
	for(var i=0;i<totalQuestions;i++){	
		quizresp[i] = "Z";
		questionsnumbers_content += "<div id='qnumber"+i+"' onClick= 'showQuestion("+i+")' style='text-align: center; font-size:25px; background-color:#556677; color:#000000; width: 40px; float: left;'>" + (i+1) + "</div>";
		var questionArray = questions[i].split("@;");
		var qtype = questionArray[2];
		allQuestionsType += i + "-" +qtype + ";";
		questions_content += "<div id='question_div"+i+"' style='display:none;'>";
		if(qtype == "1" || qtype == "4"){
			questions_content += (i+1) + ". " + questionArray[1] + "</br>";
			var options = questionArray[3].split("@,");
			for(var j=0;j<(options.length-1);j++){
				var qname = "question" + i;
				questions_content += "<label style='margin-left: 25px;'><input type='radio' name="+qname+" onchange='isAnswered("+i+", "+qtype+")' class='regular-radio big-radio' value='"+options[j]+"'/><label for='radio-2-1'></label> " + String.fromCharCode(65 + j) + " . " +options[j] +"</label> <br/> <br/>";
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
	showQuestion(0);	
}

String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
};

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
	if(qtype == 4){
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

function previousQuestion(){
	if(currentQuestion!=0){
	showQuestion(currentQuestion-1);
	}else{
		showQuestion(totalQuestions-1);
		}
}
function nextQuestion(){
	if(currentQuestion!=(totalQuestions-1)){
		showQuestion(currentQuestion+1);
	}else{
		showQuestion(0);
	}
}
var quiz_questions = "<%= application.getAttribute(InstructorID+"quizQuestions").toString()%>";
document.timerForm.timetxt.value = "<%=minutes + ":" + seconds%>";
</script>
</head>
<body onload="calUpdate();">	
		<div style="min-height: 350px; font-size:14px; font-weight: bold">
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
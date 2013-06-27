<%@page import="com.clicker.databaseconn.QuizRecordQueries"%>
<%@page import="java.util.*"%>
<%@page import="com.clicker.wrappers.*"%>
<%@page errorPage ="./CoordinatorError.jsp" %>
<%@page import="java.util.concurrent.ConcurrentHashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
String CoordinatorID= (String) session.getAttribute("CoordinatorID");
if (CoordinatorID == null) {
	request.setAttribute("Error","Session has ended.  Please login.");
	RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
	rd.forward(request, response);
	return;
}

int minutes = Integer.parseInt(application.getAttribute("minutes").toString());
int seconds = Integer.parseInt(application.getAttribute("seconds").toString());

String rightTime = minutes+":"+seconds;
session.setAttribute("QuizTime",rightTime);
System.out.println("Quiz Time "+rightTime);

int QuizID = Integer.parseInt(application.getAttribute("QuizID").toString());
Vector<Question> Questiondetails = new Vector<Question>();
Questiondetails = (Vector <Question>) application.getAttribute("Questiondetails");
ConcurrentHashMap<String, String> AllParticipantResponse = new ConcurrentHashMap<String, String>();
application.setAttribute("AllParticipantResponse", AllParticipantResponse);
application.setAttribute("ParticipantCount", 0);
Question question;
String questionStr="";
%>
<html>
<head>
<script language="JavaScript" src="../../javascript/jquery-1.3.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script language="JavaScript" type="text/javascript">
function Minutes(data) {
	for(var i=0;i<data.length;i++) 
		if(data.substring(i,i+1)==":") 
			break;
	return(data.substring(0,i)); 
}
		
function Seconds(data) {        
	for(var i=0;i<data.length;i++) 
		if(data.substring(i,i+1)==":") 
			break;
	return(data.substring(i+1,data.length)); 
}

function CountDown() { 
 	cmin2=1*Minutes(document.timerForm.timetxt.value);
 	csec2=0+Seconds(document.timerForm.timetxt.value); 
 	down=setInterval(function(){DownRepeat();},1000);
}

function Display(min,sec) {     
	var disp;       
	if(min<=9) disp=" 0";   
	else disp=" ";  
	disp+=min+":";  
	if(sec<=9) disp+="0"+sec;       
	else disp+=sec; 
	return(disp); 
}

var xmlhttp;
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

function DownRepeat() { 
	csec2--;        
	if(csec2==-1) { 
		csec2=59; cmin2--; 
	}	
	if (document.getElementById) 
	{ 
		document.timerForm.timetxt.value = Display(cmin2,csec2); 		
	}		
	if ((cmin2 == 0) && (csec2 == 0)) {
		clearInterval(down);
		getXMLhttp();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.location.href = "./ResponseSend.jsp";
			}
		};
		xmlhttp.open("GET",	"./QuizSSE.jsp?Status=stop", true);
		xmlhttp.send();		
	} 	
	updateTime(cmin2, csec2);
}
	
function updateTime(cmin2, csec2){
	$('#QuizUpdatediv').load("./QuizTimeUpdate.jsp?minutes="+cmin2+"&seconds="+csec2+"&requestfrom=server");
	$('#QuizUpdatediv').hide();	
}
</script>

<title>Remote Quiz</title>
</head>
<body onload="CountDown();">
<center>
<%@ include file="../../jsp/includes/menuheaderForCoordinator.jsp" %>
<div id="content_in">
<div style="min-height: 425px ;font-weight: bold;">
<center>
<div style="height:15px;text-align: left;">Quiz Module </div><br/>
<form id = "timerForm" name="timerForm">
<input type=text id=timetxt name=timetxt size=7 value ='<%=rightTime%>' readonly="readonly"/>
</form>
<br/>
Quiz ID : <%=QuizID%>
<br/>
<div style="font-size: 18px; height:380px;overflow: auto; border: solid; text-align: justify;">
<% 
QuizRecordQueries qrq = new QuizRecordQueries();
//qrq.addQuizRecord(conn, QuizID);
//String QuizRecordID = String.valueOf(qrq.getLatestQuizRecordID(conn, QuizID));

int QuestionIndex=0;
Vector<String> CorrectAnswer = new Vector<String>();
while ((QuestionIndex + 1) <= Questiondetails.size())
{
	System.out.println("Question Index is at Start : "+QuestionIndex);	
	question = Questiondetails.elementAt(QuestionIndex);	
	int QuestionType = question.getQuestionType();
	String QuestionAnswer="";
	questionStr = question.getQuestionText();
	System.out.println(questionStr);
%>
	Question No : <%= QuestionIndex + 1%>
	<br/>
	Question : <%=questionStr%>
	<br/>
<%
	Vector<Option> Options = question.getOptions();	
	Option option[] = new Option[Options.size()];	
	String OptionResponse="";	
	for (int i=0; i<Options.size(); i++)
	{
		OptionResponse =  Character.toString((char)(i+65));	
		option[i]=Options.elementAt(i);
		System.out.println("Options Stored in an Array = "+OptionResponse+":"+option[i].getOptionValue());
		if(QuestionType!=3){
%>	
	Option <%=OptionResponse%> : <%=option[i].getOptionValue().toString()%>
	<br/>
<%	}}%>
	<br/>
	<p/>
<%	
	if (QuestionType==1)
	{
		for(int i=0;i<Options.size();i++){
			if (Options.elementAt(i).getCorrect()==true)
			{
				QuestionAnswer = QuestionAnswer+Character.toString((char)(i+65));
				CorrectAnswer.add(QuestionAnswer);
			}
		}				
	}
	else if (QuestionType==2)
	{
		for(int i=0;i<Options.size();i++){
			if (Options.elementAt(i).getCorrect()==true)
			{
				QuestionAnswer = QuestionAnswer+Character.toString((char)(i+65));				
			}
		}				
		CorrectAnswer.add(QuestionAnswer);
	}
	else if (QuestionType==3)
	{		
		CorrectAnswer.add(option[0].getOptionValue().toString());
	}
	else if (QuestionType==4)
	{
		for(int i=0;i<Options.size();i++){
			Option opt = Options.elementAt(i);
			if (opt.getCorrect()==true)
			{
				QuestionAnswer +=(opt.getOptionValue().equalsIgnoreCase("true")?"A":"B");				
			}
		}				
		CorrectAnswer.add(QuestionAnswer);
	}	
	QuestionIndex++;
	System.out.println("Correct Answer is "+CorrectAnswer.get(QuestionIndex-1));
	System.out.println("Question Index is at end : "+QuestionIndex);	
}
application.setAttribute("CorrectAnswer",CorrectAnswer);
//session.setAttribute("QuizRecordID", QuizRecordID);
//conn.close();
%>
</div>
</center>
</div>
</div>
<div id="QuizUpdatediv"></div>
<%@ include file="../../jsp/includes/footer.jsp" %>
</center>
</body>
</html>
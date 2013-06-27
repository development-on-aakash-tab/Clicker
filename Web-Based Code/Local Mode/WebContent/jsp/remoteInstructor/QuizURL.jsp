<%@page import="com.clicker.databaseconn.QuizRecordQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="com.clicker.util.RemoteXML"%>
<%@page import="com.clicker.util.QuizParamXML"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="com.clicker.wrappers.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script language="JavaScript" src="../../javascript/RemoteQuiz.js"></script>
<script type="text/javascript" src="../../javascript/RemoteResponses.js"></script>
<script src="../../javascript/jquery-1.3.2.min.js"></script>

<script language="JavaScript" type="text/javascript">
<!--
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

function countDown() { 
 	cmin2=1*Minutes(document.QuizURLform.timetxt.value);
 	csec2=0+Seconds(document.QuizURLform.timetxt.value); 
	DownRepeat(); 
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

function DownRepeat() {	
	csec2--;        
	if(csec2==-1) { 
	csec2=59; cmin2--; 
	}
	
	if (document.getElementById) 
	{ 
		document.getElementById("timetxt").style.visibility='visible';
		document.QuizURLform.timetxt.value = Display(cmin2,csec2); 		
	}	
	
	if((cmin2==0)&&(csec2==0))
	{		
		//document.getElementById("ViewResponsesButton").style.visibility='visible';
		stopQuiz();				
	} 
	else down=setTimeout("DownRepeat()",1000); 
}
var xmlhttp;

//This method will get the XMLHTTP object for work with ajax
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
function stopQuiz()
{
	
	//$('#StopQuizdiv').load("../../StopQuizServlet");
	//$('#StopQuizdiv').hide();
	//$('#QuizID_Timediv').hide();

	getXMLhttp();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.location.href = "./RemoteCenter.jsp?quiztype=normal";	
		}
	};
	xmlhttp.open("GET",	"../../StopQuizServlet", true);
	xmlhttp.send();
	
}

-->
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<SCRIPT type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</SCRIPT>
<title>Quiz URL</title>
</head>
<body onload="countDown();">
	<%
String InstructorID = (String) session.getAttribute("InstructorID");

if (InstructorID == null) {
	request.setAttribute("Error",
			"Your session has expired.");
	RequestDispatcher rd = request
			.getRequestDispatcher("../../jsp/remoteInstructor/InstructorError.jsp");
	rd.forward(request, response);
}

Timestamp quizTimeStamp;

java.util.Date date= new java.util.Date();
quizTimeStamp = new Timestamp(date.getTime());
System.out.println("Quiz Time Stamp is "+quizTimeStamp);

session.setAttribute("QuizTimestamp", quizTimeStamp);

String QuizStatus = (String) application.getAttribute(InstructorID+"QuizStatus");
String QuizName = (String) application.getAttribute(InstructorID+"QuizName");

if (QuizStatus=="STOP")
{
	String QuizMessage="This Quiz is already taken";	
%>

	<center>
		<%@ include file="../../jsp/newMenu/newMenuForRemoteInst.jsp"%>
		<div id="content_in">
			<div style="min-height: 425px; font-weight: bold;">
				<center>
					<div style="height: 20px; text-align: left;">Quiz Module</div>
					<%=QuizMessage%>
					<br /> <br /> <br> <input type="button" value="Reconduct Quiz"
						onclick="ReShowQuizdlg('<%=QuizName%>');" />
				</center>
			</div>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
	<%
return;
}

String minutes=request.getParameter("minutes").toString();
String seconds=request.getParameter("seconds").toString();

int min = Integer.parseInt(minutes);
int sec = Integer.parseInt(seconds);

int totaltime = min*60+sec;

int QuizID = Integer.parseInt(request.getParameter("QuizID").toString());

String totaltime_in_sec = String.valueOf(totaltime);
String Quiz_ID=String.valueOf(QuizID);
String Filepath = getServletContext().getRealPath("/");
Vector<Question> Questiondetails = new Vector<Question>();
//Questiondetails = dbqueries.getallQuestionDetails(conn, String.valueOf(QuizID));
Questiondetails = (Vector<Question>) application.getAttribute(InstructorID+"Questiondetails");
//QuizParamXML.makeXML(Quiz_ID, totaltime_in_sec, Filepath);
QuizParamXML.createQuizXML(Filepath, Questiondetails, Integer.toString(QuizID), totaltime_in_sec);

String CourseID = (String) session.getAttribute("InstructorCourse");

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

DatabaseConnection dbconn = new DatabaseConnection();
DatabaseQueries dbqueries = new DatabaseQueries();
Connection conn = dbconn.createDatabaseConnection();
dbqueries.updateCourseStatus(conn, 1, CourseID);


application.setAttribute(InstructorID+"QuizStatus", "START");
application.setAttribute(InstructorID+"minutes", minutes);
application.setAttribute(InstructorID+"seconds", seconds);
session.setAttribute("qztyp", "normal");
%>
	<center>
	<%@ include file="../../jsp/newMenu/newMenuForRemoteInst.jsp"%>

		<div id="content_in" style="min-height: 425px;">
			<div style="min-height: 425px; font-weight: bold;">
				<center>
					<div style="height: 20px; text-align: left;">Quiz Module</div>
					<table width="100%">
						<tr>
							<td width="100%" align="center"><span id="theTime"
								class="timeClass"></span></td>
						</tr>
					</table>
					<div id="QuizID_Timediv">
						<form id="QuizURLform" name="QuizURLform">
							<input type=text id=timetxt name=timetxt size=7
								value='<%=rightTime%>' readonly="readonly"
								style="visibility: hidden" />
						</form>
						<br /> Quiz ID is =
						<%=QuizID%>
						<br />
						<div style="font-size: 18px; text-align: justify;overflow:auto; height:550px">
							<%
Question question;
String questionStr="";


int QuestionIndex=0;
String CorrectAnswer[]=new String[Questiondetails.size()];

while ((QuestionIndex + 1) <= Questiondetails.size())
{
	question = Questiondetails.elementAt(QuestionIndex);	
	int QuestionType = dbqueries.getQuestionTypeforQuiz(conn, question.getQuestionID());
	String QuestionAnswer="";
	questionStr = question.getQuestionText();	
	%>
	Question No :
	<%= QuestionIndex + 1%>
	<br /> Question :
	<%=questionStr%>
	<br />
	<%
	Vector<Option> Options = question.getOptions();
	Option option[] = new Option[Options.size()];
	String OptionResponse="";

	for (int i=0; i<Options.size(); i++)
	{
		OptionResponse =  Character.toString((char)(i+65));
		option[i]=Options.elementAt(i);
		if(QuestionType!=3){
		%>
			Option
			<%=OptionResponse%>
			:
			<%=option[i].getOptionValue().toString()%>
		<br />
	<%}}%>
	<br />
	<p />
	<p />
	<%	
	if (QuestionType==1)
	{
		for(int i=0;i<Options.size();i++){
			if (Options.elementAt(i).getCorrect()==true)
			{
				QuestionAnswer = QuestionAnswer+Character.toString((char)(i+65));
				CorrectAnswer[QuestionIndex] = QuestionAnswer;
			}
		}				
	}
	QuestionIndex++;	
}
conn.close();
%>
						</div>
					</div>
					<br />					
					<div id="StopQuizdiv"></div>
				</center>
			</div>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
</body>
</html>
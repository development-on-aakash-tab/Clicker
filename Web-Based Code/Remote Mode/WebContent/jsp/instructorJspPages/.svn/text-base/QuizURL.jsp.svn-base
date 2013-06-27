<%@page import="com.clicker.databaseconn.QuizRecordQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="com.clicker.wrappers.*"%>
<%@page import="java.util.concurrent.ConcurrentHashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script language="JavaScript" src="../../javascript/Quiz.js"></script>
<script language="JavaScript" src="../../javascript/jquery-1.3.2.min.js"></script>
<script language="JavaScript" src="../../javascript/LocalResponses.js"></script>
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
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

function countDown() { 
 	cmin2=1*Minutes(document.QuizURLform.timetxt.value);
 	csec2=0+Seconds(document.QuizURLform.timetxt.value); 
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

function DownRepeat() {	
	var cid="";
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
		cid= document.getElementById("cid").innerHTML;
		clearInterval(down);
		collectResponse(cid);				
	} 	
	updateQuizTime(cmin2, csec2, cid);	
}

function updateQuizTime(min, sec, cid)
{
	$('#QuizUpdatediv').load("../../QuizTimeUpdate?minutes="+min+"&seconds="+sec+"&cid="+cid+"&requestfrom=server");
	$('#QuizUpdatediv').hide();		
}

</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quiz URL</title>
</head>
<body onload="countDown();">
<div id ="cid" style="display: none;"><%=session.getAttribute("courseID").toString()%></div>
	<%
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility

String InstructorID = (String) session.getAttribute("InstructorID");

if (InstructorID == null) {
	request.setAttribute("Error","Your session has expired.");
	RequestDispatcher rd = request.getRequestDispatcher("../../jsp/instructorJspPages/InstructorError.jsp");
	rd.forward(request, response);
	return;
}

ConcurrentHashMap<String, String> AllStudentResponse = new ConcurrentHashMap<String, String>();
String CourseID = (String) session.getAttribute("courseID");
application.setAttribute(CourseID + "AllStudentResponse", AllStudentResponse);
application.setAttribute(CourseID + "StudentResponseCount", 0);

String QuizStatus = (String) application.getAttribute(InstructorID+"QuizStatus");
String QuizName = (String) application.getAttribute(InstructorID+"QuizName");

if (QuizStatus=="STOP")
{
	String QuizMessage="This Quiz is already taken";	
%>

	<center>
		<%@ include file="../newMenu/newMenuwithCSS.jsp"%> 
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

int QuizID = Integer.parseInt(request.getParameter("QuizID").toString());

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

System.out.println(seconds);
application.setAttribute(InstructorID+"QuizStatus", "START");
application.setAttribute(InstructorID+"minutes", minutes);
application.setAttribute(InstructorID+"seconds", seconds);
%>
	<center>
		<%@ include file="../newMenu/newMenuwithCSS.jsp"%> 
		<div id="content_in">
			<div style="min-height: 425px; font-weight: bold;">
				<center>
					<div style="height: 20px; text-align: left;">Quiz Module</div>
					<table width="100%">
						<tr>
							<td width="100%" align="center"><span id="theTime"
								class="timeClass"></span></td>
						</tr>
					</table>

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
Vector<Question> Questiondetails = new Vector<Question>();
Questiondetails = dbqueries.getallQuestionDetails(conn, String.valueOf(QuizID));

int QuestionIndex=0;
String CorrectAnswer[]=new String[Questiondetails.size()];

while ((QuestionIndex + 1) <= Questiondetails.size())
{
	question = Questiondetails.elementAt(QuestionIndex);	
	int QuestionType = dbqueries.getQuestionTypeforQuiz(conn, question.getQuestionID());
	String QuestionAnswer="";
	questionStr = question.getQuestionText().replace("\r\n","<br/>").replace("\n","<br/>");	
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
	if (i==0)
	{
		OptionResponse="A";			
	}
	else if (i==1)
	{
		OptionResponse="B";			
	}
	else if (i==2)
	{
		OptionResponse="C";			
	}
	else if (i==3)
	{
		OptionResponse="D";			
	}
	else if (i==4)
	{
		OptionResponse="E";			
	}
	else if (i==5)
	{
		OptionResponse="F";			
	}
	
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
	if (Options.elementAt(0).getCorrect()==true)
	{
		QuestionAnswer = QuestionAnswer+"A";
		CorrectAnswer[QuestionIndex] = QuestionAnswer;
	}
	if (Options.elementAt(1).getCorrect()==true)
	{
		QuestionAnswer = QuestionAnswer+"B";
		CorrectAnswer[QuestionIndex] = QuestionAnswer;
	}
	if (Options.elementAt(2).getCorrect()==true)
	{
		QuestionAnswer = QuestionAnswer+"C";
		CorrectAnswer[QuestionIndex] = QuestionAnswer;
	}
	
	if (Options.size()>3)
	{
		if (Options.elementAt(3).getCorrect()==true)
		{
			QuestionAnswer = QuestionAnswer+"D";
			CorrectAnswer[QuestionIndex] = QuestionAnswer;
		}
	}
	
	if (Options.size()>4)
	{
		if (Options.elementAt(4).getCorrect()==true)
		{
			QuestionAnswer = QuestionAnswer+"E";
			CorrectAnswer[QuestionIndex] = QuestionAnswer;
		}
	}
	
	if (Options.size()>5)
	{
		if (Options.elementAt(5).getCorrect()==true)
		{
			QuestionAnswer = QuestionAnswer+"F";
			CorrectAnswer[QuestionIndex] = QuestionAnswer;
		}	
	}			
}

QuestionIndex++;	
}

conn.close();
%>
					</div>
					<div style="height: 30px"></div>
					<center></center>
					<input type="button" id="ViewResponsesButton"
						value="View Responses" style="visibility: hidden"
						onclick="viewResponses();" />
				</center>
			</div>
		</div>
		<div id="QuizUpdatediv"></div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
</body>
</html>
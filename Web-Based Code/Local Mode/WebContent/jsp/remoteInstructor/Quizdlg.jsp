<%@page import="com.clicker.databaseconn.DatabaseQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="com.clicker.databaseconn.QuizRecordQueries"%>
<%@page import="com.clicker.util.RemoteXML"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.Writer"%>
<%@page import="java.io.File"%>
<%@page import="com.clicker.wrappers.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<script src="../../javascript/jquery-1.3.2.min.js"></script>
<script src="../../javascript/jquery-spin.js"></script>
<script src="../../javascript/RemoteQuiz.js"></script>

<script>	
  $(document).ready(function(){
		$.spin.imageBasePath = '../../images/spin1/';		
		
		$('#Minutes').spin({
			max: 59,
			min: 0
		});
		
		$('#Seconds').spin({
			max: 50,
			min: 0
		});
		
  });
  </script>

<SCRIPT type="text/javascript">
	//window.history.forward();
  </SCRIPT>

<style>
input {
	text-align: right;
	vertical-align: middle;
}
</style>

<title>Quiz</title>
</head>


<%
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility*/

String InstructorID = (String) session.getAttribute("InstructorID");

if (InstructorID == null) {
	request.setAttribute("Error",
			"Your session has expired.");
	RequestDispatcher rd = request
			.getRequestDispatcher("../../jsp/remoteInstructor/InstructorError.jsp");
	rd.forward(request, response);
}

Question question;
String questionStr="";

String QuizName = request.getParameter("QuizName");

DatabaseConnection dbconn = new DatabaseConnection();
DatabaseQueries dbqueries = new DatabaseQueries();

Connection conn = dbconn.createDatabaseConnection();


int QuizID = dbqueries.getQuizID(conn, QuizName);

String Quiz_ID = String.valueOf(QuizID);
%>
<script type="text/javascript">
	var QuizID=<%=Quiz_ID%>;
</script>

<body onload="noBack();">
	<center>
		<%@ include file="../newMenu/newMenuForRemoteInst.jsp"%>
		<div id="content_in">
			<div style="min-height: 425px; font-weight: bold;">
				<center>
					<div style="height: 20px; text-align: left;">Quiz Module</div>
					<div style="font-size: 18px">
						Quiz ID :
						<%=QuizID%></div>
					<div style="height: 30px"></div>
					<div style="font-size: 18px; height:400px;overflow: auto; border: solid; text-align: justify;">
						<%
QuizRecordQueries qrq = new QuizRecordQueries();
qrq.addRemoteQuizRecord(conn, QuizID);

String QuizRecordID = String.valueOf(qrq.getLatestRemoteQuizRecordID(conn, QuizID));
Vector<Question> Questiondetails = new Vector<Question>();
Questiondetails = dbqueries.getallQuestionDetails(conn, String.valueOf(QuizID)); 

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
	else if (QuestionType==2)
	{
		for(int i=0;i<Options.size();i++){
			if (Options.elementAt(i).getCorrect()==true)
			{
				QuestionAnswer = QuestionAnswer+Character.toString((char)(i+65));				
			}
		}				
		CorrectAnswer[QuestionIndex] = QuestionAnswer;
	}
	else if (QuestionType==3)
	{		
		CorrectAnswer[QuestionIndex] = option[0].getOptionValue().toString();		
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
		CorrectAnswer[QuestionIndex] = QuestionAnswer;
	}
	QuestionIndex++;
}

RemoteXML remoteXML = new RemoteXML();

String filepath = getServletContext().getRealPath("/");
File file = new File(filepath + "sversion.txt");

if (!file.exists()) {
    try {
        file.createNewFile();
        Writer output = new BufferedWriter(new FileWriter(file));
        output.write(0 + "");
        output.close();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}

if(session.getAttribute("ResponseReceivedRCID")!=null){
	session.removeAttribute("ResponseReceivedRCID");
	session.removeAttribute("ResponseReceivedFiles");
}

session.setAttribute(InstructorID + "CorrectAnswer",CorrectAnswer);
session.setAttribute("QuizRecordID", QuizRecordID);
application.setAttribute(InstructorID+"QuizName",QuizName);
application.setAttribute(InstructorID+"QuizID",QuizID);
application.setAttribute(InstructorID+"Questiondetails",Questiondetails);
application.setAttribute(InstructorID+"QuizStatus",null);
conn.close();
%>

					</div>
					<p />
					<form name="Timer">
						<br />
						<div style="font-size: 14px; font-weight: bold">Select Time
							for Quiz</div>
						<br />
						<div style="font-weight: bold;">
							Minutes <input type="text" style="width: 30px" id="Minutes"
								value="00" /> Seconds <input type="text" style="width: 30px"
								id="Seconds" value="20" />
						</div>
					</form>
					<div id="Timerdiv"></div>
					<p />
					<input type="button" value="Launch Quiz URL"
						onclick="launchQuizURL(QuizID)">
					<p />
				</center>
			</div>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
</body>
</html>
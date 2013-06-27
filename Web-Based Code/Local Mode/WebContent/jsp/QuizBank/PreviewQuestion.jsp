<%@page import="com.clicker.databaseconn.DatabaseQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="com.clicker.databaseconn.QuizRecordQueries"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="com.clicker.wrappers.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="../../javascript/jquery-1.3.2.min.js"></script>
<script src="../../javascript/jquery-spin.js"></script>
<script src="../../javascript/QuizBank.js"></script>

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
			.getRequestDispatcher("../../jsp/instructorJspPages/InstructorError.jsp");
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

<body style="padding-left: 0px; padding-right: 0px">
	<div
		style="font-size: 14px; text-align: justify; margin-left: 0px; margin-right: 0px">
		<table style="width: 528px; height: 200px">
			<%
QuizRecordQueries qrq = new QuizRecordQueries();
qrq.addQuizRecord(conn, QuizID);
String QuizRecordID = String.valueOf(qrq.getLatestQuizRecordID(conn, QuizID));
Vector<Question> Questiondetails = new Vector<Question>();
Questiondetails = dbqueries.getallQuestionDetails(conn, String.valueOf(QuizID));

String breakDefault=null;
int QuestionIndex=0;
String CorrectAnswer[]=new String[Questiondetails.size()];

while ((QuestionIndex + 1) <= Questiondetails.size())
{
	question = Questiondetails.elementAt(QuestionIndex);	
	int QuestionType = dbqueries.getQuestionTypeforQuiz(conn, question.getQuestionID());
	String QuestionAnswer="";
	questionStr = question.getQuestionText();	
%>
			<tr>
				<td>Question No : <%= QuestionIndex + 1%>&nbsp;&nbsp;<%=questionStr%></td>
			</tr>
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
%>

			<tr>
				<td>Option <%=OptionResponse%> : <%=option[i].getOptionValue().toString()%>
					&nbsp;&nbsp;&nbsp;
				</td>
			</tr>

			<%
//if(OptionResponse.equals("B")||OptionResponse.equals("D")){	
%><!--  <br/> -->
			<%
//}
%>

			<%}%>

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
%><tr>
				<td><br /></td>
			</tr>
			<%
}

conn.close();
%>
		</table>
	</div>
	<p />
</body>
</html>
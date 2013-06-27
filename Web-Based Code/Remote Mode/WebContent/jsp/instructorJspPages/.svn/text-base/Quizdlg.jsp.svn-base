<!-- This jsp is used for displaying Questions in Quizzes
and storing Correct Answers in Array-->

<%@page import="com.clicker.util.QuizHelper"%>
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
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<script src="../../javascript/jquery-1.3.2.min.js"></script>
<script src="../../javascript/jquery-spin.js"></script>
<script src="../../javascript/Quiz.js"></script>

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
	window.history.forward();
	function noBack() 
	{ 
		window.history.forward(); 
	}
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
			.getRequestDispatcher("../../jsp/instructorJspPages/InstructorError.jsp");
	rd.forward(request, response);
}

Question question;
String questionStr="";

String QuizName = request.getParameter("QuizName");
int type = Integer.parseInt(request.getParameter("type"));
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
			<%@ include file="../newMenu/newMenuwithCSS.jsp"%> 
		<div id="content_in">
			<div style="min-height: 425px; font-weight: bold;">
				<center>
					<div style="height: 20px; text-align: left;">Quiz Module</div>
					<div style="font-size: 18px">
						Quiz ID :
						<%=QuizID%>
						Quiz Name:
						<%=QuizName%></div>
					<div style="height: 30px"></div>

					<div style="font-size: 18px; height:400px;overflow: auto; border: solid; text-align: justify;">
						<%
QuizRecordQueries qrq = new QuizRecordQueries();
if(type == 1)
	qrq.addQuizRecord(conn, QuizID);

String QuizRecordID = String.valueOf(qrq.getLatestQuizRecordID(conn, QuizID));
String QuizRecordTS = qrq.getLatestQuizRecordTime(conn, QuizID);
String noofQuestions = qrq.getNumberofQuestions(conn, QuizID);
String quiztimestamp[ ] = qrq.getQuizTimestamp(QuizID);
Vector<Question> Questiondetails = new Vector<Question>();
Questiondetails = dbqueries.getallQuestionDetails(conn, String.valueOf(QuizID));

int QuestionIndex=0;
Vector<String> CorrectAnswer = new Vector<String>();

while ((QuestionIndex + 1) <= Questiondetails.size())
{
	question = Questiondetails.elementAt(QuestionIndex);	
	int QuestionType = question.getQuestionType();
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
}
if(type ==1){
application.setAttribute(InstructorID + "CorrectAnswer",CorrectAnswer);
session.setAttribute("QuizRecordID", QuizRecordID);
session.setAttribute("QuizRecordTS", QuizRecordTS);
session.setAttribute("noofQuestions", noofQuestions);
session.setAttribute("QuizID", QuizID);
application.setAttribute(InstructorID+"QuizName",QuizName);
application.setAttribute(InstructorID+"QuizID",QuizID);
QuizHelper quizHelper = new QuizHelper();
String quizQuestions = quizHelper.getQuizQuestions(Questiondetails).toString();
quizQuestions = quizQuestions.replace("\r\n", "<br/>").replace("\n", "<br/>").replace("\"", "\\\"");
application.setAttribute(InstructorID+"Questiondetails",Questiondetails);
application.setAttribute(InstructorID+"quizQuestions",quizQuestions);
application.setAttribute(InstructorID+"QuizStatus",null);
}
conn.close();
%>
					</div>
					<%if(type == 1)
					  {%>
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
						onclick="launchQuizURL(QuizID, '<%=session.getAttribute("courseID").toString()%>')">
					<p />
					<br />
					<%}
					 else if(type == 2)
					{%>
						<center>
						<br/><h2> Quiz Conduction Record </h2>
						
	 					<div style="width:420px; height : 120px; overflow:auto;">
						<table border = '1' style = "width : 400px;">
							<thead>
								<tr style  = "font-size : 20px;">
										<th> Quiz Record Number </th> 
				 						<th> Quiz Conducted On </th>  
								</tr>
							</thead>
			
					<% 
						for (int i = 0; i < (quiztimestamp.length); i++)
						{ %>
							<tr style = "font-size : 15px"> 
									<td> <center> <%=i+1 %> </center> </td>
								 	<td> <center> <%=quiztimestamp[i] %></center> </td> 
								 
							</tr>
						<%}%>	
			
						</table>
						</div>
						</center>
					<Form id = "DeleteQuiz" action = "../../deleteQuiz" method = "post" name = "DeleteQuiz" >
						<input type = hidden name = "QuizID" id = "QuizID" value = "<%=QuizID %>" />
						<br /><input type = "submit" value = "Delete Quiz" onclick="return confirm('Are you sure you want to Delete this Quiz?')"/>
					</Form>
					<%}%>
				</center>
			</div>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
</body>
</html>
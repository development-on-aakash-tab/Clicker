<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.clicker.wrappers.*"%>
<!DOCTYPE html>
<%
String StudentID =(String) session.getAttribute("StudentID");
if (StudentID == null) {
	request.setAttribute("Error",	"Your session has expired.");
	RequestDispatcher rd = request.getRequestDispatcher("../../jsp/studentJspPages/StudentError.jsp");
	rd.forward(request, response);
	return;
}
String InstructorID = session.getAttribute("InstructorID").toString();
Vector <Question> Questiondetails =(Vector<Question>) application.getAttribute(InstructorID+"Questiondetails");
int QuizID = Integer.parseInt(application.getAttribute(InstructorID+"QuizID").toString());
String QuizName = application.getAttribute(InstructorID+"QuizName").toString();
//String questionsResponse = session.getAttribute("questionsResponse").toString();
String TempQuestionResponseArray[]=null;
if(session.getAttribute("questionsResponse")!=null){
	String questionsResponse = session.getAttribute("questionsResponse").toString();
	session.setAttribute("TempQuestionResponseArray",  questionsResponse.split("@@"));
}
if(session.getAttribute("TempQuestionResponseArray")!=null){
	TempQuestionResponseArray=(String [])session.getAttribute("TempQuestionResponseArray");
}
Vector <String> CorrectAnswer = (Vector<String>) application.getAttribute(InstructorID + "CorrectAnswer");
Question question;
String questionStr="";
Option option=null;
int QuestionNo=0;
String OptionText="";
String StudentResponse="";
String Result="";
%>
<html>
<head>
<title>Quiz Result</title>
<link href="../../css/style.css" rel="stylesheet" type="text/css" media="screen" /> 
</head>
<body onload="javascript:window.history.forward(); return false;">
		<%@include file="../newMenu/newMenuForStudent.jsp"%>
		<div id="content_in" Style="min-height:350px">
		<br/>
	<h1>Quiz Result</h1>
	<h2>
		Quiz Name :
		<%=QuizName%></h2><br/>
<div style="overflow: auto; height: 350px">
	<table border="1">
		<tr>
			<td><b>Question No.</b></td>
			<td><b>Question</b></td>
			<td><b>Your Response</b></td>
			<td><b>Your Response Value</b></td>
			<td><b>Result</b></td>
		</tr>

		<%
		System.out.println(StudentID+ "-------count"+ Questiondetails.size());
		while ((QuestionNo+1) <= Questiondetails.size())
		{
			question = Questiondetails.elementAt(QuestionNo);
			Vector<Option> Options = question.getOptions();
			StudentResponse = TempQuestionResponseArray[QuestionNo];	
			System.out.println("StudentResponse" + StudentResponse);
			OptionText ="";
			if(StudentResponse.equals("Z"))
			{
				OptionText = " - ";
			}
			else if ("ABCDEF".contains(StudentResponse) && StudentResponse.length()==1)
			{
				int index = "ABCDEF".indexOf(StudentResponse);
				option = Options.elementAt(index);	
				OptionText = option.getOptionValue().toString();
			}	
			else if(StudentResponse.length()>=1 && question.getQuestionType() == 2){
				for(int i=0;i<StudentResponse.length();i++){
					char resp = StudentResponse.charAt(i);
					int index = ((int)resp)-65 ;
					option = Options.elementAt(index);			
					OptionText += option.getOptionValue().toString() + " , ";
				}
			}else if(question.getQuestionType() == 3){	
				System.out.println("Options : "+ StudentResponse);
					OptionText = StudentResponse;			
			}

			if(StudentResponse.equals("Z")){
				Result = "No Result";
			}
			else if (StudentResponse.equals(CorrectAnswer.get(QuestionNo)))
			{
				Result="Correct";
			}
			else
			{
				Result="Wrong";
			}		
%>
		<tr>
			<td><%=QuestionNo+1%></td>
			<td><%=question.getQuestionText()%></td>
			<td><%=StudentResponse%></td>
			<td><%=OptionText%></td>
			<td><%=Result%></td>
		</tr>
		<%
QuestionNo++;
}
		if(session.getAttribute("questionsResponse")!=null){
			session.removeAttribute("questionsResponse");
		}
%>
	</table>
	</div>
	</div>
	
	<%@ include file="../../jsp/includes/footer.jsp"%>
</body>
</html>
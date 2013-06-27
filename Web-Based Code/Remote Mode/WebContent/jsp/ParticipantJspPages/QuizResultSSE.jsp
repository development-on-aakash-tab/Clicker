<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.clicker.wrappers.*"%>

<!DOCTYPE html>
<%
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility*/

String ParticipantID=(String) session.getAttribute("ParticipantID");

System.out.println("ParticipantID is : " + ParticipantID);

if (ParticipantID == null) {
	request.setAttribute("Error", "Session has ended.");
	RequestDispatcher rd = request.getRequestDispatcher("ParticipantError.jsp");
	rd.forward(request, response);
	return;
}

int QuestionIndex=0;
Question question;
String questionStr="";
Option option=null;
Vector <Question> Questiondetails =(Vector<Question>) application.getAttribute("Questiondetails");
int QuizID = Integer.parseInt(application.getAttribute("QuizID").toString());
String questionsResponse=null;
if(session.getAttribute("questionsResponse")!=null){
questionsResponse = session.getAttribute("questionsResponse").toString();
}
String[] temp_questionsResponse=null;
if(questionsResponse!=null){
	session.setAttribute("temp_questionsResponse", questionsResponse.split("@@"));
}
if(session.getAttribute("temp_questionsResponse")!=null){
	temp_questionsResponse=(String [])session.getAttribute("temp_questionsResponse");
}
Vector <String> CorrectAnswer = (Vector<String>) application.getAttribute("CorrectAnswer");
int QuestionNo=0;
String OptionText="";
String StudentResponse="";
String Result="";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<center>
	<%@ include file="../../jsp/includes/menuheaderForParticipant.jsp" %>
	<div id="content_in">
		<h1> Quiz Result </h1>
		<h2> Quiz ID : <%=QuizID%> </h2>
		<br/>
		<%--<h2> Quiz Name : <%=QuizName%></h2> --%>
		<center>
		<div style="overflow: auto; height: 350px ;">
		<table border="1">
			<tr>
		<td><b>Question No.</b></td>
		<td><b>Question</b></td>
		<td><b>Your Response</b></td>
		<td><b>Your Response Value</b></td>
		<td><b>Result</b></td>
		</tr>
		<%
		System.out.println(Questiondetails.size() + " - " +temp_questionsResponse.length + " - " +ParticipantID);
		
		System.out.println("---------------------"+ (Questiondetails.size() == temp_questionsResponse.length));
		while (QuestionNo < Questiondetails.size() )
		{
			question = Questiondetails.elementAt(QuestionNo);
			Vector<Option> Options = question.getOptions();
			if(temp_questionsResponse.length > QuestionNo){
			StudentResponse = temp_questionsResponse[QuestionNo];
			}else{
				StudentResponse = "Z";
			}
			OptionText ="";
			if(StudentResponse == null || StudentResponse.equals("Z"))
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
					OptionText = StudentResponse;			
		}
			
			if(StudentResponse==null || StudentResponse.equals("Z")){
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
		//application.removeAttribute("CorrectAnswer");
		if(session.getAttribute("questionsResponse")!=null){
			session.removeAttribute("questionsResponse");
		}
		%>
		</table>
		</div>
		</center>
	</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
</body>
</html>
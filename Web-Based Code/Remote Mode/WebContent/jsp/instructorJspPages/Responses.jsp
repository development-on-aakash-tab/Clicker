<%@page import="com.clicker.databaseconn.QuizRecordQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="com.clicker.databaseconn.QuizSaveDatabaseRecords"%>
<%@page import="com.clicker.util.GenerateRandomChar"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="com.clicker.wrappers.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% 
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //lHTTP 1.0 backward compatibility

String InstructorID = (String) session.getAttribute("InstructorID");

if (InstructorID == null) {
	request.setAttribute("Error",   
			"Your session has expired.");
	RequestDispatcher rd = request
			.getRequestDispatcher("../../jsp/instructorJspPages/InstructorError.jsp");
	rd.forward(request, response);
	return;
}

Vector<Question> Questiondetails = (Vector <Question>)application.getAttribute(InstructorID+"Questiondetails");

GenerateRandomChar genChar = new GenerateRandomChar(); 
QuizSaveDatabaseRecords quizsave = new QuizSaveDatabaseRecords();

/*
* This is used during only simulation.
* Please comment it if there is not usage of simullation.
*/

/* String DemoStudentIDs[] = {"2",  "3",  "4",  "5",  "6",  "7",  "8",  "10", "12", 
						   "13", "14", "16", "17", "18", "19", "21", "22", "23", 
						   "24", "25", "26", "27", "28", "29", "30", "31", "32", 
						   "33", "34", "35", "36", "37", "39", "40", "41", "42", 
						   "43", "44", "47", "49", "50", "51", "52", "53", "54", 
						   "55", "56", "57", "58", "59", "60", "61", "62", "63"};

String DemoResponses[] = {"D", "B", "B", "C", "A", "A", "D", "C", "B", 
		                  "A", "A", "D", "C", "C", "C", "C", "C", "C", 
		                  "C", "A", "B", "C", "C", "C", "A", "C", "B",
		                  "B", "C", "C", "C", "A", "C", "B", "A", "A",
		                  "C", "C", "C", "C", "C", "C", "C", "C", "C",
		                  "C", "C", "B", "A", "C", "A", "C", "A", "D"}; */

int QuizID = Integer.parseInt(application.getAttribute(InstructorID+"QuizID").toString());

Vector <String> CorrectAnswer=(Vector<String>) application.getAttribute(InstructorID + "CorrectAnswer");
String CorrectAnswerString="";

application.setAttribute(InstructorID+"QuizStatus", "STOP");

String CourseID = (String)session.getAttribute("InstructorCourse");
DatabaseConnection dbconn = new DatabaseConnection();
DatabaseQueries dbqueries = new DatabaseQueries();
Connection conn = dbconn.createDatabaseConnection();

/*
* This is used during only simulation for adding Quiz Record.
* Please comment it if there is not usage of simullation.
*/

/* for (int i=0; i<DemoStudentIDs.length; i++)
{
	String Response="";
	
	for (int j=0; j<Questiondetails.size(); j++)
	{
		Response = DemoResponses[i];
		quizsave.insertDemoQuizRecordQuestionData(conn, QuizID, DemoStudentIDs[i], Response, Questiondetails, j);
	}	
} */ 

dbqueries.updateCourseStatus(conn, 0, CourseID);
conn.close();
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../../javascript/LocalResponses.js"></script>
<script src="../../jquery/jquery-1.5.min.js"></script>
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<script src="../../jquery/jquery-1.8.19-ui.min.js"></script>
<link href="../../jquery/jquery-ui.css" rel="stylesheet" type="text/css" />
<title>Responses</title>
</head>
<body>
	<center>
		<%@ include file="../newMenu/newMenuwithCSS.jsp"%> 
		<div id="content_in">
			<div style="min-height: 425px; font-weight: bold;">
				<center>
					<div style="height: 20px; text-align: left;">
						<h2>Response</h2>
					</div>
					Select Question Number or ID
					<%
int QuestionNumber=0;
String QuestionID="";
%>
					<select id="QuestSelect" name="QuestSelect"
						onchange="showResponses(this.value);">
						<option>Select Question Number / ID</option>
						<%
for (int i=0; i<Questiondetails.size(); i++)
{
	QuestionNumber=QuestionNumber+1;
	QuestionID = Questiondetails.elementAt(i).getQuestionID().toString();
	System.out.println("Question ID is "+QuestionID);
%>
						<option><%=QuestionNumber+"/"+QuestionID%></option>
						<%
CorrectAnswerString = CorrectAnswerString + CorrectAnswer.get(i)+"/";
}
%>
					</select>

					<script type="text/javascript">
var CorrectAnswer = '<%=CorrectAnswerString%>';
</script>

					<div style="height: 5px"></div>
					<div id="responseSummary"></div>
					<div style="height: 5px"></div>
					<div id="ResponseTable" style="overflow: auto"></div>
					<div style="height: 5px"></div>
					<div id="responseValue_hidden" style="visibility: hidden"></div>
					<div id="response_dialog" style="visibility: hidden"
						title="Response"></div>
					<br /> <input type="button" value="Bar Chart With Correct Answer"
						onclick="showCorrectBarChart(CorrectAnswer);" /> <br /> <br /> <input
						type="button" value="Bar Chart" onclick="showBarChart();" /> <br />
					<br /> <input type="button" value="Pie Chart"
						onclick="showPieChart();" /> <br /> <br />
				</center>
			</div>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
</body>
</html>
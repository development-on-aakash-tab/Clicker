<%-- 
Author : Rajavel
Purpose : This jsp file is act as helper for quiz response UI
 --%>

<%@page import="net.sf.jasperreports.engine.convert.ReportConverter"%>
<%@page import="com.clicker.databaseconn.QuizSaveDatabaseRecords"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.clicker.util.ResponseHelper"%>
<%@page import="com.clicker.wrappers.*"%>
<%@page import="java.util.concurrent.ConcurrentHashMap"%>
<%@page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Responses</title>
</head>
<body>
	<%
System.out.println("Inside responsehelper");
String responseType = request.getParameter("responseType");
if(responseType.equals("timeout")){
	ResponseHelper responseHelper = new ResponseHelper();
	int quizRecordID = Integer.parseInt(session.getAttribute("QuizRecordID").toString());
	String courseID = session.getAttribute("InstructorCourse").toString();
	String studentList = responseHelper.getTimeoutStudentList(quizRecordID, courseID);
	out.println(studentList);
}else if(responseType.equals("noanswer")){
	ResponseHelper responseHelper = new ResponseHelper();
	int quizRecordID = Integer.parseInt(session.getAttribute("QuizRecordID").toString());
	int questionID = Integer.parseInt(request.getParameter("questionID"));
	String studentList = responseHelper.getNoAnswerStudentList(quizRecordID, questionID);
	out.println(studentList);
}
else if(responseType.equals("storeresponse")){
	QuizSaveDatabaseRecords saveQuizRecord = new QuizSaveDatabaseRecords();
	String InstructorID = (String) session.getAttribute("InstructorID");
	Vector <Question> Questiondetails = (Vector <Question>) application.getAttribute(InstructorID+"Questiondetails");
	String CourseID = session.getAttribute("courseID").toString();
	ConcurrentHashMap <String, String> AllStudentResponse = (ConcurrentHashMap<String, String>) application.getAttribute(CourseID + "AllStudentResponse");	
	int quizID = application.getAttribute(InstructorID+"QuizID")==null?0:Integer.parseInt(application.getAttribute(InstructorID+"QuizID").toString());
	saveQuizRecord.saveQuizRecord(AllStudentResponse, Questiondetails, quizID);
}else if(responseType.equals("insatantResponse")){
	int iquizid =Integer.parseInt(session.getAttribute("iquizid").toString());
	ResponseHelper responseHelper = new ResponseHelper();
	out.println(responseHelper.getInsatantResponTable(iquizid));
}
%>
</body>
</html>
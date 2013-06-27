<%-- 
Author : Rajavel 
Jsp page : This jsp file is act as Helper to generate report and AJAX   
 --%>


<%@page import="com.clicker.report.ReportHelper"%>
<%
System.out.println("Inside DAO ReportHelper");
String infotype = request.getParameter("infotype");

ReportHelper reportHelper = new ReportHelper();
if(infotype.equals("studentinfo")){
    String sid = request.getParameter("sid");
    String studentinfo = reportHelper.getStudentInfo(sid);
    out.print(studentinfo);   
}
else if(infotype.equals("studentlist")){
    String cid = request.getParameter("cid");
    String studentList = reportHelper.getStudentList(cid);
    out.print(studentList);
}
else if(infotype.equals("quizstudatteninfo")){
    String cid = request.getParameter("cid");
    String quizstudatteninfo = reportHelper.getQuizStudAttenTSInfo(cid);
    out.print(quizstudatteninfo);
}
else if(infotype.equals("datequizinfo")){
    String cid = request.getParameter("cid");
    String date = request.getParameter("date");
    String instructorID = session.getAttribute("InstructorID").toString();
    String quizinfo = reportHelper.getDateQuizInfo(cid, date, instructorID);
    out.print(quizinfo);
}
else if(infotype.equals("quiztime")){
    String qid = request.getParameter("qid");
    String quiztime = reportHelper.getQuizTime(qid);
    out.print(quiztime);
}else if(infotype.equals("datequiztime")){
    String qid = request.getParameter("qid");
    String qdate = request.getParameter("qdate");
    String quiztime = reportHelper.getQuizTime(qid, qdate);
    out.print(quiztime);
}
else if(infotype.equals("atteninfo")){
	String cid = request.getParameter("cid");
    String date = request.getParameter("date");
    String atteninfo = reportHelper.getAttendanceInfo(cid, date);
    out.print(atteninfo);
}
%>
<%-- 
Author : Rajavel 
Jsp page : This jsp file is act as Helper to generate report and AJAX   
 --%>


<%@page import="com.clicker.report.*"%>
<%
System.out.println("Inside DAO ReportHelper");
String infotype = request.getParameter("infotype");

ReportHelper reportHelper = new ReportHelper();
if(infotype.equals("datequiztime")){
    String qid = request.getParameter("qid");
    String qdate = request.getParameter("qdate");
    String quiztime = reportHelper.getQuizTime1(qid, qdate);
    out.print(quiztime);
}
else if(infotype.equals("datequiz")){	
    String date = request.getParameter("qdate");
    String atteninfo = reportHelper.getQuizList(date);
    out.print(atteninfo);
}
else if(infotype.equals("remotecenterlist")){
    String qid = request.getParameter("qid");
    String qts = request.getParameter("qts");
    String remotecenter = reportHelper.getRemoteCenterList(qid, qts);
    out.print(remotecenter);
}
%>
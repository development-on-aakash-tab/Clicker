<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page language="java" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.sql.*" %>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
/*
Once a question is selected we archive it.
*/
String qid = request.getParameter("id"), delimiter = ",";
String question_id[ ] = qid.split(delimiter);
//out.println(question);
Connection conn=null,conn1 = null;
try{
DatabaseConnection dbconn = new DatabaseConnection();
conn = dbconn.createDatabaseConnection();
Statement st = conn.createStatement();
for (int i = 0; i < question_id.length; i++)
	st.executeUpdate("update question set Archived='1' where QuestionID='"+question_id[i]+"'");

}catch(Exception e){
	e.getStackTrace();
}finally{
	try{
		conn.close();
	}catch(Exception e){
		e.getStackTrace();
	}finally{
		response.sendRedirect("questionbank.jsp");
	}
}	

%>
</body>
</html>
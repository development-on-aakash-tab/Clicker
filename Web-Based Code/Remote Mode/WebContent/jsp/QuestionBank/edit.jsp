<%@ page language="java" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.sql.*" %>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import ="com.clicker.report.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Connection conn,conn1;
conn = null;
conn1=null;
int qtype = -1;
int qid = -1;
try{
DatabaseConnection db = new DatabaseConnection();
conn = db.createDatabaseConnection();
qid = Integer.parseInt(request.getParameter("qid"));

String query = "select QuestionType from question where QuestionID='"+qid+"'";

Statement pstmt = conn.prepareStatement(query);
pstmt.execute("update question set Archived=0 where QuestionID="+qid);
ResultSet rs = pstmt.executeQuery(query);


while(rs.next())
{
	qtype = rs.getInt("QuestionType");
}
}catch(Exception e){
	e.getStackTrace();
}finally{
	try{
		conn.close();
	}catch(Exception e){
		e.getStackTrace();
	}finally{
		switch(qtype)
		{
		case 1:	response.sendRedirect("singleanswer_edit.jsp?qid="+qid);
				break;
		case 2:	response.sendRedirect("MultChoice_edit.jsp?qid="+qid);
				break;
		case 3:	response.sendRedirect("integer_edit.jsp?qid="+qid);
				break;
		case 4:	response.sendRedirect("trueFalse_edit.jsp?qid="+qid);
				break;
		}
	}
}	



%>
</body>
</html>
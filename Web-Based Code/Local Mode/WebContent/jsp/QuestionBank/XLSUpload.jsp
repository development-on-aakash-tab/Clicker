<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%> 
<%@page import="java.sql.*"%> 
<%@page import="com.clicker.QuestionBank.*"%>

<% 

	XLSimport xls=new XLSimport();
	DatabaseConnection dbcon = new DatabaseConnection();
	Connection con=dbcon.createDatabaseConnection();
	String path1=request.getParameter("xls");
	System.out.println("xls"+path1);
	ServletContext context = pageContext.getServletContext();
	String filename=context.getInitParameter("file")+path1;
	File file = new File(filename);
	String status=xls.readQuestionXLSFile(file,con);
	con.close();
	response.sendRedirect("../../jsp/QuestionBank/questionbank.jsp?fileUploadStatus="+status);
%>
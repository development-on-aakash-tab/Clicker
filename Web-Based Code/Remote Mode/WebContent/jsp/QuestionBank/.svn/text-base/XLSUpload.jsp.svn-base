<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%> 
<%@page import="java.sql.*"%> 
<%@page import="com.clicker.QuestionBank.*"%>
<%@page import="java.net.*"%>
<%@page import="javax.servlet.http.*"%>

<% 

	XLSimport xls=new XLSimport();
	/*DatabaseConnection dbcon = new DatabaseConnection();
	Connection con=dbcon.createDatabaseConnection();*/
	String path1=request.getParameter("xls");
	System.out.println("xls: "+path1);
	//ServletContext context = pageContext.getServletContext();
	ServletContext context = request.getServletContext();
	String pathurl = context.getRealPath("/uploads");
	
	System.out.println("url = " + pathurl);
	//String filename="/home/manjur/Desktop/" + path1;
	//System.out.println("Filename: " + filename);
	File file = new File(pathurl + "/" + path1);
	System.out.println("Filename: " + new File(path1).getAbsolutePath());
	String instructorid = (String) session.getAttribute("InstructorID");
	String status=xls.readQuestionXLSFile(instructorid, file);
	//con.close();
	response.sendRedirect("../../jsp/QuestionBank/questionbank.jsp?fileUploadStatus="+status);
%>
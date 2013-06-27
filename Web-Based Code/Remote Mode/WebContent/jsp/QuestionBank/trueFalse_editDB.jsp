<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="java.sql.*"%>
<%@ page import = "com.clicker.QuestionBank.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String instructorid = (String) session.getAttribute("InstructorID");
	DatabaseConnection dbconn = new DatabaseConnection();
	Connection conn = dbconn.createDatabaseConnection();
	String query1="update question set Question= ? where QuestionID= ?" ;
	PreparedStatement st =conn.prepareStatement(query1);
	int qid = Integer.parseInt(request.getParameter("qid"));
	String question=request.getParameter("question");
	String option=request.getParameter("option");
	String image = request.getParameter("browse");
	st.setString(1, question);
	st.setInt(2, qid);
	
	//Adding Entry in the History table
	History history = new History (qid, question, instructorid);
	history.addentry ();
	
	int rs=st.executeUpdate();
	String query3 = "", query4 = "";
	try
	{
	out.println(option);
	if(option.equals("true")){
		
		query3="update options set OptionCorrectness='1' where QuestionID='"+qid+"' and OptionValue='true'" ;
		query4="update options set OptionCorrectness='0' where QuestionID='"+qid+"' and OptionValue='false'" ;
	}
	else{
		query3="update options set OptionCorrectness='0' where QuestionID='"+qid+"' and OptionValue='true'" ;
		query4="update options set OptionCorrectness='1' where QuestionID='"+qid+"' and OptionValue='false'" ;
	}	
	int rs2=st.executeUpdate(query3);
	int rs3=st.executeUpdate(query4);
	
	if((rs!=0)||(rs2!=0))
	{
		out.println("Successful");
		response.sendRedirect("questionbank.jsp");
		
	}
	else
		out.println(" Not Successful");
	conn.close();
}
catch(Exception e)
{
	out.println(e+"  "+option);
}

%>
</body>
</html>
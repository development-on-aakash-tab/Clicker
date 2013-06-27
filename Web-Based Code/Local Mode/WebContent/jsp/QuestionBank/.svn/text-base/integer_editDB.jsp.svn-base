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
Connection conn=null ,connRemote= null;
String instructorid = (String) session.getAttribute("InstructorID");
try{
	DatabaseConnection dbconn = new DatabaseConnection();
	conn = dbconn.createDatabaseConnection();
	String query1 = "update question set Question= ? where QuestionID= ? ";
	String query2 = "update options set OptionValue =? where QuestionID=?";
	
		PreparedStatement st = conn.prepareStatement(query1,Statement.RETURN_GENERATED_KEYS);
		String question=request.getParameter("question");
		String option=request.getParameter("answer");
		String image = request.getParameter("browse");
		int qid = -1;
		qid = Integer.parseInt(request.getParameter("qid"));
		st.setString(1,question);
		st.setInt(2,qid);
		//String query1="insert into question(Question,LevelOfDifficulty,Archived,Credit,QuestionType) values('"+question+"',1,0,5,3)";
		int rs=st.executeUpdate();
		ResultSet res = st.getGeneratedKeys();
		//String query2="insert into options(OptionValue,LevelOfDifficulty,OptionCorrectness,Archived,Credit,QuestionID) values('"+option+"',1,1,0,5,'"+question_id+"')";
		PreparedStatement st1 = conn.prepareStatement(query2,Statement.RETURN_GENERATED_KEYS);
		st1.setString(1,option);
		st1.setInt(2,qid);
		int rs2=st1.executeUpdate();
		
		// Adding entry in the Questions history table
		History history = new History (qid, question, instructorid);
		history.addentry ();
		
		if((rs!=0)||(rs2!=0))
		{
			out.println("Successful");
			response.sendRedirect("questionbank.jsp");
		}
		else
			out.println(" Not Successful");
}
catch(Exception e)
{
	out.println(e);
}finally{
	try{
		conn.close();
	}catch(Exception e){
		e.getStackTrace();
	}
}	

%>
</body>
</html>
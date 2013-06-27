<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.FileInputStream"%>
<%@ page import = "java.sql.*" %>
<%@ page import ="java.util.*" %>
<%@ page import ="java.io.*" %>
<%@page import ="com.clicker.QuestionBank.*" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Connection conn = null;
PreparedStatement st =null, st1 = null, st2 = null, st3 = null, st4=null, st5=null;
String instructorid = (String) session.getAttribute("instructorid");
int option_count=-1, old_option_count=-1;
String question = null;	
try
{
	DatabaseConnection dbcon = new DatabaseConnection();	
	conn = dbcon.createDatabaseConnection();	
	option_count = Integer.parseInt(request.getParameter("count"));
	old_option_count = Integer.parseInt(request.getParameter("old_count"));
	String[] options= new String[option_count];
	System.out.println( request.getParameter("optionIDs"));
	String[] optionIDs = request.getParameter("optionIDs").split(";");
	int correctOption = Integer.parseInt(request.getParameter("option"))-1;
	int correctness =0;
	question = request.getParameter("ques");	
	int qid = -1;
	qid = Integer.parseInt(request.getParameter("qid"));
	st = conn.prepareStatement("update question set Question= ? where QuestionID= ?");
	st.setString(1,question);
	st.setInt(2,qid);
	st.executeUpdate();
	st.close();
	st1 = conn.prepareStatement("update options set OptionValue = ?, OptionCorrectness = ? where OptionID= ?");
	
	// Adding entry to history table
	History history = new History (qid, question, instructorid);
	history.addentry ();
	
	if(old_option_count<=option_count){
		for(int i=0;i<old_option_count;i++)
		{
			st1.setString(1,request.getParameter(""+(i+1)));
			st1.setInt(2,correctOption==i?1:0);		
			st1.setInt(3,Integer.parseInt(optionIDs[i]));	
			st1.executeUpdate();
		}	
		st1.close();
		st2 = conn.prepareStatement("Insert into options(OptionValue,OptionCorrectness,LevelofDifficulty,Archived,Credit,QuestionID) values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		st3 = conn.prepareStatement("Insert into quizquestionoption(QuizID, QuestionID, OptionID) values (?, ?, ?)");
		for(int i=old_option_count;i<option_count;i++)
		{
				st2.setString(1,request.getParameter(""+(i+1)));
				st2.setInt(2,correctOption==i?1:0);		
				st2.setInt(3,1);
				st2.setInt(4,0);
				st2.setInt(5,5);
				st2.setInt(6,qid);
				st2.executeUpdate();	
				ResultSet res = st2.getGeneratedKeys();
				int optionid =0;
				if (res.next())
				{
					optionid = res.getInt(1);
				}				
				PreparedStatement preparedStatement = conn.prepareStatement("SELECT distinct QuizID from quizquestionoption where QuestionID = ?");
				preparedStatement.setInt(1, qid);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					int quizid = rs.getInt("QuizID");	
					st3.setInt(1,quizid);		
					st3.setInt(2,qid);
					st3.setInt(3,optionid);
					st3.executeUpdate();
				}				
		}	
		st3.close();
		st2.close();
	}		
	else if(old_option_count>option_count){
		for(int i=0;i<option_count;i++)
		{
			st1.setString(1,request.getParameter(""+(i+1)));
			st1.setInt(2,correctOption==i?1:0);		
			st1.setInt(3,Integer.parseInt(optionIDs[i]));	
			st1.executeUpdate();
		}	
		st1.close();
		st4 = conn.prepareStatement("Delete from quizquestionoption where OptionID = ?");
		st5 = conn.prepareStatement("Delete from options where OptionID = ?");
		for(int i=option_count;i<old_option_count;i++)
		{
				st4.setInt(1, Integer.parseInt(optionIDs[i]));				
				st4.executeUpdate();	
				st5.setInt(1, Integer.parseInt(optionIDs[i]));				
				st5.executeUpdate();
		}
		st4.close();
		st5.close();
	}	
	conn.close();
	response.sendRedirect("questionbank.jsp");
}	
catch(Exception ex)
{
	ex.printStackTrace();
}

%>
</body>
</html>
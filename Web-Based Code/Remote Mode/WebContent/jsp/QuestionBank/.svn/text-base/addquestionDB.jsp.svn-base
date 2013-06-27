<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ page import="java.io.FileInputStream" %>
    <%@ page import = "java.sql.*" %>
    <%@ page import ="java.util.*" %>
    <%@ page import ="java.io.*" %>
    <%@ page import = "com.clicker.QuestionBank.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String question;
Connection conn = null;
PreparedStatement st =null, st2 = null;
String instructorid = (String) session.getAttribute("InstructorID");
try
{
	DatabaseConnection dbcon = new DatabaseConnection();	
	conn = dbcon.createDatabaseConnection();
	st = conn.prepareStatement("Insert into question(Question,LevelOfDifficulty,Archived,Credit,QuestionType,InstrID) values(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS );
	st2 = conn.prepareStatement("Insert into options(OptionValue,OptionCorrectness,LevelofDifficulty,Archived,Credit,QuestionID) values(?,?,?,?,?,?)");
	
	//Statement statement = conn.createStatement(); 
}
catch(Exception ex)
{
	System.out.println(ex);
	System.out.println("Here:");
}


int correctOption=0, ctr=-1;
	ctr = Integer.parseInt(request.getParameter("count"));
	String[] options= new String[ctr];
	question = request.getParameter("ques");
	
	for(int i=0;i<ctr;i++)
	{
		options[i] = request.getParameter(""+(i+1));
	}
	correctOption = Integer.parseInt(request.getParameter("option"))-1;
	int qid = -1;
	//System.out.println("INSERT INTO question (Question, LevelOfDifficulty, Archived, Credit, QuestionType) VALUES(\""+question+ "\",1,0,5,1");
	st.setString(1,question);
	st.setInt(2,1);
	st.setInt(3,0);
	st.setFloat(4,5.0f);
	st.setInt(5,1);
	st.setString(6,instructorid);
	try
	{
		st.executeUpdate();
		ResultSet rs=st.getGeneratedKeys();
		if (rs.next()) 
		{
    	   	qid = rs.getInt(1);
    	} 
		else 
		{
        	throw new RuntimeException("PIB, can't find most recent insert we just entered");
    	}
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	
	// Adding entry to history table
			History history = new History (qid, question, instructorid);
			history.addentry ();
			
	for(int i=0;i<ctr;i++)
	{
	
			//out.println("Here:"+options[i]);
			//statement.setInt(1,optionID);
			//out.println(request.getParameter(options[i]+"_Value"));
			st2.setString(1,options[i]);
			if(i==correctOption)
				st2.setInt(2,1);
			else
				st2.setInt(2,0);
			st2.setInt(3,1);
			st2.setInt(4,0);
			st2.setInt(5,5);
			st2.setInt(6,qid);
		try
		{
			st2.executeUpdate();
		}
		catch(Exception ex)
		{
			//ex.printStackTrace();
		}
	}
try
{
	st.close();
	st2.close();
	conn.close();
}
catch(Exception ex)
{
	//ex.printStackTrace();
}


response.sendRedirect("singleanswer.jsp");

%>
</body>
</html>
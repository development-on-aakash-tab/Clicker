<%@ page language="java" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
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
// DELETE FROM `aakashclicker`.`quizquestionoption` WHERE `quizquestionoption`.`QuizID` = 1 AND `quizquestionoption`.`QuestionID` = 2 AND `quizquestionoption`.`OptionID` = 3;
// DELETE FROM `aakashclicker`.`options` WHERE `options`.`OptionID` = 3;
	Connection conn=null ;
	try{
	String q_id=session.getAttribute("ques_id").toString();
	int len=Integer.parseInt(request.getParameter("l"));
	int len_cor=Integer.parseInt(request.getParameter("l1"));
	String question= request.getParameter("qv");
	DatabaseConnection dbconn = new DatabaseConnection();
	conn = dbconn.createDatabaseConnection();
	Statement st = conn.createStatement();
	String[] option=request.getParameterValues("optionval");
	String[] correct=request.getParameterValues("correctval");
	String[] innerArray=null;
	String[] innerArray1=null;
	int i;
	out.println("len="+len_cor);
	for(i=0;i<option.length;i++){
		innerArray = option[i].split(",");
	}
	for(i=0;i<correct.length;i++){
		innerArray1 = correct[i].split(",");
	}

	//Update Question
	String ques_update="update question set Question='"+question+"' where QuestionID='"+q_id+"'";
	int rs=st.executeUpdate(ques_update);

	/* if(rs!=0)
	{
		out.println("Successful!!!");
	}
	else
	{
		out.println(" Not Successful!!!");
	} */
	
	//Update Options- Delete
	String opt_delete="delete from options where QuestionID='"+q_id+"'";
	int rs1=st.executeUpdate(opt_delete);
	/* if(rs1!=0)
	{
		out.println("Successfully deleted!!!");
	}
	else
	{
		out.println(" Not Successfully deleted!!!");
	} */
	
	int correctness=0;
	//Update Options - Add
	for(int j =0;j<len;j++)
	{	correctness=0;
		for(int l=0;l<len_cor;l++)
		{
			if(innerArray[j].equals(innerArray1[l]))
			{
			correctness=1;
			break;
			}
		}
		String query2 = "insert into options(OptionValue, OptionCorrectness, QuestionID) values('"+innerArray[j].toString()+"','"+correctness+"','"+q_id+"')";
		int rs2=st.executeUpdate(query2);
		
		/*  if(rs2!=0)
		{
			out.println("Successfully updated Option!!!");
		}
		else
		{
			out.println(" Not Successful!!!");
		} */
	}
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
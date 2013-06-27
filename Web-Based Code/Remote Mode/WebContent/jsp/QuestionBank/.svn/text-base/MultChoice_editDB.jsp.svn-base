<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import ="java.io.FileInputStream" %>
    <%@ page import ="java.util.*" %>
    <%@ page import ="java.io.*" %>
    <%@ page import = "com.clicker.databaseconn.DatabaseConnection" %>
   <%@page import ="com.clicker.QuestionBank.*" %> 
    <%@ page import = "java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Connection conn=null;
PreparedStatement  st =null, st1 = null, st2 = null, st3 = null, st4=null, st5=null;
String instructorid = (String) session.getAttribute("instructorid");
int option_count=-1, old_option_count=-1;
String question = null;	
try{
	DatabaseConnection dbconn = new DatabaseConnection();
	conn = dbconn.createDatabaseConnection();
	option_count = Integer.parseInt(request.getParameter("count"));
	old_option_count = Integer.parseInt(request.getParameter("old_count"));
	String[] options= new String[option_count];
	System.out.println( request.getParameter("optionIDs"));
	String[] optionIDs = request.getParameter("optionIDs").split(";");
	String[] correctIDs = request.getParameter("correctcount").split(";");
	question=request.getParameter("Question");
	int qid = -1;
	qid = Integer.parseInt(request.getParameter("qid"));

	// Adding entry to history table
	History history = new History (qid, question, instructorid);
	history.addentry ();
	
	st = conn.prepareStatement("update question set Question= ? where QuestionID= ? ");
	st.setString(1,question);
	st.setInt(2,qid);
	st.executeUpdate();
	st.close();
	st1 = conn.prepareStatement("update options set OptionValue = ?, OptionCorrectness = ? where OptionID= ?");
	if(old_option_count<=option_count){
		for(int i=0;i<old_option_count;i++)
		{
			st1.setString(1,request.getParameter(""+(i+1)));
			
			for(int p=0;p<correctIDs.length;p++){
				
				if(!(Integer.parseInt(correctIDs[p])-1==i)){
					System.out.println("not matched Matched.."+"value of correctid.."+(Integer.parseInt(correctIDs[p])-1)+"value of i..."+i);
					st1.setInt(2,0);		
				}else{
					System.out.println("Matched.."+"value of correctid.."+(Integer.parseInt(correctIDs[p])-1)+"value of i..."+i+" option id..."+Integer.parseInt(optionIDs[i]));
					st1.setInt(2,1);	
					break;	
				}
			}
			st1.setInt(3,Integer.parseInt(optionIDs[i]));	
			st1.executeUpdate();
			
			
		}	
		st2 = conn.prepareStatement("Insert into options(OptionValue,OptionCorrectness,LevelofDifficulty,Archived,Credit,QuestionID) values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		st3 = conn.prepareStatement("Insert into quizquestionoption(QuizID, QuestionID, OptionID) values (?, ?, ?)");
		for(int i=old_option_count;i<option_count;i++)
		{
			
				st2.setString(1,request.getParameter(""+(i+1)));
				for(int p=0;p<correctIDs.length;p++){
					if(!((Integer.parseInt(correctIDs[p])-1)==(i))){
						st2.setInt(2,0);	
					}else{
						st2.setInt(2,1);
						break;			
					}
				}
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
			
			for(int p=0;p<correctIDs.length;p++){
				
				if(!(Integer.parseInt(correctIDs[p])-1==i)){
					st1.setInt(2,0);
					st1.setInt(3,Integer.parseInt(optionIDs[i]));	
					st1.executeUpdate();
					
					
				}else{
					st1.setInt(2,1);
					st1.setInt(3,Integer.parseInt(optionIDs[i]));	
					st1.executeUpdate();
					break;
					
				}
			}
			
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
	response.sendRedirect("../../jsp/QuestionBank/questionbank.jsp");
}
catch(Exception ex)
{
	ex.printStackTrace();
}
//response.sendRedirect("questionbank.jsp");	%>

</body>
</html>
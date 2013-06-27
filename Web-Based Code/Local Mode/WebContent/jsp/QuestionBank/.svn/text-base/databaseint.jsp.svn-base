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
Connection conn = null;
String instructorid = (String) session.getAttribute("InstructorID");
try{
	DatabaseConnection dbconn = new DatabaseConnection();
	conn = dbconn.createDatabaseConnection();
	String query1="insert into question(Question,LevelOfDifficulty,Archived,Credit,QuestionType,InstrID) values(?,?,?,?,?,?)";
	
		PreparedStatement st =conn.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
		int question_id=0;
		String question=request.getParameter("question");
		String option=request.getParameter("answer");
		String image = request.getParameter("browse");
		st.setString(1, question);
		st.setInt(2, 1);
		st.setInt(3, 0);
		st.setInt(4, 5);
		st.setInt(5, 3);
		st.setString(6,instructorid);
		int rs=st.executeUpdate();
		ResultSet res = st.getGeneratedKeys();
		if(res.next()){
			question_id  = res.getInt(1);
		}
		String query2="insert into options(OptionValue,LevelOfDifficulty,OptionCorrectness,Archived,Credit,QuestionID) values('"+option+"',1,1,0,5,'"+question_id+"')";
		int rs2=st.executeUpdate(query2);
		if((rs!=0)||(rs2!=0))
		{
			out.println("Successful");
			response.sendRedirect("integer.jsp");
		}
		else
			out.println(" Not Successful");
		
		// Adding entry in the Questions history table
		History history = new History (question_id, question, instructorid);
		history.addentry ();
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
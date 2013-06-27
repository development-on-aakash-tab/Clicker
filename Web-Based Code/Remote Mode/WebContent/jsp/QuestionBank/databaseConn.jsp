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
	int question_id=0;String query4,query3;
	String question=request.getParameter("question");
	String credit=request.getParameter("credit");
	String option=request.getParameter("op");
	String image = request.getParameter("browse");
	DatabaseConnection dbconn = new DatabaseConnection();
	conn = dbconn.createDatabaseConnection();
	String query1="insert into question(Question,Archived,Credit,ImageName,QuestionType,InstrID) values(?, ?, ?, ?, ?, ?)";
	PreparedStatement st =conn.prepareStatement(query1 , Statement.RETURN_GENERATED_KEYS);
	st.setString(1, question);
	st.setInt(2, 0);
	st.setFloat(3, 5);
	st.setString(4,	image);
	st.setInt(5, 4);
	st.setString(6,instructorid);
	int rs=st.executeUpdate();
	ResultSet res = st.getGeneratedKeys();
	if(res.next()){
		question_id  = res.getInt(1);
	}
	
	// Adding entry in the Questions history table
	History history = new History (question_id, question, instructorid);
	history.addentry ();
	
	if(option.equals("true")){
	query3="insert into options(OptionValue,OptionCorrectness,QuestionID) values('true',1,'"+question_id+"')";
	    query4="insert into options(OptionValue,OptionCorrectness,QuestionID) values('false',0,'"+question_id+"')";
	}
	else{
		query3="insert into options(OptionValue,OptionCorrectness,QuestionID) values('true',0,'"+question_id+"')";
		query4="insert into options(OptionValue,OptionCorrectness,QuestionID) values('false',1,'"+question_id+"')";
	}
	int rs2=st.executeUpdate(query3);
	int rs3=st.executeUpdate(query4);
	if((rs!=0)||(rs2!=0)||(rs3!=0))
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
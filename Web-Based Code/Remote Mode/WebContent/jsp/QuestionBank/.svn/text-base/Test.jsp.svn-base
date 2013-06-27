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
String instructorid = (String) session.getAttribute("instructorid");
try{
	DatabaseConnection dbconn = new DatabaseConnection();
	conn = dbconn.createDatabaseConnection();
	System.out.println("Conn here:"+conn);
	int count=Integer.parseInt((String)request.getParameter("Count"));
	int questionID;
	//out.println(request.getParameter("Count"));
	String Question=(String)request.getParameter("Question");
	int LevelOfDifficulty=1;
	float credit=1;
	String imageName=(String)request.getParameter("image");
	int questionType = 2;
	String options[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	int archived=0;
	Insert_Question i_q=new Insert_Question();
	questionID=i_q.insertQuestion(conn, Question, LevelOfDifficulty, archived, credit, imageName, questionType, instructorid);
	System.out.println(questionID);
	Insert_Options i_o=new Insert_Options();
	
	// Adding entry in the Questions history table

	History history = new History (questionID, Question, instructorid);
	history.addentry ();
	
	/*PreparedStatement st=conn.prepareStatement("Insert into question(Question,LevelOfDifficulty,Archived,Credit,ImageName,QuestionType) values(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
	st.setString(1,Question);
	st.setInt(2,LevelOfDifficulty);
	st.setInt(3,archived);
	st.setFloat(4,credit);
	st.setString(5, imageName);
	st.setInt(6,questionType);
	int affected_rows=st.executeUpdate();
	ResultSet rs=st.getGeneratedKeys();
	if (rs.next()) {
        questionID = rs.getInt(1);
    } else {
        throw new RuntimeException("PIB, can't find most recent insert we just entered");
    }*/
	//PreparedStatement statement = conn.prepareStatement("Insert into options(OptionValue,OptionDesc,OptionCorrectness,LevelofDifficulty,Archived,Credit,QuestionID) values(?,?,?,?,?,?,?)");
	for(int i=1;i<=count+4;i++)
	{
		if(request.getParameter(options[i-1])!=null)
		{
			i_o.insertOption(request.getParameter(""+i).toString(), "nothing", 1, 1, 0, 1, questionID);
		}
		else
		{
			i_o.insertOption(request.getParameter(""+i).toString(), "nothing", 0, 1, 0, 1, questionID);
		}
		/*if(request.getParameter(options[i-1])!=null)
		{
			//out.println("Here:"+options[i]);
			//statement.setInt(1,optionID);
			//out.println(request.getParameter(""+i));
			statement.setString(1,request.getParameter(""+i));
			statement.setString(2,"Nothing");
			statement.setInt(3,1);
			statement.setInt(4,1);
			statement.setInt(5,0);
			statement.setInt(6,1);
			statement.setInt(7,questionID);
		}
		else
		{
			//out.println("Else:"+options[i]);
			//statement.setInt(1,optionID);
			//out.println(request.getParameter(""+i));
			statement.setString(1,request.getParameter(""+i));
			statement.setString(2,"Nothing");
			statement.setInt(3,0);
			statement.setInt(4,1);
			statement.setInt(5,0);
			statement.setInt(6,1);
			statement.setInt(7,questionID);
		}

		statement.executeUpdate();
		//optionID++;*/
	}
	/*if(affected_rows!=0)
	{
		//questionID++;
		out.println("Data Succesfully Inserted");
	}
	else
	{
		out.println("Error inserting data");
	}*/
	response.sendRedirect("MultChoice.jsp");	
}
catch(Exception e)
{
	out.println(e);
}finally{
	conn.close();
}%>

</body>
</html>
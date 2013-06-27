<%@ page language="java" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.sql.*" %>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@ page import = "java.util.*" %>
<%@ page import = "java.io.*" %>
<%@ page import ="javax.swing.*" %>
<%@ page import ="javax.script.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- djstyles.css contains styling only for Question Bank Module! -->
<link href="../../djstyles.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../../javascript/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="../../javascript/Quiz.js">
</script>
<script>
/*if(document.getElementById("nohist").value != "")
	nohistory( );
function nohistory( )
{
	//if(document.getElementById("nohist").value != "")
	alert("The Selected Question does not have any History");
	}*/

</script>
<title>History</title>
</head>
<body>

	<center><%@ include file="../newMenu/newMenuwithCSS.jsp"%>
	<%@ include file="../../jsp/raisehand/RaiseHandSideBar.jsp" %></center>
	<%! String [ ] temp1, temp2, temp3;%>
<%
	
	Connection conn = null;
	DatabaseConnection db = new DatabaseConnection ( );
	conn = db.createDatabaseConnection ( );
	PreparedStatement st = null;
		
	int qid = -1, qtype = -1;
	qid = Integer.parseInt (request.getParameter ("qid"));
	
	String question = "", instructor = "", date = "";
	String delimiter = ";";
	
	st = conn.prepareStatement("select Question, Instructor, Date from questionshistory where QuestionID = ? ORDER BY Date asc");
	st.setInt(1, qid);
	
	try
	{
		
		ResultSet rs = st.executeQuery( );
			
		while (rs.next())
		{
			
			instructor += (rs.getString("Instructor")) + ";";
			question += (rs.getString ("Question")) + ";";
			date += (rs.getTimestamp("Date")) + ";";
		}
		
		temp1 = instructor.split(delimiter);
		temp2 = question.split (delimiter);
		temp3 = date.split(delimiter);
	}
	catch (Exception e)
	{
		out.println ("Fatal Error! Exiting due to " + e);
	}
	
%>

	<center>
	
	<h1> Question Log </h1>
	 <br/><div style="width:820px; height : 420px; overflow:auto;">
		<table border = '1' style = "width : 820px;">
			<thead>
				<tr style  = "font-size : 20px;"> <th> Instructor </th> 
					 <th> Questions </th>
				 	<th> Date </th>  
				</tr>
			</thead>
			
			<% 
				for (int i = 0; i < (temp2.length); i++)
					{ %>
						<tr style = "font-size : 15px"> <td> <%=temp1[i] %> </td> 
							 <td> <%=temp2[i] %> </td>
						 	<td> <%=temp3[i] %> </td>
						</tr>
			<%}%>	
			
		</table>		
	  </div>
	   
	</center>
		   
	  	 
	<br/>
	<br/>
	<center><%@ include file="../../jsp/includes/footer.jsp" %></center>
	
</body>
</html>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.sql.*" %>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility*/
String insid=(String)session.getAttribute("instructorid");
session.setAttribute("instrid",insid);
if (insid == null) {
	request.setAttribute("Error","Your session has expired.");
	RequestDispatcher rd = request.getRequestDispatcher("../../jsp/errorpages/Error.jsp");
	rd.forward(request, response);
}

String Usertype = (String) session.getAttribute("Usertype");
%>
<html>
<head>
<!-- djstyles.css contains styling only for Question Bank Module! -->
<link href="../../djstyles.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" language="JavaScript">
function validateForm()
{
	var x=document.forms["form2"]["question"].value;
	var y=document.forms["form2"]["answer"].value;
	if (x==null || x=="" || x.trim()=="")
	{
	  	alert("Question must be filled!!!");
	  	return false;
  	}
	if (y==null || y=="" || y.trim()=="")
	{
		alert("Answer must be filled!!!")	
		return false;
	}
	if ((y<1)||(isNaN(y)==true))
	{
		alert("Enter answer between 0-9");
		return false;
	}
}
</script>
<title>Instructor integer</title>
</head>
<body>
<%
Connection conn = null;
DatabaseConnection dbconn = new DatabaseConnection();
conn = dbconn.createDatabaseConnection();
Statement st = conn.createStatement();
%>
<center>
<% if(session.getAttribute("Mode").equals("Remote")){ %>
<%//@ include file="../newMenu/newMenuForRemoteInst.jsp"%>
<%}else{ %>
	<%@ include file="../newMenu/newMenuwithCSS.jsp"%>
	<%@ include file="../../jsp/raisehand/RaiseHandSideBar.jsp" %>
<%}%>
<div id="content_in">
<% 
PrintWriter pw = response.getWriter();
String qid=request.getParameter("qid");
String query1 = "SELECT Question FROM question WHERE QuestionID='"+qid+"'";
String question = null;
ResultSet rs22 = st.executeQuery(query1);
while(rs22.next())
{
	question = rs22.getString("Question");
}
rs22.close();
%>
<form action="integer_editDB.jsp?qid=<%= qid %>" name="form2"  method="post" onsubmit ="return validateForm()">
<h2 class="pageheader" >Numeric Questions</h2>
	<p>Your Question here:</p>
	<textarea name="question" cols="20" rows="5" id="Question"><%=question %></textarea>	
	<p>Image: <input type="file" name="image" style="margin: 15px 0px 0px 0px;" /> </p> 
	<br>
<%
String ans="";
String query = "select OptionValue from options where QuestionID="+qid+"";
ResultSet rs=st.executeQuery(query);
while(rs.next())
{
	ans = rs.getString("OptionValue");
}
rs.close();
st.close();
conn.close();
%>
	<p>Answer: <input class="inputtext" type="text" name = "answer" value="<%=ans%>"></p>
	<center><input class="buttons" type="submit" value="  Save  " /><input class="buttons" type="button" value="  Cancel  " onclick="javascript:history.back()" /></center>
</form>
</div>
</center>
<script type="text/javascript" language="JavaScript">
document.forms['form2'].elements['txt'].focus();
</script>
<input type = "hidden" name="qid" value="<%=qid %>" />
<%@ include file="../../../jsp/includes/footer.jsp" %>
</body>
</html>
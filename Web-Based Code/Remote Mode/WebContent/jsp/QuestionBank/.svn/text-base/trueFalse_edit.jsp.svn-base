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
function validateForm1()
{
	//alert("hi");
	var x=document.forms["form1"]["question"].value;
	if (x==null || x=="" || x.trim()=="")
	{
  		alert("Question must be filled out!!!");
  		return false;
  	}
	if ( ( form1.op[0].checked == false ) && ( form1.op[1].checked == false ) )
	{
		alert("No Answer selected..."); 
		return false;
	}
	return true;
}
</script>

<title>Instructor Aakash Clicker</title>
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
%>
<h2 class="pageheader" >True or False Questions</h2>
<form action="trueFalse_editDB.jsp" name="form1"  method="post" onsubmit="return validateForm1()">
	<p>Your Question here:</p>
	<textarea name="question" cols="20" rows="5" id="Question"><%= question %></textarea>
	
	<p>Image: <input type="file" name="image" style="margin: 15px 0px 0px 0px;" /> </p> 
	<br>
<%
int i=0;
String query = "select OptionValue, OptionID, OptionCorrectness from options where QuestionID="+qid+"";
ResultSet rs=st.executeQuery(query);
while(rs.next())
{
	i++;
%>
<input style="margin-left: 5px" <%if(rs.getInt("OptionCorrectness")==1){ %> checked="checked" <%} %> type="radio" name="option" value="<%=rs.getString(1)%>" id="radio<%=i %>"/><label id="txt<%=i %>" > &nbsp; <%=rs.getString(1)%></label><br id="br<%=i %>" ><br id="2br<%=i %>" >
<% } conn.close();%>

<center><input class="buttons" type = "submit" value = "  Submit  " ><input class="buttons" type="button" value="  Cancel  " onclick="javascript:history.back()" /></center>
<input type = "hidden" name="qid" value="<%=qid %>" />
</form>
</div>

<%@ include file="../../../jsp/includes/footer.jsp" %>
</center>
</body>
</html>
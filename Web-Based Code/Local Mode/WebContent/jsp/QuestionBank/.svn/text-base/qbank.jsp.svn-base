<%@ page language="java" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.sql.*" %>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility*/
%>

<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<title>Question Bank</title>
<script>
function User(val)
{
	var url = "quesbnk.jsp";
	 var qtype = document.getElementById("qtype").value;
	 var quest=document.getElementById("quest").selectedIndex;
	 var y=document.getElementById("quest").options;
 
        if(val=="addxls")
		{
        	window.location.href="file.html";
		}
		else if(val=="addnew")
		{
			 if(qtype!="-1")
				{
			
	        		if(qtype=="0")
	        			{
	        			window.location.href="addquestion.jsp";
	        			}
	        		else if(qtype=="1")
	        			{
	        			window.location.href="MultChoice.jsp";
	        			}
	        		else if(qtype=="2")
		    			{
		    			window.location.href="truefalse.jsp";
		    			}
	        		else if(qtype=="3")
		    			{
		    			window.location.href="integer.jsp";
		    			}
        		}
			 else
				{
					 alert("Please select question type");
				}
			}
  		else if(val=="delete")
	   {
  			if(quest!="-1")
			{
             url = "delete.jsp?id="+y[quest].text;
			  window.location.href=url;
			}
	   
			else
			{
				 alert("Please select question ");
			}
	   }
			 else if(val=="edit")
			   {
				 if(quest!="-1")
					{
		             url = "edit.jsp?id="+y[quest].text;
					  window.location.href=url;
					}
					else
					{
						 alert("Please select question type");
					}
	           }
}
</script>
</head>


<body>
<%
    Connection conn = null;
DatabaseConnection dbconn = new DatabaseConnection();
conn = dbconn.createDatabaseConnection();
Statement st = conn.createStatement();

    int sumcount=0; 
 String question= "";
	if(request.getParameter("question")!=null && request.getParameter("question")!="")
	{
		question = request.getParameter("question").toString();
	 
	}

%>

<div id="div1" style="height:350px;">
<center>
<% if(session.getAttribute("Mode").equals("Remote")){ %>
<%@ include
			file="../newMenu/newMenuForRemoteInst.jsp"%>
<%}else{ %>
	<%@ include file="../newMenu/newMenuwithCSS.jsp"%>
<%}%></center>
<FORM NAME="frm" METHOD="post" ACTION="" onsubmit="">
<H3 ALIGN="CENTER"> <FONT SIZE=6 color="white"> Search Question</FONT>  </H3>  <BR><br><br>

<center>
<TABLE CELLSPACING=5 CELLPADDING=10 border=0  ALIGN="CENTER" width="400px">

<TR>
<TD> <FONT SIZE=3>String</FONT></TD>
<TD><INPUT TYPE="TEXT" NAME="question" id="e" value="<%=question%>">

 </TD><td><INPUT TYPE="submit" NAME="search" VALUE="Search"></td>
</TR>
<TR >
<td><FONT SIZE=3>Question Type</font></td>

<TD colspan=2 align="left"> <FONT SIZE=3 ></FONT>
<select name="qtype" id="qtype"> 
 <option value="-1">Select</option>
   <option value="0">single correct answer</option>
		    <option value="1">multiple correct answer</option>
		    <option value="2">true false</option>
		    <option value="3">numeric</option>
</select>
</TD>

</TR>
</TABLE>

<%
if(request.getParameter("question")!=null && request.getParameter("question")!="")
	{
try {
			int count=0;
			//out.println(question);
			 	String count_query="select count(*) from question where Question LIKE '%"+question+"%'  and Archived='0'";
			 	ResultSet c=st.executeQuery(count_query);
			 	while(c.next())
			 	{
			 		count=c.getInt(1);
			 	}
      	    String query = "select Question from question where question LIKE '%"+question+"%' and Archived='0'";
	    
      	   ResultSet  rs = st.executeQuery(query);
      	 if(count>=1)
		 {
			%> <select size="<%=count+1%>" name="quest" id="quest">   <%
		 }
	   while(rs.next())
		{
		   //out.println(rs.getString(1));
		   		   
%>
  <option value="<%rs.getString(1); %>" ><%=rs.getString(1) %></option>
 
<%
		}

       
			}
	catch (Exception e) 
	{
      e.printStackTrace();
    }
	}

conn.close();
%></select>
</center>
<center>
<table CELLSPACING=5 CELLPADDING=10 border=0   ALIGN="CENTER">
<TR>
<TD colspan=3 align="center">
<INPUT TYPE="button" NAME="addxls" VALUE="addxls" onclick="User(this.value);">
<INPUT TYPE="button" NAME="addnew" VALUE="addnew" onclick="User(this.value);">
<INPUT TYPE="button" NAME="delete" VALUE="delete" onclick="User(this.value);">
<INPUT TYPE="button" NAME="edit" VALUE="edit" onclick="User(this.value);">
</TD>
</TR>
 </table>
 </center>
</FORM>
<%@ include file="../../jsp/includes/footer.jsp" %>
</div>
</body>
</html>
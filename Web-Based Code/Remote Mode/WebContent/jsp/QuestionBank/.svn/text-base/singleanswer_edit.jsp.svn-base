<%@page import="java.io.PrintWriter"%>
<%@ page language="java" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.sql.*" %>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
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
<script src="../../javascript/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="../../javascript/Quiz.js">
</script>

<script type="text/javascript" language="javascript">
var ctr = 4;
function addOption()
{
	if(ctr<6)
		{
		try
		{
			ctr++;
			var label = document.createElement("label");
			var radioButton = document.createElement("input");
			var textbox = document.createElement("input");
			var removeButton = document.createElement("input");
			var newLine = document.createElement("br");
			var newLine2=document.createElement("br");
 			var spaceLabel=document.createElement("label");
 			var spaceLabel2=document.createElement("label");
			
			spaceLabel.setAttribute("id", "spaceLabel"+ctr);
			spaceLabel2.setAttribute("id", "2spaceLabel"+ctr);
			textbox.setAttribute("name", ""+ctr);
			textbox.setAttribute("type", "text");
			textbox.setAttribute("id","txt"+ctr);
			textbox.setAttribute("class", "inputtext");
			radioButton.setAttribute("type", "radio");
			radioButton.setAttribute("name", "option");
			radioButton.setAttribute("value", ctr);
			radioButton.style.marginLeft = "5px";
			radioButton.setAttribute("id", "radio"+ctr);
			label.setAttribute("id", "label"+ctr);
		}
		catch(err)
		{
			alert(err.message);
		}
		try
		{
	
			removeButton.setAttribute("type", "button");
			removeButton.setAttribute("value", "  Remove  ");
			removeButton.setAttribute("onclick","removeOption("+ctr+")");
			removeButton.setAttribute("id","remove"+ctr);
			removeButton.setAttribute("class","remove");
		}
		catch(err)
		{
			alert(err.message);
		}
		newLine.setAttribute("id", "br"+ctr);
		newLine2.setAttribute("id", "2br"+ctr);
		try
		{
			var before=document.getElementById("add");
			var par=before.parentNode;
			par.insertBefore(label,before);
			par.insertBefore(radioButton,before);
			par.insertBefore(spaceLabel,before);
			spaceLabel.innerHTML=" ";
			spaceLabel2.innerHTML=" ";
			par.insertBefore(textbox,before);
			par.insertBefore(spaceLabel2,before);
			par.insertBefore(removeButton, before);
			par.insertBefore(newLine, before);
			par.insertBefore(newLine2,before);
			label.innerHTML=(String.fromCharCode(64+ctr)+". ");
			document.forms["f"].elements["count"].value=ctr;
		}
		catch(err)
		{
			alert(err.message);
		}
	}
	else
	{
		alert("Options not more than 6!");
	}
}
function removeOption(opt)
{
	var i=0;
	
	if(ctr>4)
	{
		for(i=opt;i<ctr;i++)
		{	
			document.getElementById("txt"+i).value=document.getElementById("txt"+(i+1)).value;
			document.getElementById("radio"+i).checked = document.getElementById("radio"+(i+1)).checked;
		}
		try
		{
			var child1=document.getElementById("txt"+ctr);
			var child2=document.getElementById("radio"+ctr);
			var child3=document.getElementById("label"+ctr);
			var child4=document.getElementById("br"+ctr);
			var child8=document.getElementById("2br"+ctr);
			var child5=document.getElementById("remove"+ctr);
// 			var child6=document.getElementById("spaceLabel"+ctr);
// 			var child7=document.getElementById("2spaceLabel"+ctr);
			var parent=document.getElementById("content_in");
			parent.removeChild(child1);
			parent.removeChild(child2);
			parent.removeChild(child3);
			parent.removeChild(child4);
			parent.removeChild(child8);
			parent.removeChild(child5);
// 			parent.removeChild(child6);
// 			parent.removeChild(child7);	
		}
		catch(err)
		{
			alert("here"+err.message);
		}

		ctr--;
		document.forms["f"].elements["count"].value=ctr;
	}
	else
	{
		alert("Options must be more than 4!");
	}
}

function validateForm()
{
	var checked=false;
	if((document.forms["f"].elements["ques"].value).trim()==""|| document.forms["f"].elements["ques"].value==null)
	{
		alert("Please enter the question first");
		return false;
	}
	var i;
	for(i=1;i<=ctr;i++)
		{
			if(document.getElementById("txt"+i).value=="" || document.getElementById("txt"+i).value==null || (document.getElementById("txt"+i).value).trim()=="")
			{
			alert("Please give appropriate value for option "+i);
			return false;
			}
			if(document.getElementById("radio"+i).checked==true)
	{checked=true;
		}
		}
	if(checked==false)
{
alert("Please indicate correct answer");
return false;
}
return true;
	    
	}
</script>

<title>Single Choice Correct</title>
</head>
<body>
<%
Connection conn = null;
DatabaseConnection dbconn = new DatabaseConnection();
conn = dbconn.createDatabaseConnection();
Statement st = conn.createStatement();
%>
<form action="singleanswer_editDB.jsp" id="f" method="post" onsubmit="return validateForm()">
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
String query = "SELECT Question FROM question WHERE QuestionID='"+qid+"'";
String question = null;
ResultSet rs = st.executeQuery(query);
if(rs.next())
{
	question = rs.getString("Question");
}
rs.close();
%>
	<h2 class="pageheader" >Multiple Choice Questions, Single Choice Correct</h2>	
	<p>Your Question here:</p>
	<textarea name="ques" cols="20" rows="5" id="Question"><%=question %></textarea>

	<br> <br>
<%
int i=0;
char label = 'A';
query = "select OptionValue, OptionID, OptionCorrectness from options where QuestionID="+qid+"";
ResultSet rs1=st.executeQuery(query);
String optionIDs ="";
while(rs1.next())
{
	optionIDs += rs1.getInt("OptionID") + ";";
	i++;
%>
<label id="label<%=i %>"><%=(label++) %>. </label> 
<input style="margin-left: 5px" <%if(rs1.getInt("OptionCorrectness")==1){ %> checked="checked" <%} %> type="radio" name="option" value="<%=i %>" id="radio<%=i%>"/>
<input class="inputtext" name="<%=i %>" type="text"  id="txt<%=i %>" value="<%=rs1.getString(1) %>"/> 
<input class="remove" id="remove<%=i %>" type="button" value="  Remove  " onclick="removeOption(<%=i %>)" />
<br id="br<%=i %>" ><br id="2br<%=i %>" >

<% }

rs1.close();
st.close();
conn.close();%>
<script type="text/javascript">ctr = <%=i%> </script>	
	<input type	="hidden" name="count" value="<%=i%>"/>
	<input type	="hidden" name="old_count" value="<%=i%>"/>
	<input type	="hidden" name="optionIDs" value="<%=optionIDs%>"/>
	<input type = "hidden" name="qid" value="<%=qid%>" />
	<input type="button" id="add" onclick="addOption()" value="  Add Option  " />
	<center><input class="buttons" type="submit" value="  Save  " /><input class="buttons" type="button" value="  Cancel  " onclick="javascript:history.back()" /></center>
</div>
<%@ include file="../../jsp/includes/footer.jsp" %>
</center>
</form>
</body>
</html>
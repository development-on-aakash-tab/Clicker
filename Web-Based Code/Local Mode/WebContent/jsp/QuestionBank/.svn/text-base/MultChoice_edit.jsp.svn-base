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
<link rel="stylesheet" type="text/css" href="../../styles.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 

<title>Multiple Choice Correct</title>
</head>
<body >
 
<script type="text/javascript">
var ctr = 4;
function addOption()
{
	if(ctr<6)
	{
	try
	{
		ctr++;
		var label = document.createElement("label");
		var checkButton = document.createElement("input");
		var textbox = document.createElement("input");
		var removeButton = document.createElement("input");
		var newLine = document.createElement("br");
		var newLine2=document.createElement("br");
		var spaceLabel=document.createElement("label");
		var spaceLabel2=document.createElement("label");
		
		spaceLabel.setAttribute("id", "spaceLabel"+(ctr));
		spaceLabel2.setAttribute("id", "2spaceLabel"+(ctr));
		textbox.setAttribute("name", ""+(ctr));
		textbox.setAttribute("type", "text");
		textbox.setAttribute("id","txt"+(ctr));
		textbox.setAttribute("class", "inputtext");
		checkButton.setAttribute("type", "checkbox");
		checkButton.setAttribute("name", String.fromCharCode(64+ctr));
		checkButton.setAttribute("value", ctr);
		checkButton.style.marginLeft = "5px";
		checkButton.setAttribute("id", "check"+(ctr));
		label.setAttribute("id", "label"+(ctr));

	}
	catch(err)
	{
		alert(err.message);
	}
	try
	{
		
		removeButton.setAttribute("type", "button");
		removeButton.setAttribute("value", "  Remove  ");
		removeButton.setAttribute("onclick","removeOption("+(ctr)+")");
		removeButton.setAttribute("id","remove"+(ctr));
		removeButton.setAttribute("class","remove");

	}
	catch(err)
	{
		alert(err.message);
	}
		newLine.setAttribute("id", "br"+(ctr));
		newLine2.setAttribute("id", "2br"+(ctr));
		
		//alert(ctr);
		try
		{
			var before=document.getElementById("add");
			var par=before.parentNode;
			par.insertBefore(label,before);
			par.insertBefore(checkButton,before);
			par.insertBefore(spaceLabel,before);
			spaceLabel.innerHTML=" ";
			spaceLabel2.innerHTML=" ";
			par.insertBefore(textbox,before);
			par.insertBefore(spaceLabel2,before);
			par.insertBefore(removeButton, before);
			par.insertBefore(newLine, before);
			par.insertBefore(newLine2,before);
			label.innerHTML=(String.fromCharCode(64+ctr)+". ");
			document.forms["form"].elements["count"].value=ctr;
			//document.getElementById("hidden_count").value=ctr;
		}
		catch(err)
		{
			alert('3 '+err.message);
		}
	}
	else
	{
		alert("Options not more than 6! ");
	}
}
function removeOption(opt)
{
	var i=0;
	//alert(ctr);
	if(ctr>4)
	{
		for(i=opt;i<ctr;i++)
		{	
			document.getElementById("txt"+i).value=document.getElementById("txt"+(i+1)).value;
			document.getElementById("check"+i).checked = document.getElementById("check"+(i+1)).checked;
		}
		try
		{
		//	alert("Assigning!");
			var child1=document.getElementById("txt"+ctr);
			var child2=document.getElementById("check"+ctr);
			var child3=document.getElementById("label"+ctr);
			var child4=document.getElementById("br"+ctr);
			var child8=document.getElementById("2br"+ctr);
			var child5=document.getElementById("remove"+ctr);
// 			var child6=document.getElementById("spaceLabel"+ctr);
// 			var child7=document.getElementById("2spaceLabel"+ctr);
			var parent=document.getElementById("content_in");
			//alert("before removing i="+i);
			parent.removeChild(child1);
			parent.removeChild(child2);
			parent.removeChild(child3);
			parent.removeChild(child4);
			parent.removeChild(child8);
			parent.removeChild(child5);
			//alert("after removing i="+i);
// 			parent.removeChild(child6);
// 			parent.removeChild(child7);	
		}
		catch(err)
		{
			alert("here---"+err.message);
		}

		ctr--;
		//document.getElementById("hidden_count").value=ctr;
		document.forms["form"].elements["count"].value=ctr;
	}
	else
	{
		alert("Options must be more than 4!");
	}
}

function validateForm()
{
	var checked=false;
	if((document.forms["form"].elements["Question"].value).trim()=="" || document.forms["form"].elements["Question"].value==null)
	{
		alert("Please enter the question first");
		return false;
	}
	for(var i=1;i<=ctr;i++)
		{
		//alert(document.forms["form"].elements["check"+i].checked);
			if((document.forms["form"].elements["txt"+i].value).trim()=="" || document.forms["form"].elements["txt"+i].value==null)
				{
				alert("Please give appropriate value for answer");
				return false;
				}
			if(document.forms["form"].elements["check"+i].checked==true)
				{checked=true;
				}
		}
	if(checked==false)
	{
	alert("Please indicate correct answer");
	return false;
	}

	return true;
	//alert(checked);
	//alert(String.fromCharCode(64+i));
	if(document.forms["form"].elements[String.fromCharCode(64+i)].checked==false)
		{
		alert("Please indicate correct answer");
		return false;
		}
//		alert("here!");
		return true;
	}

function getCorrectoptionID(){
	var j="";
	for(var i=1;i<=ctr;i++)
	{
	//alert(document.forms["form"].elements["check"+i].checked);
		
		if(document.forms["form"].elements["check"+i].checked==true)
			{
			j+=document.forms["form"].elements["check"+i].value+";";
			}
	}
	document.getElementById("correctcount").value=j;
	
}

</script>

<%
Connection conn = null;
DatabaseConnection dbconn = new DatabaseConnection();
conn = dbconn.createDatabaseConnection();
Statement st = conn.createStatement();
%>
<form id="form" method="post" action="MultChoice_editDB.jsp" onsubmit="return validateForm()">
<center>
<% if(session.getAttribute("Mode").equals("Remote")){ %>
<%@ include
			file="../newMenu/newMenuForRemoteInst.jsp"%>
<%}else{ %>
	<%@ include file="../newMenu/newMenuwithCSS.jsp"%>
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
	<h2 class="pageheader" >Multiple Choice Questions, Single Choice Correct</h2>	
	<p>Your Question here:</p>
	<textarea name="Question" cols="20" rows="5" id="Question"><%=question %></textarea>

	<br> <br>
<%
int i=0;
char label = 'A';
String query = "select OptionValue, OptionID, OptionCorrectness from options where QuestionID="+qid+"";
ResultSet rs=st.executeQuery(query);
String optionIDs ="";
String CorrectOptionIds="";
while(rs.next())
{
	optionIDs += rs.getInt("OptionID") + ";";
	i++;
%>
<label id="label<%=i %>"><%=(label++) %>. </label> 
<input style="margin-left: 5px" <%if(rs.getInt("OptionCorrectness")==1){ %> checked="checked" <%} %> type="checkbox" name="option" value="<%=i %>" id="check<%=i %>"/> 
<input class="inputtext" name="<%=i %>" type="text"  id="txt<%=i %>" value="<%=rs.getString(1) %>"/> 
<input class="remove" id="remove<%=i %>" type="button" value="  Remove  " onclick="removeOption(<%=i %>)" />
<br id="br<%=i %>" ><br id="2br<%=i %>" >
<% }
rs.close();
st.close();
conn.close();
%>
<script type="text/javascript">ctr = <%=i %> </script>
	
<!-- 	<input type	="hidden" name="count" value="4"/> -->
<input type="hidden" name="count" value=<%=i%> />
<input type="hidden" name="correctcount" id="correctcount"/>
<input type	="hidden" name="old_count" value="<%=i%>"/>
<input type	="hidden" name="optionIDs" value="<%=optionIDs%>"/>
<input type = "hidden" name="qid" value="<%=qid %>" />
<input type="button" id="add" onclick="addOption()" value="  Add Option  " />
<center><input class="buttons" onclick="getCorrectoptionID()" type="submit" value="  Save  " /><input class="buttons" type="button" value="  Cancel  " onclick="javascript:history.back()" /></center>

<!-- <h2 class="pageheader" >Multiple Choice Questions, Multiple Choice Correct</h2>
	<p>Your Question here:</p>
	<textarea name="Question" cols="20" rows="5" id="Question" ></textarea>	
	<p>Image: <input type="file" name="image" style="margin: 15px 0px 0px 0px;" /> </p> 
	<br>
	A. <input style="margin-left: 5px" type="checkbox" name="A" value="A" id="check1"/> <input class="inputtext" name="1" type="text" id="txt1" /> <input class="remove" id="remove1" type="button" value="  Remove  " onclick="removeOption(1)" /><br><br>
	B. <input style="margin-left: 5px" type="checkbox" name="B" value="B" id="check2"/> <input class="inputtext" name="2" type="text" id="txt2" /> <input class="remove" id="remove2" type="button" value="  Remove  " onclick="removeOption(2)" /><br><br>
	C. <input style="margin-left: 5px" type="checkbox" name="C" value="C" id="check3"/> <input class="inputtext" name="3" type="text" id="txt3" /> <input class="remove" id="remove3" type="button" value="  Remove  " onclick="removeOption(3)" /><br><br>
	D. <input style="margin-left: 5px" type="checkbox" name="D" value="D" id="check4"/> <input class="inputtext" name="4" type="text" id="txt4" /> <input class="remove" id="remove4" type="button" value="  Remove  " onclick="removeOption(4)" /><br>
	<br>
	<input type="hidden" id="hidden_count" name="Count" value="0" />
	<input type="button" id="add" onclick="addOption()" value="  Add Option  " />
	
	<center><input id="submitB" type="submit" value="  Submit  " /></center>
	 -->	
</div>

<%@ include file="../../jsp/includes/footer.jsp" %>
</center>
</form>
</body>
</html>


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

<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
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
		}
		try
		{
			var child1=document.getElementById("txt"+ctr);
			var child2=document.getElementById("radio"+ctr);
		
			var child3=document.getElementById("label"+ctr);
			var child4=document.getElementById("br"+ctr);
			var child8=document.getElementById("2br"+ctr);
			var child5=document.getElementById("remove"+ctr);
			var child6=document.getElementById("spaceLabel"+ctr);
			var child7=document.getElementById("2spaceLabel"+ctr);
			var parent=document.getElementById("content_in");
			parent.removeChild(child1);
			parent.removeChild(child2);
			parent.removeChild(child3);
			parent.removeChild(child4);
			parent.removeChild(child8);
			parent.removeChild(child5);
			parent.removeChild(child6);
			parent.removeChild(child7);	
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
	//alert("ddd");
	var quest=document.forms["f"].elements["ques"].value;
	//var reg=new RegExp("^[a-zA-Z0-9]+[,.!@#$%^&*()_+-=:;,./<>?a-zA-Z0-9]*");
	if(quest.trim()=="")
	{
		alert("Please enter the question first");
		return false;
	}
	var i;
	for(i=1;i<=ctr;i++)
		{
			if((document.getElementById("txt"+i).value).trim()=="" || document.getElementById("txt"+i).value==null)
			{
			alert("Please give appropriate value for option "+i);
			return false;
			}
		}
	for(i=0;i<document.f.option.length;i++){
		if(document.f.option[i].checked){
			alert("Question submitted successfully");
			return true;
		}
	}
	alert("Please choose a correct answer");
	return false;
	
}
</script>

<title>Single Choice Correct</title>
</head>
<body>
<form action="addquestionDB.jsp" name="f" id="f" method="post" onsubmit="return validateForm()">
<center>
<% if(session.getAttribute("Mode").equals("Remote")){ %>
<%@ include
			file="../newMenu/newMenuForRemoteInst.jsp"%>
<%}else{ %>
	<%@ include file="../newMenu/newMenuwithCSS.jsp"%>
<%}%>
<div id="content_in">

<div style="margin-left: 0px 0px; clear: both;" align="left" id="addnewQ" >
<div style="float:left; margin: 0px 0px 0px;"><p> Select Question type:</p></div><br>
<div style="float:left; margin: 0px 0px 0px;">
<form id="quesform" action="selectQ.jsp" >
<input type="radio" name="qtype" value="2" onClick="document.getElementById('quesform').submit()"/>Multiple Choice Correct &nbsp;
<input type="radio" name="qtype" value="3" onClick="document.getElementById('quesform').submit()"/>True or False &nbsp;
<input type="radio" name="qtype" value="4" onClick="document.getElementById('quesform').submit()"/>Numeric Answer 
&nbsp;&nbsp;&nbsp;
</form>
</div>
<br><br><br><br>
</div>

	<h2 class="pageheader" >Multiple Choice Questions, Single Choice Correct</h2>	
	<p>Your Question here:</p>
	<textarea name="ques" cols="20" rows="5" id="Question"></textarea>
	
	<br><br>
	A. <input style="margin-left: 5px" type="radio" name="option" value="1" id="radio1"/> <input class="inputtext" name="1" type="text"  id="txt1" /> <input class="remove" id="remove1" type="button" value="  Remove  " onclick="removeOption(1)" /><br><br>
	B. <input style="margin-left: 5px" type="radio" name="option" value="2" id="radio2"/> <input class="inputtext" name="2" type="text"  id="txt2" /> <input class="remove" id="remove2" type="button" value="  Remove  " onclick="removeOption(2)" /><br><br>
	C. <input style="margin-left: 5px" type="radio" name="option" value="3" id="radio3"/> <input class="inputtext" name="3" type="text"  id="txt3" /> <input class="remove" id="remove3" type="button" value="  Remove  " onclick="removeOption(3)" /><br><br>
	D. <input style="margin-left: 5px" type="radio" name="option" value="4" id="radio4"/> <input class="inputtext" name="4" type="text"  id="txt4" /> <input class="remove" id="remove4" type="button" value="  Remove  " onclick="removeOption(4)" /><br>
	<br>
	<input type	="hidden" name="count" value="4"/>
	<input type="button" id="add" onclick="addOption()" value="  Add Option  " />
	<center><input id="submitB" type="submit" value="  Submit  " /></center>
</div>
<%@ include file="../../jsp/includes/footer.jsp" %>
</center>
</form>
</body>
</html>
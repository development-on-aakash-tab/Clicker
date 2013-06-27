<%-- Author : Dipti.G --%>

<%@page import="com.clicker.global.AakashClickerGlobal"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.clicker.databaseconn.DatabaseQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>Clicker WebSite</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link rel="stylesheet" type="text/css" href="images/jquery.autocomplete.css" />
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="javascript/jquery-1.4.2.min.js"></script>
<script src="javascript/jquery.autocomplete.js"></script>
<script type="text/javascript">

function validateForm(count)
{

	var remotecentername=document.getElementById("RemoteCenterID").value;
	
	if(count>=1){
	alert("Coordinator Already Register.\nThis is one time registration process.\nUse Update Link for change in Coordinator Details");
	return false;
	}
	else{
		var con = confirm("You have selected your Remote Center as:\n \n "+remotecentername+"\n \n ARE YOU SURE");
		if (con ==true)
	  	{
			 
			 alert("You have selected Remote center as \n \n"+remotecentername);		
		 }
		else
	  	{
			 alert("Please again select remote center!");
			 document.getElementById("RemoteCenterID").value="";
			 return false;
		 }
	}	
		
		

var username=document.forms["register"]["userName"].value;
if (username==null || username=="")
  {
  alert("Please Enter Username.");
  return false;
  }
  var password=document.forms["register"]["password"].value;
  if(password==null||password=="")
	  {
	  alert("Please Enter password.");
	  return false;
	  }
  var retypepassword=document.forms["register"]["retypepassword"].value;
  if(retypepassword==null||retypepassword=="")
	  {
	  alert("Please retype your password.");
	  return false;
	  }
  if(password!=retypepassword){
	  alert("Password Doesnot match");
	  return false;
	  }
  var email=document.forms["register"]["email"].value;
  var atpos=email.indexOf("@");
  var dotpos=email.lastIndexOf(".");
  if ((atpos<1) || (dotpos < (atpos+2)) || ((dotpos+2) >= email.length))
    {
    alert("Not a valid e-mail address");
    return false;
    }
	
  if(remotecentername==null||remotecentername==""||remotecentername.length()<6){
        alert("Please select your remote center name");
        return false;
      }
 
 return true;  

}


</script>
</head>

<body>
<% 
//used to get the count of data in coordinator table. Only one coordinator is allowed
DatabaseConnection dbconn = new DatabaseConnection();
DatabaseQueries dbqueries = new DatabaseQueries();
Connection conn = dbconn.createDatabaseConnection();
int Counter=0;
try
{
	Counter=dbqueries.getCountOfCoordinator(conn);
}

catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
		}

finally
{
	try {
		if(conn!=null)conn.close();
		} 
	catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
		}

}


%>
<div id="content" >

<!-- header -->
	<div id="headertop">
	  	<img src="images/site_logo.gif" title="" alt="" style="padding-right: 0px; padding-bottom: 0px; float:right;"/>
		<div id="logo">
			<h1 style="width:230px"><a>Clicker Remote</a></h1>			
		</div>		
	</div>
<!--Till here Header file -->
<form name="register" id="register" action="SuccessfullyRegister.jsp" method="post" onsubmit="return validateForm(<%=Counter%>)">
<div style="min-height: 415px;font-weight: bold;color:#663300">
<center>
<h2>
<br/>
ONE TIME REGISTRATION PROCESS OF REMOTE CO-ORDINATOR </h2>
<br/>
<div style="font-size:18px">Please Fill folllowing form : </div>
<br/>
<br/>
<div style="text-align: justify;color:#663300;font-size:18px;padding-left:160px">
<label style="padding-right: 129px">Username</label>: <input type ="text" id="userName" name="userName" value="" title="Login Username"/><br/><br/>
<label style="padding-right: 130px">Password</label>: <input type="password" id="password" name="password" value="" title="Login Password"/><br/><br/>
<label style="padding-right: 64px">Retype-Password</label>: <input type="password" id="retypepassword"name="retypepassword" value="" title="Enter Same Password Again"/><br/><br/>
<label style="padding-right: 168px">Email</label>: <input type ="text" id="email" name="email" value="" title="Email Address"/><br/><br/>
 <div style="overflow: auto;">
<label style="padding-right: 35px">Remote Center Name </label>: <input type="text" id="RemoteCenterID" name="RemoteCenterID" value="" class="input_text" title="Select Your Remote center Name" style="overflow: auto;"/>
</div>
<br/>
<br/>
<center>
<input type="submit" style="color:#663300;size: 50px;font-size:20px;font-weight:bold;" value="Submit" name="submit"/>
</center>
<div style="text-align: right;">
<a href="UpdateCoordinatorDetails.jsp" style="color:#663300" title="To update Coordinator details if not able to login">Update Co-ordinator Details</a>
</div>
<div style="text-align:left">
<a href="Login.jsp" style="color:#663300" title="Back to login page">Back to login</a>
</div>
<script>
	    jQuery(function(){
	        $("#RemoteCenterID").autocomplete("getData.jsp");
	    });
	</script>
<br/><br/>
</div>
</center>
</div>
</form>
	<div id="con_bot"></div>
<!-- footer -->
	<div id="footer">
		<p>Developed and design by Clicker Software Team IIT BOMBAY </p>
	
	</div>
<!-- / footer -->
</div>
</body>

</html>

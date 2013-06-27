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
function validateUpdate(RemoteCenterName)
{

	var remotecentername=document.getElementById("remotecentername").value;
	
	if(remotecentername!=RemoteCenterName){
		alert("You have selected wrong RemoteCenterName\n Your RemoteCenterName is "+RemoteCenterName+ " \n Please Select Same Remote Center");
		document.getElementById("remotecentername").value="";
		return false;
	}
	
	var username=document.getElementById("NewUserName").value;
	if (username==null || username=="")
 	 {
 	 		alert("Please Enter Username.");
  			return false;
  	}
  	
  var password=document.forms["update"]["NewPassword"].value;
  	if(password==null||password=="")
	  {
	 	 	alert("Please Enter password.");
	  		return false;
	  }
	  
  var retypepassword=document.forms["update"]["NewPasswordRetype"].value;
  	if(retypepassword==null||retypepassword=="")
	  {
	  	alert("Please retype your password.");
	  	return false;
	  }
  	if(password!=retypepassword){
	  	alert("Password Doesnot match");
	  	return false;
	  }
  var email=document.forms["update"]["NewEmail"].value;
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
String RemoteCenterName=null;
DatabaseConnection dbconn = new DatabaseConnection();
DatabaseQueries dbqueries = new DatabaseQueries();
Connection conn = dbconn.createDatabaseConnection();
try
{
RemoteCenterName=dbqueries.getAndMatchRemoteCenterName(conn);
System.out.println("RemoteCenterName is....."+RemoteCenterName);
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
<form name="update" id="update" action="UpdateCoordSuccess.jsp" method="post" onsubmit="return validateUpdate('<%=RemoteCenterName%>');">
<div style="min-height: 415px;font-weight: bold;font-size:18px;color:#663300">
<center>
<h1>Update Co-ordinator Details</h1>
<br/>
<br/>
<label style="padding-right: 19px">Your remote center is  </label> : <input type="text" name="remotecentername" id="remotecentername"value="" class="input_text" title="Select Your Remote center Name"  style="overflow: auto;"/>
<br/>
<br/>

<div style="font-size: 24px">Update Following</div>
<br/>
<br/>
<div style="text-align:justify; padding-left: 200px">
<label style="padding-right: 26px">Enter new username  </label>: <input type="text" name="NewUserName" id="NewUserName"  />
<br/>
<br/>
<label style="padding-right: 67px">Enter password  </label> : <input type="password" name="NewPassword" id="NewPassword" />
<br/>
<br/>
<label style="padding-right: 52px">Retype password  </label> : <input type="password" name="NewPasswordRetype" id="NewPasswordRetype" />
<br/>
<br/>
<label style="padding-right: 102px">Enter Email   </label> : <input type="text" name="NewEmail" id="NewEmail"  />
<br/>
<br/>
</div>
	<center>
<input type="submit" style="color:#663300;size: 50px;font-size:20px;font-weight:bold;" value="Submit" name="submit"/>
	</center>


</center>
<br/>
<script>
	    jQuery(function(){
	        $("#remotecentername").autocomplete("getData.jsp");
	    });
	</script>


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

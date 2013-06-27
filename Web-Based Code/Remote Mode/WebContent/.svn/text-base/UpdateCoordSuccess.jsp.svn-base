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

</head>

<%
String RemoteCenterName=request.getParameter("remotecentername");
String Username=request.getParameter("NewUserName");
String Password=request.getParameter("NewPassword");
String retypePassword=request.getParameter("NewPasswordRetype");
String email=request.getParameter("NewEmail");
int lastsplitindex = RemoteCenterName.lastIndexOf(" - ");
int rcid = Integer.parseInt(RemoteCenterName.substring(lastsplitindex + 3));
RemoteCenterName = RemoteCenterName.subSequence(0, lastsplitindex).toString();
System.out.println(RemoteCenterName);
DatabaseConnection dbconn = new DatabaseConnection();
DatabaseQueries dbqueries = new DatabaseQueries();
Connection conn = dbconn.createDatabaseConnection();
//int remoteCenterID=dbqueries.getRemoteCenterIDForInsert(conn, RemoteCenterName);
boolean flag=false;
try
{
flag=dbqueries.UpdateCoordinatorDetail(conn, Username, Password, email);
System.out.println("Successfully Updated value ...." +flag);
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
<body>
<div id="content" >

<!-- header -->
	<div id="headertop">
	  	<img src="images/site_logo.gif" title="" alt="" style="padding-right: 0px; padding-bottom: 0px; float:right;"/>
		<div id="logo">
			<h1 style="width:230px"><a>Clicker Remote</a></h1>			
		</div>		
	</div>
<!--Till here Header file -->

<div style="min-height: 425px;font-weight: bold;color:#663300">
<center>
<div style="height: 30px"></div>
<h1 style="font-size: 32px">You have sucessfully Updated Details</h1>
<div style="height: 30px"></div>
<div  style="height:300px;text-align:justify;padding-left: 200px; font-size:16px">
<table>
<tr>
<td>Username</td><td>: <%=Username%></td>
</tr>
<tr>
<td>
<br/>
</td>
</tr>
<tr>
<td>
Email  </td><td>: <%=email%></td>
</tr>
<tr>
<td>
<br/>
</td>
</tr>
<tr>
<td>RemoteCenter Name </td><td>: <%=RemoteCenterName%></td>
</tr>
</table>
<div style="height: 30px"></div>
<h1>Thank You, now <a href="Login.jsp">Login</a></h1>
</div>
</center>
</div>

	<div id="con_bot"></div>
<!-- footer -->
	<div id="footer">
		<p>Developed and design by Clicker Software Team IIT BOMBAY </p>
	
	</div>
<!-- / footer -->
</div>
</body>

</html>















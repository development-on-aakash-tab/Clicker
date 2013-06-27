
<%@page import="com.clicker.global.AakashClickerGlobal"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>

<% 
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility*/

String CoordinatorID = (String) session.getAttribute("CoordinatorID");

if ( CoordinatorID != null) {
	response.sendRedirect("./jsp/home/CoordinatorSuccess.jsp");
	return;
}
%>


<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>Clicker WebSite</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="javascript/LoginValidation.js"></script>
</head>
<body onload="checkLoginStatus()">
<div id="content">
<!-- header -->
	<div id="headertop">
	  	<img src="images/site_logo.gif" title="" alt="" style="padding-right: 0px; padding-bottom: 0px; float:right;"/>
		<div id="logo">
			<h1 style="width:230px"><a>Clicker Remote</a></h1>			
		</div>		
	</div>
<!--Till here Header file -->
		
	<div class="inner_copy"><div class="inner_copy"></div></div>
	<div style="clear:both;"></div>
	<div id="header">
		<div id="logform">
			<div id="log_top"></div>
			<div id="log">
			
				<h3>Login</h3>
				 	<form id="LoginForm" method="post" action="./Login" >
					<fieldset>
					<label for="text1">Username</label><input id="text1" type="text" name="LoginName"  />
					<label for="text2">Password</label><br/><input id="text2" type="password" name="Password"  />
					<input type="checkbox" class="radio-btn" name="mode" id="mode"
								title="Take Quiz from IITB"
								value="remoteMode" /><label for="mode"
								title="To conduct Quiz across remote centers">Remote
								Center Mode</label><br />
					<input type="submit" id="login-submit" value=""/>
					</fieldset>
				</form>
					<div style="color:#990000 ;font-weight:bold;"  id="error"></div>			
			 	<img src="images/ls10.png" title="" alt="" style="padding-right: 5px; padding-bottom: 2px;"/><a href="OneTimeCoordinatorRegistration.jsp"><font size="3px" color="#663300">Co-ordinator Registration</font></a><br/>
				<!-- <img src="images/log_ls.png" title="" alt="" style="padding-right: 5px; padding-bottom: 2px;"/><a href="#">Forgot password ?</a> -->
			</div>
			<div id="log_bot"></div>
		</div>
	</div>
<!-- / header -->
<!-- content -->
	<div id="main">
		<div id="leftcon">
		
		<h5>Aakash Clicker Remote system</h5>
			<p>An interaction between teacher and student is one of the most important factor in effective learning for a student. In current education system there is lack of interaction between student and teacher due to many reasons.</p>
			<p>Hence there is need to develop a system for interaction between teachers and students. Commercially available Clicker devices are costly, which are not affordable to institutions and students.Thus we thought of using Aakash tablet which is developed for Student only</p>
			<p>Aakash Clicker V-3 is web based participant response system which is in development stage. Instructor and participants will have Aakash tablets through which both can access Aakash Clicker V-3 using web URL. Instructor and participants both will be having different authorities.  Advantage of using Aakash tablets in participant response system is that participants will be able to view question and options unlike, only question number and type in Clicker devices. As Aakash Clicker V-3 is web based there no more necessity of using any other hardware for collecting responses entered by participants. Instructor can view reports of participants from any place. </p>
			
		</div>
		<div style="clear:both;"></div>
<!-- / content -->
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

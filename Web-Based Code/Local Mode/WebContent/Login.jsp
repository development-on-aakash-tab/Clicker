<%-- Author : Dipti.G --%>

<%@page import="com.clicker.global.AakashClickerGlobal"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% 
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility*/

String Usertype = (String) session.getAttribute("Usertype");
String Mode = (String) session.getAttribute("Mode");

if (Usertype!=null)
{
	if (Usertype.equals("Instructor"))
	{
		if (Mode.equals("Local"))
		{
			response.sendRedirect("./jsp/home/InstructorSuccess.jsp");
		}
		else if (Mode.equals("Remote"))
		{
			response.sendRedirect("./jsp/home/InstructorRemoteSuccess.jsp");
		}		
	}
	else if (Usertype.equals("Student"))
	{
		response.sendRedirect("./jsp/home/StudentSuccess.jsp");
	}
}
else if (Usertype==null)
{
	System.out.println("Usertype is not ther");
}
%>


<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Aakash Clicker WebSite</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="SHORTCUT ICON" type="image/x-icon" href="favicon.ico" />
<script type="text/javascript" src="javascript/LoginValidation.js"></script>
<script>
function testAttribute(element, attribute)
{
  var test = document.createElement(element);
  if (attribute in test)
    return true;
  else
    return false;
}

if (!testAttribute("input", "placeholder"))
{
  window.onload = function()
  {
    var uname = document.getElementById("uname");
    var text_content = "User Name";

    uname.style.color = "gray";
    uname.value = text_content;

    uname.onfocus = function() {
    if (uname.style.color == "gray")
    { uname.value = ""; uname.style.color = "black"; }
    }

    uname.onblur = function() {
    if (uname.value == "")
    { uname.style.color = "gray"; uname.value = text_content; }
    }
    
    var pword = document.getElementById("demo1");
    var text_content1 = "Password";

    pword.style.color = "gray";
    pword.value = text_content1;

    pword.onfocus = function() {
    if (pword.style.color == "gray")
    { pword.value = ""; pword.style.color = "black"; }
    }

    pword.onblur = function() {
    if (pword.value == "")
    { pword.style.color = "gray"; pword.value = text_content1; }
    }
  }
}
</script> 
</head>
<body onload="checkLoginStatus()">
	<div id="content">
		<!-- header -->
		<div id="headertop">
			<img src="images/site_logo.gif" title="" alt=""
				style="padding-right: 0px; padding-bottom: 0px; float: right;" />
			<div id="logo">
				<h1 style="width: 200px">
					<a>Clicker V-3</a>
				</h1>
				<a href="apkfiles/IITB-Clicker.apk" style="font-size: 20px;">Download Student Apk</a>
			</div>
		</div>
		<!--Till here Header file -->

		<div class="inner_copy">
			<div class="inner_copy"></div>
		</div>
		<div style="clear: both;"></div>
		<div id="header">
			<div id="logform">
				<div id="log_top"></div>
				<div id="log">

					<h3>Login</h3>
					<form id="LoginForm" method="post" action="./Login">
						<fieldset>
							<label for="uname">Username</label><input id="uname" type="text"
								name="LoginName" placeholder="Username" required/><br /> <label for="pword">Password</label><br />
							<input id="pword" type="password" name="Password" placeholder="Password" required/><br />
							<!-- <input type="checkbox" class="radio-btn" name="admin" id="admin"
								title=" To login as Administrator"
								value="Admin" /><label for="text1"
								title="Login as Administrator">Admin</label><br /> -->
							 <input type="checkbox" class="radio-btn" name="mode" id="mode"
								title="To conduct Quiz across remote centers."
								value="remoteMode" /><label for="text1"
								title="To conduct Quiz across remote centers">Remote
								Center Mode</label><br />
							<br /> <input type="submit" id="login-submit" value="" />
						</fieldset>
					</form>
					<div style="color: #990000; font-weight: bold;" id="error"></div>
					<!-- 	<img src="images/log_ls.png" title="" alt="" style="padding-right: 5px; padding-bottom: 2px;"/><a href="#">Create new account</a><br/>
				<img src="images/log_ls.png" title="" alt="" style="padding-right: 5px; padding-bottom: 2px;"/><a href="#">Forgot password ?</a> -->
				</div>
				<div id="log_bot"></div>
			</div>
		</div>
		<!-- / header -->
		<!-- content -->
		<div id="main">
			<div id="leftcon">

				<!-- ALL YOUR CLICKER CONTENT WILL COME HERE  <img src="images/img.jpg" title="" alt="" style="padding-right: 10px; padding-bottom: 10px; float:left;"/>-->


				<h5>Aakash Clicker V-3 system</h5>
				<p>An interaction between teacher and student is one of the most
					important factor in effective learning for a student. In current
					education system there is lack of interaction between student and
					teacher due to many reasons.</p>
				<p>Hence there is need to develop a system for interaction
					between teachers and students. Commercially available Clicker
					devices are costly, which are not affordable to institutions and
					students.Thus we thought of using Aakash tablet which is developed
					for Student only</p>
				<p>Aakash Clicker V-3 is web based participant response system
					which is in development stage. Instructor and participants will
					have Aakash tablets through which both can access Aakash Clicker
					V-3 using web URL. Instructor and participants both will be having
					different authorities. Advantage of using Aakash tablets in
					participant response system is that participants will be able to
					view question and options unlike, only question number and type in
					Clicker devices. As Aakash Clicker V-3 is web based there no more
					necessity of using any other hardware for collecting responses
					entered by participants. Instructor can view reports of
					participants from any place.</p>


			</div>
			<div style="clear: both;"></div>
			<!-- / content -->
		</div>
		<div id="con_bot"></div>
		<!-- footer -->
		<div id="footer">
			<p>Developed and Design by: Clicker Software Team, IIT BOMBAY</p>
		</div>
		<!-- / footer -->
	</div>
</body>
</html>

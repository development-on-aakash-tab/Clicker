<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="" />
<meta name="description" content="" />
<title>Insert title here</title>
<link href="../../styles.css" rel="stylesheet" type="text/css"
	media="screen" />
</head>
<body>
	<form>
		<div id="content">
			<div id="headertop">
				<div id="logo_in">
					<h2>
						<a href="#">Clicker Version 3</a>
					</h2>
				</div>
				<div id="menu_in" style="FONT-SIZE: xx-small;">
					<ul>
						<% String UserType = (String) session.getAttribute("Usertype");
			  if(UserType.equals("Instructor")){%>
						<li id="button1"><a href="#" title="">Attendance</a></li>
						<%} %>
						<li id="button2"><a
							href="../../jsp/instructorJspPages/InstructorQuizMenu.jsp"
							title="">Quiz</a></li>
						<li id="button3"><a href="../../jsp/report/report.jsp"
							title="">Report</a></li>
					</ul>
				</div>
				<div id="logo_out">
					<h2>
						<a href="../../jsp/logout/InstructorLogout.jsp">Logout </a>
					</h2>
				</div>


			</div>
		</div>
	</form>
</body>
</html>
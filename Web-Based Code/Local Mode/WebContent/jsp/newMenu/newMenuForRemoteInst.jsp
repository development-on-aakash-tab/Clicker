<%@page import="java.util.ArrayList"%>
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
<link rel="stylesheet" media="all" type="text/css" href="dropdown.css" />
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
				<div style="height: 45px"></div>
				<div class="menu" style="width:700px">
					<ul style="width:600px">
					<li><a class="hide" href="../../jsp/remoteInstructor/InstructorQuizMenu.jsp">Eval+</a>
					<ul><li><a href="../../jsp/remoteInstructor/InstructorQuizMenu.jsp" title="To Conduct Quiz">Quiz</a></li>
					<li><a href="../../jsp/remoteInstructor/SpotQuizMenu.jsp" title="To Conduct On the Spot Quiz">SpotQuiz </a></li>
					<li><a href="../../jsp/remoteInstructor/InstantQuiz.jsp" title="To Conduct On the instant Quiz">Instant Quiz </a></li>
					</ul></li>
						<li><a class="hide" href="../../jsp/QuestionBank/questionbank.jsp">Question Bank</a></li>
						<li><a class="hide" href="../../jsp/createquiz/CreateQuiz.jsp">Create Quiz</a></li>
						<li><a class="hide" href="../../jsp/remoteInstructor/report.jsp">Report</a></li>
						<li style="padding-left:0px"><a class="hide" href="../../jsp/logout/InstructorLogout.jsp">Logout</a></li>
					</ul>
				</div>
				<!--  <div id="logo_out">
					<h2>
						<a href="../../jsp/logout/InstructorLogout.jsp">Logout </a>
					</h2>
				</div>  -->
			</div>
		</div>
	</form>
</body>
</html>
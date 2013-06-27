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
				<div class="menu">
					<ul>
						<li><a class="hide" href="../../jsp/instructorJspPages/InstructorQuizMenu.jsp">Eval+</a>

							<ul>
								<li><a class="submenu"
									href="../../jsp/instructorJspPages/InstructorQuizMenu.jsp"
									title="To Conduct Quiz">Quiz</a></li>
								<li><a class="submenu" href="../../jsp/SpotQuiz/SpotQuizMenu.jsp" title="To Conduct On the Spot Quiz">Spot
										Quiz </a></li>
								<li><a class="submenu" href="../../jsp/instructorJspPages/InstantQuiz.jsp" title="To Conduct On the instant Quiz">Instant Quiz
										</a></li>
								<li><a class="submenu" href="../../jsp/polls/poll.jsp" title="To Conduct Poll">Poll</a></li>
							</ul></li>

						<li><a class="hide"
							href="../../jsp/QuestionBank/questionbank.jsp">Question Bank</a></li>

						<li><a class="hide"
							href="../../jsp/createquiz/CreateQuiz.jsp">Create Quiz</a></li>
						<%
 				 boolean flag=false;
 				 if(session.getAttribute("courseID")!=null){
					System.out.println("In if");
					ArrayList<String> list=(ArrayList<String>) getServletContext().getAttribute("raisedCourses");
  					if(list==null){
						flag=false;
						System.out.println("list null");
  					}
			  		else{
		  				if(list.contains(session.getAttribute("courseID")))
		  					flag=true;
		  				else
			  			    System.out.println("Not found in list");
		  			}	
  				}
				if(!flag){
				%>
						<li><a class="hide" href="../../jsp/raisehand/RaiseHandEnable.jsp">Raisehand</a></li>
						<%}else{ %>	
						<li><a class="hide" href="../../jsp/raisehand/RaiseHandDisable.jsp">Raisehand</a></li>
					<%} %>
						<li><a class="hide" href="../../jsp/report/report.jsp">Report</a></li>
						<li><a class="hide" href="../../jsp/admin/courses.jsp">Admin</a>
						<ul>
								<li><a class="submenu" href="../../jsp/admin/courses.jsp">Course</a></li>
								<li><a class="submenu" href="../../jsp/admin/department.jsp" >Department </a></li>
								<li><a class="submenu" href="../../jsp/admin/instructor.jsp">Instructor</a></li>
								<li><a class="submenu" href="../../jsp/admin/student.jsp">Student</a></li>
							</ul>
						</li>
						<li><a class="hide"
							href="../../jsp/logout/InstructorLogout.jsp">Logout</a></li>


					</ul>

				</div>
				<!--  <div id="logo_out">
					<h2>
						<a href="../../jsp/logout/InstructorLogout.jsp">Logout </a>
					</h2>
				</div>
-->

			</div>
		</div>
	</form>
</body>
</html>
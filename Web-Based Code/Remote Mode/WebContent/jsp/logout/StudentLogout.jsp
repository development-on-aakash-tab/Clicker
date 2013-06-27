<%@page import="com.clicker.global.AakashClickerGlobal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility

String StudentID =(String) session.getAttribute("StudentID");

System.out.println("Student id is : " + StudentID);

if (StudentID == null) {
	request.setAttribute("Error",	"Session has ended.");
	RequestDispatcher rd = request.getRequestDispatcher("../../jsp/studentJspPages/StudentError.jsp");
	rd.forward(request, response);
	return;
}

AakashClickerGlobal.StudentIDs.remove(StudentID);
/*session.removeAttribute("Usertype");
session.removeAttribute("InstructorID");
session.removeAttribute("StudentID");
session.removeAttribute("Error");
session.removeAttribute("StudentCourse");
session.removeAttribute("QuestionResponseArray");
session.removeAttribute("QuestionIndex");*/
if(session.getAttribute("previousThread")!=null){
	Thread previousThread = (Thread)session.getAttribute("previousThread");
	previousThread.interrupt();}
session.invalidate();
%>
<html>
<head>
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Student Logout</title>
</head>
<body>
	<center>
		<div id="content_in">
			<div style="min-height: 300px">
				<center>
					<div id="log_out">
						<br> <br> <br> <br>
						<h2 style="color:">
							You have successfully logged out.<br> Thank you
						</h2>
						<br> Close the Browser and Login by Clicker App. <br>
						<!--  <a href="../../Login.jsp">Again Login</a> -->
						<br />
						<!-- <a href="javascript:window.opener='x'; window.close();">Close Browser</a> -->
					</div>
				</center>
			</div>
		</div>
	</center>
</body>
</html>
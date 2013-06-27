<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--<%@ page import="java.util.*,com.clicker.raisehand.*" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility

	String InstructorID = (String) session.getAttribute("InstructorID");
    session.setAttribute("insid",InstructorID);
	System.out.println("Instructor ID is : " + InstructorID);

	if (InstructorID == null) {
		request.setAttribute("Error","Your session has expired.");
		RequestDispatcher rd = request.getRequestDispatcher("../../jsp/errorpages/Error.jsp");
		rd.forward(request, response);
	}
	System.out.println("Course ID :"+(String)session.getAttribute("courseID"));
	System.out.println((String)session.getAttribute("Usertype"));
%>
<html>
<head>
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Instructor</title>
</head>
<body>
	<%-- <%@ include file="../../jsp/raisehand/RaiseHandSideBar.jsp" %>--%>
	<center>
		<%--<%@ include file="../../jsp/includes/menuheaderForAdmin.jsp" %>--%>
		<div id="content_in">
			<div style="min-height: 500px">
				<div style="height: 20px"></div>
				<h1>Welcome Admin !</h1>
				<br />

			</div>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
</body>
</html>
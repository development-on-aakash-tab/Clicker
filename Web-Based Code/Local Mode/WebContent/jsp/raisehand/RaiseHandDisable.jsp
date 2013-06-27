<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.clicker.databaseconn.DatabaseQueries"%>
<%@page import="java.sql.*"%>

<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility*/
	
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility

	String InstructorID = (String) session.getAttribute("InstructorID");

	System.out.println("Instructor ID is : " + InstructorID);

	if (InstructorID == null) {
		request.setAttribute("Error",
				"Your session has expired.");
		RequestDispatcher rd = request
				.getRequestDispatcher("../../jsp/instructorJspPages/InstructorError.jsp");
		rd.forward(request, response);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../../javascript/jquery.js"></script>
<script type="text/javascript" src="../../javascript/RaiseHand.js"></script>
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Disable Raise Hand</title>
</head>
<body>
	<%@ include file="../../jsp/raisehand/RaiseHandSideBar.jsp"%>
	<center>
		<%@ include file="../newMenu/newMenuwithCSS.jsp"%>
		<div id="content_in">
			<div style="min-height: 500px">
				<div style="height: 20px"></div>
				<br />
				<form action="../../switchRaiseHand" method="GET">
					<center>
						<br /> <input type="hidden" name="enable" value="0"> <input
							type="submit"
							style="height: 50px; width: 200px; font-weight: bold;"
							value="      Disable Raise Hand      " /><br> <input
							type="button"
							style="height: 50px; width: 200px; font-weight: bold;"
							value="  View Past Saved Doubts  "
							onclick="location.href='../../retrieveDoubts'" />
					</center>
				</form>

			</div>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
</body>
</html>
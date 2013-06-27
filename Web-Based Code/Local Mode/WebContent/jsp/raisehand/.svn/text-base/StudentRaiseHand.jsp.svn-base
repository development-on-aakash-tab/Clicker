<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String StudentID =(String) session.getAttribute("StudentID");	
	System.out.println("Student id is : " + StudentID);
	if (StudentID == null) {
		request.setAttribute("Error", "Session has ended.  Please login.");
		RequestDispatcher rd = request.getRequestDispatcher("../../jsp/errorpages/Error.jsp");
		rd.forward(request, response);
		return;
	}
%>
<html>
<head>
<script type="text/javascript" src="../../javascript/RaiseHand.js"></script>
<link href="../../css/style.css" rel="stylesheet" type="text/css" media="screen" />
<title>Raise Hand</title>
</head>
<body>
		<%@include file="../newMenu/newMenuForStudent.jsp"%>
		<div id="content_in">
			<div style="min-height: 350px">
				<div style="height: 20px"></div>
				<div id="formdiv">
					<br />
						<form action="../../raiseHandChannel" method="post">
							<h2>Enter your message</h2>
							<br />
							<br />
							<textarea rows="5" cols="60" name="msgbody"></textarea>
							<br />
							<br /> <input type="submit" value="  Raise Hand  " id="submit" />
						</form>
				</div>
			</div>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	<%if(request.getParameter("status")!=null){%>
	<script type="text/javascript">alert('Doubt Submitted')</script>
	<%} %>
</body>
</html>
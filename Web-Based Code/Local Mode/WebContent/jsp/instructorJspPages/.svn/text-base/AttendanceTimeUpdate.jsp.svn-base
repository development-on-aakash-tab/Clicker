<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String StudentID = (String) session.getAttribute("StudentID");
String InstructorID = (String) session.getAttribute("InstructorID");
if (StudentID == null) {
	request.setAttribute("Error",
			"Your session has expired.");
	RequestDispatcher rd = request
			.getRequestDispatcher("../../jsp/studentJspPages/StudentError.jsp");
	rd.forward(request, response);
}

String minutes = request.getParameter("minutes").toString();
String seconds = request.getParameter("seconds").toString();

application.setAttribute(InstructorID+"Attminutes", minutes);
application.setAttribute(InstructorID+"Attseconds", seconds);
%>
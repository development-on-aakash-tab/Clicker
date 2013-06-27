<%
String CourseID = (String) session.getAttribute("courseID");
int count = Integer.parseInt(application.getAttribute(CourseID + "StudentResponseCount").toString());
out.print(count);
%>
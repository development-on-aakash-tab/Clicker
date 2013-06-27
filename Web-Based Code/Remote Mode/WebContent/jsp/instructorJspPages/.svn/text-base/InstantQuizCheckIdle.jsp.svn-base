<%
response.setHeader("pragma", "no-cache,no-store");  
response.setHeader("cache-control", "no-cache,no-store,max-age=0,max-stale=0");  
String StudentID =(String) session.getAttribute("StudentID");
if (StudentID == null) {
	request.setAttribute("Error", "Session has ended.  Please login.");
	RequestDispatcher rd = request.getRequestDispatcher("../../jsp/studentJspPages/StudentError.jsp");
	rd.forward(request, response);
	return;
}
if(application.getAttribute("InstantResponseCount")!=null){	
int count = Integer.parseInt(session.getAttribute("courseID").toString() + application.getAttribute("InstantResponseCount").toString());
out.print(count);
}
else{
	out.print(0);
}
%>
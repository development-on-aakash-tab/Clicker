<%@page import="java.util.concurrent.ConcurrentHashMap"%>
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

if(request.getParameter("resp")!=null){
	String CourseID = session.getAttribute("courseID").toString();
	if(application.getAttribute(CourseID+"InstantResponse")!=null){		
		String resp = request.getParameter("resp");
		ConcurrentHashMap <String, String> InstantResponse = (ConcurrentHashMap<String, String>) application.getAttribute( CourseID + "InstantResponse");
		InstantResponse.put(StudentID, resp);		
		synchronized (this) {				
			int count = Integer.parseInt(application.getAttribute(CourseID + "InstantResponseCount").toString());
			application.setAttribute(CourseID+"InstantResponseCount", count+1);
		}
	}	
}
%>
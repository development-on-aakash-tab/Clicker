<%@page import="java.util.concurrent.ConcurrentHashMap"%>
<%
	response.setHeader("Cache-Control",	"no-cache, no-store, must-revalidate"); //HTTP 1.1
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
	String StudentID = (String) session.getAttribute("StudentID");
	System.out.println("-------------EQ - " + StudentID);
	if (StudentID == null) {
		request.setAttribute("Error", "Your session has expired.");
		RequestDispatcher rd = request.getRequestDispatcher("../../jsp/studentJspPages/StudentError.jsp");
		rd.forward(request, response);
		return;
	}
	if (session.getAttribute("ResultAccessCount") == null) {
		session.setAttribute("ResultAccessCount", 1);
	} else {
		int resultAccessCount = Integer.parseInt(session.getAttribute("ResultAccessCount").toString());
		session.setAttribute("ResultAccessCount", ++resultAccessCount);
	}

	if (Integer.parseInt(session.getAttribute("ResultAccessCount").toString()) == 1) {
		System.out.println("-------------RAC - " + StudentID);
		String questionsResponse = request.getParameter("response");
		session.setAttribute("questionsResponse", questionsResponse);
		String CourseID = session.getAttribute("courseID").toString();		
		ConcurrentHashMap <String, String> AllStudentResponse = (ConcurrentHashMap<String, String>) application.getAttribute( CourseID + "AllStudentResponse");
		AllStudentResponse.put(StudentID, questionsResponse);
		synchronized (this) {
		    int count = Integer.parseInt(application.getAttribute(CourseID + "StudentResponseCount").toString());
		    application.setAttribute( CourseID + "StudentResponseCount", count+1);
	  	}
		while(true){
			if(application.getAttribute("SSE"+CourseID)==null){
				break;
			}
			System.out.println("Before Thread sleep: " + StudentID);
			Thread.sleep(1000);
		}
		System.out.println("After Thread : " + StudentID);
	}
%>

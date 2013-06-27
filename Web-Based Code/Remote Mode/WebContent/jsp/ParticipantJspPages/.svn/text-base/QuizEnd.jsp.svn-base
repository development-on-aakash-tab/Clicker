
<%@page import="java.util.*"%>
<%@page import="com.clicker.wrappers.*"%>
<%@page import="java.util.concurrent.ConcurrentHashMap"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
String ParticipantID =(String)session.getAttribute("ParticipantID");

System.out.println("Quiz End : " + ParticipantID);

if (ParticipantID == null) {
	request.setAttribute("Error", "Session has ended.");
	RequestDispatcher rd = request.getRequestDispatcher("ParticipantError.jsp");
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
	String questionsResponse = request.getParameter("response");
	String questionsResponsearray = request.getParameter("quizresp");
	System.out.println(ParticipantID + " In Array : " + questionsResponsearray);
	System.out.println(ParticipantID + " questionsResponse = " + questionsResponse);
	session.setAttribute("questionsResponse", questionsResponse);
	ConcurrentHashMap <String, String> AllParticipantResponse = (ConcurrentHashMap<String, String>) application.getAttribute("AllParticipantResponse");
	AllParticipantResponse.put(ParticipantID, questionsResponse);
	synchronized (this) {
		if(application.getAttribute("ParticipantCount")!=null){
	    	int count = Integer.parseInt(application.getAttribute("ParticipantCount").toString());
	    	application.setAttribute("ParticipantCount", count+1);
		}
  	}
	while(true){
		if(application.getAttribute("SSEStatus")==null){
			break;
		}
		System.out.println("Before Thread sleep: " + ParticipantID);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	System.out.println("After Thread : " + ParticipantID);
}
%>


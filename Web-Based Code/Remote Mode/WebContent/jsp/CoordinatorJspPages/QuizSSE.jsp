<%
response.setHeader("pragma", "no-cache,no-store");  
response.setHeader("cache-control", "no-cache,no-store,max-age=0,max-stale=0");  
response.setContentType("text/event-stream;charset=UTF-8");
int messagesSent=0;
String status = request.getParameter("Status");
//System.out.println((session.getAttribute("ParticipantID")!=null)?session.getAttribute("ParticipantID").toString():"null");
if(session.getAttribute("ParticipantID")==null && session.getAttribute("CoordinatorID")==null){
	return;
}
if (status==null) {
	while(true){
	if(application.getAttribute("SSEStatus")!=null){
		System.out.println("Get SSE !!!");		
		if(session.getAttribute("ResultAccessCount")!=null){
			session.removeAttribute("ResultAccessCount");
		}
		out.print("data: Get Start\n\n");
		out.flush();
		break;
	}else{		
		out.print("data: Waiting for quiz...");
		out.flush();		
	}
	Thread.sleep(5000);
	}
}else if (status.equals("start")){
	application.setAttribute("SSEStatus", "Started");
	System.out.println("Start !!!");
	response.sendRedirect("./QuizURL.jsp");
}else if (status.equals("stop")){	
	application.removeAttribute("SSEStatus");
	System.out.println("Stoped !!!");	
}
%>
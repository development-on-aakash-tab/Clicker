<%
response.setHeader("pragma", "no-cache,no-store");  
response.setHeader("cache-control", "no-cache,no-store,max-age=0,max-stale=0");  
//response.setContentType("text/event-stream;charset=UTF-8"); 
int messagesSent=0;
String status = request.getParameter("Status");
if(null==session.getAttribute("InstructorID") && status!=null){
	return;
}

if (status==null) {
	if(session.getAttribute("courseID")!=null){
		String courseID = session.getAttribute("courseID").toString();
		if(application.getAttribute("SSE"+courseID)!=null){
			out.print(application.getAttribute(courseID + "optiontime").toString() + "\n\n");
			out.flush();
		}else{		
			out.print("Waiting for quiz...\n\n");
			out.flush();
		}
	}	
}
else if (status.equals("time")){	
	application.setAttribute(session.getAttribute("courseID").toString() + "optiontime", request.getParameter("optiontime"));
	System.out.println(request.getParameter("optiontime"));	
}
%>
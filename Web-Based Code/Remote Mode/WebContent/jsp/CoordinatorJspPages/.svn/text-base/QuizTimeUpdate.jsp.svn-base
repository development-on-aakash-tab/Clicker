<%
response.setHeader("pragma", "no-cache,no-store");  
response.setHeader("cache-control", "no-cache,no-store,max-age=0,max-stale=0");  
//response.setContentType("text/event-stream;charset=UTF-8"); 
if(session.getAttribute("ParticipantID")==null && session.getAttribute("CoordinatorID")==null){
	request.setAttribute("Error", "Your session has expired.");
	RequestDispatcher rd = null;
	if(session.getAttribute("CoordinatorID")==null){
		rd = request.getRequestDispatcher("../../jsp/CoordinatorJspPages/CoordinatorError.jsp");
	}else{
		rd = request.getRequestDispatcher("../../jsp/ParticipantJspPages/ParticipantError.jsp");	
	}
	rd.forward(request, response);
	return;
}

String requestfrom =  request.getParameter("requestfrom").toString();
if(requestfrom.equals("server")){
	String minutes = request.getParameter("minutes").toString();
	String seconds = request.getParameter("seconds").toString();
	application.setAttribute("minutes", minutes);
	application.setAttribute("seconds", seconds);
}
else if(requestfrom.equals("client")){
		if(application.getAttribute("minutes")!=null){
			String minutes = application.getAttribute("minutes").toString();
			String seconds =  application.getAttribute("seconds").toString();
			System.out.println((minutes + ":" +seconds));
			//out.print("data: " + minutes + ":" +seconds + "\n\n");
			//out.flush();
			out.print(minutes + ":" +seconds);
		}
		else{		
			//out.print("data: Waiting for quiz...");
			//out.flush();		
			out.print("Waiting for quiz...");
		}	
}

%>
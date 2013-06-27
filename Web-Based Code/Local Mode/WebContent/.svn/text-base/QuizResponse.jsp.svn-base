<% 
if(request.getParameter("resp")!=null){
	String resp = request.getParameter("resp");
	if(application.getAttribute("Responses")!=null){
		synchronized (this) {
			StringBuffer Responses = (StringBuffer) application.getAttribute("Responses");
			Responses.append(resp + ";");
			application.setAttribute("Responses", Responses);
			int count = Integer.parseInt(application.getAttribute("InstantResponseCount").toString());
			application.setAttribute("InstantResponseCount", count+1);
		}
	}	
}
%>
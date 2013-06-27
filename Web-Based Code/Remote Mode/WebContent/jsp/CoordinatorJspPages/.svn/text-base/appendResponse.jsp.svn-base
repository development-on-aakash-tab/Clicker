<%@page import="com.clicker.util.AppendXML"%>
<%@page import="java.util.Vector"%>
<%@page import="com.clicker.wrappers.*"%>
<%@page import="java.util.concurrent.ConcurrentHashMap"%>
<%
String CoordinatorID= (String) session.getAttribute("CoordinatorID");
if (CoordinatorID == null) {
	request.setAttribute("Error", "Session has ended.  Please login.");
	RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
	rd.forward(request, response);
	return;
}
AppendXML appendXML = new AppendXML();
String XMLFilename = application.getAttribute("RemoteResponseXML").toString();
Vector <Question> Questiondetails = (Vector <Question>) application.getAttribute("Questiondetails");
ConcurrentHashMap <String, String> AllParticipantResponse = (ConcurrentHashMap<String, String>) application.getAttribute("AllParticipantResponse");
String path = getServletContext().getRealPath("/") + CoordinatorID +"/";
appendXML.appendResponseInXML(path, AllParticipantResponse, XMLFilename, Questiondetails);
%>
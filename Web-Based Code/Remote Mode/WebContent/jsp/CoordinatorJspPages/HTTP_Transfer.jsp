<%@page import="com.clicker.databaseconn.QuizSaveDatabaseRecords"%>
<%@page import="com.clicker.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.concurrent.ConcurrentHashMap"%>
<%
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
String CoordinatorID = (String) session.getAttribute("CoordinatorID");
if ( CoordinatorID == null) {
	request.setAttribute("Error","Your session has expired.");
	RequestDispatcher rd = request.getRequestDispatcher("../../jsp/errorpages/Error.jsp");
	rd.forward(request, response);
	return;
}
String RemoteResponseXML = application.getAttribute("RemoteResponseXML").toString();
System.out.println("Remote Response XML Name is "+RemoteResponseXML);
String xmlpath =getServletContext().getRealPath("/") + CoordinatorID +"/"+application.getAttribute("RemoteResponseXML").toString();
System.out.println("Remote Response XML Path is "+xmlpath);
HTTP_FileTransfer transfer = new HTTP_FileTransfer();
transfer.sendthroughHTTP(xmlpath, RemoteResponseXML);
if(application.getAttribute("ParticipantCount")!=null){
	application.removeAttribute("ParticipantCount");
}
response.sendRedirect("./responsechart.jsp");
%>
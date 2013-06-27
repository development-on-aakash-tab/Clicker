<%@page import="com.clicker.util.*"%>
<%@page import="com.clicker.databaseconn.*"%>
<%@page import="java.net.URL"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="com.clicker.wrappers.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility

	String CoordinatorID = (String) session.getAttribute("CoordinatorID");
	System.out.println("Coordinator ID is : " +  CoordinatorID);

	if ( CoordinatorID == null) {
		request.setAttribute("Error","Your session has expired.");
		RequestDispatcher rd = request.getRequestDispatcher("../../jsp/errorpages/Error.jsp");
		rd.forward(request, response);
		return;
	}	
%>
<html>
<head>
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Instructor</title>
</head>
<body>
<center>
<%@ include file="../../jsp/includes/menuheaderForCoordinator.jsp" %>
<div id="content_in">
<div style="min-height: 425px">
<div style="height:20px"> </div>
	<h2>Quiz Module</h2>
	<div style="height:20px"> </div>
<%
int Progress_downloadingXML = 0;
//String userDir = System.getProperty("user.home");
String path = getServletContext().getRealPath("/") + CoordinatorID +"/";

ParameterXMLParse pxp = new ParameterXMLParse();
System.out.println("Server IP check is !!!!!!!!!!!!!!!!!!!!!!!  "+ServerIPCheck.isInternetReachable());

if (ServerIPCheck.isInternetReachable())
{
	//URL urlstring = new URL("http://localhost:8080/AakashClickerV3/sversion.txt");
	URL urlstring = new URL("http://www.it.iitb.ac.in/clicker/sversion.txt");
	String s = URL_ReadPage.readPageText(urlstring);

	int serverversion = Integer.parseInt(s);

	if (serverversion != 0) {		
		URL_XMLdownload.folderCreateOrDelete(getServletContext().getRealPath("/") + CoordinatorID);
		System.out.println("Path of file........."+path);		
		//Progress_downloadingXML = URL_XMLdownload.download_XML_File("http://localhost:8080/AakashClickerV3/Quiz.xml", path , Progress_downloadingXML);
	    Progress_downloadingXML = URL_XMLdownload.download_XML_File("http://www.it.iitb.ac.in/clicker/Quiz.xml", path , Progress_downloadingXML);
	    String QuizParameters[] = pxp.getParametersfromXML(path+"Quiz.xml");
	    String Quiz_ID = QuizParameters[0].toString();
	    String QuizTimeString = QuizParameters[1].toString();
	    int QuizTime = Integer.parseInt(QuizTimeString);
	    System.out.println("Quiz Time is " + QuizTime);
	    int QuizID = Integer.parseInt(Quiz_ID);      
	    DatabaseConnection dbconn = new DatabaseConnection();
	    Connection conn = dbconn.createDatabaseConnection();	    
	    QuizRecordQueries qrq = new QuizRecordQueries();
	    DatabaseQueries DB_queries = new DatabaseQueries();   
	    BasicXML basicXML = new BasicXML();
	    int RemoteCenterID = DB_queries.getRemoteCenterID(conn, CoordinatorID);
	    java.util.Date date = new java.util.Date();
	    java.text.DateFormat dateformat = new java.text.SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
	    String datestring = dateformat.format(date);
	    datestring = datestring.replace(" ", "_");
		String FileName = "Responses"+"_"+RemoteCenterID+"_"+"Quiz"+"_"+Quiz_ID+"_"+datestring+".xml";		
		System.out.println("Response File Name is "+path+FileName);
		basicXML.createBasicResponseXML(path + FileName);
	    Vector<Question> Questiondetails = new Vector<Question>();
	    Questiondetails = DB_queries.getQuestionDetailsFromXML(path);
	    System.out.println("Number of Question Vector : " + Questiondetails.size());
	    String quizQuestions = DB_queries.getQuizQuestions(Questiondetails).toString();
	    System.out.println("Number of Question String : " + quizQuestions.split("@@").length);
	    quizQuestions = quizQuestions.replace("\r\n", " ").replace("\n", " ");
	    application.setAttribute("QuizID",QuizID);
	    //remove Questiondetails or quizQuestions both are same content
	    application.setAttribute("Questiondetails",Questiondetails);
	    application.setAttribute("quizQuestions",quizQuestions);
	    application.setAttribute("QuizStatus", "START");
	    application.setAttribute("minutes", QuizTime/60);
	    application.setAttribute("seconds", QuizTime%60);
	    application.setAttribute("RemoteResponseXML", FileName);    
	    response.sendRedirect("./QuizSSE.jsp?Status=start");
	    conn.close();
   	}
	else
	{
	%>
	<center>
	<h2>Waiting For Main Center's Instructor<br/>
	<br/></h2>
	Quiz is not launched from main center
	</center>
	<%
	response.addHeader("Refresh","1");
	application.setAttribute(CoordinatorID+"QuizStatus", "STOP");
	}	
}
else
{
	%>	
	<h2>Network is unreachable or<br/>
	<br/>
	Connection refused. <br/>
	This happen due to- <br/>
	1. Web server is down or stop at IIT Bombay <br/>
	or 2. Internet Connection problem at (your side) Remote Coordinator Center.
	<br/>
	Please re-check and Login again.  
	<br/></h2>
	<%
	response.addHeader("Refresh","5");	
}
%>	
	</div>
</div>
<%@ include file="../../jsp/includes/footer.jsp" %>
</center>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.io.*"%>
    <%@page import="java.util.*"%>
    <%@page import="java.text.*"%>
    <%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility

	String InstructorID = (String) session.getAttribute("InstructorID");
    session.setAttribute("insid",InstructorID);
	System.out.println("Instructor ID is : " + InstructorID);

	if (InstructorID == null) {
		request.setAttribute("Error","Your session has expired.");
		RequestDispatcher rd = request.getRequestDispatcher("../../jsp/errorpages/Error.jsp");
		rd.forward(request, response);
	}
	//System.out.println("Course ID :"+(String)session.getAttribute("courseID"));
	//System.out.println((String)session.getAttribute("Usertype"));
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
 
 <style> 



          
        </style>
<title>Insert title here</title>
</head>
    <%@ include file="../newMenu/newMenuwithCSS.jsp" %>
<body>
<%

try {
	java.util.Date now = new java.util.Date();
	String Date_Format = "yyyy-MM-dd";
	SimpleDateFormat sdf = new SimpleDateFormat(Date_Format);
	String str = sdf.format(now);
    Runtime runtime = Runtime.getRuntime();
    String path = getServletContext().getRealPath("/");
    System.out.println("hi"+str+path);
    String name=str+"_backup.sql";
    File backupFile = new File(path+name);
    FileWriter fw = new FileWriter(backupFile);
  	  Process child = runtime.exec("mysqldump --user=root --password= --lock-all-tables --opt aakashclicker");
    InputStreamReader irs = new InputStreamReader(child.getInputStream());
    BufferedReader br = new BufferedReader(irs);
    
    String line;
    while( (line=br.readLine()) != null ) {
        fw.write(line + "\n");
    }
    fw.close();
    irs.close();
    br.close();
} catch (IOException ex) {
    ex.printStackTrace();
}
%>



<br><br><div style= "width:750px;color:white; height:200px; margin-left:300px;"> 
<h1>Backup is created Successfully !!!</h1></div>

</body>
<%@ include file="../../../jsp/includes/footer.jsp" %>
</html>
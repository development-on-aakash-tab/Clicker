<%@page import="com.clicker.util.ResponseHelper"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility*/



String InstructorID = (String) session.getAttribute("InstructorID");

if (InstructorID == null) {
	request.setAttribute("Error", "Your session has expired."); 
	RequestDispatcher rd = request
			.getRequestDispatcher("../../jsp/remoteInstructor/InstructorError.jsp");
	rd.forward(request, response);
	return;
}
String QuizID = "";
String quizType = request.getParameter("quiztype");
if(quizType.equals("normal")){
QuizID = application.getAttribute(InstructorID+"QuizID").toString();
}else if(quizType.equals("instant")){
	QuizID = session.getAttribute("iquizid").toString();
}
ResponseHelper responseHelper = new ResponseHelper();
String centers = responseHelper.getRemoteCenterList();
String[] centerID = centers.split("@@")[0].split("@;");
String[] centerName = centers.split("@@")[1].split("@;");
int noofcenter = centerID.length;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<script type="text/javascript" src="../../javascript/RemoteResponses.js">
</script>
<style type="text/css">
#RemoteCenterTablediv {
	width: 600px;
	margin-left: 10%;
	margin-right: 10%;
	margin-top: 20px;
	background-color: #ffffff;
	max-height: 300px;
	min-height: 300px;
	overflow: scroll;
}
</style>
<title>Remote Centers</title>
</head>
<body onload="responseStatus('<%=QuizID%>', '<%=noofcenter%>', '<%=quizType%>')">
	<center>
		<%@ include
			file="../../jsp/newMenu/newMenuForRemoteInst.jsp"%>
		<div id="content_in" style="height: 450px">
		<div id="hide_centerid" style="display: none;"><%=centers.split("@")[0]%></div>
			<div style="height: 400px; font-weight: bold">
				<div style="height: 20px"></div>
				<center>
					<font style="color: red;">Please wait for few seconds as
						responses are being collected from Remote Centers !!! </font>
					<div id="QuizID_hidden" style="visibility: hidden"></div>
					<div id="RemoteCenterStatusdiv"></div>
					<div id="RemoteCenterTablediv" style="overflow: auto; height:400px">					
						<table border="1" id="RCListTable" style="width: 580px;">
							<tr id="row0" style="font-weight: 700">
								<td>Center ID</td>
								<td>Center Name</td>
								<td style="cursor: pointer;" onclick="sortResonse()">Response Received</td>
							</tr>
							<% 																	
								for(int i=0;i<noofcenter;i++){
									String cid = centerID[i];
									%>
									<tr id="row<%=cid%>" onclick="rowSelected('<%=cid%>', <%=noofcenter%>)">
									<td><%=cid%></td>
									<td><%=centerName[i]%></td>
									<td style="text-align: center;"><input disabled="disabled"
										type="checkbox" name="status" id="check<%=cid%>" value="null" /></td>
									</tr>
								<%								
								}					
							%>
						</table>
					</div>
					<input type="button" value="View Chart" name="viewresponce"
						onclick="redirectResponseChart('response')" /> <input type="button"
						value="View All Response" name="viewallresponce"
						onclick="redirectResponseChart('allresponse')" />
				</center>
			</div>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
</body>
</html>
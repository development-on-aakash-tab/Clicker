<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.clicker.databaseconn.Insert_Attendance"%>
<%@page import="com.clicker.databaseconn.QuizRecordQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="com.clicker.wrappers.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<style type="text/css">
<!--
.timeClass {
	font-family: arial, verdana, helvetica, sans-serif;
	font-weight: normal;
	font-size: 50pt;
}
-->
</style>
<title>Insert title here</title>
</head>
<script language="JavaScript" src="../../javascript/attendance.js"></script>
<script language="JavaScript" src="../../javascript/Responses.js"></script>
<script language="JavaScript" src="../../javascript/jquery-1.3.2.min.js"></script>
<script language="JavaScript" type="text/javascript">
<!--
	var myArray = new Array(2);
	var qAt = document.URL.indexOf("?");
	var params = document.URL.substr(qAt + 1);
	paramsplit = params.split("&");
	//alert(paramsplit);
	param1 = paramsplit[0].split("=");
	param2 = paramsplit[1].split("=");
	//alert(param1[1]);
	//alert(param2[1]);
	myArray[0] = param1[1];
	myArray[1] = param2[1];

	var sec = parseInt(myArray[1]);
	var min = parseInt(myArray[0]);

	function countDown() {
		sec--;
		if (sec == -01) {
			sec = 59;
			min = min - 1;
		}

		if (sec <= 9) {
			sec = "0" + sec;
		}

		time = (min <= 9 ? "0" + min : min) + " min and " + sec + " sec ";
		updateAttendanceTime(min, sec);
		if (document.getElementById) {
			document.getElementById('theTime').innerHTML = time;
			document.getElementById("StartTimerButton").style.visibility = 'hidden';
		}

		SD = window.setTimeout("countDown();", 1000);
		if (min == '00' && sec == '00') {
			sec = "00";
			window.clearTimeout(SD);			
			window.location.href="../../jsp/instructorJspPages/attendance_responce.jsp";		
			
		}
	}
	function updateAttendanceTime(min, sec)
	{
		$('#AttendanceUpdatediv').load("./AttendanceTimeUpdate.jsp?minutes="+min+"&seconds="+sec);
		$('#AttendanceUpdatediv').hide();	
	}
</script>



<body onload="countDown()">
	<%
		response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
		response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
		response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
		response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility*/
		String InstructorID = (String) session.getAttribute("InstructorID");
		System.out.println("Instructor ID is : " + InstructorID);

		if (InstructorID == null) {
			request.setAttribute("Error", "Your session has expired.");
			RequestDispatcher rd = request.getRequestDispatcher("../../jsp/instructorJspPages/InstructorError.jsp");
			rd.forward(request, response);
		}

		String AttendanceStatus = (String) application.getAttribute(InstructorID+ "AttendanceStatus");
		
		if (AttendanceStatus == "TAKEN") {
			String AttendanceMessage = "Attendance is already taken";
	%>
	<center>
		<%@ include file="../newMenu/newMenuwithCSS.jsp"%> 
		 
		<div id="content_in">
			<div style="min-height: 300px; font-weight: bold">
				<center>
					<div style="height: 20px; text-align: left; font-size: 14px">Attendance
						Module</div>
					<%=AttendanceMessage%>
					<br /> <br /> <br> <input type="button"
						value="Reconduct Quiz" onclick="ReShowAttendancedlg();" />
				</center>
			</div>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
	<%
		return;
		}
		
		String minutes = request.getParameter("minutes").toString();
		String seconds = request.getParameter("seconds").toString();
		application.setAttribute(InstructorID + "Attendance_Status", "START");
		java.util.Date date= new java.util.Date();
		
		java.sql.Timestamp  sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
		
		//System.out.println("Time Stamp------------->"+sqlDate);
		
		application.setAttribute(InstructorID + "Attendance_TS", new Timestamp(date.getTime()));
		
		//System.out.println("==============>"+InstructorID + "  Attendance_Status  "+ "START");
		application.setAttribute(InstructorID + "Attminutes", minutes);
		application.setAttribute(InstructorID + "Attseconds", seconds);
		application.setAttribute(InstructorID + "AttendanceTime", sqlDate);
		
		
		DatabaseConnection dbconn = new DatabaseConnection();
		Insert_Attendance Student_save = new Insert_Attendance();
		Connection conn = dbconn.createDatabaseConnection();
		Student_save.insert_student(conn, InstructorID, sqlDate);
		conn.close();		
		
	%>

	<SCRIPT type="text/javascript">
window.history.forward();
</SCRIPT>


	<center>
		<%@ include file="../newMenu/newMenuwithCSS.jsp"%> 
		<div id="content_in">
			<div style="min-height: 300px; font-weight: bold">
				<center>
					<div style="height: 20px; text-align: left; font-size: 14px">Attendance
					</div>
					</br> </br>
					<table width="100%">
						<tr>
							<td width="100%" align="center"><span id="theTime"
								class="timeClass"></span></td>
						</tr>
					</table>
					<div style="height: 30px"></div>
					<center>
						<input type="button" id="StartTimerButton" value="Start Timer"
							onclick="countDown();" />
					</center>
					<input type="button" id="ViewResponsesButton"
						value="View Attendance" style="visibility: hidden"
						onclick="viewAttendance()" />
				</center>
			</div>
		</div>
		<div id="AttendanceUpdatediv" style="display: none;"></div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
</body>
</html>
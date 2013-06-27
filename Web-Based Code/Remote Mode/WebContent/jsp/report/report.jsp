<!-- 
This jsp file is used for UI of report generation process
Author : Rajavel

Referances  :

1. DatePicker :
	http://jqueryui.com/demos/datepicker/
	http://docs.jquery.com/UI/API/1.8/Datepicker

2. Autocomplete :
	http://docs.jquery.com/UI/Autocomplete
	http://jqueryui.com/demos/autocomplete/#maxheight

-->


<%@page import="com.clicker.report.ReportHelper"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
	String UserID = "";
	String Usertype = (String) session.getAttribute("Usertype");

	System.out.println("Usertype is......... : " + Usertype);
	String DeptName = (String) session.getAttribute("DeptName");
	String DeptID = (String) session.getAttribute("DeptID");
	Boolean isMultiCourseForInstructor = false;
	String courseID = "";
	String courseName = "";
	ReportHelper reportHelper = new ReportHelper();
	if (Usertype == null) {
		request.setAttribute("Error", "Your session has expired.");
		System.out.println("calling usertype...... session ended");
		RequestDispatcher rd = request
				.getRequestDispatcher("../../jsp/errorpages/Error.jsp");
		rd.forward(request, response);
		return;
	}

	if (isMultiCourseForInstructor == false	&& Usertype.equals("Instructor")) {		
			UserID = (String) session.getAttribute("InstructorID");
			courseID = session.getAttribute("courseID").toString();		
			courseName = reportHelper.getCourseName(courseID);
	}
	
	int sdd = 0, smm=0, syyyy=0;
	int edd = 0, emm=0, eyyyy=0;
	String dates="";
	int courseatt_sdd = 0, courseatt_smm=0, courseatt_syyyy=0;
	int courseatt_edd = 0, courseatt_emm=0, courseatt_eyyyy=0;
	int raiseHand_sdd = 0, raiseHand_smm=0, raiseHand_syyyy=0;
	int raiseHand_edd = 0, raiseHand_emm=0, raiseHand_eyyyy=0;
	String courseatt_dates="";
	String studentIDs ="";
	String raiseHand_dates="";
	if (Usertype.equals("Instructor")) {
		UserID = (String) session.getAttribute("InstructorID");		
		String calendarDate = reportHelper.getCalendarDate(courseID, "quizCondectedDate", UserID);			
		dates = calendarDate.split("@")[0];
		String date = calendarDate.split("@")[1];
		syyyy = Integer.parseInt(date.split("-")[0]);
		smm = Integer.parseInt(date.split("-")[1]);
		sdd = Integer.parseInt(date.split("-")[2]);
		date = calendarDate.split("@")[2];
		eyyyy = Integer.parseInt(date.split("-")[0]);
		emm = Integer.parseInt(date.split("-")[1]);
		edd = Integer.parseInt(date.split("-")[2]);
		
		calendarDate=  reportHelper.getCalendarDate(courseID, "attendanceTakenDate", UserID);
		courseatt_dates = calendarDate.split("@")[0];
		System.out.println(courseatt_dates);			
		date = calendarDate.split("@")[1];
		courseatt_syyyy = Integer.parseInt(date.split("-")[0]);
		courseatt_smm = Integer.parseInt(date.split("-")[1]);
		courseatt_sdd = Integer.parseInt(date.split("-")[2]);						
		date = calendarDate.split("@")[2];
		courseatt_eyyyy = Integer.parseInt(date.split("-")[0]);
		courseatt_emm = Integer.parseInt(date.split("-")[1]);
		courseatt_edd = Integer.parseInt(date.split("-")[2]);
		
		calendarDate=  reportHelper.getCalendarDate(courseID, "raiseHandDate", UserID);
		raiseHand_dates = calendarDate.split("@")[0];
		System.out.println("Raise Hand : " + raiseHand_dates);			
		date = calendarDate.split("@")[1];
		raiseHand_syyyy = Integer.parseInt(date.split("-")[0]);
		raiseHand_smm = Integer.parseInt(date.split("-")[1]);
		raiseHand_sdd = Integer.parseInt(date.split("-")[2]);						
		date = calendarDate.split("@")[2];
		raiseHand_eyyyy = Integer.parseInt(date.split("-")[0]);
		raiseHand_emm = Integer.parseInt(date.split("-")[1]);
		raiseHand_edd = Integer.parseInt(date.split("-")[2]);
		
		studentIDs = reportHelper.getStudentIDs(courseID);
	} else {
		UserID = (String) session.getAttribute("StudentID");
	}
	System.out.println(dates);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Clicker Report</title>
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<script src="../../javascript/report.js" type="text/javascript"></script>
<link href="../../jquery/jquery-ui.css" rel="stylesheet" type="text/css"
	media="screen" />
<script src="../../jquery/jquery-1.5.min.js"></script>
<script src="../../jquery/jquery-1.8.19-ui.min.js"></script>
<style type="text/css">
#highlight,.highlight {
	background-color: #000000;
}

.ui-autocomplete {
	max-height: 100px;
	overflow-y: auto;
	/* prevent horizontal scrollbar */
	overflow-x: hidden;
	/* add padding to account for vertical scrollbar */
	padding-right: 20px;
}
/* IE 6 doesn't support max-height
	 * we use height instead, but this forces the menu to always be this tall
	 */
html .ui-autocomplete {
	height: 100px;
}
select{
font-size:18px;width: 120px;
}
</style>
<script>
 	var start= new Date(<%=syyyy%> , <%=smm-1%>, <%=sdd%>);
  	var end = new Date(<%=eyyyy%> , <%=emm-1%>, <%=edd%>);
  	var dateString = "<%=dates%>";
  	var dates= dateString.split(",");
  	$(function() {
		$( "#quiz_datepicker" ).datepicker({
			minDate: start, maxDate: end,
			changeMonth: true,
			changeYear: true, 
			beforeShowDay: highlightDays
		});	

		function highlightDays(date) {
			//alert(date);
			for (var i = 0; i < dates.length; i++) {
				if (dates[i] == date.formatYYYYMMDD()) {
					return [true, 'highlight'];
		        }
		    }
		    return [true, ''];
		} 
		Date.prototype.formatYYYYMMDD=function(){
		    var dd = this.getDate(), mm = this.getMonth()+1, yyyy = this.getFullYear();
		    if(dd<10){
		      dd = '0' + dd;
		    }
		    if(mm<10){
		      mm = '0'+ mm;
		    }
		  return String(yyyy + "-" + mm + "-" + dd);
		  };
	});			
  	var courseatt_start= new Date(<%=courseatt_syyyy%> , <%=courseatt_smm-1%>, <%=courseatt_sdd%>);
  	var courseatt_end = new Date(<%=courseatt_eyyyy%> , <%=courseatt_emm-1%>, <%=courseatt_edd%>);
  	var courseatt_dateString = "<%=courseatt_dates%>";
  	var courseatt_dates= courseatt_dateString.split(",");
  	$(function() {
		$( "#courseAtt_datepicker" ).datepicker({
			minDate: courseatt_start, maxDate: courseatt_end,
			changeMonth: true,
			changeYear: true, 
			beforeShowDay: highlightDays
		});	

		function highlightDays(date) {
			//alert(date);
			for (var i = 0; i < courseatt_dates.length; i++) {
				if (courseatt_dates[i] == date.formatYYYYMMDD()) {
					return [true, 'highlight'];
		        }
		    }
		    return [true, ''];
		} 
		Date.prototype.formatYYYYMMDD=function(){
		    var dd = this.getDate(), mm = this.getMonth()+1, yyyy = this.getFullYear();
		    if(dd<10){
		      dd = '0' + dd;
		    }
		    if(mm<10){
		      mm = '0'+ mm;
		    }
		  return String(yyyy + "-" + mm + "-" + dd);
		  };
	});	  			
  	var raiseHand_start= new Date(<%=raiseHand_syyyy%> , <%=raiseHand_smm-1%>, <%=raiseHand_sdd%>);
  	var raiseHand_end = new Date(<%=raiseHand_eyyyy%> , <%=raiseHand_emm-1%>, <%=raiseHand_edd%>);
  	var raiseHand_dateString = "<%=raiseHand_dates%>";
  	//document.getElementById("hide_querydates").innerHTML = raiseHand_dateString;
  	var raiseHand_dates= raiseHand_dateString.split(",");
  	$(function() {
		$( "#studentQuery_datepicker" ).datepicker({
			minDate: raiseHand_start, maxDate: raiseHand_end,
			changeMonth: true,
			changeYear: true, 
			beforeShowDay: highlightDays
		});	

		function highlightDays(date) {
			//alert(date);
			for (var i = 0; i < raiseHand_dates.length; i++) {
				if (raiseHand_dates[i] == date.formatYYYYMMDD()) {
					return [true, 'highlight'];
		        }
		    }
		    return [true, ''];
		} 
		Date.prototype.formatYYYYMMDD=function(){
		    var dd = this.getDate(), mm = this.getMonth()+1, yyyy = this.getFullYear();
		    if(dd<10){
		      dd = '0' + dd;
		    }
		    if(mm<10){
		      mm = '0'+ mm;
		    }
		  return String(yyyy + "-" + mm + "-" + dd);
		  };
	});	

  	$(function() {
  	  	var studList = "<%=studentIDs%>";
		var availableList = studList.split(",");
		$( "#studentid_autocomplete" ).autocomplete({
			source: availableList,
			focus: function( event, ui ) {
				$( "#studentid_autocomplete" ).val( ui.item.label );
				currentStudID();
				return false;
			},
			select: function( event, ui ) {
				$( "#studentid_autocomplete" ).val( ui.item.label );
				return false;
			}			
			
		});
	});

	
	</script>
</head>
<%
	if (isMultiCourseForInstructor == false
			&& Usertype.equals("Instructor")) {
	%>
<body onload='fillStudQuizDetail("<%=courseID%>")' style="font-size: 20px">
	<%} else {	%>

<body style="font-size:22px">
	<%}%>
	<%
		if (Usertype.equals("Instructor")) {
	%>
	<%@ include file="../newMenu/newMenuwithCSS.jsp"%> 
	<%} else {	%>
	<%@include file="../newMenu/newMenuForStudent.jsp"%>
	<%}	%>
	<div id="content_in" style=" font-size:20px;min-height: 425px;">
		<div style="text-align: left; font-weight: bold">Report
			Module</div>
		<div id="rpt_left_content">
			<%
				if (Usertype.equals("Instructor")) {
			%>
			<div class="rpt_menu" id="mnu_student" style="color: red"
				onclick="showReportContent('student', 'quiz', 'course')">Student</div>
			<div class="rpt_menu" id="mnu_quiz"
				onclick="showReportContent('quiz', 'student', 'course')">Quiz</div>
			<div class="rpt_menu" id="mnu_course"
				onclick="showReportContent('course', 'quiz', 'student')">Course</div>
			<%}%>
		</div>
		<div id="rpt_header">
			Department Name :
			<%=DeptName%><br /> <br /> Course :
			<%
 				if (isMultiCourseForInstructor == false
 					&& Usertype.equals("Instructor")) {
 			%>
			<%=courseName%>
			<%
				} else {
			%>
			<select name="courseid" id="courseid" onchange="fillStudQuizDetail(this.value)">
				<option value="Course ID">Course ID</option>
				<%
				String courseIDs = reportHelper.getCourseIDs(Usertype, UserID, DeptID);
				String[] courseIDArray = courseIDs.split(",");
				for(int i=0;i<courseIDArray.length;i++)
				{%>
				<option value="<%=courseIDArray[i]%>"><%=courseIDArray[i]%>
				</option>
				<%}						
				%>
				
			</select>
			<%}%>
		</div>
		<div id="rpt_right_content">
			<div class="rpt_content" id="student_content" style="display: block;">
				<div id="UserType_hidden" style="display: none;"><%=Usertype%></div>
				<div id="StudentList_hidden" style="display: none;"><%=studentIDs%></div>
				<div id="CurrentStudID_hidden" style="display: none;"><%=UserID%></div>
				<table width="480px" cellspacing="10" cellpadding="10">
					<tr>
						<%
							if (Usertype.equals("Instructor")) {
						%>
						<td align="center">Student ID : <input type="text"
							id="studentid_autocomplete" onchange="currentStudID()"
							onkeydown="currentStudID()" /> <!--<select name="studid"
							id="studid" onchange="currentStudID()">
								<option value="Student ID">Student ID</option>
						</select> --> <%} else { %>
						<td align="center">Student ID : <%=UserID%> <%} %>
						</td>
					</tr>
					<tr>
						<td><label><input type="radio" name="stud" value="StudentInfo"
							onclick="studDetail()"> Student Information</input><br /></label></td>
					</tr>
					<tr>
						<td><label><input type="radio" name="stud"
							value="StudentResultPercentage" onchange="studDetail()">
							Result</input></label></td>
					</tr>
				</table>
				<div id="stud_info" style="margin-left: 40px; display: none"></div>
				<div id="stud_rpt_btn" style="display: none">
				<form action="../../Report" target="_blank" method="post"
						onsubmit="return isstudentvalidate()">
						<input type="text" style="display: none;" id="hide_cid"
							name="hide_cid" /> <input type="text" style="display: none;"
							id="hide_sid" name="hide_sid" /> <input type="text"
							style="display: none;" id="hide_rptname" name="hide_rptname" />
						<input style="margin-left: 150px" type="submit" name="report"
							value="Student Report" />
					</form>
				</div>
			</div>
			<div id="quizreporttype" style="text-align: center; display: none;">
				<label><input type="radio" name="reportby" value="date" checked="checked"
					onchange="setQuizReportType(this.value)"> Report by Date </label> <label><input
					type="radio" name="reportby" value="quizname"
					onchange="setQuizReportType(this.value)"> Report by Quiz
				name </label>
			</div>
			<br />
			<div id="quiz_detail" style="display: none; margin-left: 30px">
				Quiz Name : <select name="quizname" id="quizname"
					onchange="fillQuizTime(this.value, 'quiztime', 'quizTS')">
					<option value="Quiz Name">Quiz Name</option>
				</select> <br /> <br />
				<div id="quizTS" style="display: none;">
					Quiz Time Stamp : <select name="quiztime" id="quiztime"
						onchange="setQuizTime()">
						<option value="Time Stamp">TimeStamp</option>
					</select>
				</div>
			</div>
			<div id="date_quiz_detail" style="display: none; margin-left: 30px">
				Quiz Date : <input type="text" id="quiz_datepicker"
					style="width: 80px;"
					onchange="fillDateQuizDetail('<%=courseID%>', this.value)">
				Quiz Name : <select name="date_quizname" id="date_quizname"
					onchange="fillQuizTime(this.value, 'date_quiztime', 'date_quizTS')">
					<option value="Quiz Name">Quiz Name</option>
				</select> <br />
				<br />
				<div id="date_quizTS" style="display: none;">
					Quiz Time Stamp : <select name="date_quiztime" id="date_quiztime"
						onchange="date_setQuizTime()">
						<option value="Time Stamp">Time Stamp</option>
					</select>
				</div>
			</div>
			<div class="rpt_content" id="quiz_content" style="margin-left: 30px">
				<table width="480px" cellspacing="10" cellpadding="10">
					<tr>
						<td><label><input type="radio" checked="checked" name="quiz"
							value="QuizDetail" onchange="quizDetail()"> Quiz Detail</input></label><br /></td>
					</tr>
					<tr>
						<td><label><input type="radio" name="quiz" value="QuizResponse"
							onchange="quizDetail()"> Quiz Response</input></label></td>
					</tr>
					<tr>
						<td><label><input type="radio" name="quiz" value="QuizResult"
							onchange="quizDetail()"> Quiz Result</input></label></td>
					</tr>
				</table>
				<form action="../../Report" target="blank" method="post"
					onsubmit="return isQuizValid()">
					<input type="text" style="display: none;" id="hide_cid1"
						name="hide_cid1" /> <input type="text" style="display: none;"
						id="hide_qid" name="hide_qid" /> <input type="text"
						style="display: none;" id="hide_qts" name="hide_qts" /> <input
						type="text" style="display: none;" value="QuizDetail"
						id="hide_qrptname" name="hide_qrptname" /> <input
						style="margin-left: 150px;" type="submit" name="report"
						value="Quiz Report" />
				</form>
			</div>
			<div class="rpt_content" id="course_content">
				<table width="480px" cellspacing="10" cellpadding="10">
					<tr>
						<td colspan="2"><label><input type="radio" name="course"
							checked="checked" value="StudentList"
							onchange="courseDetail()"> Student List</input></label><br /></td>
					</tr>
					<tr>
						<td><label><input type="radio" name="course" value="Attendance"
							onchange="courseDetail()"> Attendance</input></label>
							<div id="courseAttDate" style="display: none;">
								Date : <input type="text" id="courseAtt_datepicker"
									onchange="fillAttenDetail('<%=courseID%>', this.value, 'attendancetimestamp', 'att_ts')"
									style="width: 80px;">
							</div></td>
						<td>
							<div id="att_ts" style="display: none;">
								Time Stamp : <select name="attendancetimestamp"
									id="attendancetimestamp" onclick="setAttendanceTS()">
									<option value="">Time Stamp</option>
								</select>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2"><label><input type="radio" name="course"
							value="QuizSummary" onchange="courseDetail()"> Quiz
							Summary</input></label></td>
					</tr>
					<tr>
						<td colspan="2"><label><input type="radio" name="course"
							value="StudentQuery" onchange="courseDetail()">
							Student Query</input><label>
							<div id="hide_querydates" style="display: none;"><%=raiseHand_dates%></div>
							<div id="studentQueryDate" style="display: none;">
								Date : <input type="text" id="studentQuery_datepicker"
									onchange="setStudentQueryDate('<%=courseID%>', this.value)"
									style="width: 80px;">
							</div></td>
					</tr>
				</table>
				<form action="../../Report" target="blank" method="post"
					onsubmit="return iscoursevalidate()">
					<input type="text" style="display: none;" id="hide_cid2"
						name="hide_cid2" /> <input type="text" style="display: none;"
						value="StudentList" id="hide_crptname" name="hide_crptname" />
					<input type="text" style="display: none;" id="hide_att_ts"
						name="hide_att_ts" /> <input type="text" style="display: none;"
						id="hide_query_date" name="hide_query_date" /> <input
						style="margin-left: 150px" type="submit" name="report"
						value="Course Report" />
				</form>
			</div>
		</div>
	</div>
	<%@ include file="../../jsp/includes/footer.jsp"%>
</body>
</html>
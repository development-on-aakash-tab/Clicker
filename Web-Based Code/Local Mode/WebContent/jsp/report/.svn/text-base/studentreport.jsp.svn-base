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
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%
	String UserID = "";
	UserID = (String) session.getAttribute("StudentID");
	String Usertype = (String) session.getAttribute("Usertype");
	String DeptName = (String) session.getAttribute("DeptName");
	String DeptID = (String) session.getAttribute("DeptID");	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Report</title>
<link href="../../css/style.css" rel="stylesheet" type="text/css" media="screen" />
<style type="text/css">
/* RADIO */
.regular-radio {
	display: none;
}
.regular-radio + label {
	-webkit-appearance: none;
	background-color: #fafafa;
	border: 1px solid #cacece;
	box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0px -13px 8px -10px rgba(0,0,0,0.05);
	padding: 9px;
	border-radius: 35px;
	display: inline-block;
	position: relative;
}
.regular-radio:checked + label:after {
	content: ' ';
	width: 10px;
	height: 10px;
	border-radius: 30px;
	position: absolute;
	top: 3px;
	background: #00FF00;
	box-shadow: inset 0px 0px 8px rgba(0,0,0,0.3);
	text-shadow: 0px;
	left: 3px;
	font-size: 32px;
}
.regular-radio:checked + label {
	background-color: #e9ecee;
	color: #99a1a7;
	border: 1px solid #adb8c0;
	box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0px -13px 8px -10px rgba(0,0,0,0.05), inset 13px 8px -10px rgba(255,255,255,0.1), inset 0px 0px 8px rgba(0,0,0,0.1);
}
.regular-radio + label:active, .regular-radio:checked + label:active {
	box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0px 1px 3px rgba(0,0,0,0.1);
}
.regular-radio.big-radio + label {
	padding: 15px;
}
.regular-radio.big-radio:checked + label:after {
	width: 15px;
	height: 15px;
	left: 8px;
	top: 8px;
}
</style>
<script type="text/javascript">
var xmlhttp;

//This method will get the XMLHTTP object for work with ajax
function getXMLhttp() {
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE
		try {
			xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
}

function fillStudQuizDetail(courseID) {
	document.getElementById("hide_cid").value = courseID;
}

function studDetail() {
	var studradio = document.getElementsByName("stud");
	var reportname = "";
	for ( var i = 0; i < studradio.length; i++) {
		if (studradio[i].checked)
			reportname = studradio[i].value;
	}		
	document.getElementById("hide_rptname").value = reportname;
	if (reportname == "StudentInfo") {
		var sid ="";
		sid = document.getElementById("CurrentStudID_hidden").innerHTML;
		document.getElementById("stud_rpt_btn").style.display = "none";
		document.getElementById("stud_info").style.display = "block";
		if (sid == "" || sid == "Student ID") {
			var studdetails = "<i style='color:red;'>Enter Student ID...</i>";
			document.getElementById("stud_info").innerHTML = studdetails;
			return;
		}
		getXMLhttp();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var studinfo = xmlhttp.responseText;
				var stud = studinfo.split("~");
				var studdetails = "<table border='1px'> <tr> <td>Student Name </td><td> " + stud[0];
				studdetails += "</td></tr><tr><td>Year </td><td> " + stud[1];
				studdetails += "</td></tr><tr><td>Semester </td><td> " + stud[2] + "</td></tr></table>";
				document.getElementById("stud_info").innerHTML = studdetails;
			}
		};
		xmlhttp.open("GET", "../../jsp/report/DAOReportHelper.jsp?infotype=studentinfo&sid=" + sid,	true);
		xmlhttp.send();
	} else {
		document.getElementById("stud_rpt_btn").style.display = "block";
		document.getElementById("stud_info").style.display = "none";
	}
}

/**
 * This method is used to validate before student report generation
 */
function isstudentvalidate(){
	var utype = document.getElementById("UserType_hidden").innerHTML;
	var cid = document.getElementById("courseid").value;	
	if (cid == "Course ID")
	{
		alert("Select Course ID");
		return false;
	}
	var curstudid = document.getElementById("CurrentStudID_hidden").innerHTML;
	document.getElementById("hide_sid").value = curstudid;
	return true;
}
</script>
</head>
<body style="font-size:22px">	
	<%@include file="../newMenu/newMenuForStudent.jsp"%>
	<div id="content_in" style="text-align:left; font-size:20px;min-height: 350px;">
		<div style="font-weight: bold">Report	Module</div>
		<div id="rpt_header" style="text-align: center;" >
			Department Name :
			<%=DeptName%><br /> <br /> Course :			
			<select name="courseid" id="courseid" onchange="fillStudQuizDetail(this.value)">
				<option value="Course ID">Course ID</option>
				<%
				ReportHelper reportHelper = new ReportHelper();
				String courseIDs = reportHelper.getCourseIDs(Usertype, UserID, DeptID);
				String[] courseIDArray = courseIDs.split(",");
				for(int i=0;i<courseIDArray.length;i++)
				{%>
				<option value="<%=courseIDArray[i]%>"><%=courseIDArray[i]%>
				</option>				
			</select>
			<%}%>
		</div>
		<div id="rpt_right_content" style="text-align: left; margin-left: 60px;">
			<div class="rpt_content" id="student_content" style="display: block;">
				<div id="UserType_hidden" style="display: none;"><%=Usertype%></div>
				<div id="CurrentStudID_hidden" style="display: none;"><%=UserID%></div>
				<table width="480px" cellspacing="10" cellpadding="10">
					<tr>						
						<td align="center">Student ID : <%=UserID%> 
						</td>
					</tr>
					<tr>
						<td><label><input id="info" type="radio" name="stud" class="regular-radio big-radio" value="StudentInfo" onclick="studDetail()"><label for='radio-2-1'></label> Student Information<br /></label></td>
					</tr>
					<tr>
						<td><label><input id="result" type="radio" name="stud" class="regular-radio big-radio" value="StudentResultPercentage" onchange="studDetail()"><label for='radio-2-1'></label> Result</label></td>
					</tr>
				</table>
				<div id="stud_info" style="margin-left: 40px; display: none"></div>
				<div id="stud_rpt_btn" style="display: none">
				<form action="../../Report" target="_blank" method="post" onsubmit="return isstudentvalidate()">
						<input type="text" style="display: none;" id="hide_cid"	name="hide_cid" /> 
						<input type="text" style="display: none;" id="hide_sid" name="hide_sid" />
						<input type="text" style="display: none;" id="hide_rptname" name="hide_rptname" />
						<input style="margin-left: 150px" type="submit" name="report" value="Student Report" />
					</form>
				</div>
			</div>			
		</div>
	</div>
	<%@ include file="../../jsp/includes/footer.jsp"%>
</body>
</html>
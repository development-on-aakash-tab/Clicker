/*
 * Author : Rajavel
 * This Java Script file is used for report information
 */

/*
 * Reference :
 * 		www.w3schools.com
 */

var xmlhttp;

// This method will get the XMLHTTP object for work with ajax
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

/**
 * This method is used to show and hide the report content based on report menu select
 * @param show Which report to show currently
 * @param hide1 Which report to hide currently
 * @param hide2 Which report to hide currently
 * @param hide3 Which report to hide currently
 */
var global_setQuizReportType_status;
function showReportContent(show, hide1, hide2) {
	document.getElementById(show + "_content").style.display = "block";
	document.getElementById(hide1 + "_content").style.display = "none";
	document.getElementById(hide2 + "_content").style.display = "none";
	
	document.getElementById("mnu_" + show).style.color = "#FF0000";
	document.getElementById("mnu_" + hide1).style.color = "#0000FF";
	document.getElementById("mnu_" + hide2).style.color = "#0000FF";
	global_setQuizReportType_status="yes";
	if (show == "quiz") { 
		show_hide_quiz_Detail("show");
	} else {
		show_hide_quiz_Detail("hide");
	}
}

/**
 * This method is used to show or hide the quiz details like quiz name and time stamp
 * @param status Show are hide current status
 */
function show_hide_quiz_Detail(status) {
	if (status == "show") {
		document.getElementById("quizreporttype").style.display = "block";
		var quizreporttyperadio = document.getElementsByName("reportby");
		var quizreporttype = "";
		for ( var i = 0; i < quizreporttyperadio.length; i++) {
			if (quizreporttyperadio[i].checked){
				quizreporttype = quizreporttyperadio[i].value;
			}
		}		
		setQuizReportType(quizreporttype);
	} else {
		document.getElementById("quizreporttype").style.display = "none";
		document.getElementById("date_quiz_detail").style.display = "none";
		document.getElementById("quiz_detail").style.display = "none";
	}
}
var global_reporttype;
function setQuizReportType(reporttype){
	global_reporttype = reporttype;
	if(reporttype=="date"){
		document.getElementById("date_quiz_detail").style.display = "block";
		document.getElementById("quiz_detail").style.display = "none";
		if(global_setQuizReportType_status=="yes"){
		document.getElementById("date_quizname")[0].selected = "1";
		document.getElementById("date_quizTS").style.display = "none";
		}
	}else{
		document.getElementById("quiz_detail").style.display = "block";
		document.getElementById("date_quiz_detail").style.display = "none";
		if(global_setQuizReportType_status=="yes"){
		document.getElementById("quizname")[0].selected = "1";
		document.getElementById("quizTS").style.display = "none";
		}
	}
}
/**
 * This method is used to fill drop down values for Student id, quiz name and attendance time stamp
 * @param courseID Course id
 */
function fillStudQuizDetail(courseID) {
	// Set the current selected course id in hidden fields for report parameter in student, quiz, course
	document.getElementById("hide_cid").value = courseID;
	document.getElementById("hide_cid1").value = courseID;
	document.getElementById("hide_cid2").value = courseID;
	getXMLhttp();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var resps = xmlhttp.responseText;
			if (window.ActiveXObject) {
				var responArray1 = resps.split("@#")[1].split("~");
				var QName = document.getElementById("quizname");
				while (QName.length > 1) {
					QName.remove(1);
				}
				var QNameOptions = responArray1[0].split(";");
				for ( var i = 1; i < (QNameOptions.length); i = i + 2) {
					var QNameOpt = document.createElement('option');
					QNameOpt.text = QNameOptions[i + 1];
					QNameOpt.value = QNameOptions[i];
					try {
						QName.add(QNameOpt, null); // standards compliant; doesn't work in IE                        
					} catch (ex) {
						QName.add(QNameOpt); // IE only
					}
				}
			} else {
				var responArray = resps.split("@#")[0].split("~");
				try {
					document.getElementById("quizname").innerHTML = responArray[0];
				} catch (ex) {
					var qname = "<select name='quizname' id='quizname' onclick='fillQuizTime(this.value, \'quiztime\' , \'quizTS\')' onkeypress='fillQuizTime(this.value, \'quiztime\', \'quizTS\')' style='width: 150px'>";
					qname += responArray[0] + "</Select>";
				}
			}
		}
	};
	
	xmlhttp.open("GET", "../../jsp/report/DAOReportHelper.jsp?infotype=quizstudatteninfo&cid="+ courseID, true);
	xmlhttp.send();
}

function fillDateQuizDetail(courseID, date) {
	// Set the current selected course id in hidden fields for report parameter in student, quiz, course 
	document.getElementById("hide_cid").value = courseID;
	document.getElementById("hide_cid1").value = courseID;
	document.getElementById("hide_cid2").value = courseID;
	getXMLhttp();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var resps = xmlhttp.responseText;
			if (window.ActiveXObject) {
				var responArray1 = resps.split("@#")[1];
				alert(responArray1);
				var QName = document.getElementById("date_quizname");
				while (QName.length > 1) {
					QName.remove(1);
				}
				var QNameOptions = responArray1.split(";");
				for ( var i = 1; i < (QNameOptions.length); i = i + 2) {
					var QNameOpt = document.createElement('option');
					QNameOpt.text = QNameOptions[i + 1];
					QNameOpt.value = QNameOptions[i];
					try {
						QName.add(QNameOpt, null); // standards compliant; doesn't work in IE                        
					} catch (ex) {
						QName.add(QNameOpt); // IE only
					}
				}				
			} else {
				var responArray = resps.split("@#")[0];
				try {
					document.getElementById("date_quizname").innerHTML = responArray;
				} catch (ex) {
					var qname = "<select name='date_quizname' id='date_quizname' onclick='date_fillQuizTime(this.value, \'quiztime\', \'date_quizTS\')' onkeypress='date_fillQuizTime(this.value, \'quiztime\', \'date_quizTS\')' style='width: 150px'>";
					qname += responArray + "</Select>";
					document.getElementById("date_quizname").outerHTML = qname;
				}
			}
		}
	};
	var dateArray = date.split("/");
	date = dateArray[2] + "-" + dateArray[0] +"-"+ dateArray[1];
	xmlhttp.open("GET",	"../../jsp/report/DAOReportHelper.jsp?infotype=datequizinfo&cid="+ courseID +"&date="+date, true);
	xmlhttp.send();
}

var globale_AttFlag;
function fillAttenDetail(courseID, date, attID, attTS_display) {
	getXMLhttp();
	//document.getElementById("att_ts").style.display="block";
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var resps = xmlhttp.responseText;
			var AttenTSOptions = resps.split("@#")[0].split(",");
			attTSLength = AttenTSOptions.length;
			if(attTSLength<2){
				document.getElementById(attTS_display).style.display="none";				
				globale_AttFlag = "null";				
				return;
			}
			else if (attTSLength==2){
				globale_AttFlag = "available";
				document.getElementById(attTS_display).style.display="none";
			}
			else if (attTSLength>2){
				globale_AttFlag = "available";
				document.getElementById(attTS_display).style.display="block";
			}
			if (window.ActiveXObject) {
				var AttenTS = document.getElementById(attID);
				while (AttenTS.length > 1) {
					AttenTS.remove(1);
				}							
				for ( var i = 1; i < (AttenTSOptions.length); i++) {
					var AttenTSOpt = document.createElement('option');
					AttenTSOpt.text = AttenTSOptions[i];
					AttenTSOpt.value = AttenTSOptions[i];
					try {
						AttenTS.add(AttenTSOpt, null); // standards compliant; doesn't work in IE  
					} catch (ex) {
						AttenTS.add(AttenTSOpt); // IE only
					}
				}
			} else {
				var responOptions = resps.split("@#")[1];
				try {
					document.getElementById(attID).innerHTML = responOptions;
				} catch (ex) {
					var attents = "<select name='" +attID+ "' id='"+attID+"' style='width: 150px'>";
					attents += responOptions + "</Select>";
					document.getElementById(attID).outerHTML = attents;
				}
			}
		}
	};
	
	var dateArray = date.split("/");
	date = dateArray[2] + "-" + dateArray[0] +"-"+ dateArray[1];
	xmlhttp.open("GET",	"../../jsp/report/DAOReportHelper.jsp?infotype=atteninfo&cid="+ courseID + "&date="+date, true);	
	xmlhttp.send();	
}


/**
 * This method fill the quiz time stamp based on quiz name selection
 * @param QID Quiz id
 * @param QTSid Quiz Time stamp
 */
var responselen;
var global_divQTSid;
//var global_responseTS;
function fillQuizTime(QID, QTSid, divQTSid) {
	// Set the current selected Quiz id in hidden fields for report parameter
	
	global_divQTSid = divQTSid;
	document.getElementById("hide_qid").value = QID;
	getXMLhttp();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var QTS = document.getElementById(QTSid);
			var response=xmlhttp.responseText;
			responselen = response.split("@#")[0].split(";").length;
			var quizradio = document.getElementsByName("quiz");
			var reportname = "";
			for ( var i = 0; i < quizradio.length; i++) {
				if (quizradio[i].checked)
					reportname = quizradio[i].value;
			}
			if(reportname=="QuizDetail" && document.getElementById("quiz_content").style.display == "block"){
				document.getElementById(divQTSid).style.display = "none";
				if(responselen ==2){
					document.getElementById(divQTSid).style.display = "none";
					var responseTS = response.split("@#")[0].split(";")[1];
					//global_responseTS = responseTS;
					document.getElementById("hide_qts").value = responseTS;	
				}
			}
			else if(responselen ==2){
				document.getElementById(divQTSid).style.display = "none";
				var responseTS = response.split("@#")[0].split(";")[1];
				//global_responseTS = responseTS;
				document.getElementById("hide_qts").value = responseTS;
			}else if (responselen > 2){
				document.getElementById(divQTSid).style.display = "block";
			}
			if (window.ActiveXObject) {
				while (QTS.length > 1) {
					QTS.remove(1);
				}
				var resval = response.split("@#")[0];
				var QTSOptions = resval.split(";");
				for ( var i = 1; i < (QTSOptions.length); i++) {
					var QTSOpt = document.createElement('option');
					QTSOpt.text = QTSOptions[i];
					QTSOpt.value = QTSOptions[i];
					try {
						QTS.add(QTSOpt, null); // standards compliant; doesn't work in IE                    
					} catch (ex) {
						QTS.add(QTSOpt); // IE only
					}
				}
			} else {
				try {
					document.getElementById(QTSid).innerHTML = response.split("@#")[1];
				} catch (ex) {
					var qtime = "<select name='quiztime' id='quiztime' style='width: 150px' >";
					qtime += response.split("@#")[1] + "</Select>";
					document.getElementById(QTSid).outerHTML = qtime;
				}
			}
		}
	};
	if(QTSid == "date_quiztime"){
		var date =  document.getElementById("quiz_datepicker").value;
		var dateArray = date.split("/");
		date = dateArray[2] + "-" + dateArray[0] +"-"+ dateArray[1];
		xmlhttp.open("GET", "../../jsp/report/DAOReportHelper.jsp?infotype=datequiztime&qid="+ QID + "&qdate="+date, true);
		xmlhttp.send();
	}else{
		xmlhttp.open("GET", "../../jsp/report/DAOReportHelper.jsp?infotype=quiztime&qid="+ QID, true);
		xmlhttp.send();
	}
}

/**
 * This method is used to set the current selected quiz time stamp in hidden fields for report parameter
 */
function setQuizTime() {	
	var quiztime_op = document.getElementById("quiztime");
	var quiztime = quiztime_op.options[quiztime_op.selectedIndex].text;
	document.getElementById("hide_qts").value = quiztime;
}

function date_setQuizTime() {	
	var quiztime_op = document.getElementById("date_quiztime");
	var quiztime = quiztime_op.options[quiztime_op.selectedIndex].text;
	document.getElementById("hide_qts").value = quiztime;
}

/**
 * This method is used to set the current student id in hidden fields for student report parameter
 * @param sid student id
 */
function currentStudID() {
	var cur_sid = document.getElementById("studentid_autocomplete").value;
	document.getElementById("CurrentStudID_hidden").innerHTML = cur_sid;
	document.getElementById("hide_sid").value = cur_sid;
	studDetail();
}


/**
 * This method is used to show the student details in table format
 */
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
		if(document.getElementById("UserType_hidden").innerHTML == "Student"){
			//alert(sid);
			sid = document.getElementById("CurrentStudID_hidden").innerHTML;
			document.getElementById("stud_rpt_btn").style.display = "none";
			document.getElementById("stud_info").style.display = "block";
		}else{
		sid = document.getElementById("studentid_autocomplete").value;
		var slist = document.getElementById("StudentList_hidden").innerHTML;
		if(slist.indexOf(sid + ",") == -1){
			alert("Student ID is not available");
			return false;
		}
		document.getElementById("stud_rpt_btn").style.display = "none";
		document.getElementById("stud_info").style.display = "block";
		//sid = document.getElementById("CurrentStudID_hidden").innerHTML;
		}
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
	//Enable if you want use multiple course for a instructor
	/*var cid = document.getElementById("courseid").value;	
	if (cid == "Course ID")
		{
			alert("Select Course ID");
			return false;
		}*/	
	var utype = document.getElementById("UserType_hidden").innerHTML;
	if(utype == "Student")
	{		
		var cid = document.getElementById("courseid").value;	
		if (cid == "Course ID")
			{
				alert("Select Course ID");
				return false;
			}
	var curstudid = document.getElementById("CurrentStudID_hidden").innerHTML;
	document.getElementById("hide_sid").value = curstudid;
	}else{			
	//var sid = document.getElementById("studid").value;
	//if (sid == "Student ID"){
	var sid = document.getElementById("studentid_autocomplete").value;
	var slist = document.getElementById("StudentList_hidden").innerHTML;
	if (sid == ""){
		alert("Enter Student ID");
		return false;
	}else if(slist.indexOf(sid + ",") == -1){
		alert("Student ID is not available");
		return false;
	}
	}	
	return true;
}

/**
 * This method is used to get the current selected quiz report name and set value in hiddent filed for report
 */
function quizDetail() {
	var quizradio = document.getElementsByName("quiz");
	var reportname = "";
	for ( var i = 0; i < quizradio.length; i++) {
		if (quizradio[i].checked)
			reportname = quizradio[i].value;
	}
	var isquizseleted;
	if(global_reporttype == "date"){
		isquizseleted = document.getElementById("date_quizname").selectedIndex;
	}
	else{
		isquizseleted = document.getElementById("quizname").selectedIndex;
	}
	if(reportname=="QuizDetail"){
		document.getElementById(global_divQTSid).style.display = "none";
	}
	else if(responselen ==2){
		document.getElementById(global_divQTSid).style.display = "none";
	}else if (responselen > 2 &&  isquizseleted != 0){
		document.getElementById(global_divQTSid).style.display = "block";
	}		
	document.getElementById("hide_qrptname").value = reportname;
}

/**
 * This method is used to validate before quiz report generation
 */
function isQuizValid(){
	var quizreporttyperadio = document.getElementsByName("reportby");
	var quizreporttype = "";
	for ( var i = 0; i < quizreporttyperadio.length; i++) {
		if (quizreporttyperadio[i].checked)
			quizreporttype = quizreporttyperadio[i].value;
	}
	var qname, qts, timestamp_display;
	if(quizreporttype=="quizname"){
	 qname= document.getElementById("quizname").value;
	 qts = document.getElementById("quiztime").value;
	timestamp_display = document.getElementById("quizTS").style.display;
	}else{
		qname = document.getElementById("date_quizname").value;
		qts = document.getElementById("date_quiztime").value;
		timestamp_display = document.getElementById("date_quizTS").style.display;
	}
	//Enable if you want use multiple course for a instructor
	/*var cid = document.getElementById("courseid").value;
	if(cid == "Course ID"){
		alert("Select Course ID");
		return false;
	}else*/
	
	if (qname == "Quiz Name"){
		alert("Select Name of the Quiz");
		return false;
	}else if (qts == "Time Stamp" && timestamp_display=="block"){
		alert("Select Quiz Timestamp");
		return false;
	}
	
	getXMLhttp();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			xmlhttp.responseText;		
			return true;
		}
	};
	var reptname = document.getElementById("hide_qrptname").value;
	if (reptname == "QuizResponse" || reptname == "QuizResult") {
		var cid = document.getElementById("hide_cid1").value;
		var qid = document.getElementById("hide_qid").value;
		var qts = document.getElementById("hide_qts").value;
		if (reptname == "QuizResponse") {
			reptname = "QuizResponseChart";
		} else if (reptname == "QuizResult") {
			reptname = "QuizResultChart";
		}
		//Synchronize request becouse after chat generation only, it will return true and go to ReportGenerator 
		xmlhttp.open("GET", "../../Chart?hide_chart_cid=" + cid
				+ "&hide_chart_rptname=" + reptname + "&hide_chart_qid=" + qid
				+ "&hide_chart_qts=" + qts, false);
		xmlhttp.send();
	}
}

/**
 * This method is used to set the current Attnedance time stamp in hidden fields of course report
 */
function setAttendanceTS() {
	var atttime_op = document.getElementById("attendancetimestamp");
	var atttime = atttime_op.options[atttime_op.selectedIndex].text;	
	document.getElementById("hide_att_ts").value = atttime;
}


/**
 * This method is used to get current couse name and set value in hidden fields of course report and show or hide attendance time stamp based on selection
 */
function courseDetail() {
	var quizradio = document.getElementsByName("course");
	var reportname = "";
	var i;
	for (i = 0; i < quizradio.length; i++) {
		if (quizradio[i].checked){
			reportname = quizradio[i].value;
			
			break;
		}
	}
	if (reportname == "Attendance")
		{
		document.getElementById("courseAttDate").style.display = "block";
		document.getElementById("studentQueryDate").style.display = "none";
		}
	else if (reportname == "StudentQuery")
	{		
		document.getElementById("studentQueryDate").style.display = "block";
		document.getElementById("courseAttDate").style.display = "none";
	}
	else{
		document.getElementById("studentQueryDate").style.display = "none";
		document.getElementById("courseAttDate").style.display = "none";
	}
	document.getElementById("hide_crptname").value = reportname;
}

/**
 * This method is used to validate before student report generation
 */
function iscoursevalidate(){
	//Enable if you want use multiple course for a instructor
	/*var cid = document.getElementById("courseid").value;
	if (cid == "Course ID")
		{
			alert("Select Course ID");
			return false;
		}*/
	
	var courseradio = document.getElementsByName("course");
	var reportname = "";
	for ( var i = 0; i < courseradio.length; i++) {
		if (courseradio[i].checked)
			reportname = courseradio[i].value;
	}
	if(reportname == "StudentQuery"){
		var date = document.getElementById("studentQuery_datepicker").value;
		if (date == "")
			{
			alert("Select the date");
			return false;
			}
		var dateArray = date.split("/");
		date = dateArray[2] + "-" + dateArray[0] +"-"+ dateArray[1];
		document.getElementById("hide_query_date").value = date;
		var querydates = document.getElementById("hide_querydates").innerHTML;
		if(querydates.indexOf(date)==-1){
			alert("No query is available on this day");
			return false;
		}		
		return true;
	}else if(reportname == "Attendance"){		
		if(globale_AttFlag == "null"){
			alert("No attendance is available on this day");
			return false;
		}else{	
			var atttime_op = document.getElementById("attendancetimestamp");
			var atttime = atttime_op.options[atttime_op.selectedIndex].text;
			document.getElementById("hide_att_ts").value = atttime;			
		}
		getXMLhttp();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				xmlhttp.responseText;		
				return true;
			}
		};
		var reptname = document.getElementById("hide_crptname").value;
		var cid = document.getElementById("hide_cid2").value;
		var ats = document.getElementById("hide_att_ts").value;
		xmlhttp.open("GET", "../../Chart?hide_chart_cid=" + cid
					+ "&hide_chart_rptname=" + reptname + "&hide_chart_ats=" + ats, true);
		xmlhttp.send();
	}	
}



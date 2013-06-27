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

function showDate(){
	var reportradio = document.getElementsByName("report_op");
	var reportname = "";
	for ( var i = 0; i < reportradio.length; i++) {
		if (reportradio[i].checked)
			reportname = reportradio[i].value;
	}
	if(reportname == "RemoteQuizResponse"){
		document.getElementById("div_qdate").style.display = "block";
		document.getElementById("div_rc").style.display = "block";
		//document.getElementById("remotecenter").style.display = "block";
	}else{
		document.getElementById("div_qdate").style.display = "none";
		document.getElementById("div_rc").style.display = "none";
		document.getElementById("div_qts").style.display = "none";
		//document.getElementById("remotecenter").style.display = "none";
	}
}

function listQuizNames(QTSid){
	getXMLhttp();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var response=xmlhttp.responseText;
			var Quiz = document.getElementById(QTSid);			
			if (window.ActiveXObject) {
				while (Quiz.length > 1) {
					Quiz.remove(1);
				}
				var resval = response.split("@#")[0];
				var QTSOptions = resval.split(";");
				for ( var i = 1; i < (QTSOptions.length); i++) {
					var QTSOpt = document.createElement('option');
					QTSOpt.text = QTSOptions[i];
					QTSOpt.value = QTSOptions[i];
					try {
						Quiz.add(QTSOpt, null); // standards compliant; doesn't work in IE                    
					} catch (ex) {
						Quiz.add(QTSOpt); // IE only
					}
				}
			} else {
				try {
					document.getElementById(QTSid).innerHTML = response.split("@#")[1];
				} catch (ex) {
					var qtime = "<select name='Quizname' id='quiz'' style='width: 150px' >";
					qtime += response.split("@#")[1] + "</Select>";
					document.getElementById(QTSid).outerHTML = qtime;
				}
			}
		}
	};	
		var date =  document.getElementById("datepicker").value;
		var dateArray = date.split("/");
		date = dateArray[2] + "-" + dateArray[0] +"-"+ dateArray[1];
		xmlhttp.open("GET", "../../jsp/remoteInstructor/DAOReportHelper.jsp?infotype=datequiz&qdate="+date, true);
		xmlhttp.send();	
}

function fillTimeStamp(QTSid, divQTSid){	
	getXMLhttp();
	var QID = document.getElementById("quiz").value;
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var QTS = document.getElementById(QTSid);
			var response=xmlhttp.responseText;
			responselen = response.split("@#")[0].split(";").length;
			var quizradio = document.getElementsByName("report_op");
			var reportname = "";
			for ( var i = 0; i < quizradio.length; i++) {
				if (quizradio[i].checked)
					reportname = quizradio[i].value;
			}
			if(responselen ==2){
				document.getElementById(divQTSid).style.display = "none";
				var responseTS = response.split("@#")[0].split(";")[1];
				//global_responseTS = responseTS;
				document.getElementById("hide_qts").value = responseTS;
				fillRemoteCenter(responseTS, QID);
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
					var qtime = "<select name='QTimeStamp' id='qts' style='width: 150px' >";
					qtime += response.split("@#")[1] + "</Select>";
					document.getElementById(QTSid).outerHTML = qtime;
				}
			}
		}
	};	
		var date =  document.getElementById("datepicker").value;
		var dateArray = date.split("/");		
		date = dateArray[2] + "-" + dateArray[0] +"-"+ dateArray[1];
		xmlhttp.open("GET", "../../jsp/remoteInstructor/DAOReportHelper.jsp?infotype=datequiztime&qid="+ QID + "&qdate="+date, true);
		xmlhttp.send();	
}

function setQTS(){
	
	var quiztime_op = document.getElementById("qts");
	var ts = quiztime_op.options[quiztime_op.selectedIndex].text;
	document.getElementById("hide_qts").value = ts;
	var QID = document.getElementById("quiz").value;
	fillRemoteCenter(ts, QID);
}

function fillRemoteCenter(ts, QID){
	getXMLhttp();
	var RC = document.getElementById("remotecenter");
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var response=xmlhttp.responseText;
			if (window.ActiveXObject) {
				while (RC.length > 1) {
					RC.remove(1);
				}
				var resval = response.split("@#")[0];
				var RCOptions = resval.split(";");
				for ( var i = 1; i < (RCOptions.length); i++) {
					var RCOpt = document.createElement('option');
					RCOpt.text = RCOptions[i].split("$")[1];
					RCOpt.value = RCOptions[i].split("$")[0];
					try {
						RC.add(RCOpt, null); // standards compliant; doesn't work in IE                    
					} catch (ex) {
						RC.add(RCOpt); // IE only
					}
				}
			} else {
				try {
					RC.innerHTML = response.split("@#")[1];
				} catch (ex) {
					var rc = "<select name='remotecenter' id='remotecenter' style='width: 150px' >";
					rc += response.split("@#")[1] + "</Select>";
					RC.outerHTML = rc;
				}
			}
		}
	};	
	xmlhttp.open("GET", "../../jsp/remoteInstructor/DAOReportHelper.jsp?infotype=remotecenterlist&qid="+ QID + "&qts="+ts, true);
	xmlhttp.send();	
}

function isValid(){
	var reportname = "";
	var reportradio = document.getElementsByName("report_op");
	for ( var i = 0; i < reportradio.length; i++) {
		if (reportradio[i].checked)
			reportname = reportradio[i].value;
	}
	if(reportname == "RemoteCenterList"){
		return true;
	}
	var quiz = document.getElementById("quiz").value;
	if(quiz == "Quiz Name"){
		alert("Select Quiz Name");
		return false;
	}
	var div_qts = document.getElementById("div_qts");
	var qts = document.getElementById("qts");
	if(div_qts.style.display == "block" && qts.value=="Time Stamp"){
		alert("Select Time Stamp");
		return false;
	}
	var centerid = document.getElementById("remotecenter").value;
	if(centerid == "Select Center"){
		alert("Select Remote center");
		return false;
	}
	
	if(reportname == "RemoteQuizResponse"){
		var date = document.getElementById("datepicker").value;
		var quizDates = document.getElementById("hide_quizdates").innerHTML;
		var dateArray = date.split("/");
		date = dateArray[2] + "-" + dateArray[0] +"-"+ dateArray[1];
		if(quizDates.indexOf(date) == -1){
			alert("There is 'No Quiz' on that date");
			return false;
		}
		getXMLhttp();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				xmlhttp.responseText;		
				return true;
			}
		};
		var qid = document.getElementById("quiz").value;
		var qts = document.getElementById("hide_qts").value;
		xmlhttp.open("GET", "../../RemoteChart?centerid=" + centerid
				+ "&rptname=" + reportname + "&qid=" + qid
				+ "&qts=" + qts, true);
		xmlhttp.send();
		}
	}	
	
/*
 * Author : Gobinath
 * This Java Script file is used for Attendance information
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

function ReShowAttendancedlg() { // alert("Hi");
	// alert(QuizName);
	window.location.href = "../../jsp/instructorJspPages/Instructor_Attendance.jsp";
}

function launchAttendanceURL() {

	// var minutes = document.Timer.Minutes.value;
	// var seconds = document.Timer.Seconds.value;
	var minutes="0";
	var seconds="10";
	window.location.href = "../../jsp/instructorJspPages/AttendanceURL.jsp?minutes="
			+ minutes + "&seconds=" + seconds;
}

function AttendanceResponses() {
	alert("AttendanceResponses by gobi");
	//window.location.href="../../jsp/instructorJspPages/attendance_responce.jsp";
}

function viewAttendance() {
	//alert("View Attendance");
	window.location.href = "../../jsp/instructorJspPages/attendance_responce.jsp";
}



function Student_Attendance(courseID) {
	
	getXMLhttp();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var AttenTSOptions = (xmlhttp.responseText).split(";");
			var AttenTS = document.getElementById("att_ts");
			while (AttenTS.length > 1) {
				AttenTS.remove(1);
			}
			for ( var i = 0; i < (AttenTSOptions.length) - 1; i++) {
				var AttenTSOpt = document.createElement('option');
				AttenTSOpt.text = AttenTSOptions[i];
				AttenTSOpt.value = AttenTSOptions[i];
				try {
					AttenTS.add(AttenTSOpt, null);
				} catch (ex) {
					AttenTS.add(AttenTSOpt);
				}
			}
		}
	};
	xmlhttp.open("GET", "Attendance_submit.jsp?studentID="+ts+"&cid=" + courseID, true);
	xmlhttp.send();

}
function showPresent()
	{
	getXMLhttp();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		
			var array = xmlhttp.responseText.split("@");
			
			var resps = array[0].split("#");
			
			var pre="<h3>Present:"+resps[0]+"</h3>";
			var abs="<h3>Absent:"+resps[1]+"</h3>";
			
		    var temp = "<table border="
					+ 1
					+ "> <tr><td>Student ID</td><td>Student Name</td><td>Attandance</td></tr>";	
			
			for ( var i = 0; i < array.length - 1; i++) {
				var resp = array[i].split(";");
				temp += "<tr><td>" + resp[0] + "</td><td>" + resp[1]
						+ "</td><td>" + resp[2] + "</td></tr>";
			}
			temp += "</table>";
		    document.getElementById("Attendance_detail").innerHTML = temp;
		}
	};
	xmlhttp.open("GET", "Attendance_Helper.jsp?info=Present", true);
	xmlhttp.send(); 
}



function showAbsent()
{	
	getXMLhttp();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		
			var array = xmlhttp.responseText.split("@");
			var temp = "<table border="
					+ 1
					+ "> <tr><td>Student ID</td><td>Student Name</td><td>Attandance</td></tr>";	
			
			for ( var i = 0; i < array.length - 1; i++) {
				var resp = array[i].split(";");
				
				temp += "<tr><td>" + resp[0] + "</td><td>" + resp[1]
						+ "</td><td>" + resp[2] + "</td></tr>";
			}
			temp += "</table>";			
			document.getElementById("Attendance_detail").innerHTML = temp;
		}
	};
	xmlhttp.open("GET", "Attendance_Helper.jsp?info=Absent", true);
	xmlhttp.send();
}




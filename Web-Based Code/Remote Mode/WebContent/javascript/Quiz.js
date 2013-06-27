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
function showQuiz(CourseID)
{	
if (CourseID=="")
  {
  document.getElementById("QuizList").innerHTML="";
  return;
  } 

$('#QuizList').load("../../jsp/instructorJspPages/ListQuiz.jsp?CourseID="+CourseID);

}

function showQuizdlg(QuizName, type)
{	
	if (QuizName=="Select Quiz")
	{
		alert("Please Select Quiz");
		return;
	}
	window.location.href="../../jsp/instructorJspPages/Quizdlg.jsp?QuizName="+QuizName + "&type=" + type;
			
}

function ReShowQuizdlg(QuizName)
{	
	window.location.href="../../jsp/instructorJspPages/Quizdlg.jsp?QuizName="+QuizName;
}

function launchQuizURL(QuizID, cid)
{	
	var minutes=document.Timer.Minutes.value;
	var seconds=document.Timer.Seconds.value;
	if (minutes==0 && seconds==0)
	{
		alert("Please give time for Quiz");
		return;
	}
	
	getXMLhttp();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			window.location.href="../../jsp/instructorJspPages/QuizURL.jsp?minutes="+minutes+"&seconds="+seconds+"&QuizID="+QuizID;
		}		
	};	
	xmlhttp.open("GET", "../../jsp/instructorJspPages/InstructorQuizSSE.jsp?Status=start&cid="+cid, true);
	xmlhttp.send();
	
}


function showSpotQuiz(CourseID)
{	
if (CourseID=="")
  {
  document.getElementById("SpotQuizList").innerHTML="";
  return;
  } 

$('#SpotQuizList').load("../../jsp/SpotQuiz/ListSpotQuiz.jsp?CourseID="+CourseID);

}


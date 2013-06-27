function showQuiz(CourseID)
{	
if (CourseID=="")
  {
  document.getElementById("QuizList").innerHTML="";
  return;
  } 
$('#QuizList').load("../../jsp/remoteInstructor/ListQuiz.jsp?CourseID="+CourseID);

}

function showQuizdlg(QuizName)
{	
	if (QuizName=="Select Quiz")
	{
		alert("Please Select Quiz");
		return;
	}
	window.location.href="../../jsp/remoteInstructor/Quizdlg.jsp?QuizName="+QuizName;
}

function ReShowQuizdlg(QuizName)
{	
	window.location.href="../../jsp/remoteInstructor/Quizdlg.jsp?QuizName="+QuizName;
}

function launchQuizURL(QuizID)
{	
	var minutes=document.Timer.Minutes.value;
	var seconds=document.Timer.Seconds.value;
	
	if (minutes==0 && seconds==0)
	{
		alert("Please give time for Quiz");
		return;
	}
	
	window.location.href="../../jsp/remoteInstructor/QuizURL.jsp?minutes="+minutes+"&seconds="+seconds+"&QuizID="+QuizID;
}

function showSpotQuiz(CourseID)
{	
	if (CourseID=="")
	{
		
		document.getElementById("SpotQuizList").innerHTML="";
		return;
	} 

	$('#SpotQuizList').load("../../jsp/remoteInstructor/ListSpotQuiz.jsp?CourseID="+CourseID);

}

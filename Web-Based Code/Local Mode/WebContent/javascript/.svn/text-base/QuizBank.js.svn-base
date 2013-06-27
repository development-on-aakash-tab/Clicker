function showQuizList(CourseID)
{	
if (CourseID=="")
  {
  document.getElementById("QuizList").innerHTML="";
  return;
  } 
/*if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("QuizList").innerHTML=xmlhttp.responseText;
    }
  };
xmlhttp.open("GET","../../jsp/instructorJspPages/ListQuiz.jsp?CourseID="+CourseID,true);
xmlhttp.send();*/

$('#QuizList').load("../../jsp/QuizBank/QuizBankListQuiz.jsp?CourseID="+CourseID);

}



function showquestion(QuizName)
{	
	if (QuizName=="")
	  {
	  document.getElementById("previewQuestion").innerHTML="";
	  return;
	  } 
	/*window.open("../../jsp/QuizBank/PreviewQuestion.jsp?QuizName="+QuizName,'mywin',
			'left=20,top=20,width=500,height=500,toolbar=1,resizable=0');*/
	$('#previewQuestion').load("../../jsp/QuizBank/PreviewQuestion.jsp?QuizName="+QuizName);
		
}


function getquestion(Keyword)
{	
	
	if (Keyword=="")
	{
		alert("Please Enter Keyword");
		return;
	}
	alert("the search keyword : "+Keyword);
	//window.location.href="../../jsp/instructorJspPages/Quizdlg.jsp?QuizName="+QuizName;
}


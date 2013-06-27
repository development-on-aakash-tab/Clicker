<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
<meta http-equiv="pragma" content="no-cache" />
<title>Quiz</title>
<script src="jquery/firefox-event-source.js"></script>
<script >
if(typeof(EventSource)!=="undefined")
  {
	var source=new EventSource("jsp/instructorJspPages/InstantSSE.jsp");
	source.addEventListener("message", function(event) {	
		var content = event.data;
		if(content == "Waiting for quiz..."){
			document.getElementById("quizStatus").innerHTML = content;
		}else{
			window.location.href="QuizData.jsp";			
		}
	}, false);	
  }
else
  {
  document.getElementById("result").innerHTML="Sorry, your browser does not support server-sent events...";
  }
</script> 
</head>
<body style="background: #AABBFF;">
	<div id="quizStatus" style="font-size: 34px; "></div>
	<div id="waiting" style="margin:120px 0px 0px 120px; ">		
		<img alt="Loading..." src="images/loading_transparent1.gif">
	</div>
	
</body>
</html>
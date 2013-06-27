<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="../../jquery/firefox-event-source.js"></script>
<style>
<!--
li{
width:auto;
height: 80px;
font-size:20px;
background: #CDAA54;
color: #fff;
display: inline;
border:2px solid; 
border-color:#CDAA54; 
border-radius:8px;
padding: 3px;
margin-left: 5px;
}
a {
color: #000;
text-decoration: none;
}
a:hover {
text-decoration: none;
color: #fff
}
-->
</style>

<script>
if(typeof(EventSource)!=="undefined")
  {
	var source=new EventSource("../../jsp/instructorJspPages/InstructorQuizSSE.jsp");			
	source.addEventListener("message", function(event) {	
		if(event.data == "Get Start"){			
			window.location.href="../../jsp/studentJspPages/StudentQuizURL.jsp";
		}else if(event.data == "Get Instant Quiz Start"){			
			window.location.href="InstantQuizData.jsp";
		}		
	}, false);
	
  }
</script>   
<div id="content">
<div id="headertop" style="color:#fff; margin:auto; height: 70px; width:800px; border:2px solid; border-color:#B88A00; border-radius:12px; background-color: #B88A00">
<div style="text-align: center; font-size: 20px;">Clicker Version 3</div>
<div class="menu" style="text-align: left; margin-top:15px;">
<ul>
<li><a class="hide" href="../../jsp/studentJspPages/StudentQuizListener.jsp">Quiz</a></li>
<li><a class="hide" href="../../jsp/raisehand/StudentRaiseHand.jsp">RaiseHand</a></li>
<li><a class="hide" href="../../jsp/polls/Student_Poll.jsp">Poll</a></li>
<li><a class="hide" href="../../jsp/report/studentreport.jsp">Report</a></li>
<li><a class="hide"href="../../jsp/logout/StudentLogout.jsp">Logout</a></li>
</ul>
</div>
</div>
</div>

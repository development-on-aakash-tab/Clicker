
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility

String InstructorID = (String) session.getAttribute("InstructorID");
if (InstructorID == null) {
	request.setAttribute("Error","Your session has expired.");
	RequestDispatcher rd = request.getRequestDispatcher("../../jsp/instructorJspPages/InstructorError.jsp");
	rd.forward(request, response);
	return;
}
%>
 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
<meta http-equiv="pragma" content="no-cache" />
<title>Instant Quiz</title>
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<link rel="stylesheet" media="all" type="text/css" href="../../styles.css" />
<script src="../../javascript/jquery-1.3.2.min.js"></script>
<script src="../../javascript/jquery-spin.js"></script>
<script>	
  $(document).ready(function(){
		$.spin.imageBasePath = '../../images/spin1/';				
		$('#Minutes').spin({
			max: 59,
			min: 0
		});		
		$('#Seconds').spin({
			max: 50,
			min: 0
		});		
  	});
  
  	function Minutes(data) {
		for(var i=0;i<data.length;i++) 
		if(data.substring(i,i+1)==":") 
		break;
		return(data.substring(0,i)); 
	}
			
	function Seconds(data) {        
		for(var i=0;i<data.length;i++) 
		if(data.substring(i,i+1)==":") 
		break;
		return(data.substring(i+1,data.length)); 
	}

	var cid = "";
	function countdown(course) {
		var radios = document.getElementsByName("optns");
		var flag = false;
		var resp = "Z";
		var nunoptions = radios.length;
		for(var i = 0; i < nunoptions; i++) {
		    if(radios[i].checked)
			{
			    resp = String.fromCharCode(65 + i);
		    	flag = true; 
			}
		}
		if(flag == false){
			alert("Kindly select Correct Answer");
			return false;
		}
		
		cid = course;
		cmin2=document.getElementById("Minutes").value;
	 	csec2=parseInt(document.getElementById("Seconds").value);
	 	var time = cmin2 * 60  + parseInt(csec2);
	 	if(cmin2==0 && csec2==0){
		 	alert("Kindly give quiz time");
		 	return false;
		 }
	 	startInstantQuiz(time, resp, nunoptions);			 	
	}

	function Display(min,sec) {     
		var disp;       
		if(min<=9) disp=" ";   
		else disp=" ";  
		disp+=min+":";  
		if(sec<=9) disp+="0"+sec;       
		else disp+=sec; 
		return(disp); 
	}

	function DownRepeat() {	
		document.getElementById("timetxt").value = Display(cmin2,csec2);
		if((cmin2==0)&&(csec2==0))
		{
			clearInterval(down);
			stopQuiz();			
			return false;	
		}
		csec2--;        
		if(csec2==-1) { 
			csec2=59; cmin2--;
		}
		//updateQuizTime();
	}
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
	var noof =0;
	function startInstantQuiz(time, resp, nunoptions){
		getXMLhttp();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				noof = document.getElementById("noofOptions").value;				
				document.getElementById("content_in").innerHTML = "<div style='margin: 80px 0px 0px 280px'><input type='text' id='timetxt' readonly size=4 style='font-size:40px;'/> </div>";
			 	down=setInterval(function(){DownRepeat();},1000);
			}
		};
		xmlhttp.open("POST","../../jsp/remoteInstructor/InstantQuizStart.jsp",true);
	    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	    xmlhttp.send("time="+time + "&cor_resp="+resp + "&nunoptions="+nunoptions);		
	}

	function stopQuiz()
	{
		getXMLhttp();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.location.href = "./RemoteCenter.jsp?quiztype=instant";	
			}
		};
		xmlhttp.open("GET",	"../../StopQuizServlet", true);
		xmlhttp.send();
		
	}	

	function displayOptions(optionNmbr){
		var options ="";
		for(var i=0;i<optionNmbr;i++){
			options +="<label style='margin-left: 25px;'><input type='radio' name=optns class='regular-radio big-radio' value='"+String.fromCharCode(65 + i)+"'/><label for='radio-2-1'></label><span style='font-size: 24px'> "+String.fromCharCode(65 + i)+" </span></label>";
		}
		document.getElementById("optionscorrect").innerHTML = options;
	}
 </script>
</head>
<body>
<%@ include file="../../jsp/newMenu/newMenuForRemoteInst.jsp"%>
	<div id="content_in" style="height: 320px">
	<div style="margin: 80px 0px 0px 240px">
		Select Number of Options : <select name="noofOptions" id="noofOptions" onclick="displayOptions(this.value)">
			<option value="2">&nbsp;2&nbsp;</option>
			<option value="3">&nbsp;3&nbsp;</option>
			<option value="4" selected>&nbsp;4&nbsp;</option>
			<option value="5">&nbsp;5&nbsp;</option>
			<option value="6">&nbsp;6&nbsp;</option>
		</select> <br/><br/>
		<div id= "optionscorrect"></div> <br/><br/>
		<div style="font-weight: bold;">
			Minutes <input type="text" style="width: 30px" id="Minutes"	value="00" /> 
			Seconds <input type="text" style="width: 30px" id="Seconds" value="30" />
		</div>		
		<br /><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="Launch Quiz" onclick="countdown('<%=session.getAttribute("courseID").toString()%>')">	 
	</div>	
	</div>
<%@ include file="../../jsp/includes/footer.jsp"%>
</body>
</html>
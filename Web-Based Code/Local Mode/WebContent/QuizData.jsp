<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quiz</title>
<script src="jquery/firefox-event-source.js"></script>
<script src="javascript/jquery-1.3.2.min.js"></script>
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
<script >
var flag = false;
var qflag =true;
String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}; 

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

function storeResponse(){
	getXMLhttp();
	var radios = document.getElementsByName("question");
	var resp = "Z";
	for(var i = 0; i < radios.length; i++) {
	    if(radios[i].checked)
		    {
	    	resp = String.fromCharCode(65 + i);		    
		    }
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			//alert("Your response is sent");
		}		
	};	
	xmlhttp.open("GET", "QuizResponse.jsp?resp="+resp, true);
	xmlhttp.send();
}

function loadQuestion(n){
	var options ="";
	for(var i=0;i<n;i++){
		options +="<label style='margin-left: 25px;'><input type='radio' name=question class='regular-radio big-radio' value='"+String.fromCharCode(65 + i)+"'/><label for='radio-2-1'></label><span style='font-size: 24px'> "+String.fromCharCode(65 + i)+" </span></label>";
	}
	document.getElementById("quiz").innerHTML = options;
}

if(typeof(EventSource)!=="undefined")
  {
	var source=new EventSource("jsp/instructorJspPages/InstantSSE.jsp?"+new Date().getTime());
	source.addEventListener("message", function(event) {	
		var content = event.data;
		if(content == "Waiting for quiz..."){
			window.location.href="Quiz.jsp";
		}else{
			document.getElementById("quizStatus").innerHTML = content.split(":")[1] + ":"  + content.split(":")[2];
			var min = content.split(":")[1].trim();
			var sec = content.split(":")[2].trim();
			if(qflag==true){
				var noofoptions = content.split(":")[0].trim();
				loadQuestion(noofoptions);
				qflag = false;
				flag = true;
			}
			if(sec<5 && min==0 && flag == true){
				storeResponse();
				flag=false;
			}
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
<div id="quizStatus" style="font-size: 38px"></div><br/><br/>
<div id="quiz" style="margin:120px 0px 0px 120px; ">
</div>
</body>
</html>
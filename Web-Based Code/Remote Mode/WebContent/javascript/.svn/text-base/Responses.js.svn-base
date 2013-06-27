var currentQuestionID;
var xmlhttp;
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
function showResponses(QuestionID) {
	
		QuestionID =QuestionID.split("/");
		alert("Question ID is "+QuestionID[1]);		
		currentQuestionID=QuestionID[0]-1;
		getXMLhttp();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("ResponseTable").innerHTML = xmlhttp.responseText;
				countResponse();
			}
		};
		xmlhttp.open("GET", "ResponseTable.jsp?QuestionID="+QuestionID[1], true);
		xmlhttp.send();
	}

function generateChart(coordinatorID){
	getXMLhttp();
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			var images="<center>";
			var responses = xmlhttp.responseText.split("@@");
			for(var i=1;i<responses.length;i++){
				images += "<img alt='Loading...' src='../../"+coordinatorID+"/Chart"+(i-1)+".jpeg?"+new Date().getTime()+"' onclick='showResponsesDialog(\""+responses[i]+"\")'> <br/><br/>";
			}
			document.getElementById("responseChart").innerHTML = images + "</center>";	
			invockEverySec();
		}
	};
	xmlhttp.open("GET", "../../generateChart", true);
	xmlhttp.send();
}

function showResponsesDialog(resp) {	
	var respTable ="<table border='1' style='width: 580px;'><tr><th>Participant ID</th><th>Response</th></tr>";
	var respArray = resp.split("@;");
	for(var i=0;i<(respArray.length)-1;i++){
		var currentResp = respArray[i].split("@,");
		respTable += "<tr><td align='center'>" + currentResp[0] + "</td><td align='center'>" + currentResp[1] + "</td></tr>";
	}
	respTable += "</table>";
	document.getElementById("ResponseDialog").innerHTML = respTable;
	document.getElementById("ResponseDialog").style.visibility = 'visible';
	document.getElementById("ResponseDialog").title ="Responses";
	$("#ResponseDialog").dialog({height: 400, width: 600, modal: true});	
}

function invockEverySec() { 
 	checkAvailable=setInterval(function(){isQuizAvailable();},1000);
}

function isQuizAvailable(){
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			//alert(result);
			if(result.trim()>0){
				window.location ="../../jsp/CoordinatorJspPages/QuizStatusListener.jsp";
			}
		}
	};
	xmlhttp.open("GET", "../../jsp/CoordinatorJspPages/isQuizStarted.jsp", true);
	xmlhttp.send();
}

String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
};
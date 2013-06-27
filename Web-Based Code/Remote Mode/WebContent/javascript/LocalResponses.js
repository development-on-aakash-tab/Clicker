var currentQuestionID;
var graphResponses;
var xmlhttp;
var numberofstudent;

//This method will get the XMLHTTP object for work with ajax
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
	currentQuestionID=QuestionID[0]-1;		
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.getElementById("ResponseTable").innerHTML = xmlhttp.responseText.split("@#")[0];
			numberofstudent=xmlhttp.responseText.split("@#")[1];
			countResponse(xmlhttp.responseText.split("@#")[1]);
		}
	};
	xmlhttp.open("GET", "../../jsp/instructorJspPages/ResponseTable.jsp?QuestionID="+QuestionID[1], true);
	xmlhttp.send();
}

function showResponsesDialog(QuestionID) {	
	getXMLhttp();
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			document.getElementById("ResponseDialog").innerHTML = xmlhttp.responseText.split("@#")[0];
			document.getElementById("ResponseDialog").style.visibility = 'visible';
			document.getElementById("ResponseDialog").title ="Responses";
			$("#ResponseDialog").dialog({height: 400, width: 600, modal: true});
		}
	};	
	xmlhttp.open("GET", "../../jsp/instructorJspPages/ResponseTable.jsp?QuestionID="+QuestionID, true);
	xmlhttp.send();
}

/**
 * This method is used to get the time out students and show it to dialog box
 */
function showTimeoutList(){
	getXMLhttp();
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			document.getElementById("response_dialog").innerHTML = xmlhttp.responseText;
			document.getElementById("response_dialog").style.visibility = 'visible';
			document.getElementById("response_dialog").title ="Time Out Students";
			$("#response_dialog").dialog({height: 400, width: 600, modal: true});
		}
	};
	xmlhttp.open("GET","../../jsp/instructorJspPages/responsehelper.jsp?responseType=timeout",true);
	xmlhttp.send();

}

/**
 * This method is used to get the no answer students and show it to dialog box
 */
function showNoAnswerList(){
	getXMLhttp();
	var questionID = document.getElementById("QuestSelect").value.split("/")[1];
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			document.getElementById("response_dialog").innerHTML = xmlhttp.responseText;
			document.getElementById("response_dialog").style.visibility = 'visible';
			document.getElementById("response_dialog").title ="No Response Students";
			$("#response_dialog").dialog({height: 400, width: 600, modal: true});
		}
	};
	xmlhttp.open("GET","../../jsp/instructorJspPages/responsehelper.jsp?responseType=noanswer&questionID="+questionID,true);
	xmlhttp.send();
}


var idleseconds=0;
var lastcount=0;
var checkidle;
function collectResponse(cid){
	document.getElementById("content_in").innerHTML = "<center><br/><br/><br/>Collecting response ...<br/><br/><br/><img alt='Loading...' src='../../images/loading_transparent1.gif'></center>";
	checkidle=setInterval(function(){checkIdle();},1000);   
}

var respCollSec=0;
var dbUpdateSec=0;
var chartSec=0;
function checkIdle(){	
	getXMLhttp();
	respCollSec++;	
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			if(idleseconds>=3){
            	checkidle=window.clearInterval(checkidle);
            	window.location.href="../../jsp/instructorJspPages/responseschart.jsp?resp_dbtime=Response Time : "+ respCollSec;
            	//storeResponse();
            }
        	participantCount = xmlhttp.responseText;
        	if(lastcount!=participantCount){      
        		lastcount = participantCount;	
        	}else{
				idleseconds++;				
            }
		}
	};
	xmlhttp.open("GET","../../jsp/instructorJspPages/checkidle.jsp",true);
	xmlhttp.send();    
}

function storeResponse(){
	dbSec=setInterval(function(){countStoreSec();},1000);
	xmlhttp.onreadystatechange=function()
    {      
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {       
        	dbSec=window.clearInterval(dbSec);
        	//window.location.href="../../jsp/instructorJspPages/responseschart.jsp?resp_dbtime=Response Time : "+ respCollSec;        	
        }
    };
    xmlhttp.open("GET","../../jsp/instructorJspPages/responsehelper.jsp?responseType=storeresponse",true);
    xmlhttp.send();
}

function countStoreSec(){
	dbUpdateSec++;
}

function countChartSec(){
	chartSec++;
}

/*function viewResponses(cid){
	getXMLhttp();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			window.location.href="../../jsp/instructorJspPages/responseschart.jsp";
		}		
	};	
	xmlhttp.open("GET", "../../jsp/instructorJspPages/InstructorQuizSSE.jsp?Status=stop&cid="+cid, true);
	xmlhttp.send();
	
}*/


function showBarChart() {
	var QuestSelectValue = document.getElementById("QuestSelect").value;
	if (QuestSelectValue=="Select Question Number / ID")
	{
		alert("Select Question Number / ID");
		return;
	}
	var CorrectAnswer="No";
	window.open("../../drawBarChart?BarResponseString="+countResponse(numberofstudent,CorrectAnswer)+"&CorrectAnswer="+CorrectAnswer);
	//var chartwidth=document.documentElement.clientWidth;	
	//document.location.href="../../drawBarChart?BarResponseString="+countResponse()+"&CorrectAnswer="+CorrectAnswer + "&chartwidth="+chartwidth;
}

function showCorrectBarChart(CorrectAnswer) {
	var QuestSelectValue= document.getElementById("QuestSelect").value;
	if (QuestSelectValue=="Select Question Number / ID")
	{
		alert("Select Question Number / ID");
		return;
	}
	var CorrectAnswersList = CorrectAnswer.split("/");	
	var currentCorrectAnswer = CorrectAnswersList[currentQuestionID];
	window.open("../../drawBarChart?BarResponseString="+countResponse(numberofstudent,currentCorrectAnswer)+"&CorrectAnswer="+currentCorrectAnswer);
}

function showPieChart() {
	var QuestSelectValue = document.getElementById("QuestSelect").value;
	if (QuestSelectValue=="Select Question Number / ID")
	{
		alert("Select Question Number / ID");
		return;
	}
	window.open("../../drawPieChart?PieResponseString="+countResponse(numberofstudent,"No"));
}

function countResponse(noofstudent, correctAnswer) {
	var response = "";
	var allResponses;
	var responseValues = "";
	var A = 0, B = 0, C = 0, D = 0, E = 0, F = 0, X = 0, Z = 0,others=0,P=0,Q=0;
	var respCount = 0;

	var table = document.getElementById("restbl");
	var rows = table.getElementsByTagName('tr');
	
	for ( var i = 1; i < rows.length; i++) {
		var cells = rows[i].getElementsByTagName('td');
		//var nextcells ="null";
		var isnextcellavailable = false;
		if((rows.length-1) > i){
			isnextcellavailable=true;
			nextcells = rows[i+1].getElementsByTagName('td');
		}
		responseValues += "@";

		for ( var j = 0; j < cells.length; j++) {
			responseValues += cells[j].innerHTML + ";";
			if (j == 0) {
				var studID = cells[0].innerHTML;
				if (isnextcellavailable) {
					var nextstudID = nextcells[0].innerHTML;
					if (studID != nextstudID) {
						respCount++;
					}
				}
			}
			if (j == 2) 
				//if (j == 1) 
			{
				response = cells[2].innerHTML;
				//response = cells[1].innerHTML;
				if (response == "A") {
					A++;
				} else if (response == "B") {
					B++;
				} else if (response == "C") {
					C++;
				} else if (response == "D") {
					D++;
				} else if (response == "E") {
					E++;
				} else if (response == "F") {
					F++;
				} else if (response == "X") {
					X++;
				} else if (response == "Z") {
					Z++;
				}else
				{ others++;
				if(response != correctAnswer)
					Q++;
				else
					P++;
				}

			}
		}
	}
	document.getElementById("responseValue_hidden").innerHTML = responseValues;
	
	if(others == 0)
	{
		allResponses = "A=" + A + ";B=" + B + ";C=" + C + ";D=" + D +";E=" + E;
		graphResponses = "A=" + A + ";B=" + B + ";C=" + C + ";D=" + D + ";E=" + E;
		document.getElementById("responseSummary").innerHTML = "<b>Response Summary : A="
			+ A
			+ ", B="
			+ B
			+ ", C="
			+ C
			+ ", D="
			+ D
			+ ", E="
			+ E
			+ ", F="
			+ F
			+ "<br/><br/>Responses = "
			+ (respCount+1)
			+ ",  <div id='timeout' style='display: inline;cursor:pointer;color:#FFFFFF' onclick='showTimeoutList()' > Time Out="
			+(noofstudent - (respCount+1))
			+ "</div>,  Answers = "
			+ ((respCount+1)-Z)
			+ ",  <div id='noanswer' style='display: inline;cursor:pointer;color:#FFFFFF' onclick='showNoAnswerList()' >No Answer="
			+ Z + "</div></b>";
	}
	else
	{ allResponses = "A=" + P +";B=" + Q +";No Ans=" + Z;
	document.getElementById("responseSummary").innerHTML = "<b>Response Summary : Correct="
		+ P
		+ ", Incorrect="
		+ Q
		+ "<br/><br/>Responses = "
		+ (P + Q + Z)
		+ ",  <div id='timeout' style='display: inline;cursor:pointer;color:#FFFFFF' onclick='showTimeoutList()' > Time Out="
		+(noofstudent - (P + Q + Z))
		+ "</div>,  Answers = "
		+ (P + Q)
		+ ",  <div id='noanswer' style='display: inline;cursor:pointer;color:#FFFFFF' onclick='showNoAnswerList()' >No Answer="
		+ Z + "</div></b>";
	}
	return allResponses;
	
}


function getChart(qid, qts, noofquestions, instructorID){
	//alert(qid + qts + path + instructorID);
	cSec=setInterval(function(){countChartSec();},1000);
	var questions = noofquestions.split("@");
	getXMLhttp();
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			cSec=window.clearInterval(cSec);
			//alert(chartSec);
			var images="<center>";
			for(var i=0;i<(questions.length-1);i++){
				images += "<img alt='Loading...' src='../../"+instructorID+"/Chart"+i+".jpeg?"+new Date().getTime()+"' onclick='showResponsesDialog("+questions[i]+")'> <br/><br/>";
			}
			document.getElementById("responseChart").innerHTML = images + "</center>";
			document.getElementById("responseTimes").innerHTML += " Chart Generation Time : " + chartSec;
			//storeResponse();			
		}
	};
	//xmlhttp.open("GET", "../../Chart?hide_chart_rptname=QuizResponseChart&hide_chart_qid=" + qid+ "&hide_chart_qts=" + qts, true);
	xmlhttp.open("GET", "../../ChartFromHashMap", true);
	xmlhttp.send();
}

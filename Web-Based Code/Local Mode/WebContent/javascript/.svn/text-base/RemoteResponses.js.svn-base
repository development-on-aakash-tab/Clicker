var CurrentSelectedRow;
var graphResponses;
var xmlhttp;
var previousSelectedRow=0;
var previousSelectedColor= "#ffffff";
function rowSelected(SelectedRow, rcCount)
{
	CurrentSelectedRow = SelectedRow;
	
	/*for (var i=1; i<=rcCount; i++)
	{
		id = "row" + i;        
        document.getElementById(id).style.background =  "#ffffff";
        ffffff
	}*/
	// document.getElementById(id).style.background =  "#226600";
	var pid = "row" + previousSelectedRow;	
	document.getElementById(pid).style.background =  previousSelectedColor;
	var cid = "row" + SelectedRow;
	previousSelectedColor = document.getElementById(cid).style.background;	
    document.getElementById(cid).style.background =  "#88ff99";
    previousSelectedRow = SelectedRow;
}

function getXMLhttp(){
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari        
        xmlhttp=new XMLHttpRequest();
    }
    else if (window.ActiveXObject) { // IE
        try {
            xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch (e) {
            try {
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
            catch (e) {}
        }
    }
}

String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
};

var myUpdate;
var idlecount=0;
var totalresponse =0;
var quiztype = "";
function responseStatus(QuizID, noofcenter, qt)
{
	quiztype = qt;
	document.getElementById("QuizID_hidden").innerHTML = QuizID;
    getXMLhttp();
    //updateCenterStatus(noofcenter);
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
        	var RCIDsFiles = xmlhttp.responseText;
        	var responseReceivedRC = RCIDsFiles.split("@")[0].trim();
        	var fileReceivedRC = RCIDsFiles.split("@")[1].trim();
        	if(isNaN(responseReceivedRC)){
        		var responseCenterIDs = responseReceivedRC.split(";");
            	var responseFiles = fileReceivedRC.split(";");
            	totalresponse = responseCenterIDs.length - 1;
                for(var i=0; i<responseCenterIDs.length - 1; i++){
                	var cid =responseCenterIDs[i].trim();
                	document.getElementById("check"+cid).checked = true;
                	document.getElementById("check"+cid).value = responseFiles[i];
                	document.getElementById("row"+cid).style.background =  "#FFA500";
                }
                document.getElementById("RemoteCenterStatusdiv").innerHTML = "<h4>Remote Centers Responded = " +totalresponse +" out of "+noofcenter+"</h4>";
            }
        	idlecount=0;
            myUpdate=setInterval(function(){updateCenterStatus(noofcenter);},2000);
        }
    };
    xmlhttp.open("GET","../../jsp/remoteInstructor/DAOResponse.jsp?ResponseType=getExistingRCStatus",true);
    xmlhttp.send();
    
}

function updateCenterStatus(noofcenter){
	idlecount++;
	var currentResponse = "";
	var currentFiles = "";
	xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
        	responses = xmlhttp.responseText.trim();
        	currentResponse = responses.split("@#")[0];
        	currentFiles = responses.split("@#")[1];
        	if(idlecount>=10){
        		window.clearInterval(myUpdate);
        		return;
        	}
        	if((currentResponse!=null || currentResponse.trim()!="")){       	
        	var responseCenterIDs = currentResponse.split(";");
        	var responseFiles = currentFiles.split(";");
            if(isNaN(totalresponse)){
            	totalresponse = 0;
            }            
        	if(responseCenterIDs.length>1){
        		idlecount=0;
        	}
        	totalresponse += responseCenterIDs.length -1;
            for(var i=0; i<responseCenterIDs.length - 1; i++){
            	var centerID = responseCenterIDs[i].trim();
            	var RCcheckbox = document.getElementById("check"+centerID);
            	RCcheckbox.checked = true;
            	document.getElementById("row"+centerID).style.background =  "#FFA500";
            	RCcheckbox.value = responseFiles[i].trim();            	
            }
            document.getElementById("RemoteCenterStatusdiv").innerHTML = "<h4>Remote Centers Responded = " +totalresponse +" out of "+noofcenter+"</h4>";
            doSaveInDB(currentResponse, currentFiles);
        	}
        }
    };
    //xmlhttp.open("GET","../../jsp/remoteInstructor/DAOResponse.jsp?ResponseType=ResponseStatus&existingStatus="+responseReceivedRC,true);
    xmlhttp.open("POST","../../jsp/remoteInstructor/DAOResponse.jsp",false);
    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xmlhttp.send("ResponseType=ResponseStatus");
    //xmlhttp.send();
}

/*function getIndexRCID(rcid){
	rcid = rcid.trim();
	var centerids = document.getElementById("hide_centerid").innerHTML;
	var ids = centerids.split(";");
	for(var i=0;i<ids.length;i++){
		if(ids[i]==rcid){
			return i;
		}
	}
	return -1;
}*/

//String.prototype.trim=function(){return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');};

function doSaveInDB(currentResponse, currentFiles){
	if(currentResponse.trim() != "" || currentResponse.trim() != null){
	getXMLhttp();
	xmlhttp.onreadystatechange=function()
    {      
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {        	
        	responses = xmlhttp.responseText;
        }
    };
    xmlhttp.open("POST","../../jsp/remoteInstructor/DAOResponse.jsp",true);
    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xmlhttp.send("ResponseType=SaveResponse&filenames="+currentFiles + "&quiztype="+quiztype); 
	}
}

function sortResonse(){
	var RCTable = document.getElementById('RCListTable');
    var rowLength = RCTable.rows.length;
    for (i = 1, j=(rowLength-1); i < j; ){
       	var topRCells = RCTable.rows.item(i).cells;
       	var topRCID = topRCells.item(0).innerHTML;
       	var bottomRCells = RCTable.rows.item(j).cells;
       	var bottomRCID = bottomRCells.item(0).innerHTML;
       	var topCheckBox = document.getElementById("check" + topRCID);
       	var bottomCheckBox = document.getElementById("check" + bottomRCID);
       	while(topCheckBox.checked==true){
       		i++;
       		topRCells = RCTable.rows.item(i).cells;
           	topRCID = topRCells.item(0).innerHTML;
           	topCheckBox = document.getElementById("check" + topRCID);
       	}
       	while(bottomCheckBox.checked==false){
       		j--;
       		bottomRCells = RCTable.rows.item(j).cells;
           	bottomRCID = bottomRCells.item(0).innerHTML;
           	bottomCheckBox = document.getElementById("check" + bottomRCID);
       	}
       	if(i<j){
       		topRCells = RCTable.rows.item(i).cells;
           	topRCID = topRCells.item(0).innerHTML;
            bottomRCells = RCTable.rows.item(j).cells;
           	bottomRCID = bottomRCells.item(0).innerHTML;
       		var topRow = document.getElementById("row" + topRCID);
       		var bottomRow = document.getElementById("row" + bottomRCID);	
       		swap(topRow, bottomRow);
       		i++;j--;
       	}
    }
}

function swap(element1,element2){
    var pa1= element1.parentNode;
    var pa2= element2.parentNode;
    var placeholder= element2.cloneNode(false);
    pa1.insertBefore(placeholder,element1);
    pa2.insertBefore(element1,element2);
    pa1.replaceChild(element2,placeholder);
}

function redirectResponsePage(responseType, noofcenters)
{
	//var checkbox = document.getElementsByName("status");
	var quizID = document.getElementById("QuizID_hidden").innerHTML;
	
	if(responseType == "Response"){		
		//var index = parseInt(CurrentSelectedRow) -1;
		if (isNaN(CurrentSelectedRow))
        {
            alert ("Please Select Remote Center");
            return;
        }
		
		//var filename = checkbox[index].value;
		var filename = document.getElementById("check"+CurrentSelectedRow).value;
		
		if(filename=="null")
        {
            alert("Select response received remote center");
            return;
        }else{
            window.location="../../jsp/remoteInstructor/RemoteResponse.jsp?responseType=Response&filename="+filename + "&quizid=" +quizID;
        }
	}
	else if (responseType == "AllResponses")
	{
		var filenames = "";
		var allcenters=document.getElementById("hide_centerid").innerHTML.trim().split(";");
		for(var i=0; i<(allcenters.length-1); i++){
			var checkboxitem= document.getElementById("check"+allcenters[i]);
			if(checkboxitem.checked == true){
                if(checkboxitem.value != "null"){
                    filenames += checkboxitem.value + ";";                   
                }
            }
		}
		if(filenames=="")
        {
            alert("No Responses received from any Remote Center, Please Wait !!!");
            return;
        }
		window.location="../../jsp/remoteInstructor/RemoteResponse.jsp?responseType=AllResponses&filenames="+filenames+ "&quizid=" +quizID; 
	}
}


function redirectResponseChart(responseType)
{
	//alert(quiztype);
	// var checkbox = document.getElementsByName("status");
	var quizID = document.getElementById("QuizID_hidden").innerHTML;
	if(responseType == "response"){
		//var index = parseInt(CurrentSelectedRow) -1;
		if (isNaN(CurrentSelectedRow))
        {
            alert ("Please Select Remote Center");
            return;
        }
		
		//var filename = checkbox[index].value;
		var filename = document.getElementById("check"+CurrentSelectedRow).value;
		if(filename=="null")
        {
            alert("Select response received remote center");
            return;
        }else{
        	if(quiztype=="normal"){
        		window.location="../../jsp/remoteInstructor/responsechart.jsp?responseType=response&centerid="+CurrentSelectedRow + "&quizid=" +quizID;
        	}
        	else if(quiztype=="instant"){
        		window.location="../../jsp/remoteInstructor/responseinstantchart.jsp?responseType=response&centerid="+CurrentSelectedRow + "&quizid=" +quizID;
        	}
        }
	}
	else if (responseType == "allresponse")
	{
		if(totalresponse==0)
        {
            alert("No Responses received from any Remote Center, Please Wait !!!");
            return;
        }
		if(quiztype=="normal"){
			window.location="../../jsp/remoteInstructor/responsechart.jsp?responseType=allresponse&quizid=" +quizID;
    	}
    	else if(quiztype=="instant"){
    		window.location="../../jsp/remoteInstructor/responseinstantchart.jsp?responseType=allresponse&quizid=" +quizID;
    	}
		 
	}
}


function showChart(responseType, centerid, qid, noofquestions, questionIDs, user){
	
    if(responseType=="response"){
    	getXMLhttp();    
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
            	var images="<center>";
            	var questionIDs_array = questionIDs.split("@");
            	for(var i=0;i<noofquestions;i++){
            		images += "<img alt=\"Loading\" src=\"../../"+ user +"/Question" + (i+1)  + ".jpeg?"+new Date().getTime()+"\" onclick=\"showRemoteresponseDialog('"+centerid+"', '"+questionIDs_array[i]+"')\"><br/><br/>";
            	}
            	document.getElementById("remoteresp_Chart").innerHTML = images + "</center>";
            }
        };
        xmlhttp.open("GET","../../RemoteChart?rptname=response&centerid="+centerid + "&qid="+qid,true);
        xmlhttp.send();
    }else {
    	getXMLhttp();    
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
            	var images="<center>";
            	var questionIDs_array = questionIDs.split("@");
            	for(var i=0;i<noofquestions;i++){
            		images += "<img alt=\"Loading\" src=\"../../"+ user +"/Question" + (i+1)  + ".jpeg?"+new Date().getTime()+"\" onclick=\"showAllRemoteresponseDialog('"+questionIDs_array[i]+"')\"><br/><br/>";
            	}
            	document.getElementById("remoteresp_Chart").innerHTML = images + "</center>";
            }
        };
        xmlhttp.open("GET","../../RemoteChart?rptname=AllRemoteCenterResponse&qid="+qid,true);
        xmlhttp.send();
    }
}

function showInstantChart(responseType, centerid, qid, user){
	if(responseType=="response"){
    	getXMLhttp();    
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
            	var images="<center>";
            	images += "<img alt=\"Loading\" src=\"../../"+ user +"/InstantQuiz.jpeg?"+new Date().getTime()+"\" onclick=\"showRemoteresponseInstantDialog('"+centerid+"', '"+qid+"')\"><br/><br/>";
            	document.getElementById("remoteresp_Chart").innerHTML = images + "</center>";
            }
        };
        xmlhttp.open("GET","../../RemoteInstantChart?rptname=response&centerid="+centerid + "&qid="+qid,true);            
        xmlhttp.send();
    }else {
    	getXMLhttp();    
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
            	var images="<center>";
            	images += "<img alt=\"Loading\" src=\"../../"+ user +"/InstantQuiz.jpeg?"+new Date().getTime()+"\" onclick=\"showAllRemoteresponseInstantDialog('"+qid+"')\"><br/><br/>";
            	document.getElementById("remoteresp_Chart").innerHTML = images + "</center>";
            }
        };
        xmlhttp.open("GET","../../RemoteInstantChart?rptname=AllRemoteCenterResponse&qid="+qid,true);            
        xmlhttp.send();
    }
}

function showRemoteresponseDialog(centerID, questionid){
	getXMLhttp();    
    xmlhttp.onreadystatechange=function()
    {        
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {     
        	//var centerID = filename.substring((filename.indexOf("_") + 1), filename.indexOf("_",filename.indexOf("_")+1));
            document.getElementById("rrdialog").innerHTML = xmlhttp.responseText;
            document.getElementById("rrdialog").style.visibility = 'visible';
            document.getElementById("rrdialog").title ="Remotecenter "+centerID+" Responses";
            $("#rrdialog").dialog({height: 400, width: 600, modal: true});
        }
    };
    xmlhttp.open("GET","../../jsp/remoteInstructor/DAOResponse.jsp?ResponseType=QuestionResponse&centerid="+centerID + "&questid="+questionid, true);
    xmlhttp.send();	
	
}

function showAllRemoteresponseDialog(questionid){
	getXMLhttp();    
    xmlhttp.onreadystatechange=function()
    {        
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {     
        	//var centerID = filename.substring((filename.indexOf("_") + 1), filename.indexOf("_",filename.indexOf("_")+1));
            document.getElementById("rrdialog").innerHTML = xmlhttp.responseText;
            document.getElementById("rrdialog").style.visibility = 'visible';
            document.getElementById("rrdialog").title ="All Remotecenters Responses";
            $("#rrdialog").dialog({height: 400, width: 600, modal: true});
        }
    };
    xmlhttp.open("GET","../../jsp/remoteInstructor/DAOResponse.jsp?ResponseType=QuestionAllResponse&questid="+questionid, true);
    xmlhttp.send();	
	
}

function showRemoteresponseInstantDialog(centerID, qid){
	getXMLhttp();    
    xmlhttp.onreadystatechange=function()
    {        
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {     
        	//var centerID = filename.substring((filename.indexOf("_") + 1), filename.indexOf("_",filename.indexOf("_")+1));
            document.getElementById("rrdialog").innerHTML = xmlhttp.responseText;
            document.getElementById("rrdialog").style.visibility = 'visible';
            document.getElementById("rrdialog").title ="Remotecenter "+centerID+" Responses";
            $("#rrdialog").dialog({height: 400, width: 600, modal: true});
        }
    };
    xmlhttp.open("GET","../../jsp/remoteInstructor/DAOResponse.jsp?ResponseType=InstantQuizResponse&centerid="+centerID + "&qid="+qid, true);
    xmlhttp.send();	
	
}

function showAllRemoteresponseInstantDialog(qid){
	getXMLhttp();    
    xmlhttp.onreadystatechange=function()
    {        
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {     
        	//var centerID = filename.substring((filename.indexOf("_") + 1), filename.indexOf("_",filename.indexOf("_")+1));
            document.getElementById("rrdialog").innerHTML = xmlhttp.responseText;
            document.getElementById("rrdialog").style.visibility = 'visible';
            document.getElementById("rrdialog").title ="All Remotecenters Responses";
            $("#rrdialog").dialog({height: 400, width: 600, modal: true});
        }
    };
    xmlhttp.open("GET","../../jsp/remoteInstructor/DAOResponse.jsp?ResponseType=InstantQuizAllResponse&qid="+qid, true);
    xmlhttp.send();	
	
}

function responseType()
{
	var query = window.location.href.split("?")[1];
    var queryString = query.split("&");
    document.getElementById("piequeryString").value = query;
    document.getElementById("barqueryString").value = query;
    var responseType = queryString[0].split("=")[1];
    var filename = queryString[1].split("=")[1];
    document.getElementById("respquizid_hidden").innerHTML = queryString[2].split("=")[1];
    if(responseType=="Response"){
        document.getElementById("saveResponse_div").style.visibility = 'hidden';
        viewResponses(filename);        
    }
    else if (responseType=="AllResponses"){
        document.getElementById("saveResponse_div").style.visibility = "visible";        
        viewAllResponses(filename);
    }
}

function viewResponses(fileName)
{
	if(fileName == "null"){
        return;
    }
	
	getXMLhttp();    
    xmlhttp.onreadystatechange=function()
    {        
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {            
            document.getElementById("Responsediv").innerHTML = xmlhttp.responseText;
            countResponse();
        }
    };
    xmlhttp.open("GET","../../jsp/remoteInstructor/DAOResponse.jsp?ResponseType=Response&fileName="+fileName, true);
    xmlhttp.send();	
}

function viewAllResponses(fileNames)
{
	getXMLhttp();
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {            
            document.getElementById("Responsediv").innerHTML = xmlhttp.responseText;
            countResponse();
        }
    };
    xmlhttp.open("GET","../../jsp/remoteInstructor/DAOResponse.jsp?ResponseType=AllResponses&fileNames="+fileNames, true);
    xmlhttp.send();
}

function countResponse(){    
    // parse and get count of a, b, c, d, e ,f and x    
    var response = "";
    var A = 0, B = 0, C=0, D=0, E=0, F=0, X=0, Z=0;
    var table =document.getElementById("restbl");    
    var rows = table.getElementsByTagName('tr');        
    var responseValues ="";
    for (var i = 1; i < rows.length; i++) {
        var cells = rows[i].getElementsByTagName('td');
        responseValues += "@";
        for(var j=0; j < cells.length; j++){
            responseValues +=  cells[j].innerHTML + ";";
            if(j==2){
                response = cells[2].innerHTML;
                if(response=="A"){
                    A++;
                }else if (response=="B"){
                    B++;
                }else if (response=="C"){
                    C++;
                }else if (response=="D"){
                    D++;
                }else if (response=="E"){
                    E++;
                }else if (response=="F"){
                    F++;
                }else if (response=="X"){
                    X++;
                }
                else if (response=="Z"){
                    Z++;
                } 
            }
        }        
    }
    document.getElementById("responseValue_hidden").innerHTML = responseValues;
    var allResponses = "A="+A+";B="+B+";C="+C+";D="+D+";E="+E+";F="+F+";X="+X+";Z="+Z;
    graphResponses = "A=" + A + ";B=" + B + ";C=" + C + ";D=" + D + ";E="
	+ E + ";F=" + F;
    document.getElementById("responseSummary").innerHTML = "<b>Response Summary : A="+A+", B="+B+", C="+C+", D="+D+", E="+E+", F="+F+"<br/><br/>Responses = "+(A+B+C+D+E+F+Z)+",  Time Out="+X+ ",  Answers = "+(A+B+C+D+E+F)+",  No Answer="+Z+"</b>";    
    return allResponses;
}

function showPieChart() {
	window.open("../../drawPieChart?PieResponseString="+graphResponses);
}

function showBarChart() {
	var CorrectAnswer="No";
	window.open("../../drawBarChart?BarResponseString="+graphResponses+"&CorrectAnswer="+CorrectAnswer);
}

function showCorrectBarChart(CorrectAnswer) {
	var CorrectAnswersList = CorrectAnswer.split("/");	
	var currentCorrectAnswer = CorrectAnswersList[0];
	window.open("../../drawBarChart?BarResponseString="+graphResponses+"&CorrectAnswer="+currentCorrectAnswer);
}

function saveResponse()
{
	var responseValues =document.getElementById("responseValue_hidden").innerHTML;
	getXMLhttp();    
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            var status = xmlhttp.responseText;
            alert(status);
            document.getElementById("saveresponse").disabled = "true";
        }
    };
    xmlhttp.open("GET","../../jsp/remoteInstructor/DAOResponse.jsp?ResponseType=Save&responseValues="+responseValues,true);
    xmlhttp.send();
}

function back(){
	window.history.back();
}
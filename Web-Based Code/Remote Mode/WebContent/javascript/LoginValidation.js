

function checkLoginStatus() {
	window.history.forward();
	document.getElementById("error").innerHTML = "";
	var query = window.location.href.split("?")[1];
	var errorStatus = query.split("=")[1];
	if (errorStatus == "error") {
		document.getElementById("error").innerHTML="Username/Password is incorrect<br/> Please Retry with Correct data";		
	}
}

function checkIp() {
	var ip = document.getElementById('text3');
	var filter = /^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$/;
	if (!filter.test(ip.value)) {
		alert('Please enter valid IP address');
		ip.focus;
		return false;
	}
	return true;
}


function loadParticipantList(){
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var participantList = xmlhttp.responseText;
			document.getElementById("participant_list").innerHTML = participantList;
			invockEverySec();
		}
	};
	xmlhttp.open("GET", "../../jsp/CoordinatorJspPages/CoordinatorHelper.jsp?helperText=participantList", true);
	xmlhttp.send();
}

function deleteAllAction(){
	var checkbox = document.getElementsByName("plist");
	if(checkbox[0].checked == true){
		for(var i=1; i<checkbox.length; i++){
			checkbox[i].checked = true;	        
			checkbox[i].disabled=true;
		}    
    }else{
    	for(var i=1; i<checkbox.length; i++){
			checkbox[i].checked = false;	     
			checkbox[i].disabled = false;
		} 
    }
}

function deleteParticipantList(){
	var checkbox = document.getElementsByName("plist");
	
	if(checkbox[0].checked == true){
		var r=confirm("Do you want delete entire participants ?");
		if (r==true)
		{
			deleteAction('all');
		}	
	}else{	
		var r=confirm("Do you want delete selected participants ?");
		if (r==true)
		{
		var deleteList ="";
		for(var i=1; i<checkbox.length; i++){
			if(checkbox[i].checked == true){
	            if(checkbox[i].value != "null"){
	            	deleteList += checkbox[i].value + ",";                   
	            }
	        }
		}
		deleteAction(deleteList);
		}
	}	
}

function deleteAction(action){
	if(action==""){
		alert("Select Participant List");
		return false;
	}
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			alert(result);
			window.location ="../../jsp/CoordinatorJspPages/CoordinatorAdmin.jsp";
		}
	};
	xmlhttp.open("GET", "../../jsp/CoordinatorJspPages/CoordinatorHelper.jsp?helperText=deleteParticipantList&action="+action, true);
	xmlhttp.send();
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
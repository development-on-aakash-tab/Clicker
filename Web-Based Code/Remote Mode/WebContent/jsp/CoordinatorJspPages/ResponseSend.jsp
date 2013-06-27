<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String CoordinatorID= (String) session.getAttribute("CoordinatorID");
if (CoordinatorID == null) {
	request.setAttribute("Error", "Session has ended.  Please login.");
	RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
	rd.forward(request, response);
	return;
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" type="text/javascript">
var xmlhttp;
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
var idleseconds=0;
var lastcount=0;
var checkidle;
function collectResponse(){
	getXMLhttp();
	checkidle=setInterval(function(){checkIdle();},1000);    
}

function checkIdle(){
	xmlhttp.onreadystatechange=function()
    {      
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {       
            if(idleseconds>=5){
            	checkidle=window.clearInterval(checkidle);
            	appendXML();
            }
        	participantCount = xmlhttp.responseText;
        	if(lastcount!=participantCount){      
        		lastcount = participantCount;	
        		idleseconds = 0;
        	}else{
				idleseconds++;				
            }
        }
    };
    xmlhttp.open("GET","../../jsp/CoordinatorJspPages/checkidle.jsp",true);
    xmlhttp.send();
}

function appendXML(){
	xmlhttp.onreadystatechange=function()
    {      
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {       
        	document.location.href="./HTTP_Transfer.jsp";
        }
    };
    xmlhttp.open("GET","../../jsp/CoordinatorJspPages/appendResponse.jsp",true);
    xmlhttp.send();
}
</script>
<title>Remote Center Response XML Transfer </title>
</head>
<body onload="collectResponse();">
<center>
<%@ include file="../../jsp/includes/menuheaderForCoordinator.jsp" %>
<div id="content_in">
<div style="min-height: 425px">
<div style="height:60px"> </div>
<center>
<form id = "timerForm" name="timerForm">
<H2>Collecting responses please wait...</H2><br/><br/>
<img alt="Collecting Response" src="../../images/loading_transparent.gif"/>
</form>
</center>
</div>
</div>
<%@ include file="../../jsp/includes/footer.jsp" %>
</center>

</body>
</html>
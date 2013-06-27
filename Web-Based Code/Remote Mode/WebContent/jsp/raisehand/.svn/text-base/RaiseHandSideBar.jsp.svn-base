
<%@page import="java.util.*,com.clicker.raisehand.*"%>
<%
  boolean raisedflag=false;
  if(session.getAttribute("courseID")!=null){
  	System.out.println("RHSB:In if");
  	ArrayList<String> list=(ArrayList<String>)getServletContext().getAttribute("raisedCourses");
  	if(list==null){
  		raisedflag=false;
		System.out.println("RHSB:list null");
  	}
	else{
		if(list.contains(session.getAttribute("courseID")))
			raisedflag=true;
	    else
		    System.out.println("RHSB:Not found in list");
	    }	
  }
  if(raisedflag){
%>
<html>
<head>
<script type="text/javascript" src="../../javascript/jquery.js"></script>
<link href="../../jquery-ui-1.8.21.css" rel="stylesheet" type="text/css" />
<script src="../../javascript/jquery.js"></script>
<script src="../../javascript/jquery-ui.min.js"></script>
<script type="text/javascript">
var open = false;

try{
	$(document).ready(function(){
		restoreDoubts();
		repeat();
	$(".bar").toggle(function(){
	 	$("#sideBar").animate({width:"50%"},"slow",function(){		
		});
	 	$("#arrow").animate({right:"52.3%"},"slow",function(){
	 		$(this).attr("src","../../images/arrow2.png");
	 	});
	 	open=!open;
 	 	//alert(open);
	 	$('#sideBar').css("visibility","visible");
		$("#showHide").animate({right:"52%"},"slow");
	},function(){
		$("#sideBar").animate({width:"0%"},"slow",function(){
			$('#sideBar').css("visibility","hidden");
			open=!open;
 			//alert(open);
			$('#arrow').attr("src","../../images/arrow.png");
		});
		$("#showHide").animate({right:"2%"},"slow");
		$("#arrow").animate({right:"2.5%"},"slow");
	});
	$(".bar").click(function(){
		$("#fakeshowHide").stop();
		$('#fakeshowHide').css("visibility","hidden");
	});
	});
}
catch(err){
	alert(err.message);
}
function repeat(){
	$.get("../../raiseHandChannel",function(result){
		if(result!=""){
			if(!open){
 				//alert("Not open");
				$('#fakeshowHide').css("visibility","visible");
			}
			$("#sideBar").append(result);
			$("#fakeshowHide").effect("pulsate",{times:4},2000,function(){
			});
		}
	});
	setTimeout("repeat()",100);
}
function removeDoubt(id){
	$("#"+id).remove();
	try{
		$.post("../../restoreDoubts",{doubt:id});
	}
	catch(err){
		alert(err.message);
	}
	
}
function saveDoubt(id){
	var stuID=$('#studentID'+id).val();
	var cID=$('#courseID'+id).val();
	var comm=$('#comments'+id).val();
	try{
		$.post("../../saveDoubt",{studentID:stuID,courseID:cID,comments:comm});
	}
	catch(err){
		alert(err.message);
	}
	removeDoubt(id);
	alert("Doubt Saved");
}
function restoreDoubts(){
	try{
		//alert("trying to restore");
		$.get("../../restoreDoubts",function(result){
			if(result!=""){
				$("#sideBar").append(result);
			}
		});
	}
	catch(err){
		alert(err.message);																																																																														 																																											
	}
}
</script>
<link rel="stylesheet" href="../../SideBarStyle.css" type="text/css"
	media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<img id="arrow" class="bar" src="../../images/arrow.png" />
	<div id="showHide" class="bar"></div>
	<div id="fakeshowHide"></div>
	<div id="sideBar">
		<div style="text-align: center; width: 100%;">
			<h2
				style="color: white; text-align: center; background-color: #454593; opacity: 0.9; color: white; padding-bottom: 3%;">
				<br>Raised Hands
			</h2>
		</div>
	</div>
</body>
</html>
<%}%>
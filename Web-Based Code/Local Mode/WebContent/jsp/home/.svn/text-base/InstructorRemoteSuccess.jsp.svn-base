<%-- Author : Manjur and  Dipti.G --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility

	String InstructorID = (String) session.getAttribute("InstructorID");
	session.setAttribute("insid",InstructorID);
	System.out.println("Instructor ID is : " + InstructorID);
	

	if (InstructorID == null) {
		request.setAttribute("Error","Your session has expired.");
		RequestDispatcher rd = request
				.getRequestDispatcher("../../jsp/errorpages/Error.jsp");
		rd.forward(request, response);
	}
	System.out.println("Course ID :"+(String)session.getAttribute("courseID"));
	System.out.println((String)session.getAttribute("Usertype"));
%>
<html>
<head>
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
<link href="../../jquery-ui-1.8.21.custom.css" rel="stylesheet"	type="text/css" />
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Instructor</title>
<script type="text/javascript" src="../../javascript/jquery.js"></script>
<script type="text/javascript" src="../../javascript/jquery-ui.min.js"></script>
<script type="text/javascript">


<%if(session.getAttribute("courseID")==null){%>


$(document).ready(function() {
	  $("#dialog").dialog({
		 draggable: false, 
		 position: "top", 
		 modal:true,
	     closeOnEscape: false,
	     dialogClass: 'myPosition',
	     beforeclose: function(){ return false;}
	  });
      $("#dialog").css("visibility","visible"); 	
      $("#dialog").dialog(function(){
    	  $("#dialog").css("visibility","hidden"); 	
      }); 	
  });
<%}%>


function validateCourseID(){
	
	try{
		
		if(document.forms["CourseIDform"].elements["courseID"].value=="Select Course"){
			alert("Select a valid course");
			
			return false;
		}
		else{
			
			return true;
		}
	}
	catch(err){
		alert(err.message);
	}
}


</script>
</head>
<body>
	<center>
		<%@ include
			file="../../jsp/newMenu/newMenuForRemoteInst.jsp"%>
		<div id="content_in" style="height: 425px">
			<div style="height: 425px">
				<div style="height: 20px"></div>
				<h1>Welcome Instructor !</h1>
				<br />
				<h2>You have selected Remote center Mode</h2>
				<p>This help you to conduct quiz across remote center over
					differnt places at a time</p>
				<br />

			</div>
			<div id="dialog" style="visibility: hidden; background: white;">
				<center>
					<br>
					<form id="CourseIDform" action="../../setCourse" method="GET"
						onsubmit="return validateCourseID()">
						Select Course&nbsp&nbsp&nbsp&nbsp <select id="StudentCourseSelect" name="courseID">
							<option>Select Course</option>
		 		  			<%
							String[] courseList = (String[])session.getAttribute("courseList");
							for (int i=0; i<courseList.length; i++){%>
							<option><%=courseList[i]%></option>
							<%}%>
						</select> <br>
						<br> <input type="submit" value=" Set Course " />
					</form>
				</center>
			</div>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
</body>
</html>
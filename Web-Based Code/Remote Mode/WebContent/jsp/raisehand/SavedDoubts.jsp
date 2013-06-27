
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.clicker.raisehand.*,java.util.*"%>
<%@page import="com.clicker.databaseconn.DatabaseQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="java.sql.*"%>
<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility*/
	
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxycache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility

	String InstructorID = (String) session.getAttribute("InstructorID");

	System.out.println("Instructor ID is : " + InstructorID);

	if (InstructorID == null) {
		request.setAttribute("Error",
				"Your session has expired.");
		RequestDispatcher rd = request
				.getRequestDispatcher("../../jsp/instructorJspPages/InstructorError.jsp");
		rd.forward(request, response);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="./jsp/raisehand/SavedDoubts.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../SavedDoubts.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<script type="text/javascript" src="../../javascript/jquery.js"></script>
<title>Saved Doubts</title>
<script type="text/javascript">
	$(document).ready(function(){
		fillContent();
		$("#delete").click(function(){
			$(":checkbox:checked").each(
				function(){
					$.post("../../deleteDoubt?timeStamp="+$(this).val());
					$(this).parent().parent().remove();
				}
			);
		});
		function fillContent(){
			//alert("../../retrieveDoubts?text="+$("#textBox").val()+"&date="+$("#date").val());
			$.get("../../retrieveDoubts?text="+$("#textBox").val()+"&date="+$("#date").val(),function(result){
				$("#doubts").html(result);
			});
		}
		$("#textBox").keyup(function(){
			fillContent();
		});
		$("#date").change(function(){
			fillContent();
		});
		
	});
</script>
</head>
<body>
	<center>
		<%@ include file="../newMenu/newMenuwithCSS.jsp"%> 
		<div style="min-height: 500px; margin-bottom: 20px;">
			<%ArrayList<RaiseHandRowData> list = (ArrayList<RaiseHandRowData>)request.getAttribute("records"); %>
			<table style="width: 600px; margin-top: 30px;">
				<tr>
					<td><label style="font-size: medium;"><b>Search By
								Text</b></label></td>
					<td><input type="text" id="textBox"
						style="width: 400px; margin-left: 25px;"></td>
				</tr>
				<tr>
					<td><label style="font-size: medium;"><b>Search By
								Date</b></label></td>
					<td><select id="date" style="width: 200px; margin-left: 25px;">
							<option value="1">All Time</option>
							<%String comparator="";%>
							<%for(RaiseHandRowData rdata:list){
				if(!rdata.getRaiseHandTimeStamp().split(" ")[0].equals(comparator)){%>
							<option><%=rdata.getRaiseHandTimeStamp().split(" ")[0]%></option>
							<%}comparator=rdata.getRaiseHandTimeStamp().split(" ")[0];%>
							<%} %>
					</select></td>
				</tr>
			</table>
			<input class="buttons" type="button"
				value="   Reply to Selected Doubts  " id="reply" onclick="">&nbsp&nbsp&nbsp&nbsp&nbsp
				<input
				class="buttons" type="button" value="   Delete Selected Doubts  "
				id="delete">
			<div id="doubts" style="height: 400px; width: 800px; overflow: auto;">

			</div>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
</body>
</html>
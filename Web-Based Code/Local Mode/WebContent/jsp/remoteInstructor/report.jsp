<!-- 
This jsp file is used for UI of report generation process
Author : Rajavel

Referances  :

1. DatePicker :
	http://jqueryui.com/demos/datepicker/
	http://docs.jquery.com/UI/API/1.8/Datepicker

2. Autocomplete :
	http://docs.jquery.com/UI/Autocomplete
	http://jqueryui.com/demos/autocomplete/#maxheight

-->
<%@page import="java.sql.*"%>
<%@page import="com.clicker.databaseconn.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String dates = "";
	int sdd = 0, smm = 0, syyyy = 0;
	int edd = 0, emm = 0, eyyyy = 0;
	String Mode = (String) session.getAttribute("Mode");
	if (Mode == null ) {
		request.setAttribute("Error", "Your session has expired.");
		System.out.println("calling usertype...... session ended");
		RequestDispatcher rd = request
				.getRequestDispatcher("../../jsp/errorpages/Error.jsp");
		rd.forward(request, response);
		return;
	}else if(Mode.equals("Local")){
		request.setAttribute("Error", "You are not in remote mode... login as remote mode...");
		System.out.println("calling usertype...... session ended");
		RequestDispatcher rd = request
				.getRequestDispatcher("../../jsp/errorpages/Error.jsp");
		rd.forward(request, response);
		return;
	}
	try {
		DatabaseConnection dbcon = new DatabaseConnection();
		Connection con = dbcon.createDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = null;
		rs = st.executeQuery("SELECT distinct date(QuizTimeStamp) as TimeStamp FROM remotequizrecord order by QuizTimeStamp");
		while (rs.next()) {
			String date = rs.getString("TimeStamp");
			dates += date + ",";
		}
		if (rs.first()) {
			String date = rs.getString("TimeStamp");
			syyyy = Integer.parseInt(date.split("-")[0]);
			smm = Integer.parseInt(date.split("-")[1]);
			sdd = Integer.parseInt(date.split("-")[2]);
		}
		if (rs.last()) {
			String date = rs.getString("TimeStamp");
			eyyyy = Integer.parseInt(date.split("-")[0]);
			emm = Integer.parseInt(date.split("-")[1]);
			edd = Integer.parseInt(date.split("-")[2]);
		}
		rs.close();
		st.close();
		con.close();
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	System.out.println(dates);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Clicker Remote Report</title>
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
<script src="../../javascript/remote_report.js" type="text/javascript"></script>
<link href="../../jquery/jquery-ui.css" rel="stylesheet" type="text/css"
	media="screen" />
	<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<script src="../../jquery/jquery-1.5.min.js"></script>
<script src="../../jquery/jquery-1.8.19-ui.min.js"></script>
<style type="text/css">
#highlight,.highlight {
	background-color: #000000;
}
</style>
<script>
 	var start= new Date(<%=syyyy%> , <%=smm-1%>, <%=sdd%>);
  	var end = new Date(<%=eyyyy%> , <%=emm-1%>, <%=edd%>);
  	var dateString = "<%=dates%>";
  	var dates= dateString.split(",");
  	$(function() {
		$( "#datepicker" ).datepicker({
			minDate: start, maxDate: end,
			changeMonth: true,
			changeYear: true, 
			beforeShowDay: highlightDays
		});	

		function highlightDays(date) {
			for (var i = 0; i < dates.length; i++) {
				if (dates[i] == date.formatYYYYMMDD()) {
					return [true, 'highlight'];
		        }
		    }
		    return [true, ''];
		} 
		Date.prototype.formatYYYYMMDD=function(){
		    var dd = this.getDate(), mm = this.getMonth()+1, yyyy = this.getFullYear();
		    if(dd<10){
		      dd = '0' + dd;
		    }
		    if(mm<10){
		      mm = '0'+ mm;
		    }
		  return String(yyyy + "-" + mm + "-" + dd);
		  };
	});			
	</script>
</head>
<body>
	<%@ include
			file="../../jsp/newMenu/newMenuForRemoteInst.jsp"%>
	<div id="content_in" style="min-height: 325px; margin-top: 100px;">
		<form action="../../RemoteReport" target="blank" method="post" onsubmit="return isValid()">
			<table style="margin-left: 100px;" width="350px" cellspacing="10" cellpadding="10">
			<tr>
				<td colspan="2" ><br />
					<input type="radio" name="report_op" checked="checked" value="RemoteCenterList" onchange="showDate()"> <label>Remote Center List</label>
				</td>
			</tr>
			<tr>
				<td colspan="2" ><br />
					<div id="hide_quizdates" style="display: none;"><%=dates%></div> 
					<input	type="radio" name="report_op" value="RemoteQuizResponse" onchange="showDate()"> <label>Quiz Response</label>
			
					<div id="div_qdate" style="display: none; margin-left: 40px;">
						<br/>
						Date <input type="text" id="datepicker"	onchange="listQuizNames('quiz')" style="width: 80px;" /> 
						<select	name="Quizname" id="quiz" onchange="fillTimeStamp('qts', 'div_qts')">
							<option value="Quiz Name">Quiz Name</option>
						</select>						
					</div>
					<div id="div_qts" style="display: none; margin-left: 40px;">
						<br/>Time Stamp <select name="QTimeStamp" id="qts" onchange="setQTS()">
						<option value="Time Stamp">Time Stamp</option>
						</select>
					</div>
					<div id="div_rc" style="display: none; margin-left: 40px;">
						<br/>
						Remote Centers <select name="remotecenter" id="remotecenter">
							<option value="Select Center">Remote Center</option>							
						</select>
					</div>
					
				</td>
			</tr>
			<tr>				
				<td align="center"><br />
					<input type="submit" name="report_sub" value="Generate Report" />
					<input type="text" name="hide_qts" id="hide_qts" style="display: none;" />					
				</td>
				</tr>
			</table>
		</form>
	</div>
	<%@ include file="../../jsp/includes/footer.jsp"%>
</body>
</html>